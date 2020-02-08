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
    private final String INSERT = "insert into link (user_dni_senior, user_dni_junior, date, assessment, comment) values(?,?,?,?,?)";
    private final String UPDATE = "update link set user_dni_senior = ?,user_dni_junior = ?,assessment = ?,date = ?,comment = ? where user_dni_senior = ? AND " +
            "user_dni_junior = ?";
    public final String INSERT_NEW_LINK ="insert into link (user_dni_senior, user_dni_junior, assessment, date, comment) values(?,?,NULL,?,NULL)";
    public final String DELETE = "delete from link where user_dni_senior = ? AND user_dni_junior = ? ";



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
    public int insert(Link link){
        return jdbcTemplate.update(INSERT,link.getUser_dni_senior(),link.getUser_dni_junior(),link.getDate(),link.getAssessment(),link.getComment());
    }
    public int insertNew(Link link){
        return jdbcTemplate.update(INSERT_NEW_LINK,link.getUser_dni_senior(),link.getUser_dni_junior(),link.getDate());
    }

    public int update(Link link){
        return jdbcTemplate.update(UPDATE,link.getUser_dni_senior(),link.getUser_dni_junior(),link.getDate(),link.getAssessment(),link.getComment());
    }

    public int delete(Link link){
        return jdbcTemplate.update(DELETE,link);
    }





}