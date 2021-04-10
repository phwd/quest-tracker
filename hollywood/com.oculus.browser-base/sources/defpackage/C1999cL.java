package defpackage;

import java.util.Locale;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.chromium.base.Callback;

/* renamed from: cL  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1999cL extends TP {
    @Override // defpackage.AbstractC3511lC0, defpackage.TP
    public void a(int i, String str, Callback callback) {
        super.a(i, str, new C1828bL(callback));
    }

    @Override // defpackage.AbstractC3511lC0, defpackage.TP
    public void d(int i, String str, byte[] bArr) {
        h(i, str, bArr, TP.f8956a);
    }

    @Override // defpackage.TP
    public void h(int i, String str, byte[] bArr, Callback callback) {
        byte[] bArr2;
        try {
            bArr2 = AbstractC5161uu.f11443a.a(1).doFinal(bArr);
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            AbstractC1220Ua0.a("EFPTDS", String.format(Locale.ENGLISH, "Problem encrypting data. Details: %s", e.getMessage()), new Object[0]);
            bArr2 = null;
        }
        super.h(i, str, bArr2, callback);
    }
}
