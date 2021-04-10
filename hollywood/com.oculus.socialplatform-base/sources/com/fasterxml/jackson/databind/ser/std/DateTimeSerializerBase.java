package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04600qk;
import X.C02180iD;
import X.C02310iT;
import X.C03550nX;
import X.C04940ra;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DateTimeSerializerBase<T> extends StdScalarSerializer<T> implements AbstractC04600qk {
    public final DateFormat A00;
    public final boolean A01;

    public abstract long A00(T t);

    public abstract DateTimeSerializerBase<T> A01(boolean z, DateFormat dateFormat);

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public abstract void serialize(T t, AbstractC02300iS v, AbstractC02120i3 v2) throws IOException, C02310iT;

    @Override // X.AbstractC04600qk
    public final JsonSerializer<?> A2P(AbstractC02120i3 r6, AbstractC02220iI r7) throws C02180iD {
        C03550nX A012;
        Object clone;
        if (!(r7 == null || (A012 = r6._config.A01().A01(r7.A4N())) == null)) {
            if (A012.A00.isNumeric()) {
                return A01(true, null);
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
                return A01(false, simpleDateFormat);
            } else if (timeZone != null) {
                DateFormat dateFormat = r6._config._base._dateFormat;
                if (dateFormat.getClass() == C04940ra.class) {
                    clone = C04940ra.A06.clone();
                } else {
                    clone = dateFormat.clone();
                }
                DateFormat dateFormat2 = (DateFormat) clone;
                dateFormat2.setTimeZone(timeZone);
                return A01(false, dateFormat2);
            }
        }
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        if (this.A01) {
            throw new NullPointerException("expectIntegerFormat");
        } else if (this.A00 == null) {
            throw new NullPointerException("getProvider");
        } else {
            throw new NullPointerException("expectStringFormat");
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isEmpty(T t) {
        if (t == null || A00(t) == 0) {
            return true;
        }
        return false;
    }

    public DateTimeSerializerBase(Class<T> cls, boolean z, DateFormat dateFormat) {
        super(cls);
        this.A01 = z;
        this.A00 = dateFormat;
    }
}
