package com.kovbas.search_before_create.service;

import com.kovbas.search_before_create.entity.Company;

import java.util.List;

public interface CompanyService {

    /**
     * Search companies by the giving name pattern
     *
     * @param name Name of company/companies
     * @return List of companies
     */
    List<Company> findCompanyByName(String name);
}
