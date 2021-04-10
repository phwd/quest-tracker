package org.chromium.chrome.browser.sync;

import J.N;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.components.signin.base.CoreAccountInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TrustedVaultClient {

    /* renamed from: a  reason: collision with root package name */
    public static TrustedVaultClient f10771a;
    public final Sn1 b;
    public final Set c = new TreeSet();

    public TrustedVaultClient(Sn1 sn1) {
        this.b = sn1;
    }

    public static TrustedVaultClient a() {
        if (f10771a == null) {
            Objects.requireNonNull(AppHooks.get());
            f10771a = new TrustedVaultClient(new Sn1());
        }
        return f10771a;
    }

    public static boolean b(long j) {
        return a().c.contains(Long.valueOf(j));
    }

    public static void fetchKeys(long j, CoreAccountInfo coreAccountInfo) {
        Objects.requireNonNull(a().b);
        C5232vH0 c2 = C5232vH0.c(Collections.emptyList());
        On1 on1 = new On1(j, coreAccountInfo);
        Pn1 pn1 = new Pn1(j, coreAccountInfo);
        c2.h(on1);
        c2.a(pn1);
    }

    public static void markKeysAsStale(long j, CoreAccountInfo coreAccountInfo) {
        Objects.requireNonNull(a().b);
        C5232vH0 c2 = C5232vH0.c(Boolean.FALSE);
        Qn1 qn1 = new Qn1(j);
        Rn1 rn1 = new Rn1(j);
        c2.h(qn1);
        c2.a(rn1);
    }

    public static void registerNative(long j) {
        a().c.add(Long.valueOf(j));
    }

    public static void unregisterNative(long j) {
        a().c.remove(Long.valueOf(j));
    }

    public void c() {
        for (Long l : this.c) {
            N.MlSGBpm_(l.longValue());
        }
    }
}
