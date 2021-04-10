package defpackage;

import android.graphics.Rect;

/* renamed from: BM  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BM implements DI0 {
    @Override // defpackage.DI0
    public float a(Rect rect) {
        return ((float) Math.min(rect.width(), rect.height())) / 3.0f;
    }

    @Override // defpackage.DI0
    public float b(Rect rect) {
        return ((float) Math.min(rect.width(), rect.height())) / 2.0f;
    }
}
