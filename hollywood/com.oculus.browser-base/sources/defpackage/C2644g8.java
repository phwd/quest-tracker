package defpackage;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.appcompat.widget.ContentFrameLayout;

/* renamed from: g8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2644g8 extends ContentFrameLayout {
    public final /* synthetic */ LayoutInflater$Factory2C3156j8 N;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2644g8(LayoutInflater$Factory2C3156j8 j8Var, Context context) {
        super(context, null);
        this.N = j8Var;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.N.w(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (x < -5 || y < -5 || x > getWidth() + 5 || y > getHeight() + 5) {
                LayoutInflater$Factory2C3156j8 j8Var = this.N;
                j8Var.u(j8Var.D(0), true);
                return true;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setBackgroundResource(int i) {
        setBackgroundDrawable(AbstractC5544x8.a(getContext(), i));
    }
}
