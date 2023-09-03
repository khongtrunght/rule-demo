package org.viettel.ocs.rulemgt.repository;

import java.util.List;

import org.viettel.ocs.rulemgt.bean.request.RuleQueryCondition;
import org.viettel.ocs.rulemgt.model.CorrelationRule;

public interface CorrelationRuleRepositoryCustom {
    List<CorrelationRule> findCorrelationRulesByCondition(RuleQueryCondition ruleQueryCondition);
}
