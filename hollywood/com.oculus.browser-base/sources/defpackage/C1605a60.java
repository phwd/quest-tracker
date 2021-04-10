package defpackage;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: a60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1605a60 extends EK0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f9408a;

    public C1605a60(int i) {
        this.f9408a = i;
    }

    @Override // defpackage.EK0
    public void g(Rect rect, View view, RecyclerView recyclerView, VK0 vk0) {
        if (LocalizationUtils.isLayoutRtl()) {
            rect.right = j(view, recyclerView, vk0);
        } else {
            rect.left = j(view, recyclerView, vk0);
        }
    }

    public int j(View view, RecyclerView recyclerView, VK0 vk0) {
        return this.f9408a;
    }
}
