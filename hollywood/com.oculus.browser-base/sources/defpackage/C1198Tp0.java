package defpackage;

import org.chromium.chrome.browser.notifications.scheduler.NotificationSchedulerTask;

/* renamed from: Tp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1198Tp0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC0804Ne f8986a;

    public C1198Tp0(NotificationSchedulerTask notificationSchedulerTask, AbstractC0804Ne ne) {
        this.f8986a = ne;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f8986a.a(((Boolean) obj).booleanValue());
    }
}
