package com.mail.tracking.TrackingMail.repository;

import com.mail.tracking.TrackingMail.model.PostOffice;
import org.springframework.stereotype.Component;



@Component
public interface PostOfficeRepository {

    PostOffice getPostOfficeById(Long id);

    void updatePostOffice(PostOffice postOffice);

}
