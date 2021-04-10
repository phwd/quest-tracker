package defpackage;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* renamed from: Zk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1554Zk0 implements AbstractC3511lC0 {

    /* renamed from: a  reason: collision with root package name */
    public final Map f9365a = new HashMap();

    public static String e(int i) {
        return String.format(Locale.US, "%d", Integer.valueOf(i));
    }

    @Override // defpackage.AbstractC3511lC0
    public void a(int i, String str, Callback callback) {
        PostTask.c(Zo1.f9374a, new RunnableC1493Yk0(this, callback, i));
    }

    @Override // defpackage.AbstractC3511lC0
    public void b(int i, String str) {
        this.f9365a.remove(e(i));
    }

    @Override // defpackage.AbstractC3511lC0
    public byte[] c(int i, String str) {
        return null;
    }

    @Override // defpackage.AbstractC3511lC0
    public void d(int i, String str, byte[] bArr) {
        this.f9365a.put(e(i), bArr);
    }
}
