package org.viettel.ocs.rulemgt.bean.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuleUpdateRequest {
    private String description;

    private String content;

    private int enabled;

    private String ruleId;

    private String loopControlName;
}
