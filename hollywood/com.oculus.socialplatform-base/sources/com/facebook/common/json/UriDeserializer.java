package com.facebook.common.json;

import X.AbstractC02210iH;
import X.C03620oC;
import android.net.Uri;
import com.facebook.infer.annotation.Nullsafe;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import java.io.IOException;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class UriDeserializer extends FromStringDeserializer<Uri> {
    public UriDeserializer() {
        super(Uri.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final Uri A0Q(String str, AbstractC02210iH r3) throws IOException, C03620oC {
        return Uri.parse(str);
    }
}
