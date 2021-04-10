package com.oculus.mediaupload.api;

import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class TypedByteArrayWithFilename extends TypedByteArray implements TypedOutput, TypedInput {
    public final String fileName;

    public TypedByteArrayWithFilename(byte[] bArr, String str) {
        super("video/mp4", bArr);
        this.fileName = str;
    }

    @Override // retrofit.mime.TypedOutput, retrofit.mime.TypedByteArray
    public final String fileName() {
        return this.fileName;
    }
}
