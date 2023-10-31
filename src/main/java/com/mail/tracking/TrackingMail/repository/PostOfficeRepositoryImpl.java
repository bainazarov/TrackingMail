package com.mail.tracking.TrackingMail.repository;
import com.mail.tracking.TrackingMail.model.PostOffice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class PostOfficeRepositoryImpl implements PostOfficeRepository {

    private final JdbcTemplate jdbcTemplate;

    public PostOfficeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PostOffice getPostOfficeById(Long id) {
        String sql = "SELECT * FROM post_office WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PostOfficeRowMapper(), id);
    }

    @Override
    public void updatePostOffice(PostOffice postOffice) {
        String sql = "UPDATE post_office SET index = ?, name = ?, address = ? WHERE id = ?";
        jdbcTemplate.update(sql, postOffice.getIndex(), postOffice.getName(), postOffice.getAddress(), postOffice.getId());
    }

    private static class PostOfficeRowMapper implements RowMapper<PostOffice> {
        public PostOffice mapRow(ResultSet rs, int rowNum) throws SQLException {
            PostOffice postOffice = new PostOffice();
            postOffice.setId(rs.getLong("id"));
            postOffice.setIndex(rs.getString("index"));
            postOffice.setName(rs.getString("name"));
            postOffice.setAddress(rs.getString("address"));
            return postOffice;
        }
    }
}
