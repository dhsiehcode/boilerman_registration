package com.boilerman.signupV3;

import com.boilerman.signupV3.DTO.AgeGroup;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AgeGroupRepository extends JpaRepository<AgeGroup, Long> {

    AgeGroup findByPaymentNum(String paymentNum);
}
