package com.blanke.hanu.repository.criteria;

import com.blanke.hanu.entity.Product;
import com.blanke.hanu.mapper.ProductMapper;
import com.blanke.hanu.rest.dto.PagingDTOResponse;
import com.blanke.hanu.rest.dto.ProductDTOFilter;
import com.blanke.hanu.rest.dto.ProductDTOResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductRepositoryCriteria {
    EntityManager entity;

    public PagingDTOResponse search(ProductDTOFilter productDTOFilter) {
        StringBuilder query = new StringBuilder("select p from Product p where 1=1 ");
        Map<String, Object> param = new HashMap<>();// save param
        //dynamic query
        if (productDTOFilter.getCategoryId() != null) {
            query.append(" and p.category.id =:categoryId ");
            param.put("categoryId", productDTOFilter.getCategoryId());
        }
        query.append(" and p.price between :priceFrom and :priceTo ");
        param.put("priceFrom", productDTOFilter.getPriceFrom());
        param.put("priceTo", productDTOFilter.getPriceTo());

        query.append(" and p.name like :name");
        param.put("name", "%" + productDTOFilter.getName() + "%");

        Query countQuery = entity.createQuery(query.toString().replace("select p", "select count(p.id)"));

        if (productDTOFilter.getSortByPrice() != null){
            query.append(" order by p.price ").append(productDTOFilter.getSortByPrice());
        }

        TypedQuery<Product> productTypedQuery = entity.createQuery(query.toString(), Product.class);
        // set param
        param.forEach((k, v) -> {
            productTypedQuery.setParameter(k, v);
            countQuery.setParameter(k, v);
        });

        Integer pageIndex = productDTOFilter.getPageIndex();
        Integer pageSize = productDTOFilter.getPageSize();

        long totalProduct = (long) countQuery.getSingleResult();
        long totalPage = totalProduct / pageSize;
        if (totalProduct % pageSize != 0) {
            totalPage++;
        }


        // Pagging
        productTypedQuery.setFirstResult((pageIndex - 1) * pageSize);
        productTypedQuery.setMaxResults(pageSize);

        List<Product> productList = productTypedQuery.getResultList();
        List<ProductDTOResponse> productDTOResponseList = productList.stream()
                .map(ProductMapper::toProductDTOResponse)
                .collect(Collectors.toList());

        return PagingDTOResponse.builder()
                .totalPage(totalPage)

                .totalElement(totalProduct)
                .data(productDTOResponseList)
                .build();
    }
}
