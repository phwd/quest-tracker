package com.facebook.common.json;

import android.net.Uri;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;

public class UriDeserializer extends FromStringDeserializer {
    public UriDeserializer() {
        super(Uri.class);
    }
}
