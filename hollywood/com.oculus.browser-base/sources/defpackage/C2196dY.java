package defpackage;

import java.io.OutputStream;

/* renamed from: dY  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2196dY implements AbstractC2025cY {

    /* renamed from: a  reason: collision with root package name */
    public final String f9788a;

    public C2196dY(String str) {
        this.f9788a = str;
    }

    @Override // defpackage.AbstractC2025cY
    public void a(OutputStream outputStream) {
        outputStream.write(this.f9788a.getBytes());
    }
}
