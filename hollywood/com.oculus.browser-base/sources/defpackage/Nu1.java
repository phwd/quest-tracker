package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Objects;

/* renamed from: Nu1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Nu1 extends LinearLayoutManager {
    public final /* synthetic */ ViewPager2 G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Nu1(ViewPager2 viewPager2, Context context) {
        super(1, false);
        this.G = viewPager2;
    }

    @Override // defpackage.IK0
    public boolean D0(PK0 pk0, VK0 vk0, int i, Bundle bundle) {
        Objects.requireNonNull(this.G.a0);
        return super.D0(pk0, vk0, i, bundle);
    }

    @Override // defpackage.IK0
    public boolean K0(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        return false;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void a1(VK0 vk0, int[] iArr) {
        int i;
        int i2;
        ViewPager2 viewPager2 = this.G;
        int i3 = viewPager2.W;
        if (i3 == -1) {
            super.a1(vk0, iArr);
            return;
        }
        RecyclerView recyclerView = viewPager2.P;
        if (viewPager2.M.r == 0) {
            i2 = recyclerView.getWidth() - recyclerView.getPaddingLeft();
            i = recyclerView.getPaddingRight();
        } else {
            i2 = recyclerView.getHeight() - recyclerView.getPaddingTop();
            i = recyclerView.getPaddingBottom();
        }
        int i4 = (i2 - i) * i3;
        iArr[0] = i4;
        iArr[1] = i4;
    }

    @Override // defpackage.IK0
    public void l0(PK0 pk0, VK0 vk0, D d) {
        super.l0(pk0, vk0, d);
        Objects.requireNonNull(this.G.a0);
    }

    @Override // defpackage.IK0
    public void m0(PK0 pk0, VK0 vk0, View view, D d) {
        Su1 su1 = (Su1) this.G.a0;
        LinearLayoutManager linearLayoutManager = su1.d.M;
        int i = 0;
        int R = linearLayoutManager.r == 1 ? linearLayoutManager.R(view) : 0;
        LinearLayoutManager linearLayoutManager2 = su1.d.M;
        if (linearLayoutManager2.r == 0) {
            i = linearLayoutManager2.R(view);
        }
        d.j(C.a(R, 1, i, 1, false, false));
    }
}
