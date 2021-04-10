package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AnonymousClass08;
import X.C1025qv;
import X.NX;
import android.net.Uri;
import com.fasterxml.jackson.databind.ext.CoreXMLDeserializers;
import java.io.File;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Currency;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.xml.namespace.QName;

public abstract class FromStringDeserializer extends StdScalarDeserializer {
    public static final long serialVersionUID = 1;

    public Object A0N(Object obj, AbstractC1022qr qrVar) {
        throw C1025qv.A00(null, AnonymousClass08.A06("Don't know how to convert embedded Object of type ", obj.getClass().getName(), " into ", this._valueClass.getName()));
    }

    public Object A0O(String str, AbstractC1022qr qrVar) {
        if (this instanceof CoreXMLDeserializers.QNameDeserializer) {
            return QName.valueOf(str);
        }
        if (this instanceof CoreXMLDeserializers.DurationDeserializer) {
            return CoreXMLDeserializers.A00.newDuration(str);
        }
        if (this instanceof JdkDeserializers$UUIDDeserializer) {
            return UUID.fromString(str);
        }
        if (this instanceof JdkDeserializers$URLDeserializer) {
            return new URL(str);
        }
        if (this instanceof JdkDeserializers$URIDeserializer) {
            return URI.create(str);
        }
        if (this instanceof JdkDeserializers$PatternDeserializer) {
            return Pattern.compile(str);
        }
        if (this instanceof JdkDeserializers$LocaleDeserializer) {
            return JdkDeserializers$LocaleDeserializer.A00(str);
        }
        if (this instanceof JdkDeserializers$InetAddressDeserializer) {
            return InetAddress.getByName(str);
        }
        if (this instanceof JdkDeserializers$FileDeserializer) {
            return new File(str);
        }
        if (this instanceof JdkDeserializers$CurrencyDeserializer) {
            return Currency.getInstance(str);
        }
        if (this instanceof JdkDeserializers$CharsetDeserializer) {
            return Charset.forName(str);
        }
        if (!(this instanceof DateDeserializers$TimeZoneDeserializer)) {
            return Uri.parse(str);
        }
        return TimeZone.getTimeZone(str);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        String A0n = qiVar.A0n();
        if (A0n != null) {
            if (A0n.length() != 0) {
                String trim = A0n.trim();
                if (trim.length() != 0) {
                    try {
                        Object A0O = A0O(trim, qrVar);
                        if (A0O != null) {
                            return A0O;
                        }
                    } catch (IllegalArgumentException unused) {
                    }
                    qrVar.A0L(this._valueClass);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        } else if (qiVar.A0U() == NX.VALUE_EMBEDDED_OBJECT) {
            Object A0Z = qiVar.A0Z();
            if (A0Z != null) {
                if (!this._valueClass.isAssignableFrom(A0Z.getClass())) {
                    return A0N(A0Z, qrVar);
                }
                return A0Z;
            }
        } else {
            qrVar.A0J();
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        return null;
    }

    public FromStringDeserializer(Class cls) {
        super(cls);
    }
}
