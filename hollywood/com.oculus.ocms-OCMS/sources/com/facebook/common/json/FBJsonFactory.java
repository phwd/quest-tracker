package com.facebook.common.json;

import com.facebook.infer.annotation.Nullsafe;
import com.fasterxml.jackson.core.JsonFactory;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Deprecated
public class FBJsonFactory extends JsonFactory {
    @Deprecated
    public static final FBJsonFactory instance = new FBJsonFactory();

    private FBJsonFactory() {
    }
}
