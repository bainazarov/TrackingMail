package com.mail.tracking.TrackingMail.repository;

import com.mail.tracking.TrackingMail.model.MailingHistory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MailingHistoryRepository {
    void createMailingHistory(MailingHistory history);

    void receivedMailingHistory(MailingHistory history);

    List<MailingHistory> getMailingHistoryByMailingId(Long mailingId);
}
