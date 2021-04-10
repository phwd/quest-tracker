package defpackage;

import android.graphics.Rect;
import android.view.TouchDelegate;
import java.util.Objects;
import org.chromium.chrome.browser.ntp.snippets.SectionHeaderView;

/* renamed from: pR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4233pR0 implements Runnable {
    public final SectionHeaderView F;
    public final int G;

    public RunnableC4233pR0(SectionHeaderView sectionHeaderView, int i) {
        this.F = sectionHeaderView;
        this.G = i;
    }

    public void run() {
        SectionHeaderView sectionHeaderView = this.F;
        int i = this.G;
        Objects.requireNonNull(sectionHeaderView);
        Rect rect = new Rect();
        sectionHeaderView.H.getHitRect(rect);
        rect.top -= i;
        rect.bottom += i;
        rect.left -= i;
        rect.right += i;
        sectionHeaderView.setTouchDelegate(new TouchDelegate(rect, sectionHeaderView.H));
    }
}
