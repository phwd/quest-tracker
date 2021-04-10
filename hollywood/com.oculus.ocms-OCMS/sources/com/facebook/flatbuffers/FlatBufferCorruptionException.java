package com.facebook.flatbuffers;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FlatBufferCorruptionException extends RuntimeException {
    public FlatBufferCorruptionException(String str, Throwable th) {
        super(str, th);
    }
}
