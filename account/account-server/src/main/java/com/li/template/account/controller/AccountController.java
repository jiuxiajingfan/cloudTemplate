package com.li.template.account.controller;

import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Nine
 * @Date 2023/9/21 16:26
 * @Version 1.0
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @GetMapping("/test")
    public String test() {
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context.getAuthentication());
        System.out.println(context);
        return "pong!";
    }

    @GetMapping("/test2")
    @PermitAll
    public String test2() {
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context);
        return "pong!";
    }
}
