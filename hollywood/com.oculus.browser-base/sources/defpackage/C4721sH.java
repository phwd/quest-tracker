package defpackage;

import android.content.IntentFilter;
import android.os.Build;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import org.chromium.base.Callback;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.ContextUtils;
import org.chromium.base.PathUtils;
import org.chromium.base.task.PostTask;

/* renamed from: sH  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4721sH {

    /* renamed from: a  reason: collision with root package name */
    public C4380qH f11262a;
    public boolean b;
    public boolean c;
    public C4038oH d;
    public ArrayList e;
    public String f;
    public ArrayList g = new ArrayList();

    public C4721sH() {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.MEDIA_REMOVED");
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter.addDataScheme("file");
        this.f11262a = new C4380qH(this, null);
        ContextUtils.getApplicationContext().registerReceiver(this.f11262a, intentFilter);
    }

    public static boolean b(String str) {
        if (!ContentUriUtils.e(str) && str != null) {
            String[] allPrivateDownloadsDirectories = PathUtils.getAllPrivateDownloadsDirectories();
            for (int i = 1; i < allPrivateDownloadsDirectories.length; i++) {
                if (str.startsWith(allPrivateDownloadsDirectories[i])) {
                    return true;
                }
            }
            if (Build.VERSION.SDK_INT >= 30) {
                for (String str2 : PathUtils.getExternalDownloadVolumesNames()) {
                    if (str.startsWith(str2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void a(Callback callback) {
        if (this.c || !this.b) {
            this.g.add(callback);
            if (this.d == null) {
                C4038oH oHVar = new C4038oH(this, new C4209pH());
                this.d = oHVar;
                Executor executor = AbstractC2032cb.f9616a;
                oHVar.f();
                ((ExecutorC1463Ya) executor).execute(oHVar.e);
                return;
            }
            return;
        }
        PostTask.b(Zo1.f9374a, new RunnableC0884Ol((AbstractC0823Nl) callback, this.e), 0);
    }
}
