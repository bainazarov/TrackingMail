package com.mail.tracking.TrackingMail.repository;

import com.mail.tracking.TrackingMail.model.MailingHistory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MailingHistoryRepositoryImpl implements MailingHistoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public MailingHistoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createMailingHistory(MailingHistory history) {
        String sql = "INSERT INTO mailing_history (mailing_id, status, date, post_office) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, history.getMailing().getId(), history.getStatus(), history.getDate(), history.getPostOffice().getId());
    }

    @Override
    public void receivedMailingHistory(MailingHistory history) {
        String sql = "INSERT INTO mailing_history (mailing_id, status, date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, history.getMailing().getId(), history.getStatus(), history.getDate());
    }

    @Override
    public List<MailingHistory> getMailingHistoryByMailingId(Long mailingId) {
        String sql = "SELECT * FROM mailing_history WHERE mailing_id = ? ORDER BY date ASC";
        return jdbcTemplate.query(sql, new MailingHistoryRowMapper(), mailingId);
    }

    private static class MailingHistoryRowMapper implements RowMapper<MailingHistory> {
        public MailingHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
            MailingHistory history = new MailingHistory();
            history.setId(rs.getLong("id"));
            history.setStatus(rs.getString("status"));
            history.setDate(rs.getTimestamp("date").toLocalDateTime());
            return history;
        }
    }
}
