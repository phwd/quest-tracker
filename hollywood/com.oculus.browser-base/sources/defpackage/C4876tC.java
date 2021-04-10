package defpackage;

import android.os.IBinder;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* renamed from: tC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4876tC implements IBinder.DeathRecipient {

    /* renamed from: a  reason: collision with root package name */
    public final BinderC5046uC f11329a;
    public final CustomTabsSessionToken b;

    public C4876tC(BinderC5046uC uCVar, CustomTabsSessionToken customTabsSessionToken) {
        this.f11329a = uCVar;
        this.b = customTabsSessionToken;
    }

    public void binderDied() {
        IBinder iBinder;
        BinderC5046uC uCVar = this.f11329a;
        CustomTabsSessionToken customTabsSessionToken = this.b;
        WY0 wy0 = uCVar.f11397a;
        CustomTabsConnection customTabsConnection = wy0.I.f11189a;
        Objects.requireNonNull(customTabsConnection);
        PostTask.c(Zo1.f9374a, new RunnableC2314eC(customTabsConnection, customTabsSessionToken));
        try {
            synchronized (wy0.F) {
                AbstractC5947zY zYVar = customTabsSessionToken.f9465a;
                if (zYVar == null) {
                    iBinder = null;
                } else {
                    iBinder = ((C5607xY) zYVar).f11613a;
                }
                if (iBinder != null) {
                    iBinder.unlinkToDeath((IBinder.DeathRecipient) wy0.F.getOrDefault(iBinder, null), 0);
                    wy0.F.remove(iBinder);
                }
            }
        } catch (NoSuchElementException unused) {
        }
    }
}
