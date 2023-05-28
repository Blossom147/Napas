package com.example.napas.repo;

import com.example.napas.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepo extends JpaRepository<BankEntity, Integer > {
    BankEntity findByBin(String bin);
}
