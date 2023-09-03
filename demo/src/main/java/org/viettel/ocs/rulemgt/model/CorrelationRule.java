package org.viettel.ocs.rulemgt.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Properties;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class CorrelationRule implements Cloneable {


    @Id
    @SerializedName(value = "ruleId")
    private String rid;
    @Field("rulename")
    @SerializedName(value = "ruleName")
    private String name;
    private String description;
    private int enabled;
    private long templateID;
    private String engineID;
    private String engineType;
    private String creator;
    private String modifier;
    private Properties params;
    private String content;
    private String vendor;
    @SerializedName(value = "createtime")
    private Date createTime;
    @SerializedName(value = "updatetime")
    private Date updateTime;
    @SerializedName(value = "package")
    private String packageName;
    @SerializedName(value = "controlloopname")
    private String closedControlLoopName;
    @SerializedName(value = "engineinstance")
    private String engineInstance;

    @Override
    public Object clone() {
        CorrelationRule r = null;
        try {
            r = (CorrelationRule) super.clone();
        } catch (CloneNotSupportedException e) {
            // This will never happen.
            throw new InternalError(e);
        }

        r.rid = rid;
        r.name = name;
        r.description = description;
        r.enabled = enabled;
        r.templateID = templateID;
        r.engineID = engineID;
        r.engineType = engineType;
        r.creator = creator;
        r.modifier = modifier;
        r.params = params == null ? null : (Properties) params.clone();
        r.content = content;
        r.vendor = vendor;
        r.createTime = createTime == null ? null : (Date) createTime.clone();
        r.updateTime = updateTime == null ? null : (Date) updateTime.clone();
        r.packageName = packageName;
        r.closedControlLoopName = closedControlLoopName;
        r.engineInstance = engineInstance;

        return r;
    }
}