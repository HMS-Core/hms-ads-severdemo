/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.

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

package com.huawei.ads.demo;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestfulInterfaceCallDemo {
    /**
     * Gateway address for OAuth authentication
     */
    private static String tokenUrl = "https://login.cloud.huawei.com/oauth2/v3/token";
    /**
     * OAuth2.0 client ID and key generated on HUAWEI Developers
     */
    private static String clientId = "102422523";
    private static String secret = "a2c95f25bc421a3fd0f77d2118743d925c68fd189675ad6a4a7980a45125e705";
    /**
     * Gateway for invoking data
     */
    private static String reportUrl = "https://ads.cloud.huawei.com/openapi/monetization/reports/v1/publisher";

    public static void main(String[] args) throws IOException {
        String token = getOauthToken();
        callApiUseToken(token);
    }

    /**
     * Obtain token information
     *
     * @return Return the token value
     * @throws Throw the IOException exception
     */
    private static String getOauthToken() throws IOException {
        // Set Content-Type in the packet header
        PostMethod postMethod = new PostMethod(tokenUrl);
        postMethod.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=utf-8");

        // Set the packet body
        List<NameValuePair> param = new ArrayList<>();
        param.add(new NameValuePair("grant_type", "client_credentials"));
        param.add(new NameValuePair("client_id", clientId));
        param.add(new NameValuePair("client_secret", secret));
        postMethod.setRequestBody(param.toArray(new NameValuePair[param.size()]));

        HttpClient httpClient = new HttpClient();
        int ret = httpClient.executeMethod(postMethod);
        String accessToken = null;
        String tokenResponse = postMethod.getResponseBodyAsString();
        if (ret == 200) {
            JSONObject atJson = JSONObject.parseObject(tokenResponse);
            accessToken = atJson.getString("access_token");
        } else {
            System.out.println("request access token failed " + tokenResponse);
        }
        return accessToken;
    }

    /**
     * Call the Publisher Service Reporting API
     * @param accessToken Token obtained based on the client ID and key
     * @throws IOException
     */
    private static void callApiUseToken(String accessToken) throws IOException {
        // Set the packet header.Content-Type， Authorization
        PostMethod postMethod = new PostMethod(reportUrl);
        postMethod.setRequestHeader("Content-Type","application/json;charset=utf-8");
        postMethod.setRequestHeader("Authorization","Bearer " + accessToken);

        // Set the packet body
        Map<String, Object> bodyMap = new HashMap<>();
        Map<String, String> filterMap = new HashMap<>(16);
        filterMap.put("currency", "CNY");
        bodyMap.put("filtering", filterMap);
        bodyMap.put("start_date", "2020-05-01");
        bodyMap.put("end_date", "2020-06-20");
        bodyMap.put("time_granularity", "STAT_TIME_GRANULARITY_DAILY");
        bodyMap.put("order_type", "DESC");

        RequestEntity requestEntity = new StringRequestEntity( JSONObject.toJSONString(bodyMap),
                "application/json" ,"UTF-8");
        postMethod.setRequestEntity(requestEntity);

        HttpClient httpClient = new HttpClient();
        int ret = httpClient.executeMethod(postMethod);
        String rpsContent = postMethod.getResponseBodyAsString();
        if (ret == 200) {
            System.out.println(rpsContent);
        } else {
            System.out.println("callApi failed: ret =" + ret + " rsp=" + rpsContent);
        }
    }
}
