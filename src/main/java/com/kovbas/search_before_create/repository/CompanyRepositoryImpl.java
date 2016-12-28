package com.kovbas.search_before_create.repository;

import com.kovbas.search_before_create.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepositoryImpl extends BaseRepositoryImpl<Company, Long> implements CompanyRepository {

    @Override
    public List<Company> findByNameLikeIgnoreCase(String name) {

        return getJdbcTemplate().query(
                "SELECT id, name, description FROM companies WHERE LOWER(name) LIKE LOWER(?)",
                new Object[]{name},
                (rs, rowNum) -> new Company(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description")
                )
        );
    }
}
