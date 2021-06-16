package com.mobiquity.service;

import com.mobiquity.exception.APIException;

import java.io.IOException;
import java.util.List;

public interface FileReaderService {
  List<String> readLineByLine(String path) throws IOException, APIException;
}
