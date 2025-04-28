package controller;

import dto.FarmerRegisterRequest;
import model.Farmer;
import service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farmers")
public class FarmerController {

    private final FarmerService farmerService;

    @Autowired
    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @PostMapping("/register")
    public Farmer registerFarmer(@RequestBody FarmerRegisterRequest request) {
        return farmerService.registerFarmer(request);
    }
}
