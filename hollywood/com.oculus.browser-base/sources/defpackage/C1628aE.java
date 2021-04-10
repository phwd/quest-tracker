package defpackage;

import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: aE  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1628aE extends AbstractC0685Le1 {

    /* renamed from: a  reason: collision with root package name */
    public final Object f9420a = new Object();
    public final ExecutorService b = Executors.newFixedThreadPool(4, new YD(this));

    @Override // defpackage.AbstractC0685Le1
    public boolean a() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
