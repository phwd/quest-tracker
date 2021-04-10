package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04030gh;
import X.AbstractC04100gp;
import X.AbstractC05430l6;
import X.AnonymousClass006;
import X.AnonymousClass0jg;
import X.C03990gZ;
import X.C04690j1;
import X.C06460nB;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DateDeserializers$DateBasedDeserializer<T> extends StdScalarDeserializer<T> implements AbstractC05430l6 {
    public final DateFormat _customFormat;
    public final String _formatString;

    public abstract DateDeserializers$DateBasedDeserializer<T> A0P(DateFormat dateFormat, String str);

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public final Date A0M(AbstractC04100gp r10, AbstractC04020gg r11) throws IOException, AnonymousClass0jg {
        Date parse;
        if (this._customFormat == null || r10.A0a() != EnumC04820ji.VALUE_STRING) {
            return super.A0M(r10, r11);
        }
        String trim = r10.A0e().trim();
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

    @Override // X.AbstractC05430l6
    public final JsonDeserializer<?> A21(AbstractC04020gg r6, AbstractC04030gh r7) throws C03990gZ {
        C04690j1 A01;
        DateFormat dateFormat;
        if (!(r7 == null || (A01 = r6._config.A01().A01(r7.A3p())) == null)) {
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
                if (dateFormat2.getClass() == C06460nB.class) {
                    dateFormat = new C06460nB(timeZone);
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
