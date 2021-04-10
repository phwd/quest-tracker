package defpackage;

import J.N;
import java.nio.ByteBuffer;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: Tb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1163Tb1 {
    public static C0797Nb1 a(Tab tab) {
        ByteBuffer byteBuffer;
        TabImpl tabImpl = (TabImpl) tab;
        Ax1 ax1 = null;
        if (!tabImpl.isInitialized()) {
            return null;
        }
        C0797Nb1 nb1 = new C0797Nb1();
        int i = 0;
        if (C5383wB.q(tabImpl).T != null) {
            ax1 = C5383wB.q(tabImpl).T;
        } else {
            LoadUrlParams loadUrlParams = tabImpl.X;
            if (loadUrlParams == null) {
                byteBuffer = (ByteBuffer) N.MNwGha8e(tabImpl.L);
            } else {
                C2512fL0 fl0 = loadUrlParams.d;
                byteBuffer = (ByteBuffer) N.M_N0bb_o(loadUrlParams.f10938a, fl0 != null ? fl0.f9916a : null, fl0 != null ? fl0.b : 0, loadUrlParams.b, tabImpl.H);
            }
            if (byteBuffer != null) {
                ax1 = new Ax1(byteBuffer);
                ax1.b = 2;
            }
        }
        nb1.f8557a = ax1;
        nb1.e = T51.k(tab);
        nb1.b = C5383wB.q(tab).Q;
        nb1.d = C5383wB.q(tab).S;
        nb1.g = C5383wB.q(tab).X;
        if (tab.P() && !tab.isNativePage()) {
            i = tab.m();
        }
        nb1.f = i;
        nb1.c = C5383wB.q(tabImpl).R;
        return nb1;
    }
}
