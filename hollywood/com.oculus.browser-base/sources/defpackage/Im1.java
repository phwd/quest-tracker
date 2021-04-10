package defpackage;

import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.tracing.settings.TracingSettings;
import org.chromium.content.browser.TracingControllerAndroidImpl;

/* renamed from: Im1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Im1 extends AbstractC2032cb {
    public final /* synthetic */ Lm1 i;

    public Im1(Lm1 lm1, Hm1 hm1) {
        this.i = lm1;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        File file = new File(ContextUtils.getApplicationContext().getCacheDir() + "/traces");
        file.mkdir();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            File file2 = new File(file, "chrome-trace-" + simpleDateFormat.format(new Date()) + ".pftrace.gz");
            file2.createNewFile();
            return file2;
        } catch (IOException e) {
            AbstractC1220Ua0.a("TracingController", "Couldn't create chrome-trace temp file: %s", e.getMessage());
            return null;
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        File file = (File) obj;
        if (file == null) {
            Objects.requireNonNull(this.i);
            C1184Ti1.b(ContextUtils.getApplicationContext(), "Error occurred while recording Chrome trace, see log for details.", 0).b.show();
            this.i.b(1);
            return;
        }
        Lm1 lm1 = this.i;
        lm1.f = file;
        String join = TextUtils.join(",", TracingSettings.l1());
        String n1 = TracingSettings.n1();
        if (!((TracingControllerAndroidImpl) lm1.b).c(lm1.f.getPath(), false, join, n1, true, true)) {
            AbstractC1220Ua0.a("TracingController", "Native error while trying to start tracing", new Object[0]);
            C1184Ti1.b(ContextUtils.getApplicationContext(), "Error occurred while recording Chrome trace, see log for details.", 0).b.show();
            lm1.b(1);
            return;
        }
        lm1.b(3);
        lm1.c();
    }
}
