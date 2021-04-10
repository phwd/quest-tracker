package org.chromium.chrome.browser.feedback;

import J.N;
import java.util.HashMap;
import java.util.Map;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ProcessIdFeedbackSource extends AbstractC5079uP implements AbstractC0183Da {

    /* renamed from: a  reason: collision with root package name */
    public static final Map f10672a;
    public Map b;
    public boolean c;

    static {
        HashMap hashMap = new HashMap();
        f10672a = hashMap;
        hashMap.put("renderer", 3);
        hashMap.put("utility", 6);
        hashMap.put("gpu-process", 9);
    }

    @Override // defpackage.AbstractC0183Da
    public boolean a() {
        return this.c;
    }

    @Override // defpackage.AbstractC0183Da
    public void b(Runnable runnable) {
        N.Mx7ChZtk(this);
    }

    @Override // defpackage.AbstractC5249vP, defpackage.AbstractC5079uP
    public Map c() {
        return this.b;
    }

    public final void prepareCompleted(long j) {
        this.b = new HashMap();
        for (Map.Entry entry : f10672a.entrySet()) {
            long[] MKHKXOCT = N.MKHKXOCT(j, this, ((Integer) entry.getValue()).intValue());
            if (MKHKXOCT.length != 0) {
                StringBuilder sb = new StringBuilder();
                for (long j2 : MKHKXOCT) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(String.valueOf(j2));
                }
                this.b.put(AbstractC2531fV.g("Process IDs (", (String) entry.getKey(), ")"), sb.toString());
            }
        }
        this.b.put("Process IDs (browser)", Long.toString(N.MXN_TQGn()));
        this.c = true;
    }
}
