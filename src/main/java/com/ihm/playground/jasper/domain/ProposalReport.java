package com.ihm.playground.jasper.domain;

import com.ihm.playground.jasper.util.DataUtil;
import com.ihm.playground.jasper.model.ProposalSummaryLine;
import com.ihm.playground.jasper.service.ReportFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
/**
 * The ProposalReport domain class
 * @
 */
public class ProposalReport {

    private ReportFormat reportFormat;

    private List<ProposalSummaryLine> listOfProposalSummaryLine;

    @Override
    public String toString() {
        return DataUtil.toJsonString(this);
    }
}
