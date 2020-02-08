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
    private final String FIND_BY_USER = "select * from activities where users_mail = (?) order by timestampinitial desc";
    private final String INSERT = "insert into activities (timestampinitial, timestampend, content, type, users_mail) values(?,?,?,?,?)";
    private final String UPDATE = "update activities set timestampinitial = ?,timestampend = ?,content = ?, type = ?,users_mail = ? where timestampinitial = ? AND timestampend = ? AND users_mail = ?";
    public final String DELETE = "delete from activities where timestampinitial = ? AND timestampend = ? AND users_mail = ?";



    private final RowMapper<Activity> mapper = (resultSet, i) -> {
        return new Activity.ActivityBuilder()
                .timestampInitial(resultSet.getTimestamp("timestampinitial"))
                .timestampEnd(resultSet.getTimestamp("timestampend"))
                .content(resultSet.getString("content"))
                .type(resultSet.getString("type"))
                .usersMail(resultSet.getString("users_mail"))
                .build();
    };


    public ActivityDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Activity> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Activity.class));
    }

    public List<Activity> findByUser(String mail) {
        return jdbcTemplate.query(FIND_BY_USER, new Object[]{mail}, mapper);
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