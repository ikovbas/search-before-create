package com.kovbas.search_before_create;

import com.kovbas.search_before_create.entity.Company;
import com.kovbas.search_before_create.repository.CompanyRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.*;

public class CompanyControllerIntegrationTest extends IntegrationTest {

    @MockBean
    private CompanyRepository companyRepository;

    @Test
    public void testSearchCompaniesByName() {
        final String SEARCH_NAME = "Сompany";
        final int RESULTS_COUNT = 3;

        // Mock Company repository
        given(companyRepository.findByNameLikeIgnoreCase("%" + SEARCH_NAME + "%")).willReturn(Arrays.asList(
                new Company((long)1, "Company 1", "Description of Company 1"),
                new Company((long)2, "Company 2", "Description of Company 2"),
                new Company((long)3, "Company 3", "Description of Company 3")
        ));

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

        // Mock Company repository
        given(companyRepository.findByNameLikeIgnoreCase("%%")).willReturn(Arrays.asList(
                new Company((long)1, "Company 1", "Description of Company 1"),
                new Company((long)2, "Company 2", "Description of Company 2"),
                new Company((long)3, "Company 3", "Description of Company 3")
        ));

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

        // Mock Company repository
        given(companyRepository.findByNameLikeIgnoreCase("%" + SEARCH_NAME + "%")).willReturn(Arrays.asList(
                new Company((long)1, "Company 1", "Description of Company 1")
        ));

        ResponseEntity<List> entity = getTestRestTemplate().getForEntity(
                "/companies?name={name}", List.class, SEARCH_NAME);

        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assert.assertEquals(MediaType.APPLICATION_JSON_UTF8, entity.getHeaders().getContentType());

        // Check if result list contains only one element
        Assert.assertEquals(RESULTS_COUNT, entity.getBody().size());
        Assert.assertEquals(SEARCH_NAME, ((Map)entity.getBody().get(0)).get("name"));
    }
}
