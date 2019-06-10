package com.ihm.playground.jasper.web;

import com.ihm.playground.jasper.domain.ProposalReport;
import com.ihm.playground.jasper.model.ReportMeta;
import com.ihm.playground.jasper.service.ProposalReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/report")
@Api(value = "Report Service", description = "Report services for Unified proposal")
@Slf4j

/**
 * The ReportController controller Class
 * @author Joly Mathew
 */
public class ReportController {

    @Autowired
    ProposalReportService proposalReportService;

    @ApiOperation(value = "Request Proposal Summary Report")
    @RequestMapping(value = "/proposalsummary", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity generateProposalSummaryReport(@ApiParam(required = true, name = "Proposal Report", value = "Proposal Report ") @RequestBody ProposalReport proposalReport) {
        try {
            ReportMeta reportMeta = proposalReportService.generateProposalSummaryReport(proposalReport);
            if (null == reportMeta || null == reportMeta.getFile())
                return new ResponseEntity("Request Failed", HttpStatus.BAD_REQUEST);
            HttpHeaders headers = new HttpHeaders();
            InputStream in = new FileInputStream(reportMeta.getFile());
            byte[] media = IOUtils.toByteArray(in);
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            // set filename in header
            headers.add("Content-Disposition", "attachment; filename=" + reportMeta.getFileName());
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);

            return responseEntity;

        } catch (Exception exception) {
            log.error(exception.getMessage());
            return new ResponseEntity("Request Failed", HttpStatus.BAD_REQUEST);
        }

    }


}
