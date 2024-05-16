package com.blanke.hanu.service.impl;

import com.blanke.hanu.config.Common.ResponseModel;
import com.blanke.hanu.config.Common.request.LoginRequest;
import com.blanke.hanu.entity.SiteUser;
import com.blanke.hanu.repository.UserInfoRepository;
import com.blanke.hanu.security.JwtService;
import com.blanke.hanu.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private JwtService jwt;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public ResponseModel loginWebService(LoginRequest loginModel, HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getPassword());
        try {
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            return addAuthenAndGetToken(authentication, request);
        } catch (Exception e) {
            e.printStackTrace();
            return loginFailHandler(loginModel);
        }
    }

    @Override
    public ResponseModel signupWebService(SiteUser entity, HttpServletRequest request, HttpServletResponse response) {
        SiteUser userInfo= userInfoRepository.findByEmail(entity.getEmail()).orElse(null);
        if (userInfo != null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(1);
            responseModel.setStatusCode(HttpStatus.BAD_REQUEST);
            responseModel.setContent("Username is already taken");
            return responseModel;
        }
        entity.setPassword(encoder.encode(entity.getPassword()));
        SiteUser newUser=userInfoRepository.save(entity);
        String jwtToken = jwt.generateToken(newUser.getEmail(), String.valueOf(newUser.getId()));
        String bearerToken = "Bearer " + jwtToken;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", bearerToken);

        Map<String, String> json = new HashMap<>();
        json.put("LOGIN","1");
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatusCode(HttpStatus.OK);
        responseModel.setCode(0);
        json.put("Bearer", bearerToken);
        responseModel.setContent(json);
        return responseModel;

    }

    private ResponseModel addAuthenAndGetToken(Authentication authentication,  HttpServletRequest request) {
        ResponseModel responseModel = new ResponseModel();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        SiteUser user = userInfoRepository.findByEmail(authentication.getName()).orElse(null);


/*         check user_role
        List<Roles> rolesList = rolesService.getRolesByUserId(user.getId());
        if (rolesList.isEmpty()) {
            log.debug(LoginCode.ERR_USER_NOT_HAVE_ROLES.toString() + ":" + authentication.getName());
            responseModel.setStatusCode(LoginCode.ERR_USER_NOT_HAVE_ROLES.getStatusCode());
            responseModel.setContent(LoginCode.ERR_USER_NOT_HAVE_ROLES.getDescription());
            return responseModel;
        }*/

        String jwtToken = jwt.generateToken(user.getEmail(), String.valueOf(user.getId()));
        String bearerToken = "Bearer " + jwtToken;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", bearerToken);

        Map<String, String> json = new HashMap<>();
        json.put("LOGIN","1");
        responseModel.setStatusCode(HttpStatus.OK);
        responseModel.setCode(0);
        json.put("Bearer", bearerToken);
        responseModel.setContent(json);
        return responseModel;
    }

    private ResponseModel loginFailHandler(LoginRequest loginModel) {
        String username = loginModel.getEmail();
        ResponseModel responseModel = new ResponseModel();
        Optional<SiteUser> userInfo = userInfoRepository.findByEmail(username);
        if (!userInfo.isPresent()) {
            System.out.println("User not found");
            responseModel.setStatusCode(HttpStatus.NOT_FOUND);
            responseModel.setContent("User not found");
            responseModel.setCode(1);
            return responseModel;
        }
        responseModel.setStatusCode(HttpStatus.FOUND);
        responseModel.setCode(1);
        responseModel.setContent("Password is incorrect");
        return responseModel;
    }


}
