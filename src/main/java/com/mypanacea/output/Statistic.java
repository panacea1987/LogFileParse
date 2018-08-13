package com.mypanacea.output;

import com.mypanacea.enity.OperationFromLogFile;

import java.util.List;

public interface Statistic {
    List<String> getDistinctMethodName(List<OperationFromLogFile> allMethodId);

    void printStatistic(List<String> distinctMethod, List<OperationFromLogFile> allMethod);
}
