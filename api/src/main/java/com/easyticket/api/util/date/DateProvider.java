package com.easyticket.api.util.date;

import java.util.Date;

public interface DateProvider {
    Date getStartDate(Date startDate);
    Date getEndDate(Date endDate);
}