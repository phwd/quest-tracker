package defpackage;

import android.os.RemoteException;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/* renamed from: yF1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractBinderC5737yF1 extends AbstractBinderC5913zH1 {

    /* renamed from: a  reason: collision with root package name */
    public int f11672a;

    public AbstractBinderC5737yF1(byte[] bArr) {
        SE0.a(bArr.length == 25);
        this.f11672a = Arrays.hashCode(bArr);
    }

    public static byte[] f(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public abstract byte[] d();

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof TG1)) {
            try {
                TG1 tg1 = (TG1) obj;
                if (((AbstractBinderC5737yF1) tg1).f11672a != this.f11672a) {
                    return false;
                }
                return Arrays.equals(d(), (byte[]) BinderC0773Mq0.f(new BinderC0773Mq0(((AbstractBinderC5737yF1) tg1).d())));
            } catch (RemoteException e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            }
        }
        return false;
    }

    public int hashCode() {
        return this.f11672a;
    }
}
