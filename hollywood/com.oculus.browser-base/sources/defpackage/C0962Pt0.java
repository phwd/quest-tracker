package defpackage;

import android.view.GestureDetector;
import android.view.MotionEvent;
import org.chromium.chrome.browser.compositor.bottombar.OverlayPanelContent;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Pt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0962Pt0 extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1084Rt0 f8718a;

    public C0962Pt0(C1084Rt0 rt0, AbstractC0901Ot0 ot0) {
        this.f8718a = rt0;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float f3;
        C1084Rt0 rt0 = this.f8718a;
        int i = 2;
        if (((C1796bA) rt0.p).L != 2) {
            boolean z = true;
            if (!rt0.z) {
                float x = motionEvent2.getX() - motionEvent.getX();
                float y = motionEvent2.getY() - motionEvent.getY();
                if ((y * y) + (x * x) > rt0.s) {
                    rt0.A = Math.abs(motionEvent2.getY() - motionEvent.getY()) * 1.25f > Math.abs(motionEvent2.getX() - motionEvent.getX()) ? 2 : 1;
                    rt0.z = true;
                }
            }
            boolean z2 = rt0.y && motionEvent2.getPointerCount() == 1;
            if (rt0.z && (!rt0.v || z2)) {
                boolean z3 = rt0.A == 2;
                AbstractC0292Et0 et0 = rt0.p;
                if (et0.V) {
                    boolean z4 = f2 < 0.0f;
                    if (z3 && z4) {
                        OverlayPanelContent overlayPanelContent = et0.y0;
                        if (overlayPanelContent != null) {
                            WebContents webContents = overlayPanelContent.f10635a;
                            f3 = webContents != null ? (float) ((WebContentsImpl) webContents).M.e() : -1.0f;
                        } else {
                            f3 = 0.0f;
                        }
                        if (f3 == 0.0f) {
                            z3 = true;
                        }
                    }
                    z3 = false;
                } else if (!z3) {
                    rt0.y = false;
                }
                if (z3) {
                    i = 1;
                }
                int i2 = rt0.t;
                if (i != i2) {
                    rt0.w = i2;
                    rt0.h(i);
                    int i3 = rt0.t;
                    int i4 = rt0.w;
                    if (i3 == i4 || i4 == 0) {
                        z = false;
                    }
                    rt0.x = z;
                }
            }
        }
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
        AbstractC0292Et0 et0 = this.f8718a.p;
        float x = motionEvent.getX() * this.f8718a.f9077a;
        C1796bA bAVar = (C1796bA) et0;
        if (bAVar.f0(x, motionEvent.getY() * this.f8718a.f9077a)) {
            C4666rz s0 = bAVar.s0();
            s0.d(x * s0.m);
        }
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        C1084Rt0 rt0 = this.f8718a;
        AbstractC0292Et0 et0 = rt0.p;
        int i = 2;
        if (((C1796bA) et0).L == 2) {
            return false;
        }
        if (!et0.g0(motionEvent.getX() * rt0.f9077a, motionEvent.getY() * rt0.f9077a)) {
            i = 1;
        }
        rt0.h(i);
        return false;
    }
}
