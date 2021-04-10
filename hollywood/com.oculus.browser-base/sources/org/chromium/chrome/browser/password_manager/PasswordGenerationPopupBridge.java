package org.chromium.chrome.browser.password_manager;

import J.N;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import com.oculus.browser.R;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordGenerationPopupBridge implements PopupWindow.OnDismissListener {
    public final long F;
    public final Context G;
    public final BJ H;
    public final View I;

    public PasswordGenerationPopupBridge(View view, long j, WindowAndroid windowAndroid) {
        this.F = j;
        Context context = (Context) windowAndroid.s0().get();
        this.G = context;
        this.I = view;
        if (context == null) {
            this.H = null;
            new Handler().post(new RunnableC2443ey0(this));
            return;
        }
        BJ bj = new BJ(context, view);
        this.H = bj;
        ((EJ) bj.F).L.P.b(this);
        O4 o4 = ((EJ) bj.F).L;
        o4.O = false;
        o4.K.setOutsideTouchable(false);
        ((EJ) bj.F).K = context.getString(R.string.f57950_resource_name_obfuscated_RES_2131953112);
    }

    public static PasswordGenerationPopupBridge create(View view, long j, WindowAndroid windowAndroid) {
        return new PasswordGenerationPopupBridge(view, j, windowAndroid);
    }

    public final void hide() {
        BJ bj = this.H;
        if (bj != null) {
            bj.a();
        }
    }

    public void onDismiss() {
        N.M6qXk$DQ(this.F, this);
    }

    public final void show(boolean z, String str) {
        if (this.H != null) {
            int i = this.I.getLayoutParams().width;
            C2272dy0 dy0 = new C2272dy0(this.G, str);
            EJ ej = (EJ) this.H.F;
            ej.M = dy0;
            ej.O.setAdapter((ListAdapter) dy0);
            ej.L.e();
            EJ ej2 = (EJ) this.H.F;
            ej2.H = z;
            ej2.b();
        }
    }
}
