package defpackage;

import org.chromium.chrome.browser.omaha.notification.UpdateNotificationServiceBridge;

/* renamed from: mq1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3787mq1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final UpdateNotificationServiceBridge f10450a;

    public C3787mq1(UpdateNotificationServiceBridge updateNotificationServiceBridge) {
        this.f10450a = updateNotificationServiceBridge;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        UpdateNotificationServiceBridge updateNotificationServiceBridge = this.f10450a;
        updateNotificationServiceBridge.H = (C5321vq1) obj;
        updateNotificationServiceBridge.g();
    }
}
