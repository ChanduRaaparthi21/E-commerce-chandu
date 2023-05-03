package com.chandu.ecommorce.service;

import com.chandu.ecommorce.configuration.JwtRequestFilter;
import com.chandu.ecommorce.dao.CartDao;
import com.chandu.ecommorce.dao.UserDao;
import com.chandu.ecommorce.entity.Cart;
import com.chandu.ecommorce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chandu.ecommorce.dao.ProductDao;
import com.chandu.ecommorce.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {


    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;


    public Product addNewProduct(Product product) {
        return productDao.save(product);
    }

    public List<Product> getAllProducts(int pageNumber, String searchKey) {
        Pageable pageable = PageRequest.of(pageNumber, 8);

        if (searchKey.equals("")) {
            return (List<Product>) productDao.findAll(pageable);
        } else {
            return (List<Product>) productDao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey, searchKey, pageable);
        }


    }

    public Product getProductDetailsById(Integer productId) {

        return productDao.findById(productId).get();
    }


    public void deleteProductDetails(Integer productId) {
        productDao.deleteById(productId);
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId) {

        if (isSingleProductCheckout && productId !=0) {
            //we r going to single product\
            List<Product> list = new ArrayList<>();
            Product product = productDao.findById(productId).get();
            list.add(product);
            return list;
        } else {
            //we r going to checkout entire products
            String username =  JwtRequestFilter.CURRENT_USER;
            User user = userDao.findById(username).get();
            List<Cart> cartList = cartDao.findByUser(user);
           return cartList.stream().map(c-> c.getProduct()).collect(Collectors.toList());

        }

    }
}
