package com.ihm.playground.jasper.util;

import com.ihm.playground.jasper.service.ReportFormat;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, ReportFormat> {

    @Override
    public ReportFormat convert(String from) {
        return ReportFormat.valueOf(from);
    }
}
