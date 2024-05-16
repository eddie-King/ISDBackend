package com.blanke.hanu.service;

import com.blanke.hanu.config.Common.ResponseModel;
import com.blanke.hanu.config.Common.request.LoginRequest;
import com.blanke.hanu.entity.SiteUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    ResponseModel loginWebService(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response);
    ResponseModel signupWebService(SiteUser loginModel, HttpServletRequest request, HttpServletResponse response);
}
