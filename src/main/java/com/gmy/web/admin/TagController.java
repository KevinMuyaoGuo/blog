package com.gmy.web.admin;

import com.gmy.pojo.Tag;
import com.gmy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.*;

/**
 * @author Kevin Guo
 * @package com.gmy.web.admin
 * @date 2020/8/21 10:42
 * @description
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags-admin")
    public String tags_admin(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                                      Pageable pageable, Model model) {
        model.addAttribute("page", tagService.listTag(pageable));
        tagService.listTag(pageable);
        return "admin/tags-admin";
    }

    @GetMapping("/tags-admin/create")
    public String create(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tag-create";
    }

    @GetMapping("/tags-admin/{id}/edit")
    public String editTag(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTagById(id));
        return "admin/tag-create";
    }

    @PostMapping("/tags-admin")
    public String createPost(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        Tag t1 = tagService.getTagByName(tag.getName());
        if (t1 != null) {
            result.rejectValue("name", "nameError", "不能添加已有的标签");
        }
        if (result.hasErrors()) {
            return "admin/tag-create";
        }
        Tag t2 = tagService.saveTag(tag);
        if (t2 == null) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags-admin";
    }

    @PostMapping("/tags-admin/{id}")
    public String editPost(@Valid Tag tag, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        Tag t1 = tagService.getTagByName(tag.getName());
        if (t1 != null) {
            result.rejectValue("name", "nameError", "不能添加已有的标签");
        }
        if (result.hasErrors()) {
            return "admin/tag-create";
        }
        Tag t2 = tagService.updateTag(id, tag);
        if (t2 == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags-admin";
    }

    @GetMapping("/tags-admin/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags-admin";
    }

}
