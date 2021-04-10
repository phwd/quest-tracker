package defpackage;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: BY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BY0 extends E80 {
    public final /* synthetic */ CY0 q;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BY0(CY0 cy0, Context context) {
        super(context);
        this.q = cy0;
    }

    @Override // defpackage.E80
    public float b(DisplayMetrics displayMetrics) {
        return 100.0f / ((float) displayMetrics.densityDpi);
    }

    @Override // defpackage.E80
    public void g(View view, VK0 vk0, TK0 tk0) {
        CY0 cy0 = this.q;
        RecyclerView recyclerView = cy0.f7818a;
        if (recyclerView != null) {
            int[] b = cy0.b(recyclerView.U, view);
            int i = b[0];
            int i2 = b[1];
            int c = c(Math.max(Math.abs(i), Math.abs(i2)));
            if (c > 0) {
                tk0.b(i, i2, c, this.j);
            }
        }
    }
}
