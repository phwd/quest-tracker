package defpackage;

import java.io.FileOutputStream;

/* renamed from: OT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class OT0 implements XT0 {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f8626a;

    public OT0(byte[] bArr) {
        this.f8626a = bArr;
    }

    @Override // defpackage.XT0
    public void a(FileOutputStream fileOutputStream) {
        fileOutputStream.write(this.f8626a);
    }
}
