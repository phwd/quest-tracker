package defpackage;

import android.nfc.NdefMessage;
import android.nfc.tech.Ndef;

/* renamed from: Ro0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1074Ro0 implements AbstractC1135So0 {

    /* renamed from: a  reason: collision with root package name */
    public final Ndef f8854a;

    public C1074Ro0(Ndef ndef) {
        this.f8854a = ndef;
    }

    @Override // defpackage.AbstractC1135So0
    public NdefMessage a() {
        return this.f8854a.getNdefMessage();
    }

    @Override // defpackage.AbstractC1135So0
    public void b(NdefMessage ndefMessage) {
        this.f8854a.writeNdefMessage(ndefMessage);
    }

    @Override // defpackage.AbstractC1135So0
    public boolean c() {
        return this.f8854a.getNdefMessage() == null;
    }
}
