package org.chromium.content.browser;

import J.N;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Environment;
import android.util.Pair;
import com.oculus.browser.R;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TracingControllerAndroidImpl implements Mm1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f10919a;
    public final Nm1 b;
    public final TracingIntentFilter c;
    public boolean d;
    public boolean e = true;
    public String f;
    public boolean g;
    public boolean h;
    public long i;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class TracingIntentFilter extends IntentFilter {
        public TracingIntentFilter(Context context) {
            addAction(context.getPackageName() + "." + "GPU_PROFILER_START");
            addAction(context.getPackageName() + "." + "GPU_PROFILER_STOP");
            addAction(context.getPackageName() + "." + "GPU_PROFILER_LIST_CATEGORIES");
        }
    }

    public TracingControllerAndroidImpl(Context context) {
        this.f10919a = context;
        this.b = new Nm1(this);
        this.c = new TracingIntentFilter(context);
    }

    public static String generateTracingFilePath(String str) {
        P21 g0 = P21.g0();
        try {
            if (!"mounted".equals(Environment.getExternalStorageState())) {
                g0.close();
                return null;
            }
            if (str.isEmpty()) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                str = "chrome-profile-results-" + simpleDateFormat.format(new Date());
            }
            String path = new File(ContextUtils.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), str).getPath();
            g0.close();
            return path;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public final void a() {
        if (this.i == 0) {
            this.i = N.MWlLnA$6(this);
        }
    }

    public final void b(String str) {
        AbstractC1220Ua0.a("TracingController", str, new Object[0]);
        if (this.e) {
            C1184Ti1.b(this.f10919a, str, 0).b.show();
        }
    }

    public boolean c(String str, boolean z, String str2, String str3, boolean z2, boolean z3) {
        this.e = z;
        if (str == null && (str = generateTracingFilePath("")) == null) {
            b(this.f10919a.getString(R.string.f59580_resource_name_obfuscated_RES_2131953275));
            return false;
        } else if (this.d) {
            AbstractC1220Ua0.a("TracingController", "Received startTracing, but we're already tracing", new Object[0]);
            return false;
        } else {
            a();
            if (!N.MZYMIGWv(this.i, this, str2, str3, z3)) {
                b(this.f10919a.getString(R.string.f59570_resource_name_obfuscated_RES_2131953274));
                return false;
            }
            AbstractC1220Ua0.d("TracingController", String.format("Profiler started: %s", str2), new Object[0]);
            String str4 = this.f10919a.getString(R.string.f59590_resource_name_obfuscated_RES_2131953276) + ": " + str2;
            if (this.e) {
                C1184Ti1.b(this.f10919a, str4, 0).b.show();
            }
            this.f = str;
            this.g = z2;
            this.h = z3;
            this.d = true;
            return true;
        }
    }

    public void onKnownCategoriesReceived(String[] strArr, Object obj) {
        if (obj != null) {
            ((Callback) obj).onResult(strArr);
        }
    }

    public void onTraceBufferUsageReceived(float f2, long j, Object obj) {
        ((Callback) obj).onResult(new Pair(Float.valueOf(f2), Long.valueOf(j)));
    }

    public void onTracingStopped(Object obj) {
        if (!this.d) {
            AbstractC1220Ua0.a("TracingController", "Received onTracingStopped, but we aren't tracing", new Object[0]);
            return;
        }
        AbstractC1220Ua0.d("TracingController", String.format("Profiler finished. Results are in %s.", this.f), new Object[0]);
        String string = this.f10919a.getString(R.string.f59600_resource_name_obfuscated_RES_2131953277, this.f);
        if (this.e) {
            C1184Ti1.b(this.f10919a, string, 0).b.show();
        }
        this.d = false;
        this.f = null;
        this.g = false;
        if (obj != null) {
            ((Callback) obj).onResult(null);
        }
    }
}
