package service;

import model.Cart;
import model.Client;
import model.Product;
import org.springframework.stereotype.Service;
import repository.CartRepository;
import repository.ClientRepository;
import repository.ProductRepository;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ClientRepository clientRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    public boolean addProductToCart(Long clientId, Long productId) {
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (clientOpt.isEmpty() || productOpt.isEmpty()) return false;

        Cart cart = clientOpt.get().getCart();
        cart.addProduct(productOpt.get());
        cartRepository.save(cart);
        return true;
    }

    public boolean removeProductFromCart(Long clientId, Long productId) {
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (clientOpt.isEmpty() || productOpt.isEmpty()) return false;

        Cart cart = clientOpt.get().getCart();
        cart.removeProduct(productOpt.get());
        cartRepository.save(cart);
        return true;
    }

    public boolean checkoutCart(Long clientId) {
        Optional<Client> clientOpt = clientRepository.findById(clientId);

        if (clientOpt.isEmpty()) return false;

        Cart cart = clientOpt.get().getCart();
        for (Product product : cart.getProducts()) {
            if (product.getQuantity() > 0) {
                product.setQuantity(product.getQuantity() - 1); // уменьшаем количество на 1 (можно сделать на выбранное количество)
                productRepository.save(product);
            }
        }
        cart.clearProducts();
        cartRepository.save(cart);
        return true;
    }
}
