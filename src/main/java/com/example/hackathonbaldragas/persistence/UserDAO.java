package com.example.hackathonbaldragas.persistence;

import com.example.hackathonbaldragas.domain.Category;
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

    private final String FIND_ALL = "select * from user";
    private final String INSERT = "insert into user (dni, password, name, surnames, birthday, mail, phone, address, availability, senior) values(?,?,?,?,?,?,?,?,?,?)";
    private final String DELETE = "delete from user where dni = ?";

    //CATEGORIES
    private final String FIND_USER_CATEGORIES = "select name, description from category left join user_category on category.name = user_category.category_name where user_category.user_dni = ?";
    private final String INSERT_USER_CATEGORY = "insert into user_category  (user_dni, category_name) values(?, ?)";
    private final String DELETE_USER_CATEGORY = "delete from user_category  where user_dni = ? and category_name = ?";



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
                .categoriesList(getUserCategories(resultSet.getString("dni")))
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
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(User.class));
    }

    public List<Category> getUserCategories(String dni) {
        return jdbcTemplate.query(FIND_USER_CATEGORIES, new Object[]{dni}, categoriesMapper);
    }

    public int insert(User user) {
        return jdbcTemplate.update(INSERT, user.getDni(), user.getPassword(), user.getName(), user.getSurnames(),
                Date.valueOf(user.getBirthday()), user.getMail(), user.getPhone(), user.getAddress(), user.getAvailability(), user.getSenior());
    }

    public int delete(User user)  { return delete(user.getDni()); }
    public int delete(String dni) {
        return jdbcTemplate.update(DELETE, dni);
    }

    public int insertUserCategory(String dni, String categoryName) {
        return jdbcTemplate.update(INSERT_USER_CATEGORY, dni, categoryName);
    }

    public int deleteUserCategory(String dni, String categoryName) {
        return jdbcTemplate.update(DELETE_USER_CATEGORY, dni, categoryName);
    }


}