package defpackage;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.concurrent.Callable;
import javax.crypto.KeyGenerator;

/* renamed from: ru  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CallableC4651ru implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5331vu f11232a;

    public CallableC4651ru(C5331vu vuVar) {
        this.f11232a = vuVar;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        try {
            byte[] a2 = this.f11232a.d.a(16);
            try {
                SecureRandom secureRandom = new SecureRandom();
                HR0.a(secureRandom);
                KeyGenerator instance = KeyGenerator.getInstance("AES");
                instance.init(128, secureRandom);
                return new C4821su(instance.generateKey(), a2);
            } catch (IOException unused) {
                AbstractC1220Ua0.a("CipherFactory", "Couldn't get generator data.", new Object[0]);
            } catch (GeneralSecurityException unused2) {
                AbstractC1220Ua0.a("CipherFactory", "Couldn't get generator instances.", new Object[0]);
            }
        } catch (IOException unused3) {
            AbstractC1220Ua0.a("CipherFactory", "Couldn't get generator data.", new Object[0]);
            return null;
        } catch (GeneralSecurityException unused4) {
            AbstractC1220Ua0.a("CipherFactory", "Couldn't get generator data.", new Object[0]);
            return null;
        }
    }
}
