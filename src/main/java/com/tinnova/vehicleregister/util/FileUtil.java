package com.tinnova.vehicleregister.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public final class FileUtil {

  private FileUtil() {}

  public static String getFileAsString(String path) throws IOException {
    log.info("FileUtil.getFileAsString() - start [path: {}]", path);

    InputStream inputStream = new ClassPathResource(path).getInputStream();
    String fileString = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

    log.info("FileUtil.getFileAsString() - end");
    return fileString;
  }

}
