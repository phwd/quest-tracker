package defpackage;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.BaseGmsClient;

/* renamed from: lg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HandlerC3584lg extends YG1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BaseGmsClient f10363a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC3584lg(BaseGmsClient baseGmsClient, Looper looper) {
        super(looper);
        this.f10363a = baseGmsClient;
    }

    public static boolean a(Message message) {
        int i = message.what;
        return i == 2 || i == 1 || i == 7;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002f, code lost:
        if (r0 == 5) goto L_0x0031;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void handleMessage(android.os.Message r8) {
        /*
        // Method dump skipped, instructions count: 348
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.HandlerC3584lg.handleMessage(android.os.Message):void");
    }
}
