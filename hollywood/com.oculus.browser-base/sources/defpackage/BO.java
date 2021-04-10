package defpackage;

import J.N;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashSet;
import org.chromium.chrome.browser.feed.v2.FeedStreamSurface;

/* renamed from: BO  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BO implements ViewTreeObserver.OnPreDrawListener {
    public RecyclerView F;
    public C5757yO G;
    public HashSet H = new HashSet();
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public PO f7734J;

    public BO(RecyclerView recyclerView, C5757yO yOVar, PO po) {
        this.F = recyclerView;
        this.G = yOVar;
        this.f7734J = po;
        recyclerView.getViewTreeObserver().addOnPreDrawListener(this);
    }

    public boolean onPreDraw() {
        View u;
        RecyclerView recyclerView = this.F;
        if (recyclerView == null) {
            return true;
        }
        IK0 ik0 = recyclerView.U;
        if (!(ik0 instanceof LinearLayoutManager)) {
            return true;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) ik0;
        int k1 = linearLayoutManager.k1();
        int l1 = linearLayoutManager.l1();
        while (k1 <= l1 && k1 < this.G.a() && k1 >= 0) {
            String str = this.G.c(k1).f11544a;
            if (str.startsWith("c/") && (u = linearLayoutManager.u(k1)) != null) {
                if (!this.I) {
                    this.I = true;
                    FeedStreamSurface feedStreamSurface = this.f7734J.f8688a;
                    N.MRKpGyZI(feedStreamSurface.d, feedStreamSurface);
                }
                if (!this.H.contains(str)) {
                    boolean z = false;
                    Rect rect = new Rect(0, 0, u.getWidth(), u.getHeight());
                    int height = rect.height() * rect.width();
                    if (height > 0 && this.F.getChildVisibleRect(u, rect, null)) {
                        if (((double) (((float) (rect.height() * rect.width())) / ((float) height))) >= 0.66d) {
                            z = true;
                        }
                    }
                    if (z) {
                        this.H.add(str);
                        FeedStreamSurface feedStreamSurface2 = this.f7734J.f8688a;
                        N.M3gvoCIU(feedStreamSurface2.d, feedStreamSurface2, str);
                    }
                }
            }
            k1++;
        }
        return true;
    }
}
