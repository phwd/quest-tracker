package defpackage;

import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.browser.R;

/* renamed from: q10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4333q10 {

    /* renamed from: a  reason: collision with root package name */
    public FrameLayout f11109a;
    public final int b;
    public final int c;
    public boolean d;
    public View e;
    public View f;

    public C4333q10(FrameLayout frameLayout) {
        this.f11109a = frameLayout;
        Resources resources = frameLayout.getContext().getResources();
        this.b = resources.getDimensionPixelSize(R.dimen.f19960_resource_name_obfuscated_RES_2131165615);
        this.c = resources.getDimensionPixelSize(R.dimen.f20010_resource_name_obfuscated_RES_2131165620);
    }

    public void a() {
        if (this.d) {
            float height = (float) this.f11109a.getHeight();
            int childCount = this.f11109a.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.f11109a.getChildAt(i);
                if (!(childAt == this.e || childAt == this.f)) {
                    height = Math.min(height, childAt.getY());
                }
            }
            this.e.setY(height);
            this.f.setY(height);
        }
    }
}
