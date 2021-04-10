package com.fasterxml.jackson.databind;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02300iS;
import X.AbstractC04490qS;
import X.AbstractC04550qd;
import X.AbstractC04870rR;
import X.AnonymousClass006;
import X.C02180iD;
import X.C03620oC;
import java.io.IOException;

public abstract class JsonSerializer<T> implements AbstractC04490qS {

    public static abstract class None extends JsonSerializer<Object> {
    }

    public JsonSerializer<?> getDelegatee() {
        return null;
    }

    public Class<T> handledType() {
        return null;
    }

    public boolean isEmpty(T t) {
        return t == null;
    }

    public boolean isUnwrappingSerializer() {
        return false;
    }

    public abstract void serialize(T t, AbstractC02300iS v, AbstractC02120i3 v2) throws IOException, C03620oC;

    public JsonSerializer<T> unwrappingSerializer(AbstractC04870rR r1) {
        return this;
    }

    public boolean usesObjectId() {
        return false;
    }

    public void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        if (r3 != null) {
            throw new NullPointerException("expectAnyFormat");
        }
    }

    public JsonSerializer<T> replaceDelegatee(JsonSerializer<?> jsonSerializer) {
        throw new UnsupportedOperationException();
    }

    public void serializeWithType(T t, AbstractC02300iS r4, AbstractC02120i3 r5, AbstractC04550qd r6) throws IOException, C03620oC {
        Class handledType = handledType();
        if (handledType == null) {
            handledType = t.getClass();
        }
        throw new UnsupportedOperationException(AnonymousClass006.A07("Type id handling not implemented for type ", handledType.getName()));
    }
}
