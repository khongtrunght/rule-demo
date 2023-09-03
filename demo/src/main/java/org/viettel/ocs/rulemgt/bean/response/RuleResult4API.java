package org.viettel.ocs.rulemgt.bean.response;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class RuleResult4API {
    private String ruleId;
    private String ruleName;
    private String description;
    private String content;
    private Date createTime;
    private String creator;
    private Date updateTime;
    private String modifier;
    private int enabled;
    private String loopControlName;
}