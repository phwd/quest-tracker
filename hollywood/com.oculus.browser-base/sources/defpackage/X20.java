package defpackage;

import java.security.SecureRandom;
import java.util.concurrent.Executor;

/* renamed from: X20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X20 {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f9188a = new Object();
    public static X20 b;
    public SecureRandom c;
    public AbstractC2032cb d;
    public W20 e;
    public byte[] f;
    public String g;

    public X20() {
        V20 v20 = new V20(this);
        Executor executor = AbstractC2032cb.f9616a;
        v20.f();
        ((ExecutorC1463Ya) executor).execute(v20.e);
        this.d = v20;
    }

    public static X20 a() {
        synchronized (f9188a) {
            if (b == null) {
                b = new X20();
            }
        }
        return b;
    }
}
