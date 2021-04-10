package defpackage;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import java.util.Objects;
import org.chromium.chrome.browser.feed.v2.FeedStreamSurface;

/* renamed from: eP  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2348eP extends L21 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ K21 f9852a;
    public final /* synthetic */ C2861hP b;

    public C2348eP(C2861hP hPVar, K21 k21) {
        this.b = hPVar;
        this.f9852a = k21;
    }

    @Override // defpackage.L21
    public void a() {
        Objects.requireNonNull(this.b);
        Objects.requireNonNull(this.b);
        if (this.b.F.b()) {
            FeedStreamSurface feedStreamSurface = ((FO) this.f9852a).f8014a;
            if (feedStreamSurface.s) {
                ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(feedStreamSurface.g.getBackground(), PropertyValuesHolder.ofInt("alpha", 255));
                ofPropertyValuesHolder.setTarget(feedStreamSurface.g.getBackground());
                ofPropertyValuesHolder.setDuration(feedStreamSurface.g.y0.c).setInterpolator(G30.d);
                ofPropertyValuesHolder.start();
                feedStreamSurface.s = false;
            }
        }
    }
}
