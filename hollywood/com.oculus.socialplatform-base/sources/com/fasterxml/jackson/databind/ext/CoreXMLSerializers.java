package com.fasterxml.jackson.databind.ext;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02300iS;
import X.AbstractC04010oz;
import X.AnonymousClass0HM;
import X.C01830hA;
import X.C02180iD;
import X.C02310iT;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.IOException;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public final class CoreXMLSerializers extends C01830hA {

    public static class XMLGregorianCalendarSerializer extends StdSerializer<XMLGregorianCalendar> {
        public static final XMLGregorianCalendarSerializer A00 = new XMLGregorianCalendarSerializer();

        public XMLGregorianCalendarSerializer() {
            super(XMLGregorianCalendar.class);
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
        @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
        public final void serialize(XMLGregorianCalendar xMLGregorianCalendar, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C02310iT {
            CalendarSerializer.A00.serialize(xMLGregorianCalendar.toGregorianCalendar(), r4, r5);
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
        public final void acceptJsonFormatVisitor(AbstractC01900ha r1, AbstractC02190iF r2) throws C02180iD {
        }
    }

    @Override // X.AbstractC04640qs, X.C01830hA
    public final JsonSerializer<?> A3B(AnonymousClass0HM r3, AbstractC02190iF r4, AbstractC04010oz r5) {
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
