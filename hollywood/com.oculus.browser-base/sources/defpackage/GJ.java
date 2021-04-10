package defpackage;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;

/* renamed from: GJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GJ extends EK0 {

    /* renamed from: a  reason: collision with root package name */
    public final Class f8084a;

    public GJ(Class cls) {
        this.f8084a = cls;
    }

    @Override // defpackage.EK0
    public void g(Rect rect, View view, RecyclerView recyclerView, VK0 vk0) {
        int indexOfChild;
        ((JK0) view.getLayoutParams()).a();
        rect.set(0, 0, 0, 0);
        if (!view.getClass().getCanonicalName().equals(this.f8084a.getCanonicalName()) && recyclerView.indexOfChild(view) - 1 >= 0 && recyclerView.getChildAt(indexOfChild).getClass().getCanonicalName().equals(this.f8084a.getCanonicalName())) {
            rect.top = view.getContext().getResources().getDimensionPixelSize(R.dimen.f20310_resource_name_obfuscated_RES_2131165650);
        }
    }
}
