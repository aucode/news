package com.aucode.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project news
 * @Description: ErrorController
 * @Author
 * @Explain
 * @Date 2021-06-28 13:37
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    /**
     * 404页面
     */
    @GetMapping(value = "/404")
    public String error_404() {
        return "error/error_404";
    }

    /**
     * 500页面
     */
    @GetMapping(value = "/500")
    public String error_500() {
        return "error/error_500";
    }

}