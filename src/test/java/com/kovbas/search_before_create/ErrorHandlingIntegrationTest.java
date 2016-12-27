package com.kovbas.search_before_create;

import com.kovbas.search_before_create.errorhandling.ErrorMessage;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ErrorHandlingIntegrationTest extends IntegrationTest {

    @Test
    public void testThatNoHandlerFoundExceptionIsHandledAsExpected() {
        ResponseEntity<ErrorMessage> entity = getTestRestTemplate()
                .getForEntity("/some-wrong-endpoint", ErrorMessage.class);

        Assert.assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
        Assert.assertEquals(MediaType.APPLICATION_JSON_UTF8, entity.getHeaders().getContentType());

        // Check if error object contains appropriate fields
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), (int)entity.getBody().getStatus());
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), (int)entity.getBody().getCode());
    }
}
