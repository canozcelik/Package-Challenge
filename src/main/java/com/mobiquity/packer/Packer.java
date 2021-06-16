package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.service.PackageExtractionService;
import com.mobiquity.service.implementations.PackageExtractionServiceImpl;

import java.io.IOException;

public class Packer {

  private Packer() {}

  public static String pack(String filePath) throws APIException, IOException {
    PackageExtractionService packageExtractionService = new PackageExtractionServiceImpl();
    return packageExtractionService.evaluatePackageInput(filePath);
  }
}
