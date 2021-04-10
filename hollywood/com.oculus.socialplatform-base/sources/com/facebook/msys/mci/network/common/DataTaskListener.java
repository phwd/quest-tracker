package com.facebook.msys.mci.network.common;

import X.AnonymousClass1O1;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface DataTaskListener {
    @DoNotStrip
    void onNewTask(DataTask dataTask, AnonymousClass1O1 v);

    @DoNotStrip
    void onUpdateStreamingDataTask(byte[] bArr, String str, AnonymousClass1O1 v);
}
