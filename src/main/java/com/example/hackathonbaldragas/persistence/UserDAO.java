package com.example.hackathonbaldragas.persistence;

import com.example.hackathonbaldragas.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserDAO {

    private JdbcTemplate jdbcTemplate;

    private final String FIND_ALL = "select * from \"USER\"";
    private final String INSERT = "insert into \"USER\" (dni, password, name, surnames, birthday, mail, phone, address, availability, senior) values(?,?,?,?,?,?,?,?,?,?)"; //falta usercateogry(?)



    private final RowMapper<User> mapper = (resultSet, i) -> {
        return new User.UserBuilder()
                .dni(resultSet.getString("dni"))
                .password(resultSet.getString("password"))
                .name(resultSet.getString("name"))
                .surnames(resultSet.getString("surnames"))
                .birthday(resultSet.getDate("birthday").toLocalDate())
                .mail(resultSet.getString("mail"))
                .phone(resultSet.getString("phone"))
                .address(resultSet.getString("address"))
                .availability(resultSet.getString("availability"))
                .senior(resultSet.getBoolean("senior"))
                //.likesGender(getUserLikes(resultSet.getString("username")))
                .build();
    };

    /*private final RowMapper<Integer> likesMapper = (resultSet, i) -> {
        return resultSet.getInt("gender_id");
    };

    private final RowMapper<String> interestsMapper = (resultSet, i) -> {
        return resultSet.getString("category_name");
    };*/

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(User.class));
    }

    //Falta el tema de user_category
    public int insert(User user) {
        return jdbcTemplate.update(INSERT, user.getDni(), user.getPassword(), user.getName(), user.getSurnames(),
                Date.valueOf(user.getBirthday()), user.getMail(), user.getPhone(), user.getAddress(), user.getAvailability(), user.getSenior());
    }



}