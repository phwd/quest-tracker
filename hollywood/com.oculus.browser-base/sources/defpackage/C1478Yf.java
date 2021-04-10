package defpackage;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;

/* renamed from: Yf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1478Yf extends LinearLayout {
    public final C1672aX F;
    public final RecyclerView G;
    public final C1356Wf H;

    public C1478Yf(Context context, JW0 jw0) {
        super(context);
        setClickable(false);
        setFocusable(false);
        setOrientation(1);
        setPaddingRelative(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.f23380_resource_name_obfuscated_RES_2131165957));
        C1672aX aXVar = new C1672aX(context);
        this.F = aXVar;
        aXVar.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        aXVar.G.setVisibility(8);
        aXVar.setClickable(false);
        aXVar.setFocusable(false);
        addView(aXVar);
        RecyclerView recyclerView = new RecyclerView(context, null);
        this.G = recyclerView;
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        recyclerView.setFocusable(true);
        recyclerView.setFocusableInTouchMode(true);
        recyclerView.s0(null);
        recyclerView.t0(new LinearLayoutManager(0, false));
        C1356Wf wf = new C1356Wf(recyclerView.U);
        this.H = wf;
        recyclerView.h(wf);
        recyclerView.g(new C1417Xf(this, context.getResources().getDimensionPixelOffset(R.dimen.f23250_resource_name_obfuscated_RES_2131165944)));
        recyclerView.q0(jw0);
        addView(recyclerView);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        int i2;
        boolean z = getLayoutDirection() == 1;
        if ((!z && R40.d(keyEvent)) || (z && R40.c(keyEvent))) {
            C1356Wf wf = this.H;
            IK0 ik0 = wf.b;
            if (ik0 != null) {
                int i3 = wf.f9163a;
                if (i3 == -1) {
                    i2 = 0;
                } else if (i3 < ik0.J()) {
                    i2 = wf.f9163a + 1;
                } else {
                    i2 = wf.b.J() - 1;
                }
                wf.a(i2, false);
            }
            return true;
        } else if ((!z || !R40.d(keyEvent)) && (z || !R40.c(keyEvent))) {
            return super.onKeyDown(i, keyEvent);
        } else {
            C1356Wf wf2 = this.H;
            IK0 ik02 = wf2.b;
            if (ik02 != null) {
                int i4 = wf2.f9163a;
                wf2.a(i4 == -1 ? ik02.J() - 1 : i4 > 0 ? i4 - 1 : 0, false);
            }
            return true;
        }
    }

    public void setSelected(boolean z) {
        if (z) {
            this.H.a(0, true);
        } else {
            this.H.a(-1, false);
        }
    }
}
