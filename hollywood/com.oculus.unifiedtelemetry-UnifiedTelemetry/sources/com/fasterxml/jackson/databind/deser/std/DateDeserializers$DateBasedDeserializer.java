package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0227Wo;
import X.AbstractC0232Ww;
import X.AnonymousClass06;
import X.C0223Wj;
import X.EnumC0470q2;
import X.IH;
import X.Zy;
import X.pN;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DateDeserializers$DateBasedDeserializer<T> extends StdScalarDeserializer<T> implements Zy {
    public final DateFormat _customFormat;
    public final String _formatString;

    public abstract DateDeserializers$DateBasedDeserializer<T> A0P(DateFormat dateFormat, String str);

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public final Date A0M(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        Date parse;
        if (this._customFormat == null || ww.A0Z() != EnumC0470q2.VALUE_STRING) {
            return super.A0M(ww, wn);
        }
        String trim = ww.A0d().trim();
        if (trim.length() == 0) {
            return A08();
        }
        synchronized (this._customFormat) {
            try {
                parse = this._customFormat.parse(trim);
            } catch (ParseException e) {
                throw new IllegalArgumentException(AnonymousClass06.A08("Failed to parse Date value '", trim, "' (format: \"", this._formatString, "\"): ", e.getMessage()));
            }
        }
        return parse;
    }

    @Override // X.Zy
    public final JsonDeserializer<?> A1g(AbstractC0226Wn wn, AbstractC0227Wo wo) throws C0223Wj {
        pN A01;
        DateFormat dateFormat;
        if (!(wo == null || (A01 = wn._config.A01().A01(wo.A2d())) == null)) {
            TimeZone timeZone = A01.A03;
            String str = A01.A01;
            if (str.length() > 0) {
                Locale locale = A01.A02;
                if (locale == null) {
                    locale = wn._config._base._locale;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, locale);
                if (timeZone == null) {
                    timeZone = wn._config._base._timeZone;
                }
                simpleDateFormat.setTimeZone(timeZone);
                return A0P(simpleDateFormat, str);
            } else if (timeZone != null) {
                DateFormat dateFormat2 = wn._config._base._dateFormat;
                if (dateFormat2.getClass() == IH.class) {
                    dateFormat = new IH(timeZone);
                } else {
                    dateFormat = (DateFormat) dateFormat2.clone();
                    dateFormat.setTimeZone(timeZone);
                }
                return A0P(dateFormat, str);
            }
        }
        return this;
    }

    public DateDeserializers$DateBasedDeserializer(DateDeserializers$DateBasedDeserializer<T> dateDeserializers$DateBasedDeserializer, DateFormat dateFormat, String str) {
        super(dateDeserializers$DateBasedDeserializer._valueClass);
        this._customFormat = dateFormat;
        this._formatString = str;
    }

    public DateDeserializers$DateBasedDeserializer(Class<?> cls) {
        super(cls);
        this._customFormat = null;
        this._formatString = null;
    }
}
