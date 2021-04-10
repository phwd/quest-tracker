package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.notifications.NotificationPlatformBridge;

/* renamed from: Rp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1076Rp0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final NotificationPlatformBridge f8855a;
    public final C3444kq0 b;

    public C1076Rp0(NotificationPlatformBridge notificationPlatformBridge, C3444kq0 kq0) {
        this.f8855a = notificationPlatformBridge;
        this.b = kq0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        NotificationPlatformBridge notificationPlatformBridge = this.f8855a;
        C3444kq0 kq0 = this.b;
        Objects.requireNonNull(notificationPlatformBridge);
        if (!((Boolean) obj).booleanValue()) {
            try {
                ((C0771Mp0) notificationPlatformBridge.d).a(kq0);
                AbstractC3102iq0.f10166a.b(7, kq0.f10306a);
            } catch (RuntimeException unused) {
                AbstractC1220Ua0.a("NotificationPlatformBridge", "Failed to send notification, the IPC message might be corrupted.", new Object[0]);
                Objects.requireNonNull(AbstractC3102iq0.f10166a);
                C3273jq0.c("Mobile.SystemNotification.NotifyFailure", 7);
            }
        }
    }
}
