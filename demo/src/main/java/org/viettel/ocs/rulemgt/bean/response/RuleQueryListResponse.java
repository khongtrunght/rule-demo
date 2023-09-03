package org.viettel.ocs.rulemgt.bean.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class RuleQueryListResponse {
    private List<RuleResult4API> correlationRules = new ArrayList<RuleResult4API>();
    private int totalCount;
}