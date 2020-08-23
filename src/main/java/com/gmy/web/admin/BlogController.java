package com.gmy.web.admin;

import com.gmy.pojo.Blog;
import com.gmy.pojo.User;
import com.gmy.service.BlogService;
import com.gmy.service.TagService;
import com.gmy.service.TypeService;
import com.gmy.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author Kevin Guo
 * @package com.gmy.web.admin
 * @date 2020/8/20 14:02
 * @description
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String CREATE = "admin/blog-create";
    private static final String LIST = "admin/blogs-admin";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs-admin";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs-admin")
    public String blogs_admin(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                              BlogQuery blog, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return LIST;
    }

    @PostMapping("/blogs-admin/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                              BlogQuery blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs-admin :: blogList";
    }

    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

    @GetMapping("/blogs-admin/create")
    public String create(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return CREATE;
    }

    @GetMapping("/blogs-admin/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlogById(id);
        blog.init();
        model.addAttribute("blog", blog);
        return CREATE;
    }

    @PostMapping("/blogs-admin")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));

        Blog b = blogService.saveBlog(blog);
        if (b == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs-admin/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }

}
