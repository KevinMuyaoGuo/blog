package com.gmy.web.admin;

import com.gmy.pojo.Type;
import com.gmy.service.TypeService;
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
 * @date 2020/8/20 15:10
 * @description
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types-admin")
    public String types_admin(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                                      Pageable pageable, Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        typeService.listType(pageable);
        return "admin/types-admin";
    }

    @GetMapping("/types-admin/create")
    public String create(Model model) {
        model.addAttribute("type", new Type());
        return "admin/type-create";
    }

    @GetMapping("/types-admin/{id}/edit")
    public String editType(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getTypeById(id));
        return "admin/type-create";
    }

    @PostMapping("/types-admin")
    public String createPost(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type t1 = typeService.getTypeByName(type.getName());
        if (t1 != null) {
            result.rejectValue("name", "nameError", "不能添加已有的分类");
        }
        if (result.hasErrors()) {
            return "admin/type-create";
        }
        Type t2 = typeService.saveType(type);
        if (t2 == null) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types-admin";
    }

    @PostMapping("/types-admin/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        Type t1 = typeService.getTypeByName(type.getName());
        if (t1 != null) {
            result.rejectValue("name", "nameError", "不能添加已有的分类");
        }
        if (result.hasErrors()) {
            return "admin/type-create";
        }
        Type t2 = typeService.updateType(id, type);
        if (t2 == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types-admin";
    }

    @GetMapping("/types-admin/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types-admin";
    }

}
