package com.example.customer.Controller;

import com.example.customer.Dto.CustomerRequest;
import com.example.customer.Dto.CustomerResponse;
import com.example.customer.Entity.Customer;
import com.example.customer.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")

public class CustomerController {

    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer
            (@RequestBody @Valid CustomerRequest customerRequest){
        return new ResponseEntity<>(customerService.createCustomer(customerRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
    ){
      customerService.updateCustomer(customerRequest);
      return ResponseEntity.accepted().build();
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<CustomerResponse>> getCustomerDetails(){
        List<CustomerResponse> customerResponses=customerService.getCustomerDetails();
        return ResponseEntity.ok(customerResponses);
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String customerId){
          Boolean existId=customerService.existsById(customerId);
          return ResponseEntity.ok(existId);
    }

    @GetMapping("/findById/{customer-id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String customerId){
        CustomerResponse customerResponse=customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customerResponse);
    }
    @DeleteMapping("/deleteById/{customer-id}")
    public ResponseEntity<Boolean> deleteCustomerId(@PathVariable String customerId){
        Boolean isDeleted=customerService.deleteCustomerId(customerId);
        return ResponseEntity.ok(isDeleted);
    }



}
