package org.chromium.chrome.browser.autofill;

import J.N;
import java.util.Objects;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CreditCardScannerBridge implements AbstractC4532rB {

    /* renamed from: a  reason: collision with root package name */
    public final long f10611a;
    public final C4703sB b;

    public CreditCardScannerBridge(long j, WebContents webContents) {
        this.f10611a = j;
        this.b = new C4703sB(webContents, this);
    }

    public static CreditCardScannerBridge create(long j, WebContents webContents) {
        return new CreditCardScannerBridge(j, webContents);
    }

    @Override // defpackage.AbstractC4532rB
    public void a() {
        N.MzlSwhwH(this.f10611a, this);
    }

    public final boolean canScan() {
        Objects.requireNonNull(this.b);
        return false;
    }

    public final void scan() {
        this.b.f11255a.a();
    }
}
