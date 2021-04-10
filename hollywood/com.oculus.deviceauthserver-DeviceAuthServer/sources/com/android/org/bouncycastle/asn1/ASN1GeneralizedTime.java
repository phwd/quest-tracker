package com.android.org.bouncycastle.asn1;

import com.android.org.bouncycastle.util.Arrays;
import com.android.org.bouncycastle.util.Strings;
import com.android.volley.toolbox.ImageRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class ASN1GeneralizedTime extends ASN1Primitive {
    protected byte[] time;

    public static ASN1GeneralizedTime getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1GeneralizedTime)) {
            return (ASN1GeneralizedTime) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1GeneralizedTime) fromByteArray((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1GeneralizedTime getInstance(ASN1TaggedObject obj, boolean explicit) {
        ASN1Primitive o = obj.getObject();
        if (explicit || (o instanceof ASN1GeneralizedTime)) {
            return getInstance(o);
        }
        return new ASN1GeneralizedTime(((ASN1OctetString) o).getOctets());
    }

    public ASN1GeneralizedTime(String time2) {
        this.time = Strings.toByteArray(time2);
        try {
            getDate();
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid date string: " + e.getMessage());
        }
    }

    public ASN1GeneralizedTime(Date time2) {
        SimpleDateFormat dateF = new SimpleDateFormat("yyyyMMddHHmmss'Z'", Locale.US);
        dateF.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(dateF.format(time2));
    }

    public ASN1GeneralizedTime(Date time2, Locale locale) {
        SimpleDateFormat dateF = new SimpleDateFormat("yyyyMMddHHmmss'Z'", Locale.US);
        dateF.setCalendar(Calendar.getInstance(Locale.US));
        dateF.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(dateF.format(time2));
    }

    ASN1GeneralizedTime(byte[] bytes) {
        this.time = bytes;
    }

    public String getTimeString() {
        return Strings.fromByteArray(this.time);
    }

    public String getTime() {
        String stime = Strings.fromByteArray(this.time);
        if (stime.charAt(stime.length() - 1) == 'Z') {
            return stime.substring(0, stime.length() - 1) + "GMT+00:00";
        }
        int signPos = stime.length() - 5;
        char sign = stime.charAt(signPos);
        if (sign == '-' || sign == '+') {
            return stime.substring(0, signPos) + "GMT" + stime.substring(signPos, signPos + 3) + ":" + stime.substring(signPos + 3);
        }
        int signPos2 = stime.length() - 3;
        char sign2 = stime.charAt(signPos2);
        if (sign2 == '-' || sign2 == '+') {
            return stime.substring(0, signPos2) + "GMT" + stime.substring(signPos2) + ":00";
        }
        return stime + calculateGMTOffset();
    }

    private String calculateGMTOffset() {
        String sign = "+";
        TimeZone timeZone = TimeZone.getDefault();
        int offset = timeZone.getRawOffset();
        if (offset < 0) {
            sign = "-";
            offset = -offset;
        }
        int hours = offset / 3600000;
        int minutes = (offset - (((hours * 60) * 60) * ImageRequest.DEFAULT_IMAGE_TIMEOUT_MS)) / 60000;
        try {
            if (timeZone.useDaylightTime() && timeZone.inDaylightTime(getDate())) {
                hours += sign.equals("+") ? 1 : -1;
            }
        } catch (ParseException e) {
        }
        return "GMT" + sign + convert(hours) + ":" + convert(minutes);
    }

    private String convert(int time2) {
        if (time2 >= 10) {
            return Integer.toString(time2);
        }
        return "0" + time2;
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat dateF;
        String stime = Strings.fromByteArray(this.time);
        String d = stime;
        if (stime.endsWith("Z")) {
            if (hasFractionalSeconds()) {
                dateF = new SimpleDateFormat("yyyyMMddHHmmss.SSS'Z'", Locale.US);
            } else if (hasSeconds()) {
                dateF = new SimpleDateFormat("yyyyMMddHHmmss'Z'", Locale.US);
            } else if (hasMinutes()) {
                dateF = new SimpleDateFormat("yyyyMMddHHmm'Z'", Locale.US);
            } else {
                dateF = new SimpleDateFormat("yyyyMMddHH'Z'", Locale.US);
            }
            dateF.setTimeZone(new SimpleTimeZone(0, "Z"));
        } else if (stime.indexOf(45) > 0 || stime.indexOf(43) > 0) {
            d = getTime();
            if (hasFractionalSeconds()) {
                dateF = new SimpleDateFormat("yyyyMMddHHmmss.SSSz", Locale.US);
            } else if (hasSeconds()) {
                dateF = new SimpleDateFormat("yyyyMMddHHmmssz", Locale.US);
            } else if (hasMinutes()) {
                dateF = new SimpleDateFormat("yyyyMMddHHmmz", Locale.US);
            } else {
                dateF = new SimpleDateFormat("yyyyMMddHHz", Locale.US);
            }
            dateF.setTimeZone(new SimpleTimeZone(0, "Z"));
        } else {
            if (hasFractionalSeconds()) {
                dateF = new SimpleDateFormat("yyyyMMddHHmmss.SSS", Locale.US);
            } else if (hasSeconds()) {
                dateF = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
            } else if (hasMinutes()) {
                dateF = new SimpleDateFormat("yyyyMMddHHmm", Locale.US);
            } else {
                dateF = new SimpleDateFormat("yyyyMMddHH", Locale.US);
            }
            dateF.setTimeZone(new SimpleTimeZone(0, TimeZone.getDefault().getID()));
        }
        if (hasFractionalSeconds()) {
            String frac = d.substring(14);
            int index = 1;
            while (index < frac.length() && '0' <= (ch = frac.charAt(index)) && ch <= '9') {
                index++;
            }
            if (index - 1 > 3) {
                d = d.substring(0, 14) + (frac.substring(0, 4) + frac.substring(index));
            } else if (index - 1 == 1) {
                d = d.substring(0, 14) + (frac.substring(0, index) + "00" + frac.substring(index));
            } else if (index - 1 == 2) {
                d = d.substring(0, 14) + (frac.substring(0, index) + "0" + frac.substring(index));
            }
        }
        return DateUtil.epochAdjust(dateF.parse(d));
    }

    /* access modifiers changed from: protected */
    public boolean hasFractionalSeconds() {
        int i = 0;
        while (true) {
            byte[] bArr = this.time;
            if (i == bArr.length) {
                return false;
            }
            if (bArr[i] == 46 && i == 14) {
                return true;
            }
            i++;
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasSeconds() {
        return isDigit(12) && isDigit(13);
    }

    /* access modifiers changed from: protected */
    public boolean hasMinutes() {
        return isDigit(10) && isDigit(11);
    }

    private boolean isDigit(int pos) {
        byte[] bArr = this.time;
        return bArr.length > pos && bArr[pos] >= 48 && bArr[pos] <= 57;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength() {
        int length = this.time.length;
        return StreamUtil.calculateBodyLength(length) + 1 + length;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream out) throws IOException {
        out.writeEncoded(24, this.time);
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        return new DERGeneralizedTime(this.time);
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive o) {
        if (!(o instanceof ASN1GeneralizedTime)) {
            return false;
        }
        return Arrays.areEqual(this.time, ((ASN1GeneralizedTime) o).time);
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Object, com.android.org.bouncycastle.asn1.ASN1Primitive
    public int hashCode() {
        return Arrays.hashCode(this.time);
    }
}
