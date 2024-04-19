package com.blanke.hanu.rest;


import com.blanke.hanu.entity.SiteUser;
import com.blanke.hanu.repository.UserInfoRepository;
import com.blanke.hanu.rest.dto.request.LoginRequest;
import com.blanke.hanu.rest.dto.request.SignupRequest;
import com.blanke.hanu.rest.dto.response.JwtResponse;
import com.blanke.hanu.security.JwtAuthenticationToken;
import com.blanke.hanu.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationProvider authenticationProvider;
    private final JwtService jwtService;
    private final UserInfoRepository userInfoRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationProvider
                .authenticate(new JwtAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        //TODO: create jwt and response
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // create JWT
        String jwtToken = jwtService.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(jwtToken);

        return ResponseEntity.ok(jwtResponse);
    }

    //TODO: aplly register
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignupRequest signupRequest) {
        // add check for username exits in DB
        if (userInfoRepository.existsByEmail(signupRequest.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        // create user object
        SiteUser user = new SiteUser();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(signupRequest.getPassword());
        userInfoRepository.save(user);

        return ResponseEntity.ok("create account successfully! okkkkkk mannnnn");
    }

    @GetMapping("/access-denied")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return "redirect:/auth/login";
    }

}