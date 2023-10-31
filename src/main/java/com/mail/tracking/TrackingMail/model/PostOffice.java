package com.mail.tracking.TrackingMail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOffice {

    private Long id;
    private String index;
    private String name;
    private String address;

}
