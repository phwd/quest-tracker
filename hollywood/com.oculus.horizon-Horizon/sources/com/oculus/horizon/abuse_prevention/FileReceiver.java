package com.oculus.horizon.abuse_prevention;

import android.content.BroadcastReceiver;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

public class FileReceiver extends BroadcastReceiver {
    public static final String TAG = "FileReceiver";
    public static final String URI_KEY = "URI";
    @Inject
    @Eager
    public FileReceiverManager mFileReceiverManager;

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003a, code lost:
        if (com.oculus.signature.SignatureHelper.A02(r8, "com.oculus.vrshell", r0) == false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0033, code lost:
        if (r1.mUnlockulusHelper.A00(r8) != false) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onReceive(android.content.Context r8, android.content.Intent r9) {
        /*
        // Method dump skipped, instructions count: 248
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.abuse_prevention.FileReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
