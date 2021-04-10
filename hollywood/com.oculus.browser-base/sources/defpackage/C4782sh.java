package defpackage;

import java.nio.ByteBuffer;

/* renamed from: sh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4782sh implements AutoCloseable {
    public final GU0 F;
    public final ByteBuffer G;

    public C4782sh(GU0 gu0, ByteBuffer byteBuffer) {
        this.F = gu0;
        this.G = byteBuffer;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        GU0 gu0 = this.F;
        if (gu0 != null) {
            gu0.w(this.G);
        }
    }
}
