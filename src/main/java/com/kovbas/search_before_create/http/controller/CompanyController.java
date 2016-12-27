package com.kovbas.search_before_create.http.controller;

import com.kovbas.search_before_create.entity.Company;
import com.kovbas.search_before_create.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value="/companies", method= RequestMethod.GET)
    List<Company> findCompanies(@RequestParam(value="name", required=false) String name) {

        return companyService.findCompanyByName(name);
    }
}
