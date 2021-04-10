package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02300iS;
import X.C02180iD;
import X.C02310iT;
import java.io.File;
import java.io.IOException;

public final class StdJdkSerializers$FileSerializer extends StdScalarSerializer<File> {
    public StdJdkSerializers$FileSerializer() {
        super(File.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        throw new NullPointerException("expectStringFormat");
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Object obj, AbstractC02300iS r3, AbstractC02120i3 r4) throws IOException, C02310iT {
        r3.A0U(((File) obj).getAbsolutePath());
    }
}
