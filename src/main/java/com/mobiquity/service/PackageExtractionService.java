package com.mobiquity.service;

import com.mobiquity.exception.APIException;

import java.io.IOException;

public interface PackageExtractionService {
  String evaluatePackageInput(String filePath) throws APIException, IOException;
}
