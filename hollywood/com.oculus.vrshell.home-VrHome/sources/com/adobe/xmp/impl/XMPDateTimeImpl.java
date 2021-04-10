package com.adobe.xmp.impl;

import com.adobe.xmp.XMPDateTime;
import com.adobe.xmp.XMPException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class XMPDateTimeImpl implements XMPDateTime {
    private int day = 0;
    private int hour = 0;
    private int minute = 0;
    private int month = 0;
    private int nanoSeconds;
    private int second = 0;
    private TimeZone timeZone = TimeZone.getTimeZone("UTC");
    private int year = 0;

    public XMPDateTimeImpl() {
    }

    public XMPDateTimeImpl(Calendar calendar) {
        Date date = calendar.getTime();
        TimeZone zone = calendar.getTimeZone();
        GregorianCalendar intCalendar = (GregorianCalendar) Calendar.getInstance(Locale.US);
        intCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        intCalendar.setTimeZone(zone);
        intCalendar.setTime(date);
        this.year = intCalendar.get(1);
        this.month = intCalendar.get(2) + 1;
        this.day = intCalendar.get(5);
        this.hour = intCalendar.get(11);
        this.minute = intCalendar.get(12);
        this.second = intCalendar.get(13);
        this.nanoSeconds = intCalendar.get(14) * 1000000;
        this.timeZone = intCalendar.getTimeZone();
    }

    public XMPDateTimeImpl(Date date, TimeZone timeZone2) {
        GregorianCalendar calendar = new GregorianCalendar(timeZone2);
        calendar.setTime(date);
        this.year = calendar.get(1);
        this.month = calendar.get(2) + 1;
        this.day = calendar.get(5);
        this.hour = calendar.get(11);
        this.minute = calendar.get(12);
        this.second = calendar.get(13);
        this.nanoSeconds = calendar.get(14) * 1000000;
        this.timeZone = timeZone2;
    }

    public XMPDateTimeImpl(String strValue) throws XMPException {
        ISO8601Converter.parse(strValue, this);
    }

    @Override // com.adobe.xmp.XMPDateTime
    public int getYear() {
        return this.year;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public void setYear(int year2) {
        this.year = Math.min(Math.abs(year2), 9999);
    }

    @Override // com.adobe.xmp.XMPDateTime
    public int getMonth() {
        return this.month;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public void setMonth(int month2) {
        if (month2 < 1) {
            this.month = 1;
        } else if (month2 > 12) {
            this.month = 12;
        } else {
            this.month = month2;
        }
    }

    @Override // com.adobe.xmp.XMPDateTime
    public int getDay() {
        return this.day;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public void setDay(int day2) {
        if (day2 < 1) {
            this.day = 1;
        } else if (day2 > 31) {
            this.day = 31;
        } else {
            this.day = day2;
        }
    }

    @Override // com.adobe.xmp.XMPDateTime
    public int getHour() {
        return this.hour;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public void setHour(int hour2) {
        this.hour = Math.min(Math.abs(hour2), 23);
    }

    @Override // com.adobe.xmp.XMPDateTime
    public int getMinute() {
        return this.minute;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public void setMinute(int minute2) {
        this.minute = Math.min(Math.abs(minute2), 59);
    }

    @Override // com.adobe.xmp.XMPDateTime
    public int getSecond() {
        return this.second;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public void setSecond(int second2) {
        this.second = Math.min(Math.abs(second2), 59);
    }

    @Override // com.adobe.xmp.XMPDateTime
    public int getNanoSecond() {
        return this.nanoSeconds;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public void setNanoSecond(int nanoSecond) {
        this.nanoSeconds = nanoSecond;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object dt) {
        long d = getCalendar().getTimeInMillis() - ((XMPDateTime) dt).getCalendar().getTimeInMillis();
        if (d != 0) {
            return (int) (d % 2);
        }
        return (int) (((long) (this.nanoSeconds - ((XMPDateTime) dt).getNanoSecond())) % 2);
    }

    @Override // com.adobe.xmp.XMPDateTime
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public void setTimeZone(TimeZone timeZone2) {
        this.timeZone = timeZone2;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public Calendar getCalendar() {
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance(Locale.US);
        calendar.setGregorianChange(new Date(Long.MIN_VALUE));
        calendar.setTimeZone(this.timeZone);
        calendar.set(1, this.year);
        calendar.set(2, this.month - 1);
        calendar.set(5, this.day);
        calendar.set(11, this.hour);
        calendar.set(12, this.minute);
        calendar.set(13, this.second);
        calendar.set(14, this.nanoSeconds / 1000000);
        return calendar;
    }

    @Override // com.adobe.xmp.XMPDateTime
    public String getISO8601String() {
        return ISO8601Converter.render(this);
    }

    public String toString() {
        return getISO8601String();
    }
}
