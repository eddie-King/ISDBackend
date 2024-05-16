package com.blanke.hanu.rest;
import com.blanke.hanu.config.Common.ResponseModel;
import com.blanke.hanu.config.Common.request.LoginRequest;
import com.blanke.hanu.entity.SiteUser;
import com.blanke.hanu.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseModel login(@RequestBody LoginRequest loginModel, HttpServletRequest request, HttpServletResponse response){
        return authService.loginWebService(loginModel, request, response);
    }

    @PostMapping("/register")
    public ResponseModel signup(@RequestBody SiteUser loginModel, HttpServletRequest request, HttpServletResponse response){
        return authService.signupWebService(loginModel, request, response);
    }

}