package com.mail.tracking.TrackingMail.controller;

import com.mail.tracking.TrackingMail.model.Mailing;
import com.mail.tracking.TrackingMail.model.MailingHistory;
import com.mail.tracking.TrackingMail.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mailings")
public class TrackingController {
    private final TrackingService trackingService;

    @Autowired
    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @PostMapping
    public ResponseEntity<Void> createMailing(@RequestBody Mailing mailing) {
        trackingService.registerMailing(mailing);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{mailingId}/arrival/{postOfficeId}")
    public ResponseEntity<Void> arriveMailing(@PathVariable Long mailingId, @PathVariable Long postOfficeId) {
        trackingService.arriveMailing(mailingId, postOfficeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{mailingId}/departure/{postOfficeId}")
    public ResponseEntity<Void> departMailing(@PathVariable Long mailingId, @PathVariable Long postOfficeId) {
        trackingService.departMailing(mailingId, postOfficeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{mailingId}/receive")
    public ResponseEntity<Void> receiveMailing(@PathVariable Long mailingId) {
        trackingService.receiveMailing(mailingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{mailingId}/history")
    public ResponseEntity<List<MailingHistory>> getMailingHistory(@PathVariable Long mailingId) {
        List<MailingHistory> history = trackingService.getMailingHistoryByMailingId(mailingId);
        if (!history.isEmpty()) {
            return new ResponseEntity<>(history, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
