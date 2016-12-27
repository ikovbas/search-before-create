package com.kovbas.search_before_create;

import com.kovbas.search_before_create.entity.Company;
import com.kovbas.search_before_create.repository.CompanyRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CompanyControllerIntegrationTest extends IntegrationTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup() {
        companyRepository.save(Arrays.asList(
                new Company("Company 1", "Description of Company 1"),
                new Company("Company 2", "Description of Company 2"),
                new Company("Company 3", "Description of Company 3")
        ));
    }

    @After
    public void tearDown() {
        companyRepository.deleteAll();
    }

    @Test
    public void testSearchCompaniesByName() {
        final String SEARCH_NAME = "Company";
        final int RESULTS_COUNT = 3;

        ResponseEntity<List> entity = getTestRestTemplate().getForEntity(
                "/companies?name={name}", List.class, SEARCH_NAME);

        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assert.assertEquals(MediaType.APPLICATION_JSON_UTF8, entity.getHeaders().getContentType());

        // Check if result list contains only one element
        Assert.assertEquals(RESULTS_COUNT, entity.getBody().size());
    }

    @Test
    public void testSearchCompaniesByEmptyName() {
        final int RESULTS_COUNT = 3;

        ResponseEntity<List> entity = getTestRestTemplate().getForEntity(
                "/companies", List.class);

        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assert.assertEquals(MediaType.APPLICATION_JSON_UTF8, entity.getHeaders().getContentType());

        // Check if result list contains only one element
        Assert.assertEquals(RESULTS_COUNT, entity.getBody().size());
    }

    @Test
    public void testSearchCompanyBySpecificName() {
        final String SEARCH_NAME = "Company 1";
        final int RESULTS_COUNT = 1;

        ResponseEntity<List> entity = getTestRestTemplate().getForEntity(
                "/companies?name={name}", List.class, SEARCH_NAME);

        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assert.assertEquals(MediaType.APPLICATION_JSON_UTF8, entity.getHeaders().getContentType());

        // Check if result list contains only one element
        Assert.assertEquals(RESULTS_COUNT, entity.getBody().size());
        Assert.assertEquals(SEARCH_NAME, ((Map)entity.getBody().get(0)).get("name"));
    }
}
