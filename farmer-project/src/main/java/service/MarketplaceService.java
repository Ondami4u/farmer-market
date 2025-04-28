package service;

import model.Cart;
import model.Client;
import model.Farmer;
import model.Product;
import org.springframework.stereotype.Service;
import repository.ClientRepository;
import repository.FarmerRepository;
import repository.ProductRepository;

import java.util.Optional;

@Service
public class MarketplaceService {

    private final ClientRepository clientRepo;
    private final FarmerRepository farmerRepo;
    private final ProductRepository productRepo;

    public MarketplaceService(ClientRepository clientRepo,
                              FarmerRepository farmerRepo,
                              ProductRepository productRepo) {
        this.clientRepo = clientRepo;
        this.farmerRepo = farmerRepo;
        this.productRepo = productRepo;
    }

    public Farmer createFarmer(String name) {
        Farmer farmer = new Farmer(name);
        return farmerRepo.save(farmer);
    }

    public Client createClient(String name, String email, String password) {
        Client client = new Client(name, email, password);
        Cart cart = new Cart(client);
        client.setCart(cart);
        return clientRepo.save(client);
    }


    public Product createProduct(Long farmerId, String name, String city, String quality, int quantity, String description) {
        Optional<Farmer> farmerOpt = farmerRepo.findById(farmerId);
        if (farmerOpt.isEmpty()) return null;

        Product product = new Product(name, city, quality, quantity, description);
        product.setFarmer(farmerOpt.get());
        return productRepo.save(product);
    }

    public boolean buyProduct(Long productId, int amount) {
        Optional<Product> productOpt = productRepo.findById(productId);
        if (productOpt.isEmpty()) return false;

        Product product = productOpt.get();
        if (amount <= 0 || product.getQuantity() < amount) return false;

        product.setQuantity(product.getQuantity() - amount);
        productRepo.save(product);
        return true;
    }
}
