package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02580aL;
import X.AbstractC02640aV;
import X.AbstractC06840oE;
import X.AnonymousClass0a8;
import X.AnonymousClass0aG;
import X.C02650aW;
import X.C05750kz;
import X.C07280p4;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DateTimeSerializerBase<T> extends StdScalarSerializer<T> implements AbstractC06840oE {
    public final DateFormat A00;
    public final boolean A01;

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public abstract void A0D(T t, AbstractC02640aV v, AnonymousClass0a8 v2) throws IOException, C02650aW;

    public abstract long A0E(T t);

    public abstract DateTimeSerializerBase<T> A0F(boolean z, DateFormat dateFormat);

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(T t) {
        if (t == null || A0E(t) == 0) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC06840oE
    public final JsonSerializer<?> A1x(AnonymousClass0a8 r6, AbstractC02580aL r7) throws AnonymousClass0aG {
        C05750kz A012;
        Object clone;
        if (!(r7 == null || (A012 = r6._config.A01().A01(r7.A41())) == null)) {
            if (A012.A00.isNumeric()) {
                return A0F(true, null);
            }
            TimeZone timeZone = A012.A03;
            String str = A012.A01;
            if (str.length() > 0) {
                Locale locale = A012.A02;
                if (locale == null) {
                    locale = r6._config._base._locale;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, locale);
                if (timeZone == null) {
                    timeZone = r6._config._base._timeZone;
                }
                simpleDateFormat.setTimeZone(timeZone);
                return A0F(false, simpleDateFormat);
            } else if (timeZone != null) {
                DateFormat dateFormat = r6._config._base._dateFormat;
                if (dateFormat.getClass() == C07280p4.class) {
                    clone = C07280p4.A06.clone();
                } else {
                    clone = dateFormat.clone();
                }
                DateFormat dateFormat2 = (DateFormat) clone;
                dateFormat2.setTimeZone(timeZone);
                return A0F(false, dateFormat2);
            }
        }
        return this;
    }

    public DateTimeSerializerBase(Class<T> cls, boolean z, DateFormat dateFormat) {
        super(cls);
        this.A01 = z;
        this.A00 = dateFormat;
    }
}
