package com.example.hackathonbaldragas.persistence;

import com.example.hackathonbaldragas.domain.Milestone;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MilestoneDAO {

    private JdbcTemplate jdbcTemplate;

    private final String FIND_ALL = "select * from milestones";
    private final String INSERT = "insert into milestones (dateinitial, dateend, target, users_mail) values(?,?,?,?)";
    private final String FIND_BY_STARTDATE = "select * from milestones where dateinitial = ? AND users_mail = ?";
    private final String FIND_BY_ENDDATE = "select * from milestones where dateend = ? AND users_mail = ?";
    private final String FIND_BY_USER = "select * from milestones where users_mail = ?";

    private final RowMapper<Milestone> mapper = (resultSet, i) -> {
        return new Milestone.MilestoneBuilder()
                .dateinitial(resultSet.getDate("dateinitial").toLocalDate())
                .dateend(resultSet.getDate("dateend").toLocalDate())
                .target(resultSet.getFloat("target"))
                .users_mail(resultSet.getString("users_mail"))
                .build();
    };


    public MilestoneDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(Milestone milestone){
        return jdbcTemplate.update(INSERT, milestone.getDateinitial(), milestone.getDateend(), milestone.getTarget(), milestone.getUsers_mail());
    }

    public List<Milestone> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Milestone.class));
    }
    public List<Milestone> findMilestonesByUser(String mail){
        return jdbcTemplate.query(FIND_BY_USER, new BeanPropertyRowMapper<>(Milestone.class), mail);
    }
    public Milestone findByStart(LocalDate start, String mail) { return jdbcTemplate.queryForObject(FIND_BY_STARTDATE, new Object[]{start, mail}, mapper);}
    public Milestone findByEnd(LocalDate end, String mail) { return jdbcTemplate.queryForObject(FIND_BY_ENDDATE, new Object[]{end, mail}, mapper);};


}
