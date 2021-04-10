package defpackage;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import java.lang.ref.WeakReference;

/* renamed from: RT  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RT extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference f8833a;

    public RT(ST st) {
        this.f8833a = new WeakReference(st);
    }

    public void handleMessage(Message message) {
        ST st;
        View view;
        if (message != null && (st = (ST) this.f8833a.get()) != null && st.L != null && (view = st.M) != null) {
            int systemUiVisibility = view.getSystemUiVisibility();
            int i = message.what;
            if (i != 1) {
                if (i == 2 && st.a() && (systemUiVisibility & 1024) != 0) {
                    view.setSystemUiVisibility(systemUiVisibility & -1025);
                    st.h(67108864);
                }
            } else if ((systemUiVisibility & 4) != 4) {
                view.setSystemUiVisibility(st.g(systemUiVisibility));
                view.addOnLayoutChangeListener(new QT(this, view));
                view.requestLayout();
            }
        }
    }
}
