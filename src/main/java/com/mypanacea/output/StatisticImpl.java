package com.mypanacea.output;

import com.mypanacea.enity.OperationFromLogFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatisticImpl implements Statistic {
    private final Logger logger = LoggerFactory.getLogger(Statistic.class);

    @Override
    public List<String> getDistinctMethodName(List<OperationFromLogFile> allMethodId) {
        List<String> distinctMethod = new ArrayList<>();
        for (OperationFromLogFile m : allMethodId) {
            if (!distinctMethod.contains(m.getMethodName())) {
                distinctMethod.add(m.getMethodName());
            }
        }
        return distinctMethod;
    }

    @Override
    public void printStatistic(List<String> distinctMethod, List<OperationFromLogFile> allMethod) {
        for (String out : distinctMethod) {
            long minTime = 99999999;
            long maxTime = -99999999;
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
            logger.info(out + " min " + minTime + " max " + maxTime + " avg " + sumTime / count + " maxId " + idMaxCall + " count " + count);
        }
    }
}
