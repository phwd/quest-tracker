package defpackage;

import android.os.Handler;
import org.chromium.base.ThreadUtils;

/* renamed from: ij0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3081ij0 {

    /* renamed from: a  reason: collision with root package name */
    public final long f10158a;
    public Runnable b;
    public Handler c = new Handler(ThreadUtils.c());

    public C3081ij0(long j) {
        this.f10158a = j;
    }

    public void a() {
        if (this.b != null) {
            this.c.removeCallbacksAndMessages(null);
            this.b = null;
        }
    }
}
