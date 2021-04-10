package defpackage;

import android.graphics.Bitmap;
import android.os.SystemClock;
import android.view.View;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;

/* renamed from: M91  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class M91 extends View$OnLayoutChangeListenerC2948hv1 {
    public long L;
    public final /* synthetic */ TabListRecyclerView M;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public M91(TabListRecyclerView tabListRecyclerView, View view) {
        super(view);
        this.M = tabListRecyclerView;
    }

    @Override // defpackage.View$OnLayoutChangeListenerC2948hv1, defpackage.AbstractC3197jM0
    public Bitmap c() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Bitmap c = super.c();
        long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
        if (elapsedRealtime2 == 0) {
            elapsedRealtime2 = 1;
        }
        float C0 = TabListRecyclerView.C0(this.M);
        TabListRecyclerView.C0(this.M);
        this.L = SystemClock.elapsedRealtime() + Math.min((long) (((1.0f - C0) * ((float) elapsedRealtime2)) / C0), 300L);
        return c;
    }

    @Override // defpackage.View$OnLayoutChangeListenerC2948hv1, defpackage.HJ
    public boolean e() {
        boolean e = super.e();
        if (e) {
            this.M.s1 = SystemClock.elapsedRealtime();
        }
        if (SystemClock.elapsedRealtime() < this.L) {
            return false;
        }
        return e;
    }
}
