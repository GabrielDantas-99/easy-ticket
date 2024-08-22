package com.easyticket.api.util.date;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class DefaultDateProvider implements DateProvider {

    @Override
    public Date getStartDate(Date startDate) {
        return (startDate != null) ? startDate : new Date();
    }

    @Override
    public Date getEndDate(Date endDate) {
        if (endDate == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 10);
            return calendar.getTime();
        }
        return endDate;
    }

}