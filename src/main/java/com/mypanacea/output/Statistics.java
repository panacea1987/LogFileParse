package com.mypanacea.output;

import com.mypanacea.enity.OperationFromLogFile;

import java.util.List;
import java.util.Set;

public interface Statistics {
    Set<String> getDistinctMethodName(List<OperationFromLogFile> allMethodId);

    void printStatistic(Set<String> distinctMethod, List<OperationFromLogFile> allMethod);
}
