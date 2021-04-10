package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AbstractC02580aL;
import X.AbstractC06520n2;
import X.AnonymousClass006;
import X.AnonymousClass0aG;
import X.AnonymousClass0aT;
import X.C05750kz;
import X.C05910ld;
import X.C07280p4;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DateDeserializers$DateBasedDeserializer<T> extends StdScalarDeserializer<T> implements AbstractC06520n2 {
    public final DateFormat _customFormat;
    public final String _formatString;

    public abstract DateDeserializers$DateBasedDeserializer<T> A0P(DateFormat dateFormat, String str);

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public final Date A0M(AnonymousClass0aT r10, AbstractC02570aK r11) throws IOException, C05910ld {
        Date parse;
        if (this._customFormat == null || r10.A0G() != EnumC05930lf.VALUE_STRING) {
            return super.A0M(r10, r11);
        }
        String trim = r10.A0P().trim();
        if (trim.length() == 0) {
            return A08();
        }
        synchronized (this._customFormat) {
            try {
                parse = this._customFormat.parse(trim);
            } catch (ParseException e) {
                throw new IllegalArgumentException(AnonymousClass006.A0A("Failed to parse Date value '", trim, "' (format: \"", this._formatString, "\"): ", e.getMessage()));
            }
        }
        return parse;
    }

    @Override // X.AbstractC06520n2
    public final JsonDeserializer<?> A1w(AbstractC02570aK r6, AbstractC02580aL r7) throws AnonymousClass0aG {
        C05750kz A01;
        DateFormat dateFormat;
        if (!(r7 == null || (A01 = r6._config.A01().A01(r7.A41())) == null)) {
            TimeZone timeZone = A01.A03;
            String str = A01.A01;
            if (str.length() > 0) {
                Locale locale = A01.A02;
                if (locale == null) {
                    locale = r6._config._base._locale;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, locale);
                if (timeZone == null) {
                    timeZone = r6._config._base._timeZone;
                }
                simpleDateFormat.setTimeZone(timeZone);
                return A0P(simpleDateFormat, str);
            } else if (timeZone != null) {
                DateFormat dateFormat2 = r6._config._base._dateFormat;
                if (dateFormat2.getClass() == C07280p4.class) {
                    dateFormat = new C07280p4(timeZone);
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
