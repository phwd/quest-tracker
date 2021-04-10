package defpackage;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import java.util.Iterator;

/* renamed from: Bd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0071Bd0 implements Iterable {
    public MediaCodecInfo[] F;

    public C0071Bd0() {
        try {
            this.F = new MediaCodecList(1).getCodecInfos();
        } catch (Throwable unused) {
        }
    }

    public static int a(C0071Bd0 bd0) {
        if (bd0.b()) {
            return bd0.F.length;
        }
        try {
            return MediaCodecList.getCodecCount();
        } catch (RuntimeException unused) {
            return 0;
        }
    }

    public final boolean b() {
        return this.F != null;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new C0010Ad0(this, null);
    }
}
