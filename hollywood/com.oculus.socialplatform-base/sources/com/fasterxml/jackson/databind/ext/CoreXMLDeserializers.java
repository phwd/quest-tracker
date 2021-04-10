package com.fasterxml.jackson.databind.ext;

import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04010oz;
import X.AnonymousClass0HU;
import X.C02090hy;
import X.C03620oC;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public final class CoreXMLDeserializers extends C02090hy {
    public static final DatatypeFactory A00;

    public static class DurationDeserializer extends FromStringDeserializer<Duration> {
        public static final DurationDeserializer A00 = new DurationDeserializer();
        public static final long serialVersionUID = 1;

        public DurationDeserializer() {
            super(Duration.class);
        }

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
        public final Duration A0Q(String str, AbstractC02210iH r3) throws IOException, C03620oC {
            return CoreXMLDeserializers.A00.newDuration(str);
        }
    }

    public static class GregorianCalendarDeserializer extends StdScalarDeserializer<XMLGregorianCalendar> {
        public static final GregorianCalendarDeserializer A00 = new GregorianCalendarDeserializer();
        public static final long serialVersionUID = 1;

        public GregorianCalendarDeserializer() {
            super(XMLGregorianCalendar.class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final XMLGregorianCalendar A0A(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
            Date A0M = A0M(r3, r4);
            if (A0M == null) {
                return null;
            }
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(A0M);
            TimeZone timeZone = r4._config._base._timeZone;
            if (timeZone != null) {
                gregorianCalendar.setTimeZone(timeZone);
            }
            return CoreXMLDeserializers.A00.newXMLGregorianCalendar(gregorianCalendar);
        }
    }

    public static class QNameDeserializer extends FromStringDeserializer<QName> {
        public static final QNameDeserializer A00 = new QNameDeserializer();
        public static final long serialVersionUID = 1;

        public QNameDeserializer() {
            super(QName.class);
        }

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
        public final QName A0Q(String str, AbstractC02210iH r3) throws IOException, C03620oC {
            return QName.valueOf(str);
        }
    }

    @Override // X.C02090hy, X.AbstractC04260pg
    public final JsonDeserializer<?> A2z(AbstractC02190iF r3, AnonymousClass0HU r4, AbstractC04010oz r5) {
        Class<?> cls = r3._class;
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
}
