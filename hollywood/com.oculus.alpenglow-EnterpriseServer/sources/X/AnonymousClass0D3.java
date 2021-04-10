package X;

import android.graphics.Rect;
import android.view.View;
import java.util.ArrayList;

/* renamed from: X.0D3  reason: invalid class name */
public class AnonymousClass0D3 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransition$6";
    public final /* synthetic */ Rect A00;
    public final /* synthetic */ View A01;
    public final /* synthetic */ C03960dj A02;
    public final /* synthetic */ AnonymousClass0MN A03;
    public final /* synthetic */ AnonymousClass0MN A04;
    public final /* synthetic */ AnonymousClass0D5 A05;
    public final /* synthetic */ AnonymousClass0DF A06;
    public final /* synthetic */ Object A07;
    public final /* synthetic */ Object A08;
    public final /* synthetic */ ArrayList A09;
    public final /* synthetic */ ArrayList A0A;
    public final /* synthetic */ boolean A0B;

    public AnonymousClass0D3(AnonymousClass0DF r1, C03960dj r2, Object obj, AnonymousClass0D5 r4, ArrayList arrayList, View view, AnonymousClass0MN r7, AnonymousClass0MN r8, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
        this.A06 = r1;
        this.A02 = r2;
        this.A08 = obj;
        this.A05 = r4;
        this.A09 = arrayList;
        this.A01 = view;
        this.A03 = r7;
        this.A04 = r8;
        this.A0B = z;
        this.A0A = arrayList2;
        this.A07 = obj2;
        this.A00 = rect;
    }

    public final void run() {
        AnonymousClass0DF r3 = this.A06;
        C03960dj r0 = this.A02;
        Object obj = this.A08;
        r0.clear();
        if (obj != null) {
            r3.A0C(obj, this.A0A, this.A09);
        }
    }
}
