package com.kovbas.search_before_create.repository;

import com.kovbas.search_before_create.entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    List<Company> findByNameLikeIgnoreCase(String name);
}
