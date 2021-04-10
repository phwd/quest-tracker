package com.fasterxml.jackson.databind.ext;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1024qt;
import X.AnonymousClass2I;
import X.C00313p;
import X.C00323q;
import X.C0681fG;
import X.C0682fH;
import X.C0683fI;
import X.O4;
import X.OD;
import X.Og;
import X.PR;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public final class CoreXMLDeserializers implements Og {
    public static final DatatypeFactory A00;

    public class DurationDeserializer extends FromStringDeserializer {
        public static final DurationDeserializer A00 = new DurationDeserializer();
        public static final long serialVersionUID = 1;

        public DurationDeserializer() {
            super(Duration.class);
        }
    }

    public class GregorianCalendarDeserializer extends StdScalarDeserializer {
        public static final GregorianCalendarDeserializer A00 = new GregorianCalendarDeserializer();
        public static final long serialVersionUID = 1;

        public GregorianCalendarDeserializer() {
            super(XMLGregorianCalendar.class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final XMLGregorianCalendar A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            Date A0K = A0K(qiVar, qrVar);
            if (A0K == null) {
                return null;
            }
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(A0K);
            TimeZone timeZone = qrVar._config._base._timeZone;
            if (timeZone != null) {
                gregorianCalendar.setTimeZone(timeZone);
            }
            return CoreXMLDeserializers.A00.newXMLGregorianCalendar(gregorianCalendar);
        }
    }

    public class QNameDeserializer extends FromStringDeserializer {
        public static final QNameDeserializer A00 = new QNameDeserializer();
        public static final long serialVersionUID = 1;

        public QNameDeserializer() {
            super(QName.class);
        }
    }

    @Override // X.Og
    public final JsonDeserializer A1s(C0683fI fIVar, AnonymousClass2I r3, O4 o4, PR pr, JsonDeserializer jsonDeserializer) {
        return null;
    }

    @Override // X.Og
    public final JsonDeserializer A1v(C00323q r2, AnonymousClass2I r3, O4 o4, PR pr, JsonDeserializer jsonDeserializer) {
        return null;
    }

    @Override // X.Og
    public final JsonDeserializer A1w(C0682fH fHVar, AnonymousClass2I r3, O4 o4, PR pr, JsonDeserializer jsonDeserializer) {
        return null;
    }

    @Override // X.Og
    public final JsonDeserializer A1z(Class cls, AnonymousClass2I r3, O4 o4) {
        return null;
    }

    @Override // X.Og
    public final JsonDeserializer A21(C00313p r2, AnonymousClass2I r3, O4 o4, OD od, PR pr, JsonDeserializer jsonDeserializer) {
        return null;
    }

    @Override // X.Og
    public final JsonDeserializer A22(C0681fG fGVar, AnonymousClass2I r3, O4 o4, OD od, PR pr, JsonDeserializer jsonDeserializer) {
        return null;
    }

    @Override // X.Og
    public final JsonDeserializer A27(Class cls, AnonymousClass2I r3, O4 o4) {
        return null;
    }

    @Override // X.Og
    public final JsonDeserializer A1u(AbstractC1024qt qtVar, AnonymousClass2I r4, O4 o4) {
        Class cls = qtVar._class;
        if (cls == QName.class) {
            return QNameDeserializer.A00;
        }
        if (cls == XMLGregorianCalendar.class) {
            return GregorianCalendarDeserializer.A00;
        }
        if (cls == Duration.class) {
            return DurationDeserializer.A00;
        }
        return null;
    }

    static {
        try {
            A00 = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public CoreXMLDeserializers() {
    }

    public CoreXMLDeserializers(int i) {
    }
}
