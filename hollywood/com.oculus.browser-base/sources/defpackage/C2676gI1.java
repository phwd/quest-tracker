package defpackage;

import android.os.Trace;
import java.io.Closeable;
import java.util.Objects;

/* renamed from: gI1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2676gI1 implements Closeable {
    public static final RC1 F = FC1.b().a();
    public final boolean G;

    public C2676gI1(String str) {
        Objects.requireNonNull(F);
        boolean booleanValue = Boolean.TRUE.booleanValue();
        this.G = booleanValue;
        if (booleanValue) {
            Trace.beginSection(str.length() > 127 ? str.substring(0, 127) : str);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.G) {
            Trace.endSection();
        }
    }
}
