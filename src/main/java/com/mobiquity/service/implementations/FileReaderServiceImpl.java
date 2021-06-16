package com.mobiquity.service.implementations;

import com.mobiquity.exception.APIException;
import com.mobiquity.service.FileReaderService;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileReaderServiceImpl implements FileReaderService {

  /**
   * Reading line(s) of the file from given path
   *
   * @param path
   * @return
   * @throws IOException
   * @throws APIException
   */
  public List<String> readLineByLine(String path) throws IOException, APIException {
    var file = new File(path);
    if (!file.exists()) {
      throw new APIException("File not found!");
    }
    if (!file.canRead()) {
      throw new APIException("File can not be read!");
    }
    var it = FileUtils.lineIterator(file, StandardCharsets.UTF_8.name());
    List<String> lineList = new ArrayList<>();
    while (it.hasNext()) {
      lineList.add(it.nextLine());
    }
    return lineList;
  }
}
