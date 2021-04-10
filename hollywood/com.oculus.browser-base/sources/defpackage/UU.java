package defpackage;

import android.os.Bundle;
import java.util.Iterator;

@Deprecated
/* renamed from: UU  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class UU extends AbstractServiceC2158dG1 {
    public XH1 K = PH1.f8681a;

    public static void c(Bundle bundle) {
        Iterator<String> it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next != null && next.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    public abstract void b(String str, Bundle bundle);

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x02a5  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x02db  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x039e  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x03a7  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x03cb  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x03d3  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x03f1  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x03f5  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0411  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01fa  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01fc  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x025f  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0273  */
    @Override // defpackage.AbstractServiceC2158dG1
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleIntent(android.content.Intent r15) {
        /*
        // Method dump skipped, instructions count: 1236
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.UU.handleIntent(android.content.Intent):void");
    }

    public void onCreate() {
        super.onCreate();
        this.K = PH1.f8681a;
    }
}
