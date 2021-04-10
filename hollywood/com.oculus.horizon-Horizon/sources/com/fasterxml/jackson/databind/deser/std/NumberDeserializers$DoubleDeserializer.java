package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.oculus.horizon.service_media.OVRMediaServiceManager;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$DoubleDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Double> {
    public static final NumberDeserializers$DoubleDeserializer A00 = new NumberDeserializers$DoubleDeserializer(Double.class, Double.valueOf((double) OVRMediaServiceManager.SCREENSHOT_SHORTCUT_DELAY));
    public static final NumberDeserializers$DoubleDeserializer A01 = new NumberDeserializers$DoubleDeserializer(Double.TYPE, null);
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A09(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return A0K(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
    public final Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
        return A0K(r2, r3);
    }

    public NumberDeserializers$DoubleDeserializer(Class<Double> cls, Double d) {
        super(cls, d);
    }

    private final void A00(AbstractC04100gp r1, AbstractC04020gg r2) throws IOException, AnonymousClass0jg {
        A0K(r1, r2);
    }
}
