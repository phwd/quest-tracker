package defpackage;

import android.nfc.tech.TagTechnology;

/* renamed from: To0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1196To0 {

    /* renamed from: a  reason: collision with root package name */
    public final TagTechnology f8984a;
    public final AbstractC1135So0 b;
    public boolean c;
    public final String d;

    public C1196To0(TagTechnology tagTechnology, AbstractC1135So0 so0, byte[] bArr) {
        String str;
        this.f8984a = tagTechnology;
        this.b = so0;
        if (bArr.length < 0) {
            str = null;
        } else {
            StringBuilder sb = new StringBuilder(bArr.length * 3);
            for (byte b2 : bArr) {
                if (sb.length() > 0) {
                    sb.append(":");
                }
                sb.append(String.format("%02x", Integer.valueOf(b2 & 255)));
            }
            str = sb.toString();
        }
        this.d = str;
    }

    public void a() {
        if (!this.f8984a.isConnected()) {
            this.f8984a.connect();
            this.c = true;
        }
    }
}
