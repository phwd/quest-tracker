package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.Rn;
import com.fasterxml.jackson.databind.ext.CoreXMLDeserializers;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.xml.namespace.QName;

public abstract class FromStringDeserializer<T> extends StdScalarDeserializer<T> {
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final T A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        B3 b3 = (B3) rn;
        String str = null;
        AnonymousClass9p r1 = b3.A00;
        if (r1 == AnonymousClass9p.VALUE_STRING || !(r1 == null || r1 == AnonymousClass9p.VALUE_NULL || !r1.isScalarValue())) {
            str = b3.A09();
        }
        if (str != null) {
            if (str.length() != 0) {
                String trim = str.trim();
                if (trim.length() != 0) {
                    try {
                        T A0A = A0A(trim, rd);
                        if (A0A != null) {
                            return A0A;
                        }
                    } catch (IllegalArgumentException unused) {
                    }
                    throw null;
                }
            }
        } else if (b3.A00 == AnonymousClass9p.VALUE_EMBEDDED_OBJECT) {
            T t = (T) rn.A07();
            if (t != null) {
                if (!this._valueClass.isAssignableFrom(t.getClass())) {
                    return A09(t, rd);
                }
                return t;
            }
        } else {
            throw null;
        }
        return null;
    }

    public T A0A(String str, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        if (this instanceof CoreXMLDeserializers.QNameDeserializer) {
            return (T) QName.valueOf(str);
        }
        if (this instanceof CoreXMLDeserializers.DurationDeserializer) {
            return (T) CoreXMLDeserializers.A00.newDuration(str);
        }
        if (this instanceof JdkDeserializers$UUIDDeserializer) {
            return (T) UUID.fromString(str);
        }
        if (this instanceof JdkDeserializers$URLDeserializer) {
            return (T) new URL(str);
        }
        if (this instanceof JdkDeserializers$URIDeserializer) {
            return (T) URI.create(str);
        }
        if (this instanceof JdkDeserializers$PatternDeserializer) {
            return (T) Pattern.compile(str);
        }
        if (!(this instanceof JdkDeserializers$LocaleDeserializer)) {
            return !(this instanceof JdkDeserializers$InetAddressDeserializer) ? !(this instanceof JdkDeserializers$FileDeserializer) ? !(this instanceof JdkDeserializers$CurrencyDeserializer) ? !(this instanceof JdkDeserializers$CharsetDeserializer) ? (T) TimeZone.getTimeZone(str) : (T) Charset.forName(str) : (T) Currency.getInstance(str) : (T) new File(str) : (T) InetAddress.getByName(str);
        }
        int indexOf = str.indexOf(95);
        if (indexOf < 0) {
            return (T) new Locale(str);
        }
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        int indexOf2 = substring2.indexOf(95);
        return indexOf2 < 0 ? (T) new Locale(substring, substring2) : (T) new Locale(substring, substring2.substring(0, indexOf2), substring2.substring(indexOf2 + 1));
    }

    public FromStringDeserializer(Class<?> cls) {
        super(cls);
    }

    public T A09(Object obj, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        throw null;
    }
}
