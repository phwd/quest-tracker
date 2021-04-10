package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0264Od;
import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AnonymousClass08;
import X.C0244Mv;
import X.NX;
import X.O5;
import X.QI;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DateDeserializers$DateBasedDeserializer extends StdScalarDeserializer implements AbstractC0264Od {
    public final DateFormat _customFormat;
    public final String _formatString;

    private final DateDeserializers$DateBasedDeserializer A0N(DateFormat dateFormat, String str) {
        if (this instanceof DateDeserializers$TimestampDeserializer) {
            return new DateDeserializers$TimestampDeserializer((DateDeserializers$TimestampDeserializer) this, dateFormat, str);
        }
        if (this instanceof DateDeserializers$SqlDateDeserializer) {
            return new DateDeserializers$SqlDateDeserializer((DateDeserializers$SqlDateDeserializer) this, dateFormat, str);
        }
        if (!(this instanceof DateDeserializers$DateDeserializer)) {
            return new DateDeserializers$CalendarDeserializer((DateDeserializers$CalendarDeserializer) this, dateFormat, str);
        }
        return new DateDeserializers$DateDeserializer((DateDeserializers$DateDeserializer) this, dateFormat, str);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public final Date A0K(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Date parse;
        if (this._customFormat == null || qiVar.A0U() != NX.VALUE_STRING) {
            return super.A0K(qiVar, qrVar);
        }
        String trim = qiVar.A0p().trim();
        if (trim.length() == 0) {
            return (Date) A08();
        }
        synchronized (this._customFormat) {
            try {
                parse = this._customFormat.parse(trim);
            } catch (ParseException e) {
                throw new IllegalArgumentException(AnonymousClass08.A08("Failed to parse Date value '", trim, "' (format: \"", this._formatString, "\"): ", e.getMessage()));
            }
        }
        return parse;
    }

    @Override // X.AbstractC0264Od
    public final JsonDeserializer A1X(AbstractC1022qr qrVar, O5 o5) {
        C0244Mv A01;
        DateFormat dateFormat;
        if (!(o5 == null || (A01 = qrVar._config.A01().A01(o5.A2e())) == null)) {
            TimeZone timeZone = A01.A03;
            String str = A01.A01;
            if (str.length() > 0) {
                Locale locale = A01.A02;
                if (locale == null) {
                    locale = qrVar._config._base._locale;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, locale);
                if (timeZone == null) {
                    timeZone = qrVar._config._base._timeZone;
                }
                simpleDateFormat.setTimeZone(timeZone);
                return A0N(simpleDateFormat, str);
            } else if (timeZone != null) {
                DateFormat dateFormat2 = qrVar._config._base._dateFormat;
                if (dateFormat2.getClass() == QI.class) {
                    dateFormat = new QI(timeZone);
                } else {
                    dateFormat = (DateFormat) dateFormat2.clone();
                    dateFormat.setTimeZone(timeZone);
                }
                return A0N(dateFormat, str);
            }
        }
        return this;
    }

    public DateDeserializers$DateBasedDeserializer(DateDeserializers$DateBasedDeserializer dateDeserializers$DateBasedDeserializer, DateFormat dateFormat, String str) {
        super(dateDeserializers$DateBasedDeserializer._valueClass);
        this._customFormat = dateFormat;
        this._formatString = str;
    }

    public DateDeserializers$DateBasedDeserializer(Class cls) {
        super(cls);
        this._customFormat = null;
        this._formatString = null;
    }
}
