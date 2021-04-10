package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;

/* renamed from: O91  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class O91 extends MK0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TabListRecyclerView f8604a;

    public O91(TabListRecyclerView tabListRecyclerView, L91 l91) {
        this.f8604a = tabListRecyclerView;
    }

    @Override // defpackage.MK0
    public void b(RecyclerView recyclerView, int i, int i2) {
        int computeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        boolean z = false;
        if (computeVerticalScrollOffset == 0) {
            this.f8604a.E0(false);
        } else if (i2 != 0 && recyclerView.z0 != 2) {
            TabListRecyclerView tabListRecyclerView = this.f8604a;
            if (computeVerticalScrollOffset > 0) {
                z = true;
            }
            tabListRecyclerView.E0(z);
        }
    }
}
