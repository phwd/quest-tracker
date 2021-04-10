package defpackage;

import android.content.SharedPreferences;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: G71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class G71 implements Runnable {
    public final I71 F;
    public final int G;

    public G71(I71 i71, int i) {
        this.F = i71;
        this.G = i;
    }

    public void run() {
        I71 i71 = this.F;
        int i = this.G;
        Objects.requireNonNull(i71);
        Object obj = ThreadUtils.f10596a;
        SharedPreferences W = i71.W();
        StringBuilder i2 = AbstractC2531fV.i("SessionsCountForGroup-");
        i2.append(Integer.toString(i));
        String sb = i2.toString();
        if (W.contains(sb)) {
            W.edit().remove(sb).apply();
        }
    }
}
