package com.gmy.web;


import com.gmy.NotFoundException;
import com.gmy.service.BlogService;
import com.gmy.service.TagService;
import com.gmy.service.TypeService;
import com.gmy.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Package com.gmy.web
 * @Author Kevin Guo
 * @Date 2020/8/7
 */
@Controller
public class IndexController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    private static final int INDEX_PAGE_SIZE = 6;
    private static final int SEARCH_PAGE_SIZE = 6;

    @GetMapping("/")
    public String index(@PageableDefault(size = INDEX_PAGE_SIZE, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        return "index";
    }

    /*@GetMapping("/search")
    public String search(@PageableDefault(size = 3, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        return "search";
    }*/

    @RequestMapping("/search")
    public String search(@PageableDefault(size = SEARCH_PAGE_SIZE, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%" + query.toLowerCase() + "%", pageable));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "blog";
    }

    @GetMapping("/footer/newBlog")
    public String newBlogs(Model model) {
        model.addAttribute("newBlogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newBlogList";
    }
}
