package com.boilerman.signupV3;

import com.boilerman.signupV3.DTO.Collegiate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegiateRepository extends JpaRepository<Collegiate, Long> {

    Collegiate findByPaymentNum(String paymentNum);

}
