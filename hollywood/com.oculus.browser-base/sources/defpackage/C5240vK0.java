package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: vK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5240vK0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ RecyclerView f11475a;

    public C5240vK0(RecyclerView recyclerView) {
        this.f11475a = recyclerView;
    }

    public View a(int i) {
        return this.f11475a.getChildAt(i);
    }

    public int b() {
        return this.f11475a.getChildCount();
    }

    public void c(int i) {
        View childAt = this.f11475a.getChildAt(i);
        if (childAt != null) {
            this.f11475a.q(childAt);
            childAt.clearAnimation();
        }
        this.f11475a.removeViewAt(i);
    }
}
