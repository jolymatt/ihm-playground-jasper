package com.ihm.playground.jasper.service;

import com.ihm.playground.jasper.compile.ReportCompiler;
import com.ihm.playground.jasper.domain.ProposalReport;
import com.ihm.playground.jasper.model.ProposalSummaryLine;

import com.ihm.playground.jasper.model.ReportMeta;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
/**
 * The ProposalReport Service Implementation
 * <p> This is only for demonstration</p>
 * @author Joly Mathew
 */
public class ProposalReportServiceImpl implements ProposalReportService {

    @Autowired
    ReportCompiler reportCompiler;

    @Value("${jasper.rpt.proposalsummarytemplate}")
    private String rptTemplateFile;

    @Value("${jasper.rpt.generationlocation}")
    private String rptGenerationLocation;

    @Override
    public ReportMeta generateProposalSummaryReport(ProposalReport proposalReport)  throws Exception {
        ReportFormat reportFormat = proposalReport.getReportFormat();

        if(null== reportFormat){
            log.info("Report format is null. Switching to Default");
            reportFormat= ReportFormat.MICROSOFT_XLSX;
        }
        switch (reportFormat){
            case MICROSOFT_XLSX: return imGenerateAsXLSX(proposalReport.getListOfProposalSummaryLine());
            case ACROBAT_PDF:return imGenerateAsPDF(proposalReport.getListOfProposalSummaryLine());
            default:throw new Exception(String.format("Report format specified {} not supported",reportFormat));

        }
    }

    /**
     * Common method to compile and generate the JasperPrint
     * @param listOfProposalSummaryLine List
     * @return JasperPrint
     * @throws Exception
     */

    protected JasperPrint imFillReport(List<ProposalSummaryLine> listOfProposalSummaryLine) throws Exception{
        //This is not necessary as we can compile and keep the binary format to avoid this extra call.
        JasperReport jasperReport = reportCompiler.generateReportFromSourceFile(rptTemplateFile);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listOfProposalSummaryLine);
        Map<String, Object> params = new HashMap<>();
        params.put("reportFieldDataSource", dataSource);
//        params.put("net.sf.jasperreports.export.pdf.size.page.to.content",true);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource() );

        return jasperPrint;
    }

    /**
     * This internal Method generate report as PDF
     * @param listOfProposalSummaryLine List
     * @return ReportMeta
     * @throws Exception
     */
    protected ReportMeta imGenerateAsPDF(List<ProposalSummaryLine> listOfProposalSummaryLine)  throws Exception {

        JasperPrint jasperPrint = imFillReport(listOfProposalSummaryLine);
        //Not a good place to generate this...
        String reportName = "unified_proposal_reporting_poc"+ UUID.randomUUID()+".pdf";

        String reportFile =  rptGenerationLocation+reportName;
        JasperExportManager.exportReportToPdfFile(jasperPrint, reportFile);

       return new ReportMeta(reportFile,reportName);

    }

    /**
     * This internal method generate report as Excel
     * @param listOfProposalSummaryLine List
     * @return ReportMeta
     * @throws Exception
     */

    protected ReportMeta imGenerateAsXLSX(List<ProposalSummaryLine> listOfProposalSummaryLine)  throws Exception {

        JasperPrint jasperPrint = imFillReport(listOfProposalSummaryLine);
        //Not a good place to generate this...
        String reportName = "unified_proposal_reporting_poc"+ UUID.randomUUID()+".xlsx";
        String reportFile =  rptGenerationLocation+reportName;


        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        File outputFile = new File(reportFile);

        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
        exporter.exportReport();

        return new ReportMeta(reportFile,reportName);

    }


}
