package com.example.hackathonbaldragas.persistence;

import com.example.hackathonbaldragas.domain.Category;
import com.example.hackathonbaldragas.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class CategoryDAO {

    private JdbcTemplate jdbcTemplate;

    private final String FIND_ALL = "select * from category";
    private final String INSERT = "insert into category (name, description) values(?,?)";



    private final RowMapper<Category> mapper = (resultSet, i) -> {
        return new Category.CategoryBuilder()
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .build();
    };


    public CategoryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Category> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Category.class));
    }



}