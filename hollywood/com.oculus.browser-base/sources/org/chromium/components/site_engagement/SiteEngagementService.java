package org.chromium.components.site_engagement;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SiteEngagementService {

    /* renamed from: a  reason: collision with root package name */
    public long f10898a;

    public SiteEngagementService(long j) {
        this.f10898a = j;
    }

    public static SiteEngagementService create(long j) {
        return new SiteEngagementService(j);
    }

    public final void onNativeDestroyed() {
        this.f10898a = 0;
    }
}
