package org.chromium.chrome.browser.net.nqe;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NetworkQualityProvider {

    /* renamed from: a  reason: collision with root package name */
    public long f10698a;
    public long b;
    public int c;
    public Integer d;

    public void onEffectiveConnectionTypeChanged(int i) {
        this.d = Integer.valueOf(i);
        throw null;
    }

    public void onRTTOrThroughputEstimatesComputed(long j, long j2, int i) {
        this.f10698a = j;
        this.b = j2;
        this.c = i;
        throw null;
    }
}
