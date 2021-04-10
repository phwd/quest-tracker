package defpackage;

import android.content.Context;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ContextUtils;
import org.chromium.base.PathUtils;

/* renamed from: Jy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class CallableC0607Jy0 implements Callable {
    @Override // java.util.concurrent.Callable
    public Object call() {
        AtomicBoolean atomicBoolean = PathUtils.f10591a;
        String[] strArr = new String[3];
        Context applicationContext = ContextUtils.getApplicationContext();
        strArr[0] = applicationContext.getDir(PathUtils.c, 0).getPath();
        PathUtils.a(strArr[0], 448);
        strArr[1] = applicationContext.getDir("textures", 0).getPath();
        if (applicationContext.getCacheDir() != null) {
            if (PathUtils.d == null) {
                strArr[2] = applicationContext.getCacheDir().getPath();
            } else {
                File file = new File(applicationContext.getCacheDir(), PathUtils.d);
                file.mkdir();
                strArr[2] = file.getPath();
                PathUtils.a(strArr[2], 1472);
            }
        }
        return strArr;
    }
}
