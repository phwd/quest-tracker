package defpackage;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: Xf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1417Xf extends EK0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f9226a;

    public C1417Xf(C1478Yf yf, int i) {
        this.f9226a = i;
    }

    @Override // defpackage.EK0
    public void g(Rect rect, View view, RecyclerView recyclerView, VK0 vk0) {
        int i = this.f9226a;
        rect.left = i;
        rect.right = i;
    }
}
