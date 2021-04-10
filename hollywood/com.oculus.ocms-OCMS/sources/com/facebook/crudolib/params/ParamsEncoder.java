package com.facebook.crudolib.params;

import java.io.IOException;
import java.io.Writer;
import javax.annotation.concurrent.Immutable;

@Immutable
public interface ParamsEncoder {
    void encode(Writer writer, ParamsCollection paramsCollection) throws IOException;
}
