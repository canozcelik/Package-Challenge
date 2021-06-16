package com.mobiquity;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;
import com.mobiquity.util.GeneralUtil;

import java.io.IOException;

public class PackageChallenge {
  public static void main(String[] args) {
    try {
      System.out.println(Packer.pack(GeneralUtil.getPathToTargetFile(args)));
    } catch (APIException | IOException e) {
      System.out.println("An error has been occurred!");
      e.printStackTrace();
    }
  }
}
