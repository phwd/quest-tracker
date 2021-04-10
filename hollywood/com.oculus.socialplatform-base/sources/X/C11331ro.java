package X;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.appcompat.widget.ContentFrameLayout;

/* renamed from: X.1ro  reason: invalid class name and case insensitive filesystem */
public class C11331ro extends ContentFrameLayout {
    public final /* synthetic */ AnonymousClass1rJ A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C11331ro(AnonymousClass1rJ r2, Context context) {
        super(context, null);
        this.A00 = r2;
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.A00.A0R(keyEvent) || super.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        return false;
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (x < -5 || y < -5 || x > getWidth() + 5 || y > getHeight() + 5) {
                AnonymousClass1rJ r2 = this.A00;
                r2.A0P(r2.A0N(0), true);
                return true;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public final void setBackgroundResource(int i) {
        setBackgroundDrawable(AnonymousClass1pW.A00(getContext(), i));
    }
}
