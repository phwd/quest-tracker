package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.C02650aW;
import java.io.File;
import java.io.IOException;

public final class StdJdkSerializers$FileSerializer extends StdScalarSerializer<File> {
    public StdJdkSerializers$FileSerializer() {
        super(File.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r3, AnonymousClass0a8 r4) throws IOException, C02650aW {
        r3.A0S(((File) obj).getAbsolutePath());
    }
}
