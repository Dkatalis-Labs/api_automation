package test;

import activities.constant_details.CompareJsonConstants;
import activities.test_data_helper.CompareJsonDataProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import core.api.APIBase;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static activities.constant_details.CompareJsonConstants.headerDetails;

public class CompareJsonTests {
    Logger logger = Logger.getLogger(CompareJsonTests.class);
    ResponseBody responseObject = null;
    String response1 = null;
    String response2 = null;

    @Test(description = "Verifying and comparing two Json response body is equal (or) not : ",
            dataProvider = "compareJson",
            dataProviderClass = CompareJsonDataProvider.class)
    public void verifyJsonIsEqual(String runMode,
                                  String scenario,
                                  String id,
                                  int expectedResponseCode,
                                  String message) throws IOException {
        if (runMode.equalsIgnoreCase("Y") && scenario.toLowerCase().contains("positive")) {
            if(scenario.contains("User_Id 1")) {
                APIBase apiBase = new APIBase("constant_details.CompareJsonConstants");
                logger.info("Test scenario : " + scenario);

                responseObject = apiBase.getRequest()
                        .setHeader(headerDetails)
                        .setPathParam(CompareJsonConstants.getPathParameter(id))
                        .returnGetApiResp()
                        .getBody();

                logger.info("Response Body : " + responseObject.asString());
                response1 = responseObject.asString();
                logger.info("Response 1 : " +response1);

                int statusCode = ((Response) responseObject).getStatusCode();
                Assert.assertEquals(statusCode, expectedResponseCode, "Assertion Fail ::: Status code is as expected");

                int responseTiming = (int) apiBase.getRequest().returnGetApiRespTiming();

                if (responseTiming <= 3000) {
                    logger.info("API is taking less than 300 millisecond ::: " + responseTiming);
                } else {
                    logger.info("API is taking more than 300 millisecond ::: " + responseTiming);
                }
            }

            else if(scenario.contains("User_Id 2")) {
                APIBase apiBase = new APIBase("constant_details.CompareJsonConstants");
                logger.info("Test scenario : " + scenario);

                responseObject = apiBase.getRequest()
                        .setHeader(headerDetails)
                        .setPathParam(CompareJsonConstants.getPathParameter(id))
                        .returnGetApiResp()
                        .getBody();

                logger.info("Response Body : " + responseObject.asString());
                response2 = responseObject.asString();

                int statusCode = ((Response) responseObject).getStatusCode();
                Assert.assertEquals(statusCode, expectedResponseCode, "Assertion Fail ::: Status code is as expected");

                int responseTiming = (int) apiBase.getRequest().returnGetApiRespTiming();

                if (responseTiming <= 3000) {
                    logger.info("API is taking less than 300 millisecond ::: " + responseTiming);
                } else {
                    logger.info("API is taking more than 300 millisecond ::: " + responseTiming);
                }
            }

        } else {
            logger.info(responseObject.asString());
            Assert.assertTrue(((Response) responseObject).getBody().asString().toLowerCase().contains(message),
                    "Error message is incorrect <<<<" + message + ">>>>");
            logger.info("*************** All Assertion Passed ***************");
        }

        /**
         * Comparing two json response to verify json are equals
         */
        Gson gson = new Gson();
        String responseMap1 = gson.toJson(response1);
        String responseMap2 = gson.toJson(response2);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode tree1 = mapper.readTree(responseMap1);
        JsonNode tree2 = mapper.readTree(responseMap2);

        Assert.assertFalse(tree1.equals(tree2), "Assertion Fail ::: Two json are not equal");
        logger.info("Json compared successfully");

        logger.info("*************** Test Executed Successfully ***************");
    }
}
