/*
        Written & Developed by KAJAL Kiran
*/

package core.api;

import io.restassured.response.Response;

public class RequestHandler extends ApiConfigSetup<RequestHandler> {

    private static Response response;
    private static Object parameter;
    private static int statusCode;
    private static Long timeDuration;

    public RequestHandler(){
        super(RequestHandler.class);
    }

    /*
     * This method will Get/Retrieve request based on header,query and path params
     */

    public Response returnGetApiResp() {

                System.out.println("Hitting api");
                try {

                        response = requestSpecification.when()
                        .get()
                        .then()
                        .extract()
                        .response();
            }

         catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public Response returnGetApiRespforNC() {

        System.out.println("Hitting api");
        try {

            response = requestSpecification.when()
                    .get()
                    .then()
                    .statusCode(204)
                    .extract()
                    .response();
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


    /*
     * This method will Get/Retrieve request based on header,query and path params
     */

    public int returnGetApiRespCode() {

        System.out.println("Hitting api");
        try {
            statusCode = requestSpecification.when()
                    .get()
                    .getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return statusCode;
    }

    /*
     * This method will Get/Retrieve, response time duration of a Get calls
     */

    public long returnGetApiRespTiming() {

        System.out.println("Hitting api");
        try {
            timeDuration = requestSpecification.when()
                    .get()
                    .getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return timeDuration;
    }

    /*
     * This method will Post/Create request based on header,query and path params and
     * payload of request
     */

    public Response returnPostApiResp() {
        System.out.println("Hitting api");
        try {
            response = requestSpecification.relaxedHTTPSValidation().when()
                    .post()
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    /*
     * This method will Post/Create request based on header,query and path params and
     * payload of request
     */

    public int returnPostApiRespCode() {

        System.out.println("Hitting api");
        try {
            statusCode = requestSpecification.when()
                    .post()
                    .getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return statusCode;
    }

    /*
     * This method will Put/Modify request based on header,query and path params and
     * payload of request
     */

    public Response returnPutApiResp() {
        System.out.println("Hitting api");
        try {
            response = requestSpecification.relaxedHTTPSValidation().when()
                    .put()
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /*
     * This method will Put/modify request based on header,query and path params and
     * payload of request
     */

    public int returnPutApiRespCode() {
        System.out.println("Hitting api");
        try {
            statusCode = requestSpecification.when()
                    .put()
                    .getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusCode;
    }

    /*
     * This method will Delete request based on header,query and path params and
     * payload of request
     */

    public Response returnDeleteApiResp() {
        System.out.println("Hitting api");
        try {
            response = requestSpecification.relaxedHTTPSValidation().when()
                    .delete()
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /*
     * This method will Delete request based on header,query and path params and
     * payload of request
     */

    public int returnDeleteApiRespCode() {
        System.out.println("Hitting api");
        try {
            statusCode = requestSpecification.when()
                    .put()
                    .getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusCode;
    }

    /*
     * This method will return the parameter of response based on extracted response param
     * payload of request
     */

    public Object returnParamsFromResp(String extractedPath) {

        System.out.println("Hitting api");
        try {
            parameter = requestSpecification.when()
                    .post()
                    .then()
                    .extract()
                    .path(extractedPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parameter;
    }
}