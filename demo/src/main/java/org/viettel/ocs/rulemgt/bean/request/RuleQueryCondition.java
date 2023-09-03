package org.viettel.ocs.rulemgt.bean.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuleQueryCondition {
    @SerializedName(value = "ruleId")
    private String rid;
    @SerializedName(value = "ruleName")
    private String name;
    private int enabled;
    private String creator;
    private String modifier;
}
