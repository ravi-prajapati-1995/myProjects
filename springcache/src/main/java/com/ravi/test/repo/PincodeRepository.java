package com.ravi.test.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravi.test.domain.PinCode;

public interface PincodeRepository extends JpaRepository<PinCode, Integer> {
	public Optional<PinCode> findTopByCityIgnoreCase(String city);
}
