package org.chromium.components.crash;

import J.N;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CrashKeys {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f10834a = {"loaded_dynamic_module", "active_dynamic_module", "application_status", "installed_modules", "emulated_modules", "dynamic_module_dex_name", "partner_customization_config"};
    public final AtomicReferenceArray b = new AtomicReferenceArray(f10834a.length);
    public boolean c;

    public CrashKeys(AbstractC1799bB bBVar) {
    }

    public static CrashKeys getInstance() {
        return AbstractC1970cB.f9589a;
    }

    public void flushToNative() {
        Object obj = ThreadUtils.f10596a;
        for (int i = 0; i < this.b.length(); i++) {
            N.Mk$gnuuz(this, i, (String) this.b.getAndSet(i, null));
        }
        this.c = true;
    }

    public void set(int i, String str) {
        Object obj = ThreadUtils.f10596a;
        if (this.c) {
            N.Mk$gnuuz(this, i, str);
        } else {
            this.b.set(i, str);
        }
    }
}
