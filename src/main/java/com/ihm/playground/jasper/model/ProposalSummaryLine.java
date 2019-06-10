package com.ihm.playground.jasper.model;



import com.ihm.playground.jasper.util.DataUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * The ProposalSummaryLine model
 * @author Joly Mathew
 */
public class ProposalSummaryLine {

    private String market;
    private String station;
    private String format;
    private String daypart;
    private String notes;
    private Integer totalSpots;
    private BigDecimal rate;
    private BigDecimal totalCost;
    private Integer spotLength;
    private Integer spotsPerWeek;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer noOfWeeks;
    private BigDecimal aqhRtg;
    private Integer aqhPers;
    private BigDecimal grp;
    private BigDecimal cpp;
    private Integer impressions;
    private BigDecimal cpm;
    private Integer reach;
    private BigDecimal reachPercentage;
    private BigDecimal frequency;
    private Integer cume;
    private BigDecimal cumeRating;
    private Integer[] weeks;

    @Override
    public String toString() {
         return DataUtil.toJsonString(this);
    }
}
