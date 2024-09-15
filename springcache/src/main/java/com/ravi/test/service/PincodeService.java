package com.ravi.test.service;

import java.util.List;
import java.util.Optional;

import com.ravi.test.domain.PinCode;

public interface PincodeService {
	  List<PinCode> findAll();
	  Optional<PinCode> findById(int id);
	  PinCode save(PinCode pincode);
	  void deleteById(int id);
	  Optional<PinCode> getByCityName(String cityName);
	}

