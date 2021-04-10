package defpackage;

import android.content.SharedPreferences;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: F71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class F71 implements Runnable {
    public final I71 F;
    public final int G;

    public F71(I71 i71, int i) {
        this.F = i71;
        this.G = i;
    }

    public void run() {
        I71 i71 = this.F;
        int i = this.G;
        Objects.requireNonNull(i71);
        Object obj = ThreadUtils.f10596a;
        StringBuilder i2 = AbstractC2531fV.i("SessionsCountForGroup-");
        i2.append(Integer.toString(i));
        String sb = i2.toString();
        SharedPreferences W = i71.W();
        int i3 = W.getInt(sb, 0) + 1;
        W.edit().putInt(sb, i3).apply();
        AbstractC3364kK0.d("TabGroups.SessionsPerGroup", i3);
    }
}
