package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC1024qt;
import X.O5;
import X.PU;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.util.Collection;

public class CollectionSerializer extends AsArraySerializerBase {
    public CollectionSerializer(AbstractC1024qt qtVar, boolean z, PU pu, O5 o5, JsonSerializer jsonSerializer) {
        super(Collection.class, qtVar, z, pu, o5, jsonSerializer);
    }

    public CollectionSerializer(CollectionSerializer collectionSerializer, O5 o5, PU pu, JsonSerializer jsonSerializer) {
        super(collectionSerializer, o5, pu, jsonSerializer);
    }
}
