package com.fasterxml.jackson.dataformat.smile.async;

import java.io.IOException;

public interface NonBlockingInputFeeder {
    void endOfInput();

    void feedInput(byte[] bArr, int i, int i2) throws IOException;

    boolean needMoreInput();
}
