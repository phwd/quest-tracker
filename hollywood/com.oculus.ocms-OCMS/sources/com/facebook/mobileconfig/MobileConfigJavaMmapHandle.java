package com.facebook.mobileconfig;

import java.nio.ByteBuffer;

public class MobileConfigJavaMmapHandle extends MobileConfigMmapHandle {
    private final String mFilename;

    public MobileConfigJavaMmapHandle(String str) {
        this.mFilename = str;
    }

    @Override // com.facebook.mobileconfig.MobileConfigMmapHandle
    public String getFilename() {
        return this.mFilename;
    }

    @Override // com.facebook.mobileconfig.MobileConfigMmapHandle
    public ByteBuffer getJavaByteBuffer() {
        return mmapFile(getFilename());
    }
}
