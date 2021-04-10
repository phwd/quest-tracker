package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.crash.MinidumpUploadServiceImpl;
import org.chromium.net.NetworkChangeNotifier;

/* renamed from: Ek0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0274Ek0 implements AbstractC5481wn0 {
    public static C0274Ek0 F;
    public final WF0 G;

    public C0274Ek0(Context context, WF0 wf0, AbstractC0152Ck0 ck0) {
        this.G = wf0;
        NetworkChangeNotifier.a(this);
    }

    @Override // defpackage.AbstractC5481wn0
    public void a(int i) {
        if (i != 6) {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.G.b.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if ((activeNetworkInfo == null || !activeNetworkInfo.isConnected()) ? false : !connectivityManager.isActiveNetworkMetered()) {
                AtomicBoolean atomicBoolean = MinidumpUploadServiceImpl.b;
                File[] c = new C1619aB(ContextUtils.getApplicationContext().getCacheDir()).c(C1619aB.f9416a);
                ArrayList arrayList = new ArrayList();
                for (File file : c) {
                    if (C1619aB.d(file.getName()) < 3) {
                        arrayList.add(file);
                    }
                }
                File[] fileArr = (File[]) arrayList.toArray(new File[arrayList.size()]);
                AbstractC1220Ua0.d("MinidmpUploadService", "Attempting to upload accumulated crash dumps.", new Object[0]);
                for (File file2 : fileArr) {
                    MinidumpUploadServiceImpl.e(file2);
                }
                NetworkChangeNotifier.j(this);
                F = null;
            }
        }
    }
}
