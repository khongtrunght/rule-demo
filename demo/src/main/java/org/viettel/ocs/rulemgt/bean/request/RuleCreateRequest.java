package org.viettel.ocs.rulemgt.bean.request;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class RuleCreateRequest {
    private String ruleName;

    private String description;

    private String content;

    private String creator;

    private int enabled;

    private String loopControlName;
}
