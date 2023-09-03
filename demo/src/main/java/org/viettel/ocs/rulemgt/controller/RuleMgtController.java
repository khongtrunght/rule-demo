package org.viettel.ocs.rulemgt.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.viettel.ocs.rulemgt.bean.request.RuleCreateRequest;
import org.viettel.ocs.rulemgt.bean.request.RuleDeleteRequest;
import org.viettel.ocs.rulemgt.bean.request.RuleQueryCondition;
import org.viettel.ocs.rulemgt.bean.request.RuleUpdateRequest;
import org.viettel.ocs.rulemgt.bean.response.RuleAddAndUpdateResponse;
import org.viettel.ocs.rulemgt.bean.response.RuleQueryListResponse;
import org.viettel.ocs.rulemgt.constant.RuleMgtConstant;
import org.viettel.ocs.rulemgt.service.RuleMgtService;
import org.viettel.ocs.rulemgt.utils.GsonUtil;
import org.viettel.ocs.rulemgt.utils.UserUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/rule")
@Tag(name = "Rule Management", description = "Rule Management APIs")
public class RuleMgtController {

    @Autowired
    private RuleMgtService ruleMgtService;

    @Operation(summary = "Save a rule into the database; deploy it to the Drools engine if it is enabled.", description = "Add new correlation rule")
    @ResponseBody
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RuleAddAndUpdateResponse addCorrelationRule(HttpServletRequest request,
            @Parameter(name = "The request entity of the HTTP call, which comprises \"ruleName\"(required), "
                    + "\"loopControlName\"(required), \"content\"(required), \"enabled\"(required) "
                    + "and \"description\"(optional)", required = true) @RequestBody RuleCreateRequest ruleCreateRequest) {
        RuleAddAndUpdateResponse ruleChangeResponse;
        ruleChangeResponse = ruleMgtService.addCorrelationRule(UserUtil.getUserName(request), ruleCreateRequest);
        return ruleChangeResponse;

    }

    @Operation(summary = "Update an existing rule; deploy it to the Drools engine if it is enabled.", description = "Update correlation rule")
    @ResponseBody
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RuleAddAndUpdateResponse updateCorrelationRule(HttpServletRequest request,
            @Parameter(name = "The request entity of the HTTP call, which comprises \"ruleId\"(required), "
                    + "\"content\"(required), \"enabled\"(required) and \"description\"(optional)", required = true) @RequestBody RuleUpdateRequest ruleUpdateRequest) {
        RuleAddAndUpdateResponse ruleChangeResponse;
        ruleChangeResponse = ruleMgtService.updateCorrelationRule(UserUtil.getUserName(request), ruleUpdateRequest);
        log.info("update rule:" + ruleUpdateRequest.getRuleId() + " successful");
        return ruleChangeResponse;
    }

    @Operation(summary = "Remove a rule from Holmes")
    @DeleteMapping("/{ruleid}")
    public ResponseEntity<String> deleteCorelationRule(@PathVariable("ruleid") String ruleId) {
        ruleMgtService.deleteCorrelationRule(new RuleDeleteRequest(ruleId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Query rules using certain criteria.", description = "queryrequest - A JSON string used as a query parameter, which comprises \"ruleId\"(optional), "
                    + "\"ruleName\"(optional), \"creator\"(optional), "
                    + "\"modifier\"(optional) and \"enabled\"(optional).")
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RuleQueryListResponse getCorrelationRules(
            @Parameter(example = "{\"ruleId\":\"rule_1484727187317\"}", required = false) @RequestParam(value = "queryrequest", required = false) String ruleQueryRequest) {
        RuleQueryListResponse ruleQueryListResponse;
        RuleQueryCondition ruleQueryCondition = getRuleQueryCondition(ruleQueryRequest);

        ruleQueryListResponse = ruleMgtService
                .getCorrelationRuleByCondition(ruleQueryCondition);
        return ruleQueryListResponse;
    }

    private RuleQueryCondition getRuleQueryCondition(String queryRequest) {
        RuleQueryCondition ruleQueryCondition = GsonUtil
                .jsonToBean(queryRequest, RuleQueryCondition.class);
        if (queryRequest == null) {
            if (ruleQueryCondition == null) {
                ruleQueryCondition = new RuleQueryCondition();
            }
            ruleQueryCondition.setEnabled(RuleMgtConstant.STATUS_RULE_ALL);
        } else if (queryRequest.indexOf("enabled") == -1) {
            ruleQueryCondition.setEnabled(RuleMgtConstant.STATUS_RULE_ALL);
        }
        return ruleQueryCondition;
    }
}
