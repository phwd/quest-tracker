package com.adobe.xmp.impl;

import com.adobe.xmp.XMPDateTime;
import com.adobe.xmp.XMPException;
import com.oculus.panellib.Systrace;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class XMPDateTimeImpl implements XMPDateTime {
    public int day = 0;
    public int hour = 0;
    public int minute = 0;
    public int month = 0;
    public int nanoSeconds;
    public int second = 0;
    public TimeZone timeZone = TimeZone.getTimeZone("UTC");
    public int year = 0;

    @Override // com.adobe.xmp.XMPDateTime
    public void setDay(int i) {
        int i2 = 1;
        if (i >= 1) {
            i2 = 31;
            if (i <= 31) {
                this.day = i;
                return;
            }
        }
        this.day = i2;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public void setMonth(int i) {
        int i2 = 1;
        if (i >= 1) {
            i2 = 12;
            if (i <= 12) {
                this.month = i;
                return;
            }
        }
        this.month = i2;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public Calendar getCalendar() {
        GregorianCalendar gregorianCalendar = (GregorianCalendar) Calendar.getInstance(Locale.US);
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        gregorianCalendar.setTimeZone(this.timeZone);
        gregorianCalendar.set(1, this.year);
        gregorianCalendar.set(2, this.month - 1);
        gregorianCalendar.set(5, this.day);
        gregorianCalendar.set(11, this.hour);
        gregorianCalendar.set(12, this.minute);
        gregorianCalendar.set(13, this.second);
        gregorianCalendar.set(14, this.nanoSeconds / Systrace.START_EVENT_ID);
        return gregorianCalendar;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        XMPDateTime xMPDateTime = (XMPDateTime) obj;
        long timeInMillis = getCalendar().getTimeInMillis() - xMPDateTime.getCalendar().getTimeInMillis();
        if (timeInMillis == 0) {
            timeInMillis = (long) (this.nanoSeconds - xMPDateTime.getNanoSecond());
        }
        return (int) (timeInMillis % 2);
    }

    @Override // com.adobe.xmp.XMPDateTime
    public int getDay() {
        return this.day;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public int getHour() {
        return this.hour;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public String getISO8601String() {
        return ISO8601Converter.render(this);
    }

    @Override // com.adobe.xmp.XMPDateTime
    public int getMinute() {
        return this.minute;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public int getMonth() {
        return this.month;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public int getNanoSecond() {
        return this.nanoSeconds;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public int getSecond() {
        return this.second;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public int getYear() {
        return this.year;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public void setHour(int i) {
        this.hour = Math.min(Math.abs(i), 23);
    }

    @Override // com.adobe.xmp.XMPDateTime
    public void setMinute(int i) {
        this.minute = Math.min(Math.abs(i), 59);
    }

    @Override // com.adobe.xmp.XMPDateTime
    public void setSecond(int i) {
        this.second = Math.min(Math.abs(i), 59);
    }

    @Override // com.adobe.xmp.XMPDateTime
    public void setYear(int i) {
        this.year = Math.min(Math.abs(i), 9999);
    }

    public String toString() {
        return getISO8601String();
    }

    @Override // com.adobe.xmp.XMPDateTime
    public void setNanoSecond(int i) {
        this.nanoSeconds = i;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public void setTimeZone(TimeZone timeZone2) {
        this.timeZone = timeZone2;
    }

    public XMPDateTimeImpl() {
    }

    public XMPDateTimeImpl(String str) throws XMPException {
        ISO8601Converter.parse(str, this);
    }

    public XMPDateTimeImpl(Calendar calendar) {
        Date time = calendar.getTime();
        TimeZone timeZone2 = calendar.getTimeZone();
        GregorianCalendar gregorianCalendar = (GregorianCalendar) Calendar.getInstance(Locale.US);
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        gregorianCalendar.setTimeZone(timeZone2);
        gregorianCalendar.setTime(time);
        this.year = gregorianCalendar.get(1);
        this.month = gregorianCalendar.get(2) + 1;
        this.day = gregorianCalendar.get(5);
        this.hour = gregorianCalendar.get(11);
        this.minute = gregorianCalendar.get(12);
        this.second = gregorianCalendar.get(13);
        this.nanoSeconds = gregorianCalendar.get(14) * Systrace.START_EVENT_ID;
        this.timeZone = gregorianCalendar.getTimeZone();
    }

    public XMPDateTimeImpl(Date date, TimeZone timeZone2) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone2);
        gregorianCalendar.setTime(date);
        this.year = gregorianCalendar.get(1);
        this.month = gregorianCalendar.get(2) + 1;
        this.day = gregorianCalendar.get(5);
        this.hour = gregorianCalendar.get(11);
        this.minute = gregorianCalendar.get(12);
        this.second = gregorianCalendar.get(13);
        this.nanoSeconds = gregorianCalendar.get(14) * Systrace.START_EVENT_ID;
        this.timeZone = timeZone2;
    }
}
