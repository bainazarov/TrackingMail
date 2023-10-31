package com.mail.tracking.TrackingMail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailingHistory {

    private Long id;
    private Mailing mailing;
    private String status;
    private LocalDateTime date;
    private PostOffice postOffice;
}
