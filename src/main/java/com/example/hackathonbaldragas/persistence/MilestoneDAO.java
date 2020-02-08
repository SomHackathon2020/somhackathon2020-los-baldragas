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

    private final RowMapper<Milestone> mapper = (resultSet, i) -> {
        return new Milestone.MilestoneBuilder()
                .initialDate(resultSet.getDate("initialDate").toLocalDate())
                .finalDate(resultSet.getDate("finalDate").toLocalDate())
                .target(resultSet.getFloat("target"))
                .usersMail(resultSet.getString("usersMail"))
                .build();
    };


    public MilestoneDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(Milestone milestone){
        return jdbcTemplate.update(INSERT, milestone.getInitialDate(), milestone.getFinalDate(), milestone.getTarget(), milestone.getUsersMail());
    }

    public List<Milestone> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Milestone.class));
    }

    public Milestone findByStart(LocalDate start, String mail) { return jdbcTemplate.queryForObject(FIND_BY_STARTDATE, new Object[]{start, mail}, mapper);}
    public Milestone findByEnd(LocalDate end, String mail) { return jdbcTemplate.queryForObject(FIND_BY_ENDDATE, new Object[]{end, mail}, mapper);};


}
