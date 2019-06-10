package com.ihm.playground.jasper.compile;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.stereotype.Component;

@Component
@Slf4j
/**
 * The ReportCompiler class
 * <P> this is for demonstration only. IDeally we should not be compiling a report template everytime we need to generate report</P>
 * @author Joly Mathew
 */
public class ReportCompiler {

    public JasperReport generateReportFromSourceFile(String file) throws Exception{
        log.info("Compiling Report Design ...");
        try {
            JasperReport jasperReport =  JasperCompileManager.compileReport(file);
            log.info("Compilation Done!!! ...");
            return jasperReport;
        } catch (JRException e) {
            log.error(e.getMessage());
            throw new Exception(String.format(" Report cannot be generated from %s due to exception %s",file,e.getMessage()));
        }


    }
}
