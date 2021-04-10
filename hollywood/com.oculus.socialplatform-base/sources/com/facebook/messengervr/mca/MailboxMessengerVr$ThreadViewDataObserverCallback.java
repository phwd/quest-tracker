package com.facebook.messengervr.mca;

import X.AnonymousClass294;
import X.AnonymousClass299;
import com.facebook.messengervrcqljava.TempMessageList;
import com.facebook.msys.mci.CQLResultSet;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.Nullable;

public abstract class MailboxMessengerVr$ThreadViewDataObserverCallback {
    public abstract void callback(AnonymousClass299 v, AnonymousClass294 v2, @Nullable TempMessageList tempMessageList, int i, int i2);

    @DoNotStrip
    private void callbackJNI(CQLResultSet cQLResultSet, CQLResultSet cQLResultSet2, @Nullable CQLResultSet cQLResultSet3, int i, int i2) {
        TempMessageList tempMessageList;
        AnonymousClass299 r1 = new AnonymousClass299(cQLResultSet);
        AnonymousClass294 r2 = new AnonymousClass294(cQLResultSet2);
        if (cQLResultSet3 == null) {
            tempMessageList = null;
        } else {
            tempMessageList = new TempMessageList(cQLResultSet3);
        }
        callback(r1, r2, tempMessageList, i, i2);
    }
}
