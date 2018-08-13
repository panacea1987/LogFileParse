package com.mypanacea.tools;

import com.mypanacea.enity.OperationFromLogFile;
import com.mypanacea.enity.RowFromLogFile;
import com.mypanacea.output.Statistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class ParserLogFile implements Parser {
    private final Logger logger = LoggerFactory.getLogger(Statistic.class);
    private DateTimeFormatter formatter;
    private String positionDelimetr;
    private int dateTimePosition;
    private int openClosedTimePosition;
    private int operationPosition;
    private String operationDelimetr;
    private int operationNamePosition;
    private int operationIdPosition;
    private String operationLeftBracket;
    private String operationRightBracket;

    public ParserLogFile(@Value("${datetimeformat}") String dateFormat,
                         @Value("${positiondelimetr}") String positionDelimetr,
                         @Value("${datetimeposition}") int dateTimePosition,
                         @Value("${openclosedtypeposition}") int openClosedTimePosition,
                         @Value("${operationposition}") int operationPosition,
                         @Value("${operationdelimetr}") String operationDelimetr,
                         @Value("${operationnameposition}") int operationNamePosition,
                         @Value("${operationidposition}") int operationIdPosition,
                         @Value("${operationleftbracket}") String operationLeftBracket,
                         @Value("${operationrightbracket}") String operationRightBracket) {
        this.formatter = DateTimeFormatter.ofPattern(dateFormat);
        this.dateTimePosition = dateTimePosition;
        this.openClosedTimePosition = openClosedTimePosition;
        this.positionDelimetr = positionDelimetr;
        this.operationPosition = operationPosition;
        this.operationDelimetr = operationDelimetr;
        this.operationNamePosition = operationNamePosition;
        this.operationIdPosition = operationIdPosition;
        this.operationLeftBracket = operationLeftBracket;
        this.operationRightBracket = operationRightBracket;
    }

    @Override
    public String getInputPath(String aWord) {
        Scanner in = new Scanner(System.in);
        System.out.println(aWord);

        return in.nextLine();
    }

    public List<OperationFromLogFile> parseLogFile(String path) {

        try {
            return Files.lines(Paths.get(path)).
                    map(s -> new RowFromLogFile(
                            LocalDateTime.parse(s.split(Pattern.quote(positionDelimetr))[dateTimePosition], formatter),
                            s.split(Pattern.quote(" "))[openClosedTimePosition],
                            s.split(Pattern.quote(" "))[operationPosition].split(operationDelimetr)[operationNamePosition].replaceAll(operationLeftBracket, ""),
                            Integer.valueOf(s.split(Pattern.quote(" "))[operationPosition].split(operationDelimetr)[operationIdPosition].replaceAll(operationRightBracket, ""))
                    )).
                    collect(Collectors.groupingBy(RowFromLogFile::getId))
                    .entrySet()
                    .stream()
                    .filter(s -> s.getValue().size() == 2)
                    .map(Map.Entry::getValue)
                    .map(s -> new OperationFromLogFile(
                            s.get(0).getMethod(),
                            ChronoUnit.SECONDS.between(s.get(0).getDatetime(), s.get(1).getDatetime()),
                            s.get(0).getId()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Error write file..");
            e.printStackTrace();
            return null;
        }
    }
}