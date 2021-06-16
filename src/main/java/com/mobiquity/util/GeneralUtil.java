package com.mobiquity.util;

public class GeneralUtil {

  private GeneralUtil() {}

  /**
   * if args[0] is null return default file input path
   *
   * @param args
   * @return
   */
  public static String getPathToTargetFile(String[] args) {
    if (args.length >= 1) {
      return args[0];
    }
    return "src/main/test/resources/example_input";
  }
}
