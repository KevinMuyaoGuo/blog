package com.gmy.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kevin Guo
 * @package com.gmy.web.admin
 * @date 2020/8/20 14:02
 * @description
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @GetMapping("/blogs-admin")
    public String blogs_admin() {
        return "admin/blogs-admin";
    }
}
