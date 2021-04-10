package com.facebook.flatbuffers.flatteners;

import com.facebook.flatbuffers.FlatBuffer;
import com.facebook.flatbuffers.FlatBufferBuilder;
import com.facebook.flatbuffers.Flattener;
import java.nio.ByteBuffer;
import java.util.Currency;

public class CurrencyFlattener implements Flattener<Currency> {
    public static final CurrencyFlattener INSTANCE = new CurrencyFlattener();

    public int flattenToBuffer(Currency currency, FlatBufferBuilder flatBufferBuilder) {
        int createStringReference = flatBufferBuilder.createStringReference(currency.toString());
        flatBufferBuilder.startObject(1);
        flatBufferBuilder.addReference(0, createStringReference);
        return flatBufferBuilder.endObject();
    }

    @Override // com.facebook.flatbuffers.Flattener
    public Currency initFromFlatBuffer(ByteBuffer byteBuffer, int i) {
        return Currency.getInstance(FlatBuffer.resolveStringReference(byteBuffer, i, 0));
    }
}
