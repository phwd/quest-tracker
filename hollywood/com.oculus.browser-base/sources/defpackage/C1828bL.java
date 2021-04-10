package defpackage;

import java.util.Locale;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.chromium.base.Callback;

/* renamed from: bL  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1828bL extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f9535a;

    public C1828bL(Callback callback) {
        this.f9535a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Callback callback = this.f9535a;
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = null;
        if (bArr != null) {
            try {
                bArr2 = AbstractC5161uu.f11443a.a(2).doFinal(bArr);
            } catch (BadPaddingException | IllegalBlockSizeException e) {
                AbstractC1220Ua0.a("EFPTDS", String.format(Locale.ENGLISH, "Error encrypting data. Details: %s", e.getMessage()), new Object[0]);
            }
        }
        callback.onResult(bArr2);
    }
}
