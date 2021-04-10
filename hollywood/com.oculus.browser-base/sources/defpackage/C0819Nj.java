package defpackage;

import android.content.Context;
import android.view.GestureDetector;
import android.view.VelocityTracker;
import org.chromium.base.ThreadUtils;

/* renamed from: Nj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0819Nj extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a  reason: collision with root package name */
    public final GestureDetector f8570a;
    public final AbstractC0758Mj b;
    public VelocityTracker c = VelocityTracker.obtain();
    public boolean d;

    public C0819Nj(Context context, AbstractC0758Mj mj) {
        GestureDetector gestureDetector = new GestureDetector(context, new C0698Lj(this, null), ThreadUtils.b());
        this.f8570a = gestureDetector;
        gestureDetector.setIsLongpressEnabled(true);
        this.b = mj;
    }
}
