package com.fasterxml.jackson.databind.deser.std;

import X.RU;

public abstract class BaseNodeDeserializer extends StdDeserializer<RU> {
    public BaseNodeDeserializer() {
        super(RU.class);
    }
}
