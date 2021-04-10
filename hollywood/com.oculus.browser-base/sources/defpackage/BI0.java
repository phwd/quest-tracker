package defpackage;

import android.graphics.Rect;

/* renamed from: BI0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BI0 implements DI0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7728a;

    public BI0(int i) {
        this.f7728a = i;
    }

    @Override // defpackage.DI0
    public float a(Rect rect) {
        return Math.min((float) this.f7728a, ((float) Math.min(rect.width(), rect.height())) / 2.0f);
    }

    @Override // defpackage.DI0
    public float b(Rect rect) {
        return Math.min(((float) this.f7728a) * 1.2f, ((float) Math.min(rect.width(), rect.height())) / 2.0f);
    }
}
