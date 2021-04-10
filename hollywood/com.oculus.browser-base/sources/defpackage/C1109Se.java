package defpackage;

import android.content.Context;
import android.os.PowerManager;
import org.chromium.base.task.PostTask;

/* renamed from: Se  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1109Se implements AbstractC0804Ne {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8903a;
    public final C2046cf1 b;
    public final AbstractC0865Oe c;
    public boolean d;

    public C1109Se(Context context, PowerManager.WakeLock wakeLock, C2046cf1 cf1, AbstractC0865Oe oe) {
        this.f8903a = context;
        this.b = cf1;
        this.c = oe;
    }

    @Override // defpackage.AbstractC0804Ne
    public void a(boolean z) {
        PostTask.b(Zo1.b, new RunnableC1048Re(this, z), 0);
    }
}
