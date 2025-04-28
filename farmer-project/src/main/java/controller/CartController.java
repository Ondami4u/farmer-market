package controller;

import org.springframework.web.bind.annotation.*;
import service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{clientId}/add/{productId}")
    public boolean addProductToCart(@PathVariable Long clientId, @PathVariable Long productId) {
        return cartService.addProductToCart(clientId, productId);
    }

    @DeleteMapping("/{clientId}/remove/{productId}")
    public boolean removeProductFromCart(@PathVariable Long clientId, @PathVariable Long productId) {
        return cartService.removeProductFromCart(clientId, productId);
    }

    @PostMapping("/{clientId}/checkout")
    public boolean checkoutCart(@PathVariable Long clientId) {
        return cartService.checkoutCart(clientId);
    }
}
