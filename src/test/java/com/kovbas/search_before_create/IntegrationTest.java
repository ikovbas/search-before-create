package com.kovbas.search_before_create;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class IntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    /**
     * Returns the TestRestTemplate instance
     * @return
     */
    public TestRestTemplate getTestRestTemplate() {
        return testRestTemplate;
    }
}
