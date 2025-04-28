package service;

import dto.ClientRegisterRequest;
import model.Cart;
import model.Client;
import org.springframework.stereotype.Service;
import repository.ClientRepository;

import java.util.regex.Pattern;

@Service
public class ClientService {

    private final ClientRepository clientRepo;

    public ClientService(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Client registerClient(ClientRegisterRequest request) {
        validatePassword(request.getPassword());
        validateEmail(request.getEmail());

        Client client = new Client(
            request.getName(),
            request.getEmail(),
            request.getPassword()
        );
        Cart cart = new Cart(client);
        client.setCart(cart);

        return clientRepo.save(client);
    }

    private void validatePassword(String password) {
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        
    }

    private void validateEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!Pattern.matches(emailRegex, email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
}
