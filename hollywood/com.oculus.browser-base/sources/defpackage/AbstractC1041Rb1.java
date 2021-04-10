package defpackage;

import android.content.Context;
import com.oculus.browser.components.OculusUser;
import java.io.File;
import org.chromium.base.ContextUtils;

/* renamed from: Rb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1041Rb1 {

    /* renamed from: a  reason: collision with root package name */
    public static File f8842a;

    static {
        Context applicationContext = ContextUtils.getApplicationContext();
        StringBuilder i = AbstractC2531fV.i("tabs.");
        i.append(OculusUser.getUserID());
        f8842a = applicationContext.getDir(i.toString(), 0);
    }
}
