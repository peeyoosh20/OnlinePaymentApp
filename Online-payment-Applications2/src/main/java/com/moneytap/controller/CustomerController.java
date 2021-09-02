package com.moneytap.controller;

import com.moneytap.exceptions.AlreadyCustomerAvailableException;
import com.moneytap.exceptions.WalletIdNotFoundException;
import com.moneytap.model.Customer;
import com.moneytap.model.CustomerToken;
import com.moneytap.model.JwtRequest;
import com.moneytap.model.JwtResponse;
import com.moneytap.repository.CustomerTokenRepository;
import com.moneytap.services.CustomerService;
import com.moneytap.services.UserService;
import com.moneytap.utility.JWTUtility;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private CustomerTokenRepository customerTokenRepository;

    @PostMapping("/registration/{walletId}")
   public void customerRegistration(@RequestBody Customer customer, @PathVariable int walletId) throws WalletIdNotFoundException, AlreadyCustomerAvailableException {
        customerService.customerRegistration(customer,walletId);

    }

    @CrossOrigin()

    @GetMapping("/status")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public String home() {
        return "Hello..User--- Welcome in to Application!!";
    }

    @GetMapping("/request")
    public String request(){
        return "Request accepted";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        customerTokenRepository.deleteAll();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);


        CustomerToken customerToken=new CustomerToken();
        customerToken.setTokenId(1);
        customerToken.setToken(token);
        customerTokenRepository.save(customerToken);

        return  new JwtResponse(token);
    }



    @GetMapping("/logOut")
    public void logout(){
        customerTokenRepository.deleteAll();


    }

}
