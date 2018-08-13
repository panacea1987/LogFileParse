package com.mypanacea.output;

import com.mypanacea.enity.OperationFromLogFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class StatisticsImpl implements Statistics {
    private final Logger logger = LoggerFactory.getLogger(Statistics.class);

    @Override
    public HashSet<String> getDistinctMethodName(List<OperationFromLogFile> allMethodId) {
        HashSet<String> distinctMethod = new HashSet<>();
        for (OperationFromLogFile m : allMethodId) {
            if (!distinctMethod.contains(m.getMethodName())) {
                distinctMethod.add(m.getMethodName());
            }
        }
        return distinctMethod;
    }

    @Override
    public void printStatistic(Set<String> distinctMethod, List<OperationFromLogFile> allMethod) {
        for (String out : distinctMethod) {
            long minTime = Long.MAX_VALUE;
            long maxTime = Long.MIN_VALUE;
            int count = 0;
            int idMaxCall = 0;
            long sumTime = 0;
            for (OperationFromLogFile in : allMethod) {
                if (in.getMethodName().equals(out)) {
                    if (in.getMethodDuration() < minTime) {
                        minTime = in.getMethodDuration();
                    }
                    if (in.getMethodDuration() > maxTime) {
                        maxTime = in.getMethodDuration();
                        idMaxCall = in.getId();
                    }
                    count++;
                    sumTime += in.getMethodDuration();
                }
            }
            logger.info(out + " min " + minTime + "sec," + " max " + maxTime + "sec," + " avg " + sumTime / count + "sec," + " maxId " + idMaxCall + "," + " count " + count);
        }
    }
}
