package com.mypanacea.tools;

import com.mypanacea.enity.OperationFromLogFile;

import java.util.List;

public interface Parser {
    String getInputPath(String aWord);

    List<OperationFromLogFile> parseLogFile(String path);
}
