package defpackage;

import android.net.Uri;
import java.util.Objects;
import org.chromium.chrome.browser.contextmenu.ContextMenuNativeDelegateImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: W70  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class W70 {

    /* renamed from: a  reason: collision with root package name */
    public Y70 f9130a;
    public X70 b;
    public ContextMenuNativeDelegateImpl c;
    public Runnable d;
    public Runnable e;

    public W70(String str, String str2, String str3, String str4, boolean z, WebContents webContents, ContextMenuNativeDelegateImpl contextMenuNativeDelegateImpl, Runnable runnable, Runnable runnable2) {
        X70 a2 = X70.a();
        this.b = a2;
        Objects.requireNonNull(a2);
        Uri uri = Uri.EMPTY;
        Y70 y70 = new Y70();
        y70.f9252a = webContents;
        this.f9130a = y70;
        this.c = contextMenuNativeDelegateImpl;
        this.d = runnable;
        this.e = runnable2;
    }
}
