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
    private final String INSERT = "insert into request (user_dni, state, description) values(?,?,?)"; //falta usercateogry(?)
    private final String FIND = "SELECT * FROM request WHERE id = ?";
    private final String CREATOR = "SELECT user_dni FROM request WHERE id = ?";


    private final RowMapper<Request> mapper = (resultSet, i) -> {
        return new Request.RequestBuilder()
                .user_dni(resultSet.getString("user_dni"))
                .id(resultSet.getString("id"))
                .state(resultSet.getString("state"))
                .description(resultSet.getString("description"))
                .build();
    };


    public RequestDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(Request request){
        return jdbcTemplate.update(INSERT, request.getUser_dni(), request.getState(), request.getDescription());
    }

    public List<Request> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Request.class));
    }

    public Request find(int id) { return jdbcTemplate.queryForObject(FIND, new Object[]{id}, mapper);}

    public String getUser(int id) { return jdbcTemplate.queryForObject(CREATOR, new Object[]{id}, String.class);}


}