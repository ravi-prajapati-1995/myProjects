package com.ravi.test.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ravi.test.domain.PinCode;
import com.ravi.test.repo.PincodeRepository;
import com.ravi.test.service.PincodeService;

@Service
public class PincodeServiceImpl implements PincodeService {
  private final PincodeRepository pincodeRepository;

  @Autowired
  public PincodeServiceImpl(PincodeRepository pincodeRepository) {
    this.pincodeRepository = pincodeRepository;
  }

  @Override
  public List<PinCode> findAll() {
    return pincodeRepository.findAll();
  }

  @Override
  public Optional<PinCode> findById(int id) {
    return pincodeRepository.findById(id);
  }

  @Override
  public PinCode save(PinCode pincode) {
    return pincodeRepository.save(pincode);
  }

  @Override
  public void deleteById(int id) {
	  pincodeRepository.deleteById(id);
  }

  @Cacheable(cacheNames = "city-pin-cache")
	@Override
	public Optional<PinCode> getByCityName(String cityName) {
		return pincodeRepository.findTopByCityIgnoreCase(cityName);
	}
}

