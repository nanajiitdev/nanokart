package com.user.login.service;

import com.user.login.dto.ConsumerProfileRequest;
import com.user.login.dto.ConsumerProfileResponse;

public interface ConsumerProfileService {

    ConsumerProfileResponse saveProfile(
            ConsumerProfileRequest request,
            String email);

    ConsumerProfileResponse getProfile(
            String email);

}