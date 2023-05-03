package com.chandu.ecommorce.service;

import com.chandu.ecommorce.configuration.JwtRequestFilter;
import com.chandu.ecommorce.dao.CartDao;
import com.chandu.ecommorce.dao.ProductDao;
import com.chandu.ecommorce.dao.UserDao;
import com.chandu.ecommorce.entity.Cart;
import com.chandu.ecommorce.entity.Product;
import com.chandu.ecommorce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    public Cart addToCart(Integer productId) {
        Product product = productDao.findById(productId).get();

        String username = JwtRequestFilter.CURRENT_USER;
        User user = null;
//-----------------------------------------------------------------------------------------------------------------------------------------
        //   this is for not allowing duplicate product on cart --> like add product_1 to cart and again we can't add same product again in same user
        List<Cart> list = cartDao.findByUser(user);
        List<Cart> filterdList = list.stream().filter(c -> c.getProduct().getProductId() == productId).collect(Collectors.toList());

        if (filterdList.size() > 0) {
            return null;
        }
//------------------------------------------------------------------------------------------------------------------------
        if (username != null) {
            user = userDao.findById(username).get();
        }
        if (product != null && user != null) {
            Cart cart = new Cart(product, user);
            return cartDao.save(cart);
        }
        return null;
    }

    public List<Cart> getCartDetails() {
        String username = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(username).get();

        return cartDao.findByUser(user);
    }

    public void deleteCartItem(Integer cartId){
        cartDao.deleteById(cartId);
    }

}
