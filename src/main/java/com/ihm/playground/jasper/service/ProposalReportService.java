package com.ihm.playground.jasper.service;

import com.ihm.playground.jasper.domain.ProposalReport;
import com.ihm.playground.jasper.model.ReportMeta;

/**
 * The ProposalReportService
 */
public interface ProposalReportService {

    /**
     * This is a demonstration service which takes List of Proposal Lines and a desired ReportFormat and generate the Report
     *
     * @param ProposalReport proposalReport
     * @return ReportMeta
     * @throws Exception when generation fails
     */
    ReportMeta generateProposalSummaryReport(ProposalReport proposalReport) throws Exception;

}
