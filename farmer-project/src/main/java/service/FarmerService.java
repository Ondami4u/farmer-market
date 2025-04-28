package service;

import model.Farmer;
import org.springframework.stereotype.Service;
import repository.FarmerRepository;
import dto.FarmerRegisterRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FarmerService {

    private static final Pattern LETTER_PATTERN = Pattern.compile("[A-Za-z]");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("\\d");
    private static final Pattern SPACE_PATTERN = Pattern.compile("\\s");
    private static final Pattern SPECIAL_SYMBOL_PATTERN = Pattern.compile("[@#$%^&+=]");

    private final FarmerRepository farmerRepo;

    public FarmerService(FarmerRepository farmerRepo) {
        this.farmerRepo = farmerRepo;
    }

    public Farmer registerFarmer(FarmerRegisterRequest request) {
        validatePassword(request.getPassword());

        Farmer farmer = new Farmer(request.getFarmName());
        return farmerRepo.save(farmer);
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Пароль должен быть не менее 6 символов");
        }

        Matcher letterMatcher = LETTER_PATTERN.matcher(password);
        Matcher digitMatcher = DIGIT_PATTERN.matcher(password);
        Matcher spaceMatcher = SPACE_PATTERN.matcher(password);
        Matcher symbolMatcher = SPECIAL_SYMBOL_PATTERN.matcher(password);
        
        if (!letterMatcher.find()) {
            throw new IllegalArgumentException("Пароль должен содержать хотя бы одну букву");
        }

        if (!digitMatcher.find()) {
            throw new IllegalArgumentException("Пароль должен содержать хотя бы одну цифру");
        }

        if (spaceMatcher.find()) {
            throw new IllegalArgumentException("Пароль не должен содержать пробелы");
        }
        if (!symbolMatcher.find()) {
            throw new IllegalArgumentException("Пароль  должен содержать специальные символы");
        }
    }
}
