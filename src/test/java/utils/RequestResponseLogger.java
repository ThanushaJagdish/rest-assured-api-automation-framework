package utils;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.ExtentReporter; // 👈 IMPORTANT

public class RequestResponseLogger implements Filter {

    private static final Logger log = LogManager.getLogger(RequestResponseLogger.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        //REQUEST
        String requestLog = "REQUEST:\n" +
                "URI: " + requestSpec.getURI() + "\n" +
                "Method: " + requestSpec.getMethod() + "\n" +
                "Headers: " + requestSpec.getHeaders() + "\n" +
                "Body: " + requestSpec.getBody();

        log.info(requestLog);
        ExtentReporter.logToExtent(
        	    "<details><summary><b>REQUEST</b></summary>" +
        	    requestLog.replace("\n", "<br>") +
        	    "</details>"
        	);

        //EXECUTE
        Response response = ctx.next(requestSpec, responseSpec);

        //RESPONSE
        String responseLog = "RESPONSE:\n" +
                "Status Code: " + response.getStatusCode() + "\n" +
                "Body: " + response.getBody().asPrettyString();

        log.info(responseLog);
        ExtentReporter.logToExtent("<b>RESPONSE:</b><br>" + responseLog.replace("\n", "<br>"));

        return response;
    }
}