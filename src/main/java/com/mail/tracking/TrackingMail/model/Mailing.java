package com.mail.tracking.TrackingMail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mailing {

    private Long id;
    private String type;
    private String recipientIndex;
    private String recipientAddress;
    private String recipientName;
    private String status;
    private List<MailingHistory> history;
}
