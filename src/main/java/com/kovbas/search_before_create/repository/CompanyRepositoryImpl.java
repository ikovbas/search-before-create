package com.kovbas.search_before_create.repository;

import com.kovbas.search_before_create.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepositoryImpl extends BaseRepositoryImpl<Company, Long> implements CompanyRepository {

    @Override
    public List<Company> findByNameLikeIgnoreCase(String name) {

        final String query =
                "WITH t AS (" +
                    "SELECT *, levenshtein(name, ?) AS distance FROM companies " +
                ")" +
                "SELECT * FROM t WHERE distance <= 3 ORDER BY distance LIMIT 10";

        return getJdbcTemplate().query(query, new Object[]{name},
                (rs, rowNum) -> new Company(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description")
                )
        );
    }
}
