package defpackage;

import java.io.File;
import org.chromium.base.ContextUtils;

/* renamed from: HA  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HA extends AbstractC0500Ie {
    @Override // defpackage.AbstractC2032cb
    public Object c() {
        File file = new File(ContextUtils.getApplicationContext().getFileStreamPath("COOKIES.DAT").getAbsolutePath());
        if (!file.exists() || file.delete()) {
            return null;
        }
        StringBuilder i = AbstractC2531fV.i("Failed to delete ");
        i.append(file.getName());
        AbstractC1220Ua0.a("CookiesFetcher", i.toString(), new Object[0]);
        return null;
    }
}
