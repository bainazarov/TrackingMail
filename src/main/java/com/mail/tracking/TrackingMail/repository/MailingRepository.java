package com.mail.tracking.TrackingMail.repository;


import com.mail.tracking.TrackingMail.model.Mailing;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface MailingRepository {

    void createMailing(Mailing mailing);

    Mailing getMailingById(Long id);

    List<Mailing> getAllMailings();

    void updateMailing(Mailing mailing);

}
