package org.viettel.ocs.rulemgt.bean.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorrelationDeployRule4Engine extends CorrelationCheckRule4Engine {

    private String engineId;

    private String loopControlName;
}
