package org.chromium.chrome.browser.password_check;

import J.N;
import java.util.Iterator;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordCheckBridge {

    /* renamed from: a  reason: collision with root package name */
    public long f10731a = N.MC$M7l1y(this);
    public final C0909Ox0 b;

    public PasswordCheckBridge(C0909Ox0 ox0) {
        this.b = ox0;
    }

    public static void insertCredential(CompromisedCredential[] compromisedCredentialArr, int i, String str, GURL gurl, String str2, String str3, String str4, String str5, String str6, String str7, long j, boolean z, boolean z2, boolean z3, boolean z4) {
        compromisedCredentialArr[i] = new CompromisedCredential(str, gurl, str2, str3, str4, str5, str6, str7, j, z, z2, z3, z4);
    }

    public void onCompromisedCredentialsFetched(int i) {
        Iterator it = this.b.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC0605Jx0) uq0.next()).b();
            } else {
                return;
            }
        }
    }

    public void onPasswordCheckProgressChanged(int i, int i2) {
        Iterator it = this.b.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC0605Jx0) uq0.next()).c(i, i2);
            } else {
                return;
            }
        }
    }

    public void onPasswordCheckStatusChanged(int i) {
        Iterator it = this.b.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC0605Jx0) uq0.next()).a(i);
            } else {
                return;
            }
        }
    }

    public void onSavedPasswordsFetched(int i) {
        Iterator it = this.b.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC0605Jx0) uq0.next()).d();
            } else {
                return;
            }
        }
    }
}
