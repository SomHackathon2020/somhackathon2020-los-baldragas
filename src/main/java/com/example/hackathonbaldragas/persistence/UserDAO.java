package com.example.hackathonbaldragas.persistence;

import com.example.hackathonbaldragas.domain.Category;
import com.example.hackathonbaldragas.domain.User;
import com.example.hackathonbaldragas.domain.UserFilter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserDAO {

    private JdbcTemplate jdbcTemplate;

    private final String FIND_ALL = "select * from users";
    private final String FIND = "SELECT * FROM users WHERE mail = ?";
    private final String FIND_BY_NAME = "SELECT * FROM users WHERE name LIKE ";


    private final RowMapper<User> mapper = (resultSet, i) -> {
        return new User.UserBuilder()
                .mail(resultSet.getString("mail"))
                .password(resultSet.getString("password"))
                .name(resultSet.getString("name"))
                .surnames(resultSet.getString("surnames"))
                .birthday(resultSet.getDate("birthday").toLocalDate())
                .phone(resultSet.getString("phone"))
                .address(resultSet.getString("address"))
                .type(resultSet.getString("type"))
                .height(resultSet.getDouble("height"))
                .weight(resultSet.getDouble("weight"))
                .build();
    };

    private final RowMapper<Category> categoriesMapper = (resultSet, i) -> {
        return new Category.CategoryBuilder()
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .build();
    };

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        List<User> rawUsers = jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(User.class));
        //for(User u : rawUsers) u.setCategoriesList(getUserCategories(u.getDni()));
        return rawUsers;
    }

    public User findUser(String userMail){
        return jdbcTemplate.queryForObject(FIND, new Object[]{userMail}, mapper);
    }


    public List<User> findByName(String name){
        System.out.println("Name = " + name.getClass());
        if(name.equals("") || name == null){
            return findAll();
        }

        return jdbcTemplate.query(FIND_BY_NAME + "'%" + name + "%'", mapper);
    }
}