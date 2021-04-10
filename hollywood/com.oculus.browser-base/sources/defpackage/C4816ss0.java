package defpackage;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;

/* renamed from: ss0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4816ss0 extends LinearLayoutManager {
    public final /* synthetic */ C5836ys0 G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4816ss0(C5836ys0 ys0, Context context) {
        super(1, false);
        this.G = ys0;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.IK0
    public int O0(int i, PK0 pk0, VK0 vk0) {
        int i2;
        if (this.r == 0) {
            i2 = 0;
        } else {
            i2 = B1(i, pk0, vk0);
        }
        if (i2 == 0 && i < 0) {
            C2379ed edVar = (C2379ed) this.G.o1.f11644a.r1;
            if (edVar.R) {
                ((C3909na0) edVar.G).I.i(true, false);
            }
        }
        return i2;
    }
}
