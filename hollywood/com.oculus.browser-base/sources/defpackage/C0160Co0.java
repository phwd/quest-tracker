package defpackage;

import org.chromium.device.nfc.NfcDelegate;

/* renamed from: Co0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0160Co0 implements AbstractC3655m30 {

    /* renamed from: a  reason: collision with root package name */
    public NfcDelegate f7840a;

    public C0160Co0(NfcDelegate nfcDelegate) {
        this.f7840a = nfcDelegate;
    }

    @Override // defpackage.AbstractC3655m30
    public AbstractC3313k30 a() {
        return new C0221Do0(this.f7840a);
    }
}
