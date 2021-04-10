package defpackage;

import android.os.Bundle;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.chromium.base.task.PostTask;

/* renamed from: vu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5331vu {

    /* renamed from: a  reason: collision with root package name */
    public final Object f11504a = new Object();
    public FutureTask b;
    public C4821su c;
    public C0821Nk d = new C0821Nk();
    public final C1322Vq0 e = new C1322Vq0();

    public C5331vu(RunnableC4481qu quVar) {
    }

    public Cipher a(int i) {
        C4821su b2 = b(true);
        if (b2 != null) {
            try {
                Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(i, b2.f11307a, new IvParameterSpec(b2.b));
                return instance;
            } catch (GeneralSecurityException unused) {
            }
        }
        AbstractC1220Ua0.a("CipherFactory", "Error in creating cipher instance.", new Object[0]);
        return null;
    }

    public C4821su b(boolean z) {
        if (this.c == null && z) {
            d();
            try {
                C4821su suVar = (C4821su) this.b.get();
                synchronized (this.f11504a) {
                    if (this.c == null) {
                        this.c = suVar;
                        PostTask.b(Zo1.f9374a, new RunnableC4481qu(this), 0);
                    }
                }
            } catch (InterruptedException e2) {
                throw new RuntimeException(e2);
            } catch (ExecutionException e3) {
                throw new RuntimeException(e3);
            }
        }
        return this.c;
    }

    public boolean c(Bundle bundle) {
        if (bundle == null) {
            AbstractC1220Ua0.d("CipherFactory", "#restoreFromBundle, no savedInstanceState.", new Object[0]);
            return false;
        }
        byte[] byteArray = bundle.getByteArray("org.chromium.content.browser.crypto.CipherFactory.KEY");
        byte[] byteArray2 = bundle.getByteArray("org.chromium.content.browser.crypto.CipherFactory.IV");
        if (byteArray == null || byteArray2 == null) {
            AbstractC1220Ua0.d("CipherFactory", "#restoreFromBundle, no wrapped key or no iv.", new Object[0]);
            return false;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(byteArray, "AES");
            synchronized (this.f11504a) {
                C4821su suVar = this.c;
                if (suVar == null) {
                    AbstractC1220Ua0.d("CipherFactory", "#restoreFromBundle, creating new CipherData.", new Object[0]);
                    this.c = new C4821su(secretKeySpec, byteArray2);
                    return true;
                } else if (!suVar.f11307a.equals(secretKeySpec) || !Arrays.equals(this.c.b, byteArray2)) {
                    AbstractC1220Ua0.a("CipherFactory", "Attempted to restore different cipher data.", new Object[0]);
                } else {
                    AbstractC1220Ua0.d("CipherFactory", "#restoreFromBundle, using existing CipherData.", new Object[0]);
                    return true;
                }
            }
        } catch (IllegalArgumentException unused) {
            AbstractC1220Ua0.a("CipherFactory", "Error in restoring the key from the bundle.", new Object[0]);
        }
        return false;
    }

    public void d() {
        if (this.c == null) {
            synchronized (this.f11504a) {
                if (this.b == null) {
                    FutureTask futureTask = new FutureTask(new CallableC4651ru(this));
                    this.b = futureTask;
                    ((ExecutorC1463Ya) AbstractC2032cb.f9616a).execute(futureTask);
                }
            }
        }
    }
}
