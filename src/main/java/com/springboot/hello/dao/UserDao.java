package com.springboot.hello.dao;

import com.springboot.hello.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<User> rowMapper = (rs, rowNum) -> {
       return new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
    };

    public int add(User user) {
        return this.jdbcTemplate.update("INSERT INTO users(id, name, password) values(?, ?, ?)",
            user.getId(), user.getName(), user.getPassword());
    }

    public User findById(String id) {
        String sql = "select id, name, password from users where id =? ";
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int deleteAll() {
        return this.jdbcTemplate.update("delete from users");
    }


}
