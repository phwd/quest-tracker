package com.facebook.papaya;

import android.os.IInterface;

public interface IPapayaCallback extends IInterface {
    void onExecutorComplete(String str);
}
