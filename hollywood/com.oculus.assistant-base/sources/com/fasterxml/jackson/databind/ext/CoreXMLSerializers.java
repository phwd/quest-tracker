package com.fasterxml.jackson.databind.ext;

import X.AbstractC0286Pi;
import X.AbstractC1024qt;
import X.AnonymousClass2H;
import X.C00313p;
import X.C00323q;
import X.C0681fG;
import X.C0682fH;
import X.C0683fI;
import X.O4;
import X.PU;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public final class CoreXMLSerializers implements AbstractC0286Pi {

    public class XMLGregorianCalendarSerializer extends StdSerializer {
        public static final XMLGregorianCalendarSerializer A00 = new XMLGregorianCalendarSerializer();

        public XMLGregorianCalendarSerializer() {
            super(XMLGregorianCalendar.class);
        }
    }

    @Override // X.AbstractC0286Pi
    public final JsonSerializer A1t(AnonymousClass2H r2, C0683fI fIVar, O4 o4, PU pu, JsonSerializer jsonSerializer) {
        return null;
    }

    @Override // X.AbstractC0286Pi
    public final JsonSerializer A1x(AnonymousClass2H r2, C0682fH fHVar, O4 o4, PU pu, JsonSerializer jsonSerializer) {
        return null;
    }

    @Override // X.AbstractC0286Pi
    public final JsonSerializer A1y(AnonymousClass2H r2, C00323q r3, O4 o4, PU pu, JsonSerializer jsonSerializer) {
        return null;
    }

    @Override // X.AbstractC0286Pi
    public final JsonSerializer A23(AnonymousClass2H r2, C0681fG fGVar, O4 o4, JsonSerializer jsonSerializer, PU pu, JsonSerializer jsonSerializer2) {
        return null;
    }

    @Override // X.AbstractC0286Pi
    public final JsonSerializer A24(AnonymousClass2H r2, C00313p r3, O4 o4, JsonSerializer jsonSerializer, PU pu, JsonSerializer jsonSerializer2) {
        return null;
    }

    @Override // X.AbstractC0286Pi
    public final JsonSerializer A26(AnonymousClass2H r3, AbstractC1024qt qtVar, O4 o4) {
        Class cls = qtVar._class;
        if (Duration.class.isAssignableFrom(cls) || QName.class.isAssignableFrom(cls)) {
            return ToStringSerializer.A00;
        }
        if (XMLGregorianCalendar.class.isAssignableFrom(cls)) {
            return XMLGregorianCalendarSerializer.A00;
        }
        return null;
    }

    public CoreXMLSerializers() {
    }

    public CoreXMLSerializers(int i) {
    }
}
