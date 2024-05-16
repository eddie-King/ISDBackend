package com.blanke.hanu.service.impl;

import com.blanke.hanu.config.Common.ResponseModel;
import com.blanke.hanu.entity.Cart;
import com.blanke.hanu.entity.CartItem;
import com.blanke.hanu.repository.CartItemRepository;
import com.blanke.hanu.repository.CartRepository;
import com.blanke.hanu.rest.dto.ProductDTOResponse;
import com.blanke.hanu.rest.dto.ProductInCartDTO;
import com.blanke.hanu.security.JwtService;
import com.blanke.hanu.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

@Service
@RequiredArgsConstructor
@ControllerAdvice
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    JwtService service;

    @Override
    public void addToCart(HttpServletRequest request, ProductInCartDTO product) {

        try {
            String token = request.getHeader("Authorization");
            System.out.println("Token: " + token);
            Long userId = service.getUserIdFromToken(request);
            System.out.println("UserId: " + userId);
            Cart cart = cartRepository.save(new Cart(null, userId));
                cartItemRepository.save(
                        new CartItem(null, cart.getId(), product.getProductId(),
                                product.getQuantity(), product.getPrice()));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
