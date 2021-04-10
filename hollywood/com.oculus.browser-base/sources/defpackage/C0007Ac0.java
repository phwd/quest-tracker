package defpackage;

import java.util.HashMap;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Ac0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0007Ac0 {

    /* renamed from: a  reason: collision with root package name */
    public final HashMap f7681a = new HashMap();

    public C5958zc0 a(WebContents webContents) {
        if (webContents == null) {
            return new C5958zc0(null);
        }
        C5958zc0 zc0 = (C5958zc0) this.f7681a.get(webContents);
        if (zc0 != null) {
            return zc0;
        }
        C5958zc0 zc02 = new C5958zc0(webContents);
        this.f7681a.put(webContents, zc02);
        return zc02;
    }
}
