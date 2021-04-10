package org.chromium.components.paintpreview.player;

import J.N;
import android.graphics.Rect;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.SysUtils;
import org.chromium.base.UnguessableToken;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PlayerCompositorDelegateImpl extends AbstractC5900zD0 {

    /* renamed from: a  reason: collision with root package name */
    public AD0 f10870a;
    public long b;
    public List c = new ArrayList();

    public PlayerCompositorDelegateImpl(AbstractC0156Cm0 cm0, C1149Sv0 sv0, GURL gurl, String str, boolean z, AD0 ad0, Callback callback) {
        this.f10870a = ad0;
        if (cm0 != null && cm0.a() != 0) {
            this.b = N.MP_1CaX6(this, cm0.a(), sv0 != null ? sv0.c() : null, gurl.h(), str, z, callback, SysUtils.a() < 2048);
        }
    }

    @Override // defpackage.AbstractC5900zD0
    public int a(UnguessableToken unguessableToken, Rect rect, float f, Callback callback, Runnable runnable) {
        long j = this.b;
        if (j == 0) {
            return -1;
        }
        return N.MiIDqW7F(j, unguessableToken, callback, runnable, f, rect.left, rect.top, rect.width(), rect.height());
    }

    public void onCompositorReady(UnguessableToken unguessableToken, UnguessableToken[] unguessableTokenArr, int[] iArr, int[] iArr2, int[] iArr3, UnguessableToken[] unguessableTokenArr2, int[] iArr4) {
        this.f10870a.a(unguessableToken, unguessableTokenArr, iArr, iArr2, iArr3, unguessableTokenArr2, iArr4);
    }

    public void onModerateMemoryPressure() {
        for (Runnable runnable : this.c) {
            runnable.run();
        }
    }
}
