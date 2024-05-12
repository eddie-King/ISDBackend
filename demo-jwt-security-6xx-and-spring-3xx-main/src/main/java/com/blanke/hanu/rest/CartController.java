package com.blanke.hanu.rest;

import com.blanke.hanu.rest.dto.ProductInCartDTO;
import com.blanke.hanu.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/auth/cart")
public class CartController {
    final CartService cartService;
    @PostMapping("/save")
    public ResponseEntity<?> addProductToCart( HttpServletRequest request, @RequestBody ProductInCartDTO productInCartDTO){
       try {
           cartService.addToCart(request,productInCartDTO);
           return ResponseEntity.ok().build();
       }catch (Exception e){
           return ResponseEntity.badRequest().build();
       }
    }

}
