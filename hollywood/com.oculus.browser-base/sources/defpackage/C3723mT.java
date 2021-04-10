package defpackage;

import android.graphics.Rect;
import android.transition.Transition;

/* renamed from: mT  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3723mT extends Transition.EpicenterCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Rect f10422a;

    public C3723mT(C4577rT rTVar, Rect rect) {
        this.f10422a = rect;
    }

    public Rect onGetEpicenter(Transition transition) {
        return this.f10422a;
    }
}
