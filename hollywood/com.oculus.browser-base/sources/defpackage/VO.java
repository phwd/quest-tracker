package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import java.util.Objects;

/* renamed from: VO  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VO extends FrameLayout {
    public final /* synthetic */ XO F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VO(XO xo, Context context) {
        super(context);
        this.F = xo;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.F.n.b();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (super.onInterceptTouchEvent(motionEvent)) {
            return true;
        }
        XO xo = this.F;
        C2861hP hPVar = xo.j;
        Objects.requireNonNull(xo.g);
        return false;
    }
}
