package org.viettel.ocs.rulemgt.service;

import org.springframework.stereotype.Service;
import org.viettel.ocs.rulemgt.bean.request.CorrelationCheckRule4Engine;
import org.viettel.ocs.rulemgt.bean.request.CorrelationDeployRule4Engine;

@Service
public class EngineServiceWrapper {

    public void deleteRuleFromEngine(String packageName, String ip) {
    }

    public boolean checkRuleFromEngine(CorrelationCheckRule4Engine correlationCheckRule, String ip) {
        return false;
    }

    public String deployEngine(CorrelationDeployRule4Engine correlationRules2DeployRule, String ip) {
        return null;
    }
    
}
