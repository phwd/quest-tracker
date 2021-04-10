package com.oculus.assistant.service.api.remoteaudiosource;

import android.os.IInterface;

public interface IRemoteAudioSource extends IInterface {
    String A2v();

    boolean A3b();

    void A4S();

    void close();

    int read(byte[] bArr, int i, int i2);
}
