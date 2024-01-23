package com.li.template.account.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Tag(name = "你好")
@RequestMapping("/account")
public class AccountController {
    @GetMapping("/test")
    @PreAuthorize("@ss.hasPermission('9999','2')")
    public String test() {
        SecurityContext context = SecurityContextHolder.getContext();
        return "pong!";
    }

    @GetMapping("/test2")
    @ApiOperation("nihao")
    public String test2() {
        SecurityContext context = SecurityContextHolder.getContext();
        return "pong!";
    }
}
