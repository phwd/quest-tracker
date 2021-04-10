package defpackage;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.NdefFormatable;

/* renamed from: Qo0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1013Qo0 implements AbstractC1135So0 {

    /* renamed from: a  reason: collision with root package name */
    public final NdefFormatable f8786a;

    public C1013Qo0(NdefFormatable ndefFormatable) {
        this.f8786a = ndefFormatable;
    }

    @Override // defpackage.AbstractC1135So0
    public NdefMessage a() {
        return new NdefMessage(new NdefRecord(0, null, null, null), new NdefRecord[0]);
    }

    @Override // defpackage.AbstractC1135So0
    public void b(NdefMessage ndefMessage) {
        this.f8786a.format(ndefMessage);
    }

    @Override // defpackage.AbstractC1135So0
    public boolean c() {
        return true;
    }
}
