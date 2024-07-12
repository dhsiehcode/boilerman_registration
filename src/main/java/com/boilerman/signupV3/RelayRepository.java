package com.boilerman.signupV3;

import com.boilerman.signupV3.DTO.Relay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelayRepository extends JpaRepository<Relay, Long> {

    Relay findByPaymentNum(String payementNum);
}
