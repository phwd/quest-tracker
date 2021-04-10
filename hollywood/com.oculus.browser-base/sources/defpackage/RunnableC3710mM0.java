package defpackage;

import android.content.res.AssetManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import org.chromium.base.ContextUtils;
import org.chromium.base.LocaleUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.ui.base.ResourceBundle;

/* renamed from: mM0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC3710mM0 implements Runnable {
    public final List F = new ArrayList();
    public final String G;
    public final CountDownLatch H = new CountDownLatch(1);
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ C3881nM0 f10417J;

    public RunnableC3710mM0(C3881nM0 nm0, String str) {
        this.f10417J = nm0;
        this.G = str;
    }

    public final void a() {
        boolean z;
        String[] strArr;
        File d = this.f10417J.d();
        String str = this.G;
        String language = Locale.getDefault().getLanguage();
        boolean z2 = true;
        AbstractC1220Ua0.d("ui", "Using UI locale %s, system locale: %s (Android name: %s)", str, LocaleUtils.a(language), language);
        ArrayList arrayList = new ArrayList(6);
        String[] strArr2 = ResourceBundle.f11019a;
        for (String str2 : strArr2) {
            if (LocaleUtils.b(str2).equals(str)) {
                arrayList.add(str2);
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add("en-US");
        }
        AssetManager b = ContextUtils.b();
        String str3 = "locales" + '/' + AbstractC2531fV.h(new StringBuilder(), (String) arrayList.get(0), ".pak");
        try {
            b.open(str3).close();
            AbstractC1220Ua0.d("ui", "Found asset file: " + str3, new Object[0]);
            z = true;
        } catch (IOException unused) {
            AbstractC1220Ua0.d("ui", AbstractC2531fV.f("Missing asset file: ", str3), new Object[0]);
            z = false;
        }
        if (!z) {
            AbstractC1220Ua0.d("ui", "No locale pak files to extract, assuming app bundle.", new Object[0]);
            strArr = new String[0];
        } else {
            String[] strArr3 = new String[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                strArr3[i] = AbstractC2531fV.h(AbstractC2531fV.i("locales/"), (String) arrayList.get(i), ".pak");
            }
            AbstractC1220Ua0.d("ui", "UI Language: %s requires .pak files: %s", str, Arrays.toString(arrayList.toArray()));
            strArr = strArr3;
        }
        String str4 = AbstractC0456Hk.f8178a.k;
        int length = strArr.length;
        String[] strArr4 = new String[length];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            String str5 = strArr[i2];
            strArr4[i2] = str5.substring(str5.lastIndexOf(47) + 1) + str4;
        }
        String[] list = d.list();
        if (list == null) {
            z2 = false;
        }
        if (z2) {
            List asList = Arrays.asList(list);
            for (int i3 = 0; i3 < length; i3++) {
                z2 &= asList.contains(strArr4[i3]);
            }
        }
        if (!z2) {
            this.f10417J.a(list);
            d.mkdirs();
            if (d.exists()) {
                for (int i4 = 0; i4 < strArr.length; i4++) {
                    if (!AbstractC3375kQ.b(ContextUtils.getApplicationContext(), strArr[i4], new File(d, strArr4[i4]))) {
                        throw new RuntimeException();
                    }
                }
                return;
            }
            throw new RuntimeException();
        }
    }

    public void run() {
        TraceEvent j0 = TraceEvent.j0("ResourceExtractor.ExtractTask.doInBackground");
        try {
            a();
            if (j0 != null) {
                j0.close();
            }
            synchronized (this) {
                this.I = true;
            }
            this.H.countDown();
            PostTask.b(this.f10417J.c, new RunnableC3539lM0(this), 0);
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
