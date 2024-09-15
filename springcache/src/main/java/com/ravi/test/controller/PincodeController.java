package com.ravi.test.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.test.SpringcacheApplication;
import com.ravi.test.domain.PinCode;
import com.ravi.test.service.PincodeService;

@RestController
@RequestMapping("/pincodes")
public class PincodeController {
  private final PincodeService pincodeService;
  public static final Logger log = Logger.getLogger(PincodeController.class.getName());
  @Autowired
  CacheManager cacheManager;

  @Autowired
  public PincodeController(PincodeService pincodeService) {
    this.pincodeService = pincodeService;
  }

  @GetMapping
  public List<PinCode> findAll() {
    return pincodeService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<PinCode> findById(@PathVariable int id) {
    Optional<PinCode> pincode = pincodeService.findById(id);
    return pincode.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/a/{id}")
  public PinCode findById1(@PathVariable int id) {
    Optional<PinCode> pincode = pincodeService.findById(id);
    return pincode.get();
  }
  
  @GetMapping("/getFromCity/{cityName}")
  public ResponseEntity<PinCode> findById(@PathVariable String cityName) {
	  log.log(Level.INFO, cacheManager.getCacheNames().toString());
    Optional<PinCode> pincode = pincodeService.getByCityName(cityName);
    return pincode.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  
  @PostMapping
  public PinCode save(@RequestBody PinCode pincode) {
    return pincodeService.save(pincode);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable int id) {
    pincodeService.deleteById(id);
  }
}

