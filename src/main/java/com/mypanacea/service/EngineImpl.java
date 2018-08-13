package com.mypanacea.service;

import com.mypanacea.enity.OperationFromLogFile;
import com.mypanacea.output.Statistics;
import com.mypanacea.tools.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EngineImpl implements Engine {
    private final Logger logger = LoggerFactory.getLogger(Statistics.class);
    private Statistics statistics;
    private Parser parser;

    @Autowired
    public EngineImpl(Statistics statistics, Parser parser) {

        this.statistics = statistics;
        this.parser = parser;
    }

    @Override
    public void getStatisticFromLogFile() {
        long start = System.currentTimeMillis();

        List<OperationFromLogFile> allOperationFromLogFile = parser.parseLogFile(parser.getInputPath("Enter path"));
        Set<String> uniqueOperation = statistics.getDistinctMethodName(allOperationFromLogFile);

        statistics.printStatistic(uniqueOperation, allOperationFromLogFile);

        logger.info("Duration: " + (System.currentTimeMillis() - start) / 1000 + " c");
    }
}
