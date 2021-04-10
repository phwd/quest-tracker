package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC0278Pa;
import X.AbstractC1031r2;
import X.C0244Mv;
import X.O5;
import X.QI;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DateTimeSerializerBase extends StdScalarSerializer implements AbstractC0278Pa {
    public final DateFormat A00;
    public final boolean A01;

    private final DateTimeSerializerBase A0C(boolean z, DateFormat dateFormat) {
        if (!(this instanceof DateSerializer)) {
            if (z) {
                return new CalendarSerializer(true, null);
            }
            return new CalendarSerializer(false, dateFormat);
        } else if (z) {
            return new DateSerializer(true, null);
        } else {
            return new DateSerializer(false, dateFormat);
        }
    }

    @Override // X.AbstractC0278Pa
    public final JsonSerializer A1Y(AbstractC1031r2 r2Var, O5 o5) {
        C0244Mv A012;
        Object clone;
        if (!(o5 == null || (A012 = r2Var._config.A01().A01(o5.A2e())) == null)) {
            if (A012.A00.isNumeric()) {
                return A0C(true, null);
            }
            TimeZone timeZone = A012.A03;
            String str = A012.A01;
            if (str.length() > 0) {
                Locale locale = A012.A02;
                if (locale == null) {
                    locale = r2Var._config._base._locale;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, locale);
                if (timeZone == null) {
                    timeZone = r2Var._config._base._timeZone;
                }
                simpleDateFormat.setTimeZone(timeZone);
                return A0C(false, simpleDateFormat);
            } else if (timeZone != null) {
                DateFormat dateFormat = r2Var._config._base._dateFormat;
                if (dateFormat.getClass() == QI.class) {
                    clone = QI.A06.clone();
                } else {
                    clone = dateFormat.clone();
                }
                DateFormat dateFormat2 = (DateFormat) clone;
                dateFormat2.setTimeZone(timeZone);
                return A0C(false, dateFormat2);
            }
        }
        return this;
    }

    public DateTimeSerializerBase(Class cls, boolean z, DateFormat dateFormat) {
        super(cls);
        this.A01 = z;
        this.A00 = dateFormat;
    }
}
