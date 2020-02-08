package com.example.hackathonbaldragas.persistence;

import com.example.hackathonbaldragas.domain.Activity;
import com.example.hackathonbaldragas.domain.Link;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityDAO {

    private JdbcTemplate jdbcTemplate;

    private final String FIND_ALL = "select * from activities";
    private final String INSERT = "insert into activities (timestampinitial, timestampend, content, type, usersmail) values(?,?,?,?,?)";
    private final String UPDATE = "update activities set timestampinitial = ?,timestampend = ?,content = ?, type = ?,usersmail = ? where timestampinitial = ? AND timestampend = ? AND usersmail = ?";
    public final String DELETE = "delete from activities where timestampinitial = ? AND timestampend = ? AND usersmail = ?";



    private final RowMapper<Activity> mapper = (resultSet, i) -> {
        return new Activity.ActivityBuilder()
                .timestampInitial(resultSet.getTimestamp("timestampinitial"))
                .timestampEnd(resultSet.getTimestamp("timestampend"))
                .content(resultSet.getString("content"))
                .type(resultSet.getString("type"))
                .usersMail(resultSet.getString("usersmail"))
                .build();
    };


    public ActivityDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Activity> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Activity.class));
    }
    public int insert(Activity activity) {
        return jdbcTemplate.update(INSERT, activity.getTimestampInitial(), activity.getTimestampEnd(), activity.getContent(), activity.getType(), activity.getUsersMail());
    }

    public int update(Activity activity){
        return jdbcTemplate.update(UPDATE,activity.getTimestampInitial(),activity.getTimestampEnd(),activity.getContent(),activity.getType(),activity.getUsersMail(),activity.getTimestampInitial(),activity.getTimestampEnd(),activity.getUsersMail());
    }

    public int delete(Activity activity){
        return jdbcTemplate.update(DELETE,activity.getTimestampInitial(),activity.getTimestampEnd(),activity.getUsersMail());
    }





}