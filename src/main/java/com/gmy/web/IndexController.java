package com.gmy.web;


import com.gmy.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description
 * @Package com.gmy.web
 * @Author Kevin Guo
 * @Date 2020/8/7
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {

        System.out.println("---------index----------");
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {

        return "blog";
    }
}
