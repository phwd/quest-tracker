package X;

import android.util.Base64;
import com.facebook.cryptopub.CryptoPubNative;
import com.oculus.auth.service.passwordencryption.OculusPasswordEncryptionKeyFetcher;
import com.oculus.auth.service.passwordencryption.OculusPasswordEncryptionKeyStore;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.1Du  reason: invalid class name */
public final class AnonymousClass1Du {
    public static final AnonymousClass1Dq A03 = new AnonymousClass1Dq(41, "-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvcu1KMDR1vzuBr9iYKW8\nKWmhT8CVUBRkchiO8861H7zIOYRwkQrkeHA+0mkBo3Ly1PiLXDkbKQZyeqZbspke\n4e7WgFNwT23jHfRMV/cNPxjPEy4kxNEbzLET6GlWepGdXFhzHfnS1PinGQzj0ZOU\nZM3pQjgGRL9fAf8brt1ewhQ5XtpvKFdPyQq5BkeFEDKoInDsC/yKDWRAx2twgPFr\nCYUzAB8/yXuL30ErTHT79bt3yTnv1fRtE19tROIlBuqruwSBk9gGq/LuvSECgsl5\nz4VcpHXhgZt6MhrAj6y9vAAxO2RVrt0Mq4OY4HgyYz9Wlr1vAxXXGAAYIvrhAYLP\n7QIDAQAB\n-----END PUBLIC KEY-----\n", 0);
    public static final CryptoPubNative A04 = new CryptoPubNative();
    public static final AtomicBoolean A05 = new AtomicBoolean(false);
    public static volatile AnonymousClass1Du A06;
    public OculusPasswordEncryptionKeyStore A00;
    public OculusPasswordEncryptionKeyFetcher A01;
    public AnonymousClass1Dw A02;

    public static AnonymousClass1Du A00(OculusPasswordEncryptionKeyStore oculusPasswordEncryptionKeyStore, OculusPasswordEncryptionKeyFetcher oculusPasswordEncryptionKeyFetcher) {
        AnonymousClass1Dw r1 = AnonymousClass1Dw.DEFAULT_PWD_ENC;
        if (A06 == null) {
            synchronized (AnonymousClass1Du.class) {
                if (A06 == null) {
                    A06 = new AnonymousClass1Du(oculusPasswordEncryptionKeyStore, oculusPasswordEncryptionKeyFetcher, r1);
                    A06.A03(AnonymousClass1Dp.CONTROLLER_INITIALIZATION);
                }
            }
        }
        return A06;
    }

    public static AnonymousClass1E1 A01(String str, String str2) {
        try {
            return new AnonymousClass1E1(Base64.encodeToString(A04.encrypt(41, "-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvcu1KMDR1vzuBr9iYKW8\nKWmhT8CVUBRkchiO8861H7zIOYRwkQrkeHA+0mkBo3Ly1PiLXDkbKQZyeqZbspke\n4e7WgFNwT23jHfRMV/cNPxjPEy4kxNEbzLET6GlWepGdXFhzHfnS1PinGQzj0ZOU\nZM3pQjgGRL9fAf8brt1ewhQ5XtpvKFdPyQq5BkeFEDKoInDsC/yKDWRAx2twgPFr\nCYUzAB8/yXuL30ErTHT79bt3yTnv1fRtE19tROIlBuqruwSBk9gGq/LuvSECgsl5\nz4VcpHXhgZt6MhrAj6y9vAAxO2RVrt0Mq4OY4HgyYz9Wlr1vAxXXGAAYIvrhAYLP\n7QIDAQAB\n-----END PUBLIC KEY-----\n", str, str2), 2), new AnonymousClass1E0("Default key used.", AnonymousClass1Dx.DEFAULT_KEY_FALLBACK));
        } catch (Exception e) {
            return new AnonymousClass1E1(str, new AnonymousClass1E0(e, AnonymousClass1Dx.RAW_PASSWORD_FALLBACK));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x007c, code lost:
        if (r1 == 2779005) goto L_0x007e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final X.AnonymousClass1E1 A02(X.AnonymousClass1Du r15, java.lang.String r16, X.AnonymousClass1Ds r17) {
        /*
        // Method dump skipped, instructions count: 252
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1Du.A02(X.1Du, java.lang.String, X.1Ds):X.1E1");
    }

    public final void A03(AnonymousClass1Dp r6) {
        AnonymousClass1Dq key = this.A00.getKey();
        if (key != null) {
            if (key.A02 != AnonymousClass007.A00) {
                return;
            }
            if (key.A00() && Calendar.getInstance().getTimeInMillis() <= key.A01) {
                return;
            }
        }
        if (A05.compareAndSet(false, true)) {
            this.A01.fetchKey(2, r6, new AnonymousClass1Dv(this));
        }
    }

    public AnonymousClass1Du(OculusPasswordEncryptionKeyStore oculusPasswordEncryptionKeyStore, OculusPasswordEncryptionKeyFetcher oculusPasswordEncryptionKeyFetcher, AnonymousClass1Dw r3) {
        this.A00 = oculusPasswordEncryptionKeyStore;
        this.A01 = oculusPasswordEncryptionKeyFetcher;
        this.A02 = r3;
    }
}
