package defpackage;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

/* renamed from: Bg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0079Bg extends DW0 {
    public final List F;
    public final DD G;
    public Runnable H;

    public C0079Bg(View view) {
        super(view.getContext());
        DD dd = new DD(getContext());
        this.G = dd;
        dd.setLayoutParams(CW0.a());
        addView(dd);
        this.F = new ArrayList();
        if (dd.G != null) {
            dd.removeView(view);
        }
        dd.G = view;
        view.setLayoutParams(CW0.a());
        dd.addView(dd.G);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = getLayoutDirection() == 1;
        if (((!z && R40.d(keyEvent)) || (z && R40.c(keyEvent))) && this.F.size() == 1) {
            ((ImageView) this.F.get(0)).callOnClick();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void setSelected(boolean z) {
        Runnable runnable;
        this.G.setSelected(z);
        if (z && (runnable = this.H) != null) {
            runnable.run();
        }
    }

    public C0079Bg(Context context, int i) {
        this(LayoutInflater.from(context).inflate(i, (ViewGroup) null));
    }
}
