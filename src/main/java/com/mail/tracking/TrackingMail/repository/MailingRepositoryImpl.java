package com.mail.tracking.TrackingMail.repository;

import com.mail.tracking.TrackingMail.model.Mailing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MailingRepositoryImpl implements MailingRepository {

    private final JdbcTemplate jdbcTemplate;

    public MailingRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createMailing(Mailing mailing) {
        String sql = "INSERT INTO mailing (type, recipient_index, recipient_address, recipient_name, status) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, mailing.getType(), mailing.getRecipientIndex(), mailing.getRecipientAddress(),
                mailing.getRecipientName(), mailing.getStatus());
    }

    @Override
    public Mailing getMailingById(Long id) {
        String sql = "SELECT * FROM mailing WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new MailingRowMapper(), id);
    }

    @Override
    public List<Mailing> getAllMailings() {
        String sql = "SELECT * FROM mailing";
        return jdbcTemplate.query(sql, new MailingRowMapper());
    }

    @Override
    public void updateMailing(Mailing mailing) {
        String sql = "UPDATE mailing SET type = ?, recipient_index = ?, recipient_address = ?, " +
                "recipient_name = ?, status = ? WHERE id = ?";
        jdbcTemplate.update(sql, mailing.getType(), mailing.getRecipientIndex(), mailing.getRecipientAddress(),
                mailing.getRecipientName(), mailing.getStatus(), mailing.getId());
    }


    private static class MailingRowMapper implements RowMapper<Mailing> {
        public Mailing mapRow(ResultSet rs, int rowNum) throws SQLException {
            Mailing mailing = new Mailing();
            mailing.setId(rs.getLong("id"));
            mailing.setType(rs.getString("type"));
            mailing.setRecipientIndex(rs.getString("recipient_index"));
            mailing.setRecipientAddress(rs.getString("recipient_address"));
            mailing.setRecipientName(rs.getString("recipient_name"));
            mailing.setStatus(rs.getString("status"));
            return mailing;
        }
    }
}
