package org.chromium.chrome.browser.paint_preview.services;

import J.N;
import java.io.File;
import java.util.HashSet;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PaintPreviewTabService implements AbstractC0156Cm0 {

    /* renamed from: a  reason: collision with root package name */
    public Runnable f10728a;
    public long b;
    public long c;
    public HashSet d;

    public PaintPreviewTabService(long j, long j2) {
        String str;
        this.c = j;
        this.b = j2;
        if (!(j == 0 ? false : N.MWP3QaBv(j))) {
            long j3 = this.c;
            if (j3 == 0) {
                str = "";
            } else {
                str = N.MPozT7P0(j3);
            }
            this.d = new HashSet();
            P21 f0 = P21.f0();
            try {
                String[] list = new File(str).list();
                f0.close();
                if (list != null) {
                    for (String str2 : list) {
                        if (str2.indexOf(".") > 0) {
                            str2 = str2.substring(0, str2.lastIndexOf("."));
                        }
                        this.d.add(Integer.valueOf(Integer.parseInt(str2)));
                    }
                    return;
                }
                return;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        } else {
            return;
        }
        throw th;
    }

    @Override // defpackage.AbstractC0156Cm0
    public long a() {
        return this.b;
    }

    public void b(Tab tab, Callback callback) {
        long j = this.c;
        if (j == 0) {
            callback.onResult(Boolean.FALSE);
        } else {
            N.MV$XyJvN(j, tab.getId(), tab.l(), callback);
        }
    }

    public final void onNativeDestroyed() {
        this.c = 0;
        this.b = 0;
    }
}
