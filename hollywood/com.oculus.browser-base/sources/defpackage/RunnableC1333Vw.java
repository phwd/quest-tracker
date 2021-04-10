package defpackage;

import android.view.ViewGroup;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;

/* renamed from: Vw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1333Vw implements Runnable {
    public final /* synthetic */ ViewGroup F;
    public final /* synthetic */ CompositorViewHolder G;

    public RunnableC1333Vw(CompositorViewHolder compositorViewHolder, ViewGroup viewGroup) {
        this.G = compositorViewHolder;
        this.F = viewGroup;
    }

    public void run() {
        this.G.M.setBackgroundResource(0);
        ViewGroup viewGroup = this.F;
        if (viewGroup != null) {
            viewGroup.setBackgroundResource(0);
        }
    }
}
