package com.mail.tracking.TrackingMail.service;


import com.mail.tracking.TrackingMail.model.Mailing;
import com.mail.tracking.TrackingMail.model.MailingHistory;

import java.util.List;

public interface TrackingService {
    void registerMailing(Mailing mailing);

    void arriveMailing(Long mailingId, Long postOfficeId);

    void departMailing(Long mailingId, Long postOfficeId);

    void receiveMailing(Long mailingId);

    List<MailingHistory> getMailingHistoryByMailingId(Long mailingId);

}
