package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02220iI;
import X.AbstractC02280iQ;
import X.AbstractC04230pb;
import X.AnonymousClass006;
import X.C02180iD;
import X.C03550nX;
import X.C03620oC;
import X.C04940ra;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DateDeserializers$DateBasedDeserializer<T> extends StdScalarDeserializer<T> implements AbstractC04230pb {
    public final DateFormat _customFormat;
    public final String _formatString;

    public abstract DateDeserializers$DateBasedDeserializer<T> A0P(DateFormat dateFormat, String str);

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public final Date A0M(AbstractC02280iQ r10, AbstractC02210iH r11) throws IOException, C03620oC {
        Date parse;
        if (this._customFormat == null || r10.A0i() != EnumC03640oE.VALUE_STRING) {
            return super.A0M(r10, r11);
        }
        String trim = r10.A0m().trim();
        if (trim.length() == 0) {
            return A08();
        }
        synchronized (this._customFormat) {
            try {
                parse = this._customFormat.parse(trim);
            } catch (ParseException e) {
                throw new IllegalArgumentException(AnonymousClass006.A0D("Failed to parse Date value '", trim, "' (format: \"", this._formatString, "\"): ", e.getMessage()));
            }
        }
        return parse;
    }

    @Override // X.AbstractC04230pb
    public final JsonDeserializer<?> A2O(AbstractC02210iH r6, AbstractC02220iI r7) throws C02180iD {
        C03550nX A01;
        DateFormat dateFormat;
        if (!(r7 == null || (A01 = r6._config.A01().A01(r7.A4N())) == null)) {
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
                if (dateFormat2.getClass() == C04940ra.class) {
                    dateFormat = new C04940ra(timeZone);
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
