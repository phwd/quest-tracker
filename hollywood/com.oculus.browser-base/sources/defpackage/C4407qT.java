package defpackage;

import android.graphics.Rect;
import android.transition.Transition;

/* renamed from: qT  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4407qT extends Transition.EpicenterCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Rect f11143a;

    public C4407qT(C4577rT rTVar, Rect rect) {
        this.f11143a = rect;
    }

    public Rect onGetEpicenter(Transition transition) {
        Rect rect = this.f11143a;
        if (rect == null || rect.isEmpty()) {
            return null;
        }
        return this.f11143a;
    }
}
