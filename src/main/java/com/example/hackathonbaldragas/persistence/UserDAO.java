package com.example.hackathonbaldragas.persistence;

import com.example.hackathonbaldragas.domain.Category;
import com.example.hackathonbaldragas.domain.User;
import com.example.hackathonbaldragas.domain.UserFilter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserDAO {

    private JdbcTemplate jdbcTemplate;

    private final String FIND_ALL = "select * from user";
    private final String INSERT = "insert into user (dni, password, name, surnames, birthday, mail, phone, address, availability, senior) values(?,?,?,?,?,?,?,?,?,?)";
    private final String DELETE = "delete from user where dni = ?";
    private final String UPDATE = "UPDATE user set dni = ?, password = ?, name = ?, surnames = ?, birthday = ?, mail = ?, phone = ?, address = ?, availability = ?, senior = ? where dni = ?";

    //CATEGORIES
    private final String FIND_USER_CATEGORIES = "select name, description from category left join user_category on category.name = user_category.category_name where user_category.user_dni = ?";
    private final String INSERT_USER_CATEGORY = "insert into user_category  (user_dni, category_name) values(?, ?)";
    private final String DELETE_USER_CATEGORY = "delete from user_category  where user_dni = ? and category_name = ?";
    private final String FIND_BY_SENIOR = "select * from user where senior = ?";
    private final String FIND_BY_DNI = "select * from user where dni = ?";
    private final String FIND_BY_MAIL = "select * from user where mail = ?";

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
        List<User> rawUsers = jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(User.class));
        for(User u : rawUsers) u.setCategoriesList(getUserCategories(u.getDni()));
        return rawUsers;
    }

    public List<Category> getUserCategories(String dni) {
        return jdbcTemplate.query(FIND_USER_CATEGORIES, new Object[]{dni}, categoriesMapper);
    }

    public int insert(User user) {
        return jdbcTemplate.update(INSERT, user.getDni(), user.getPassword(), user.getName(), user.getSurnames(),
                Date.valueOf(user.getBirthday()), user.getMail(), user.getPhone(), user.getAddress(), user.getAvailability(), user.getSenior());
    }

    public int update (User user){
        return jdbcTemplate.update(UPDATE, user.getDni(), user.getPassword(), user.getName(), user.getSurnames(),
                Date.valueOf(user.getBirthday()), user.getMail(), user.getPhone(), user.getAddress(), user.getAvailability(), user.getSenior());
    }

    public List<User> findByDni(String dni) {
        return jdbcTemplate.query(FIND_BY_DNI, new Object[]{dni}, mapper);
    }

    public List<User> findByMail(String mail) {
        return jdbcTemplate.query(FIND_BY_MAIL, new Object[]{mail}, mapper);
    }

    public List<User> findByFilter(UserFilter filter){
        List<User> candidates = findAll();
        List<User> result = new ArrayList<User>();
        if(!filter.getAvailability().equals("matins i tardes")){
            for(User user : candidates) if(user.getAvailability().equals(filter.getAvailability()) || user.getAvailability().equals("matins i tardes")) result.add(user);
        }
        else result = findAll();
        List<User> total = new ArrayList<>();
        if(!filter.getCategoryList().isEmpty()){
            for(User user : result) {
                for(Category category : user.getCategoriesList()) {
                    if(filter.getCategoryList().contains(category.getName())) {
                        total.add(user);
                        break;
                    }
                }
            }
        }
        else total.addAll(result);
        return total;
    }

    public List<User> findBySenior(boolean senior){
        return jdbcTemplate.query(FIND_BY_SENIOR,new Object[]{senior},mapper);
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