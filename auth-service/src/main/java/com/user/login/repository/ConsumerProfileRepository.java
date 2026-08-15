package com.user.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.login.entity.ConsumerProfile;

public interface ConsumerProfileRepository
        extends JpaRepository<ConsumerProfile, Long> {

    Optional<ConsumerProfile> findByUserId(Long userId);

}