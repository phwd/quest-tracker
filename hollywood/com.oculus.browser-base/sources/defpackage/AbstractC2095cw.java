package defpackage;

import android.content.Context;
import java.lang.reflect.Field;

/* renamed from: cw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2095cw {
    public static String a() {
        Field field = Context.class.getField("DROPBOX_SERVICE");
        if (field != null) {
            return (String) field.get(null);
        }
        return null;
    }
}
