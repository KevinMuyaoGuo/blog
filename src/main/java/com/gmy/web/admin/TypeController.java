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

    @PostMapping("/types-admin")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type t1 = typeService.getTypeByName(type.getName());
        if (t1 != null) {
            result.rejectValue("name","nameError","不能添加已有的分类");
        }
        if (result.hasErrors()) {
            return "admin/type-create";
        }
        Type t2 = typeService.saveType(type);
        if (t2 == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/types-admin";
    }
}
