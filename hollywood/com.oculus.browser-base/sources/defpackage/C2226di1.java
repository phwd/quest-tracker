package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: di1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2226di1 extends RecyclerView {
    public final /* synthetic */ C2909hi1 k1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2226di1(C2909hi1 hi1, Context context) {
        super(context, null);
        this.k1 = hi1;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        q0(null);
        q0(this.k1.c);
        this.k1.f.a();
    }
}
