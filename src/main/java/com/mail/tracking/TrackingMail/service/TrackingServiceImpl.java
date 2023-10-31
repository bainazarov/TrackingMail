package com.mail.tracking.TrackingMail.service;

import com.mail.tracking.TrackingMail.model.Mailing;
import com.mail.tracking.TrackingMail.model.MailingHistory;
import com.mail.tracking.TrackingMail.model.PostOffice;
import com.mail.tracking.TrackingMail.repository.MailingHistoryRepository;
import com.mail.tracking.TrackingMail.repository.MailingRepository;
import com.mail.tracking.TrackingMail.repository.PostOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrackingServiceImpl implements TrackingService {
    private final MailingRepository mailingRepository;
    private final PostOfficeRepository postOfficeRepository;
    private final MailingHistoryRepository mailingHistoryRepository;

    @Autowired
    public TrackingServiceImpl(MailingRepository mailingRepository, PostOfficeRepository postOfficeRepository,
                               MailingHistoryRepository mailingHistoryRepository) {
        this.mailingRepository = mailingRepository;
        this.postOfficeRepository = postOfficeRepository;
        this.mailingHistoryRepository = mailingHistoryRepository;
    }

    @Override
    public void registerMailing(Mailing mailing) {
        mailingRepository.createMailing(mailing);

    }

    @Override
    public void arriveMailing(Long mailingId, Long postOfficeId) {
        Mailing mailing = mailingRepository.getMailingById(mailingId);
        mailing.setStatus("Arrived");
        mailingRepository.updateMailing(mailing);

        PostOffice postOffice = postOfficeRepository.getPostOfficeById(postOfficeId);
        postOfficeRepository.updatePostOffice(postOffice);

        MailingHistory history = new MailingHistory();
        history.setMailing(mailing);
        history.setStatus("Arrived");
        history.setDate(LocalDateTime.now());
        history.setPostOffice(postOffice);
        mailingHistoryRepository.createMailingHistory(history);
    }

    @Override
    public void departMailing(Long mailingId, Long postOfficeId) {
        Mailing mailing = mailingRepository.getMailingById(mailingId);
        mailing.setStatus("Departed");
        mailingRepository.updateMailing(mailing);

        PostOffice postOffice = postOfficeRepository.getPostOfficeById(postOfficeId);
        postOfficeRepository.updatePostOffice(postOffice);

        MailingHistory history = new MailingHistory();
        history.setMailing(mailing);
        history.setStatus("Departed");
        history.setDate(LocalDateTime.now());
        history.setPostOffice(postOffice);
        mailingHistoryRepository.createMailingHistory(history);
    }

    @Override
    public void receiveMailing(Long mailingId) {
        Mailing mailing = mailingRepository.getMailingById(mailingId);
        mailing.setStatus("Received");
        mailingRepository.updateMailing(mailing);

        MailingHistory history = new MailingHistory();
        history.setMailing(mailing);
        history.setStatus("Received");
        history.setDate(LocalDateTime.now());
        mailingHistoryRepository.receivedMailingHistory(history);
    }

    @Override
    public List<MailingHistory> getMailingHistoryByMailingId(Long mailingId) {
        return mailingHistoryRepository.getMailingHistoryByMailingId(mailingId);
    }

}