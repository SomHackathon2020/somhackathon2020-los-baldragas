package com.example.hackathonbaldragas.persistence;

import com.example.hackathonbaldragas.domain.Link;
import com.example.hackathonbaldragas.domain.Request;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class LinkDAO {

    private JdbcTemplate jdbcTemplate;

    private final String FIND_ALL = "select * from link";
    private final String INSERT = "insert into link (user_dni_senior, user_dni_junior, assessment, date, comment) values(?,?,?,?,?)";


    private final RowMapper<Link> mapper = (resultSet, i) -> {
        return new Link.LinkBuilder()
                .user_dni_senior(resultSet.getString("user_dni_senior"))
                .user_dni_junior(resultSet.getString("user_dni_junior"))
                .assessment(resultSet.getString("assessment"))
                .date(resultSet.getDate("date").toLocalDate())
                .comment(resultSet.getString("comment"))
                .build();
    };


    public LinkDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Link> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Link.class));
    }



}