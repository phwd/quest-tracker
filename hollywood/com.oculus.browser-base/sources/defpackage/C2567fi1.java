package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;

/* renamed from: fi1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2567fi1 extends EK0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f9943a;
    public final /* synthetic */ C2909hi1 b;

    public C2567fi1(C2909hi1 hi1, Context context) {
        this.b = hi1;
        this.f9943a = context.getResources().getDimensionPixelOffset(R.dimen.f26030_resource_name_obfuscated_RES_2131166222);
    }

    @Override // defpackage.EK0
    public void g(Rect rect, View view, RecyclerView recyclerView, VK0 vk0) {
        int K = recyclerView.K(view);
        if (K != 0) {
            rect.left = this.f9943a / 2;
        }
        if (K != this.b.f10094a.size() - 1) {
            rect.right = this.f9943a / 2;
        }
    }
}
