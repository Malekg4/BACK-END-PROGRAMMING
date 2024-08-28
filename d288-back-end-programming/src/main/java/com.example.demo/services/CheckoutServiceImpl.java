package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Customer;
import com.example.demo.entity.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;
    private CartRepository cartRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository){
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;

    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Cart cart = purchase.getCart();

        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> cart.add(item));

        cart.setCustomer(purchase.getCustomer());

        Customer customer = purchase.getCustomer();
        customer.add(cart);

        cart.setStatus(StatusType.ordered);
        cartRepository.save(cart);

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        return UUID.randomUUID().toString();
    }
}
