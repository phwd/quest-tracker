package defpackage;

import org.chromium.chrome.browser.notifications.NotificationPlatformBridge;

/* renamed from: Sp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1137Sp0 implements Hw1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f8919a;
    public final /* synthetic */ String b;
    public final /* synthetic */ String c;
    public final /* synthetic */ NotificationPlatformBridge d;

    public C1137Sp0(NotificationPlatformBridge notificationPlatformBridge, String str, String str2, String str3) {
        this.d = notificationPlatformBridge;
        this.f8919a = str;
        this.b = str2;
        this.c = str3;
    }

    @Override // defpackage.Hw1
    public void a(boolean z, String str) {
        this.d.b(this.f8919a, z ? this.b : null, this.c);
    }
}
