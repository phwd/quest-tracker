package com.facebook.common.json;

import android.net.Uri;
import com.facebook.infer.annotation.Nullsafe;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import java.io.IOException;

@Nullsafe(Nullsafe.Mode.LOCAL)
class UriDeserializer extends FromStringDeserializer<Uri> {
    UriDeserializer() {
        super(Uri.class);
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public Uri _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
        return Uri.parse(str);
    }
}
