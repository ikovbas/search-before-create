package com.kovbas.search_before_create.service;

import com.kovbas.search_before_create.entity.Company;
import com.kovbas.search_before_create.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> findCompanyByName(String name) {
        name = name == null ? "" : name;
        return companyRepository.findByNameLikeIgnoreCase(name);
    }
}
