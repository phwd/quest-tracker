package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import java.util.Objects;
import org.chromium.base.ContextUtils;

/* renamed from: vq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractActivityC5319vq extends I7 implements AbstractC3441kp0 {
    public AbstractC3270jp0 U;
    public int V;

    @Override // defpackage.AbstractC3441kp0
    public void C() {
        if (!isFinishing()) {
            recreate();
        }
    }

    @Override // defpackage.I7
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        this.U = IV.a();
        Configuration configuration = new Configuration();
        configuration.fontScale = 0.0f;
        if (f0(context, configuration)) {
            applyOverrideConfiguration(configuration);
        }
    }

    public boolean f0(Context context, Configuration configuration) {
        AbstractC3270jp0 jp0 = this.U;
        Objects.requireNonNull(jp0);
        configuration.uiMode = (jp0.c() ? 32 : 16) | (configuration.uiMode & -49);
        return true;
    }

    public SharedPreferences getSharedPreferences(String str, int i) {
        return ContextUtils.getApplicationContext().getSharedPreferences(str, i);
    }

    @Override // defpackage.AbstractActivityC3892nS, defpackage.I7
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean c = this.U.c();
        int i = this.V;
        if ((c ? 32 : 16) != (configuration.uiMode & 48) && i != 0) {
            setTheme(i);
            getTheme().applyStyle(i, true);
        }
    }

    @Override // defpackage.AbstractActivityC3892nS, defpackage.I7, defpackage.AbstractActivityC3119iw
    public void onCreate(Bundle bundle) {
        KS Y = Y();
        C5271va vaVar = AbstractC2369eZ0.f9859a;
        Y.r = new C2199dZ0();
        this.U.b(this);
        super.onCreate(bundle);
        BV bv = BV.f7741a;
        if (bv.d) {
            Configuration a2 = bv.a(this);
            Resources resources = getResources();
            resources.updateConfiguration(a2, resources.getDisplayMetrics());
        }
    }

    @Override // defpackage.AbstractActivityC3892nS, defpackage.I7
    public void onDestroy() {
        this.U.d(this);
        super.onDestroy();
    }

    @Override // defpackage.I7, android.view.ContextThemeWrapper, android.app.Activity
    public void setTheme(int i) {
        super.setTheme(i);
        this.V = i;
    }
}
