package com.mypanacea.service;

import com.mypanacea.enity.OperationFromLogFile;
import com.mypanacea.output.Statistic;
import com.mypanacea.tools.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineImpl implements Engine {
    private final Logger logger = LoggerFactory.getLogger(Statistic.class);
    private Statistic statistic;
    private Parser parser;

    @Autowired
    public EngineImpl(Statistic statistic, Parser parser) {

        this.statistic = statistic;
        this.parser = parser;
    }

    @Override
    public void getStatisticFromLogFile() {
        long start = System.currentTimeMillis();
        List<OperationFromLogFile> allOperationFromLogFile = parser.parseLogFile(parser.getInputPath("Введите путь к логфайлу"));
        List<String> uniqueOperation = statistic.getDistinctMethodName(allOperationFromLogFile);
        statistic.printStatistic(uniqueOperation, allOperationFromLogFile);
        logger.info("Duration: "+(System.currentTimeMillis() - start) / 1000 + " c");
    }
}
