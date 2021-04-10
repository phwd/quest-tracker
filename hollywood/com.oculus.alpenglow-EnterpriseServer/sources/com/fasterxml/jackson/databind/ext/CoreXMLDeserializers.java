package com.fasterxml.jackson.databind.ext;

import X.AbstractC02570aK;
import X.AbstractC06260mR;
import X.AnonymousClass0a3;
import X.AnonymousClass0aI;
import X.AnonymousClass0aT;
import X.C01260Fu;
import X.C05910ld;
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

public final class CoreXMLDeserializers extends AnonymousClass0a3 {
    public static final DatatypeFactory A00;

    public static class DurationDeserializer extends FromStringDeserializer<Duration> {
        public static final DurationDeserializer A00 = new DurationDeserializer();
        public static final long serialVersionUID = 1;

        public DurationDeserializer() {
            super(Duration.class);
        }

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
        public final Duration A0Q(String str, AbstractC02570aK r3) throws IOException, C05910ld {
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
        public final XMLGregorianCalendar A09(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
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
        public final QName A0Q(String str, AbstractC02570aK r3) throws IOException, C05910ld {
            return QName.valueOf(str);
        }
    }

    @Override // X.AnonymousClass0a3, X.AnonymousClass0n7
    public final JsonDeserializer<?> A2a(AnonymousClass0aI r3, C01260Fu r4, AbstractC06260mR r5) {
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
