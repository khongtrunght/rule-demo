package org.viettel.ocs.rulemgt.repository;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.MongoRegexCreator.MatchMode;
import org.springframework.data.mongodb.core.query.Query;
import org.viettel.ocs.rulemgt.bean.request.RuleQueryCondition;
import org.viettel.ocs.rulemgt.constant.RuleMgtConstant;
import org.viettel.ocs.rulemgt.exception.CorrelationException;
import org.viettel.ocs.rulemgt.model.CorrelationRule;

import java.lang.reflect.Field;

public class CorrelationRuleRepositoryImpl implements CorrelationRuleRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CorrelationRuleRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<CorrelationRule> findCorrelationRulesByCondition(RuleQueryCondition ruleQueryCondition)
            throws CorrelationException {

        try {
            Class clazz = ruleQueryCondition.getClass();
            Field[] fields = clazz.getDeclaredFields();

            final Query query = new Query();
            final List<Criteria> criteria = new ArrayList<>();

            // if (ruleQueryCondition.getName() != null){
            // criteria.add(Criteria.where("name").regex(MongoRegexCreator.INSTANCE.toRegularExpression(ruleQueryCondition.getName(),
            // MatchMode.CONTAINING), "i"));
            // }
            // if (ruleQueryCondition.getEnabled() != RuleMgtConstant.STATUS_RULE_ALL){
            // criteria.add(Criteria.where("enabled").is(ruleQueryCondition.getEnabled()));
            // }

            // if (ruleQueryCondition.getRid() != null){
            // criteria.add(Criteria.where("rid").is(ruleQueryCondition.getRid()));
            // }

            // if (ruleQueryCondition.getCreator() != null){
            // criteria.add(Criteria.where("creator").is(ruleQueryCondition.getCreator()));
            // }
            // if (ruleQueryCondition.getModifier() != null){
            // criteria.add(Criteria.where("modifier").is(ruleQueryCondition.getModifier()));
            // }

            // if(!criteria.isEmpty()) {
            // query.addCriteria(new Criteria().andOperator(criteria.toArray(new
            // Criteria[criteria.size()])));
            // }

            for (Field field : fields) {
                PropertyDescriptor pd = new PropertyDescriptor((String) field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object o = getMethod.invoke(ruleQueryCondition);
                if (o != null) {
                    String tempName = field.getName();
                    if ("enabled".equals(tempName)) {
                        if ((int) o != RuleMgtConstant.STATUS_RULE_ALL) {
                            criteria.add(Criteria.where(tempName).is(o));
                        }
                    } else if ("name".equals(tempName)) {
                        criteria.add(Criteria.where(tempName).regex(
                                MongoRegexCreator.INSTANCE.toRegularExpression((String) o, MatchMode.CONTAINING), "i"));
                    } else if (!"".equals(o.toString().trim())) {
                        criteria.add(Criteria.where(tempName).is(o));
                    }
                }
            }

            if (!criteria.isEmpty()) {
                query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
            }
            return mongoTemplate.find(query, CorrelationRule.class);

        } catch (Exception e) {
            throw new CorrelationException("An error occurred while building the query SQL.", e);
        }
    }

}
