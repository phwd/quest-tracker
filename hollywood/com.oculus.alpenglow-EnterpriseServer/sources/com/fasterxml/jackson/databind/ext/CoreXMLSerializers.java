package com.fasterxml.jackson.databind.ext;

import X.AbstractC02640aV;
import X.AbstractC06260mR;
import X.AnonymousClass0FM;
import X.AnonymousClass0Za;
import X.AnonymousClass0a8;
import X.AnonymousClass0aI;
import X.C02650aW;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.IOException;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public final class CoreXMLSerializers extends AnonymousClass0Za {

    public static class XMLGregorianCalendarSerializer extends StdSerializer<XMLGregorianCalendar> {
        public static final XMLGregorianCalendarSerializer A00 = new XMLGregorianCalendarSerializer();

        public XMLGregorianCalendarSerializer() {
            super(XMLGregorianCalendar.class);
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
        @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
        public final void A0D(XMLGregorianCalendar xMLGregorianCalendar, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
            CalendarSerializer.A00.A0D(xMLGregorianCalendar.toGregorianCalendar(), r4, r5);
        }
    }

    @Override // X.AbstractC06910oM, X.AnonymousClass0Za
    public final JsonSerializer<?> A2m(AnonymousClass0FM r3, AnonymousClass0aI r4, AbstractC06260mR r5) {
        Class<?> cls = r4._class;
        if (Duration.class.isAssignableFrom(cls) || QName.class.isAssignableFrom(cls)) {
            return ToStringSerializer.A00;
        }
        if (XMLGregorianCalendar.class.isAssignableFrom(cls)) {
            return XMLGregorianCalendarSerializer.A00;
        }
        return null;
    }
}
