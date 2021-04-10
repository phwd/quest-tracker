package defpackage;

import J.N;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.sync.ProfileSyncService;

/* renamed from: kH0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3355kH0 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f10270a;
    public final /* synthetic */ ProfileSyncService b;

    public C3355kH0(ProfileSyncService profileSyncService, AbstractC3184jH0 jh0) {
        this.b = profileSyncService;
        Object obj = ThreadUtils.f10596a;
        int i = profileSyncService.f + 1;
        profileSyncService.f = i;
        if (i == 1) {
            N.MvEEhEqM(profileSyncService.e, profileSyncService, true);
        }
    }

    public void a() {
        Object obj = ThreadUtils.f10596a;
        if (!this.f10270a) {
            this.f10270a = true;
            ProfileSyncService profileSyncService = this.b;
            int i = profileSyncService.f - 1;
            profileSyncService.f = i;
            if (i == 0) {
                N.MvEEhEqM(profileSyncService.e, profileSyncService, false);
            }
        }
    }
}
