/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.esb.scenario.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.wso2.carbon.esb.scenario.test.common.ScenarioConstants;
import org.wso2.carbon.esb.scenario.test.common.ScenarioTestBase;
import org.wso2.carbon.esb.scenario.test.common.http.HttpConstants;
import org.wso2.esb.integration.common.utils.clients.SimpleHttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonToSoapUsingPayloadFactoryTest extends ScenarioTestBase {

    private static final Log log = LogFactory.getLog(JsonToSoapUsingPayloadFactoryTest.class);

    private String apiName = "1_3_2_json_to_soap_using_payload_factory";
    private String apiInvocationUrl;

    @BeforeClass(alwaysRun = true)
    public void init() throws Exception {
        super.init();
        apiInvocationUrl = getApiInvocationURLHttp(apiName);
        log.info("apiInvocationUrl is set as : " + apiInvocationUrl);
    }

    @Test(description = "1.3.2.1-Valid JSON to SOAP transformation", enabled = true, dataProvider = "1.3.2.1")
    public void validJsonToSoapTransformation(String request, String expectedResponse, String header) throws IOException {

        SimpleHttpClient httpClient = new SimpleHttpClient();

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(ScenarioConstants.MESSAGE_ID, header);
        HttpResponse httpResponse = httpClient.doPost(apiInvocationUrl, headers, request, HttpConstants.MEDIA_TYPE_APPLICATION_JSON);
        String responsePayload = httpClient.getResponsePayload(httpResponse);

        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 200, "JSON to SOAP transformation failed");

        JSONObject jsonExpectedResponse = new JSONObject(expectedResponse);
        JSONObject jsonActualResponse = new JSONObject(responsePayload.trim());

        String expectedString = jsonExpectedResponse.toString();
        String actualString = jsonActualResponse.toString();

        Assert.assertEquals(expectedString, actualString);
    }

    @AfterClass(description = "Server Cleanup", alwaysRun = true)
    public void cleanup() throws Exception {

    }

    @DataProvider(name = "1.3.2.1")
    public Iterator<Object[]> jsonToSoap_1_3_2_1() throws Exception {
        String testCase = "1.3.2.1";
        return getRequestResponseHeaderList(testCase).iterator();
    }

}
