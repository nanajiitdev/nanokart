package com.user.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.login.client.OrderServiceClient;
import com.user.login.dto.ConsumerProfileRequest;
import com.user.login.dto.ConsumerProfileResponse;
import com.user.login.dto.CustomerRequest;
import com.user.login.entity.ConsumerProfile;
import com.user.login.entity.User;
import com.user.login.repository.ConsumerProfileRepository;
import com.user.login.repository.UserRepository;

@Service
public class ConsumerProfileServiceImpl implements ConsumerProfileService {

    private final ConsumerProfileRepository consumerRepository;
    private final UserRepository userRepository;
    
    @Autowired
    private OrderServiceClient orderServiceClient;

    public ConsumerProfileServiceImpl(
            ConsumerProfileRepository consumerRepository,
            UserRepository userRepository) {

        this.consumerRepository = consumerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ConsumerProfileResponse saveProfile(
            ConsumerProfileRequest request,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        ConsumerProfile profile =
                consumerRepository.findByUserId(user.getId())
                        .orElse(new ConsumerProfile());

        profile.setUser(user);

        profile.setHouseNo(request.getHouseNo());
        profile.setStreet(request.getStreet());
        profile.setLandmark(request.getLandmark());
        profile.setCity(request.getCity());
        profile.setDistrict(request.getDistrict());
        profile.setState(request.getState());
        profile.setCountry(request.getCountry());
        profile.setPincode(request.getPincode());

        ConsumerProfile saved =
                consumerRepository.save(profile);
        
        
        CustomerRequest customer = new CustomerRequest();

        customer.setCustomerId(user.getId());
        customer.setCustomerName(user.getName());
        customer.setEmail(user.getEmail());
//        customer.setMobile(request.getMobile());
//        customer.setAddress(request.getAddress());

          customer.setMobile(user.getMobile());
          customer.setAddress(

        	        request.getHouseNo() + ", " +
        	        request.getStreet() + ", " +
        	        request.getLandmark() + ", " +
        	        request.getCity() + ", " +
        	        request.getDistrict() + ", " +
        	        request.getState() + ", " +
        	        request.getCountry() + " - " +
        	        request.getPincode()

        	);
          
          System.out.println("========== Before Feign Call ==========");
          System.out.println(customer);
          orderServiceClient.saveCustomer(customer);
          System.out.println("========== After Feign Call ==========");

        return convert(saved);
    }

    @Override
    public ConsumerProfileResponse getProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        ConsumerProfile profile =
                consumerRepository.findByUserId(user.getId())
                        .orElse(null);

        ConsumerProfileResponse response = new ConsumerProfileResponse();

        response.setUserId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setMobile(user.getMobile());

        if (profile != null) {

            response.setConsumerId(profile.getConsumerId());
            response.setHouseNo(profile.getHouseNo());
            response.setStreet(profile.getStreet());
            response.setLandmark(profile.getLandmark());
            response.setCity(profile.getCity());
            response.setDistrict(profile.getDistrict());
            response.setState(profile.getState());
            response.setCountry(profile.getCountry());
            response.setPincode(profile.getPincode());

        }

        return response;
    }
    private ConsumerProfileResponse convert(
            ConsumerProfile profile) {

        ConsumerProfileResponse response =
                new ConsumerProfileResponse();

        response.setConsumerId(profile.getConsumerId());

        response.setUserId(profile.getUser().getId());

        response.setName(profile.getUser().getName());

        response.setEmail(profile.getUser().getEmail());

        response.setMobile(profile.getUser().getMobile());

        response.setHouseNo(profile.getHouseNo());

        response.setStreet(profile.getStreet());

        response.setLandmark(profile.getLandmark());

        response.setCity(profile.getCity());

        response.setDistrict(profile.getDistrict());

        response.setState(profile.getState());

        response.setCountry(profile.getCountry());

        response.setPincode(profile.getPincode());

        return response;
    }

}