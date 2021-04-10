package defpackage;

import android.content.Context;
import android.view.MotionEvent;

/* renamed from: Qt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1023Qt0 extends AbstractC5364w41 {
    public final /* synthetic */ C1084Rt0 g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1023Qt0(C1084Rt0 rt0, Context context) {
        super(context, rt0.p);
        this.g = rt0;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.g.p.a0(motionEvent.getX() * this.g.f9077a, motionEvent.getY() * this.g.f9077a);
        return true;
    }
}
