package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;

/* renamed from: fG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2499fG1 {

    /* renamed from: a  reason: collision with root package name */
    public static final NF1 f9911a = new NF1("CastDynamiteModule");

    public static OG1 a(Context context) {
        try {
            IBinder b = PJ.c(context, PJ.h, "com.google.android.gms.cast.framework.dynamite").b("com.google.android.gms.cast.framework.internal.CastDynamiteModuleImpl");
            if (b == null) {
                return null;
            }
            IInterface queryLocalInterface = b.queryLocalInterface("com.google.android.gms.cast.framework.internal.ICastDynamiteModule");
            if (queryLocalInterface instanceof OG1) {
                return (OG1) queryLocalInterface;
            }
            return new OG1(b);
        } catch (JJ e) {
            throw new RuntimeException(e);
        }
    }
}
