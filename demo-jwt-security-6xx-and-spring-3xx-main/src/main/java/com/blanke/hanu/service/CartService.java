package com.blanke.hanu.service;

import com.blanke.hanu.entity.Cart;
import com.blanke.hanu.rest.dto.ProductDTOResponse;
import com.blanke.hanu.rest.dto.ProductInCartDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface CartService {
   void addToCart (HttpServletRequest request, ProductInCartDTO product);

}
