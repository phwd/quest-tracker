package com.oculus.assistant.service.api.remoteaudiosource;

public interface RemoteAudioSource {
    public static final int READ_TYPE_BOXED_FLOAT = 3;
    public static final int READ_TYPE_BOXED_SHORT = 4;
    public static final int READ_TYPE_BYTE = 0;
    public static final int READ_TYPE_BYTE_ARRAY = 5;
    public static final int READ_TYPE_FLOAT = 1;
    public static final int READ_TYPE_SHORT = 2;

    void close();

    String getSourceName();

    int getSupportedReadType();

    boolean isSourceAvailable();

    void open();

    int read(byte[] bArr, int i, int i2);

    Float[] readPcmBoxedFloatData(int i);

    Short[] readPcmBoxedShortData(int i);

    byte[] readPcmByteArray(int i);

    float[] readPcmFloatData(int i);

    short[] readPcmShortData(int i);
}
