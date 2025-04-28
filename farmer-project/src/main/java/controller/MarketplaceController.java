package controller;

import model.Client;
import model.Farmer;
import model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.MarketplaceService;

@RestController
@RequestMapping("/api")
public class MarketplaceController {

    private final MarketplaceService marketplaceService;

    public MarketplaceController(MarketplaceService marketplaceService) {
        this.marketplaceService = marketplaceService;
    }

   
    @PostMapping("/farmers")
    public ResponseEntity<Farmer> createFarmer(@RequestParam String name) {
        Farmer farmer = marketplaceService.createFarmer(name);
        return ResponseEntity.ok(farmer);
    }

 
    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestParam String name) {
        Client client = marketplaceService.createClient(name);
        return ResponseEntity.ok(client);
    }


    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(
            @RequestParam Long farmerId,
            @RequestParam String name,
            @RequestParam String city,
            @RequestParam String quality,
            @RequestParam int quantity,
            @RequestParam String description
    ) {
        Product product = marketplaceService.createProduct(farmerId, name, city, quality, quantity, description);
        if (product == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(product);
    }

   
    @PostMapping("/products/{productId}/buy")
    public ResponseEntity<String> buyProduct(@PathVariable Long productId, @RequestParam int amount) {
        boolean success = marketplaceService.buyProduct(productId, amount);
        if (success) {
            return ResponseEntity.ok("Purchase successful");
        } else {
            return ResponseEntity.badRequest().body("Purchase failed");
        }
    }
}
