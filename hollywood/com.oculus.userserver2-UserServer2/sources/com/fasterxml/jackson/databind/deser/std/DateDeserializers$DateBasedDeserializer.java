package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass1V;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.Rn;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public abstract class DateDeserializers$DateBasedDeserializer<T> extends StdScalarDeserializer<T> implements AnonymousClass1V {
    public final DateFormat _customFormat = null;
    public final String _formatString = null;

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public final Date A07(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        Date parse;
        if (this._customFormat == null || ((B3) rn).A00 != AnonymousClass9p.VALUE_STRING) {
            return super.A07(rn, rd);
        }
        String trim = rn.A09().trim();
        if (trim.length() == 0) {
            return A02();
        }
        synchronized (this._customFormat) {
            try {
                parse = this._customFormat.parse(trim);
            } catch (ParseException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to parse Date value '");
                sb.append(trim);
                sb.append("' (format: \"");
                sb.append(this._formatString);
                sb.append("\"): ");
                sb.append(e.getMessage());
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return parse;
    }

    public DateDeserializers$DateBasedDeserializer(Class<?> cls) {
        super(cls);
    }
}
