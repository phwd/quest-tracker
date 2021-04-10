package defpackage;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.BlockingQueue;

/* renamed from: fW  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HandlerC2533fW extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BlockingQueue f9925a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC2533fW(C2704gW gWVar, Looper looper, BlockingQueue blockingQueue) {
        super(looper);
        this.f9925a = blockingQueue;
    }

    public void handleMessage(Message message) {
        this.f9925a.add((Intent) message.obj);
    }
}
