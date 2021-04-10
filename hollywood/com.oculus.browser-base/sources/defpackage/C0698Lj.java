package defpackage;

import android.view.GestureDetector;
import android.view.MotionEvent;
import org.chromium.components.browser_ui.bottomsheet.BottomSheet;

/* renamed from: Lj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0698Lj extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0819Nj f8434a;

    public C0698Lj(C0819Nj nj, AbstractC0637Kj kj) {
        this.f8434a = nj;
    }

    public boolean onDown(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        return ((BottomSheet) this.f8434a.b).w(motionEvent, motionEvent);
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (motionEvent == null || !((BottomSheet) this.f8434a.b).w(motionEvent, motionEvent2)) {
            return false;
        }
        C0819Nj nj = this.f8434a;
        if (!nj.d) {
            return false;
        }
        nj.d = false;
        AbstractC0758Mj mj = nj.b;
        BottomSheet bottomSheet = (BottomSheet) mj;
        bottomSheet.t(AbstractC4089od0.b(((BottomSheet) mj).T + (((-f2) * 218.0f) / 2000.0f), bottomSheet.g(), ((BottomSheet) this.f8434a.b).f()), true);
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
        this.f8434a.d = false;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (motionEvent == null || !((BottomSheet) this.f8434a.b).w(motionEvent, motionEvent2)) {
            return false;
        }
        float abs = Math.abs(f) > 0.0f ? Math.abs(f2) / Math.abs(f) : 2.0f;
        C0819Nj nj = this.f8434a;
        if (nj.d || abs >= 2.0f) {
            nj.c.addMovement(motionEvent2);
            BottomSheet bottomSheet = (BottomSheet) this.f8434a.b;
            boolean a2 = AbstractC4089od0.a(bottomSheet.T, bottomSheet.f());
            BottomSheet bottomSheet2 = (BottomSheet) this.f8434a.b;
            bottomSheet2.d0.getLocationInWindow(bottomSheet2.f10813J);
            if (!(((float) (bottomSheet2.d0.getHeight() + bottomSheet2.f10813J[1])) > motionEvent2.getRawY()) && a2) {
                AbstractC4277pj pjVar = ((BottomSheet) this.f8434a.b).a0;
                if (!(pjVar == null || pjVar.q() <= 0)) {
                    return false;
                }
            }
            if (a2 && f2 > 0.0f) {
                return false;
            }
            BottomSheet bottomSheet3 = (BottomSheet) this.f8434a.b;
            if (AbstractC4089od0.a(bottomSheet3.T, bottomSheet3.g()) && f2 < 0.0f) {
                return false;
            }
            C0819Nj nj2 = this.f8434a;
            BottomSheet bottomSheet4 = (BottomSheet) nj2.b;
            nj2.d = true;
            bottomSheet4.t(AbstractC4089od0.b(bottomSheet4.T + f2, bottomSheet4.g(), ((BottomSheet) this.f8434a.b).f()), false);
            return true;
        }
        nj.c.clear();
        return false;
    }
}
