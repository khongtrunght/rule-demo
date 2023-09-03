package org.viettel.ocs.rulemgt.tools;

import java.util.Arrays;

import java.util.List;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EngineTools {
    public List<String> getInstanceList(){
        log.info("get instance list success");
        return Arrays.asList("mot", "hai", "ba");
    }

    public String getEngineWithLeastRules() {
        return "192.168.1.1";
    }

    public List<String> getLegacyEngineInstances() {
        log.info("get LegacyEngine instance list success");
        return Arrays.asList("mot", "hai", "ba");
    }
}
