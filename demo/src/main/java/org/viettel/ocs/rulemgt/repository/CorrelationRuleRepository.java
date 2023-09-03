package org.viettel.ocs.rulemgt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.viettel.ocs.rulemgt.model.CorrelationRule;

public interface CorrelationRuleRepository extends MongoRepository<CorrelationRule, String>, CorrelationRuleRepositoryCustom {
    CorrelationRule findByName(String ruleName);
}
