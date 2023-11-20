package com.apisys.back.util;

import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
public class DateFormatClass {

    public Date formatarAnoDataPadrao(String dataFormatar) throws ParseException {

        // Adicionando o dia manualmente (01)


        // Parsing para LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(dataFormatar, formatter);
        System.out.println("Parsed Date: " + parsedDate);

        Date dateFormated = Date.from(parsedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("Date::" + dateFormated);

        return dateFormated ;
    }
}
