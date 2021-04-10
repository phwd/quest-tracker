package com.facebook.papaya;

import android.os.IInterface;

public interface IPapayaLogSink extends IInterface {
    void event(long j, long j2, long j3, int i, String str);

    void log(long j, long j2, long j3, int i, String str, int i2, String str2);
}
