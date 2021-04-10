package com.fasterxml.jackson.databind.ext;

import X.AbstractC0224Wl;
import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AnonymousClass8M;
import X.WV;
import X.jm;
import X.q0;
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

public final class CoreXMLDeserializers extends WV {
    public static final DatatypeFactory A00;

    public static class DurationDeserializer extends FromStringDeserializer<Duration> {
        public static final DurationDeserializer A00 = new DurationDeserializer();
        public static final long serialVersionUID = 1;

        public DurationDeserializer() {
            super(Duration.class);
        }

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
        public final Duration A0Q(String str, AbstractC0226Wn wn) throws IOException, q0 {
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
        public final XMLGregorianCalendar A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
            Date A0M = A0M(ww, wn);
            if (A0M == null) {
                return null;
            }
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(A0M);
            TimeZone timeZone = wn._config._base._timeZone;
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
        public final QName A0Q(String str, AbstractC0226Wn wn) throws IOException, q0 {
            return QName.valueOf(str);
        }
    }

    @Override // X.Z6, X.WV
    public final JsonDeserializer<?> A23(AbstractC0224Wl wl, AnonymousClass8M r4, jm jmVar) {
        Class<?> cls = wl._class;
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
