package com.google.protobuf;

import java.io.IOException;

public abstract class ByteOutput {
    public abstract void writeLazy(byte[] bArr, int i, int i2) throws IOException;
}
