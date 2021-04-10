package defpackage;

import J.N;
import android.util.TypedValue;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.feed.v2.FeedStreamSurface;

/* renamed from: CO  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CO extends MK0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FO f7805a;

    public CO(FO fo) {
        this.f7805a = fo;
    }

    @Override // defpackage.MK0
    public void a(RecyclerView recyclerView, int i) {
        Iterator it = this.f7805a.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((WW) ((M21) uq0.next())).a(i);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.MK0
    public void b(RecyclerView recyclerView, int i, int i2) {
        boolean z;
        FO fo = this.f7805a;
        if (fo.f8014a.m) {
            int i3 = fo.d + i2;
            fo.d = i3;
            if (((float) i3) >= TypedValue.applyDimension(1, 100.0f, fo.c.getResources().getDisplayMetrics())) {
                FeedStreamSurface feedStreamSurface = fo.f8014a;
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) feedStreamSurface.g.U;
                if (linearLayoutManager != null && linearLayoutManager.J() - linearLayoutManager.l1() <= feedStreamSurface.q) {
                    if (!feedStreamSurface.r) {
                        feedStreamSurface.r = true;
                        PostTask.b(Zo1.f9374a, new GO(feedStreamSurface), 0);
                    }
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    fo.d = 0;
                }
            }
        }
        FeedStreamSurface feedStreamSurface2 = this.f7805a.f8014a;
        N.Ml_7jRWP(feedStreamSurface2.d, feedStreamSurface2);
        NO no = feedStreamSurface2.j;
        Objects.requireNonNull(no);
        if (i2 != 0) {
            int i4 = no.c;
            if (i4 != 0) {
                if ((i2 > 0) != (i4 > 0)) {
                    no.a();
                }
            }
            no.c += i2;
            if (no.f8544a == null) {
                no.b = true;
                SP0 sp0 = new SP0(no, null);
                no.f8544a = sp0;
                PostTask.b(Zo1.f9374a, sp0, 200);
            } else {
                no.b = false;
            }
        }
        Iterator it = this.f7805a.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                Objects.requireNonNull((WW) ((M21) uq0.next()));
            } else {
                return;
            }
        }
    }
}
