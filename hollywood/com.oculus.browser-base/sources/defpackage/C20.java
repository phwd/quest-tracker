package defpackage;

import android.os.Bundle;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappGeolocationBridge;

/* renamed from: C20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C20 extends Tn1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ InstalledWebappGeolocationBridge f7780a;

    public C20(InstalledWebappGeolocationBridge installedWebappGeolocationBridge) {
        this.f7780a = installedWebappGeolocationBridge;
    }

    @Override // defpackage.Tn1
    public void a(String str, Bundle bundle) {
        PostTask.b(Zo1.c, new B20(this, str, bundle), 0);
    }
}
