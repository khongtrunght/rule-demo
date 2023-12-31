/*
 Copyright 2018 ZTE Corporation.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
import {Injectable} from '@angular/core';
import {Headers, Http} from '@angular/http';


import {RuleModel} from './alarmRule';
import {RuleRequest} from './ruleRequest';

@Injectable()
export class AlarmRuleService {
  private ruleUrl = "/api/holmes-rule-mgmt/v1/rule";
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {
  }

  getRules(): Promise<any> {
    return this.http.get(this.ruleUrl)
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error._body || error);
  }

  search(ruleId: string): Promise<RuleModel> {
    if (typeof (ruleId) == "string") {
      let rule = [{
        ruleId: null,
        ruleName: null,
        description: null,
        content: null,
        createtime: null,
        creator: null,
        updatetime: null,
        modifier: null,
        enabled: 0,
      }]
    }
    let data = {'ruleId': ruleId};
    var queryrequest = JSON.stringify(data);
    const url = `${this.ruleUrl}?queryrequest=${queryrequest}`;
    return this.http.get(encodeURI(url))
      .toPromise()
      .then(res => res.json().correlationRules as RuleModel[])
      .catch(this.handleError);
  }

  searchrules(rule: RuleRequest): Promise<RuleModel[]> {
    let data = {ruleName: rule.ruleName, enabled: rule.enabled}
    console.log(JSON.stringify(data));
    const url = `${this.ruleUrl}?queryrequest=${JSON.stringify(data)}`
    return this.http.get(encodeURI(url))
      .toPromise()
      .then(res => res.json().correlationRules as RuleModel[])
      .catch(this.handleError);
  }

  checkContent(ruleContent: string): Promise<any> {
    const url = "/api/holmes-engine-mgmt/v1/rule";
    let data = {content: ruleContent};
    return this.http
      .post(url, JSON.stringify(data), {headers: this.headers})
      .toPromise()
      .then(res => res)
      .catch(error => error);
  }

  updateRule(rule: RuleModel): Promise<any> {
    let rules = {
      "ruleId": rule.ruleId,
      "description": rule.description,
      "content": rule.content,
      "enabled": rule.enabled,
      "loopControlName": rule.loopControlName
    }
    const url = `${this.ruleUrl}`
    return this.http
      .post(url, JSON.stringify(rules), {headers: this.headers})
      .toPromise()
      .then(res => res)
      .catch(error => error)
  }

  save(rule: RuleModel): Promise<any> {
    let ruledata = {
      "description": rule.description,
      "content": rule.content,
      "enabled": rule.enabled,
      "ruleName": rule.ruleName,
      "loopControlName": rule.loopControlName
    }
    return this.http.put(this.ruleUrl, JSON.stringify(ruledata), {headers: this.headers})
      .toPromise()
      .then(res => res)
      .catch(error => error);
  }

  public delete(ruleId: string): Promise<void> {
    const url = `${this.ruleUrl}` + '/' + ruleId;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(res => {

      })
      .catch(this.handleError);
  }
}
