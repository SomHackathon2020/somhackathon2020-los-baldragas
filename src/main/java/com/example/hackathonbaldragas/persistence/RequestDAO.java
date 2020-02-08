package com.example.hackathonbaldragas.persistence;

import com.example.hackathonbaldragas.domain.Request;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RequestDAO {

    private JdbcTemplate jdbcTemplate;

    private final String FIND_ALL = "select * from request";
    private final String INSERT = "insert into request (user_dni, id, description) values(?,?)"; //falta usercateogry(?)



    private final RowMapper<Request> mapper = (resultSet, i) -> {
        return new Request.RequestBuilder()
                .user_dni(resultSet.getString("user_dni"))
                .id(resultSet.getString("id"))
                .description(resultSet.getString("description"))
                .build();
    };


    public RequestDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Request> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Request.class));
    }



}