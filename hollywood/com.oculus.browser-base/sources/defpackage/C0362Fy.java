package defpackage;

import J.N;
import android.content.ComponentName;
import android.content.Context;
import android.content.LocusId;
import android.view.contentcapture.ContentCaptureCondition;
import android.view.contentcapture.ContentCaptureManager;
import android.view.contentcapture.DataRemovalRequest;
import java.util.Set;
import org.chromium.base.BuildInfo;
import org.chromium.components.content_capture.ContentCaptureController;

/* renamed from: Fy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0362Fy extends ContentCaptureController {
    public boolean c;
    public ContentCaptureManager d;

    public C0362Fy(Context context) {
        this.d = (ContentCaptureManager) context.getSystemService(ContentCaptureManager.class);
        f();
    }

    public static void d(Context context) {
        ContentCaptureController.f10830a = new C0362Fy(context);
    }

    @Override // org.chromium.components.content_capture.ContentCaptureController
    public void a() {
        ContentCaptureManager contentCaptureManager = this.d;
        if (contentCaptureManager != null) {
            contentCaptureManager.removeData(new DataRemovalRequest.Builder().forEverything().build());
        }
    }

    @Override // org.chromium.components.content_capture.ContentCaptureController
    public void b(String[] strArr) {
        if (this.d != null) {
            DataRemovalRequest.Builder builder = new DataRemovalRequest.Builder();
            for (String str : strArr) {
                builder = builder.addLocusId(new LocusId(str), 0);
            }
            this.d.removeData(builder.build());
        }
    }

    @Override // org.chromium.components.content_capture.ContentCaptureController
    public boolean c() {
        return this.c;
    }

    public final void e(String str) {
        if (AbstractC0423Gy.a()) {
            AbstractC1220Ua0.d("ContentCapture", str, new Object[0]);
        }
    }

    public final void f() {
        ContentCaptureManager contentCaptureManager = this.d;
        if (contentCaptureManager == null) {
            e("ContentCaptureManager isn't available.");
            return;
        }
        ComponentName serviceComponentName = contentCaptureManager.getServiceComponentName();
        if (serviceComponentName == null) {
            e("Service isn't available.");
            return;
        }
        boolean equals = "com.google.android.as".equals(serviceComponentName.getPackageName());
        this.c = equals;
        if (!equals) {
            StringBuilder i = AbstractC2531fV.i("Package doesn't match, current one is ");
            i.append(this.d.getServiceComponentName().getPackageName());
            e(i.toString());
            if (!BuildInfo.a() && !AbstractC0423Gy.a()) {
                return;
            }
        }
        boolean isContentCaptureEnabled = this.d.isContentCaptureEnabled();
        this.c = isContentCaptureEnabled;
        if (!isContentCaptureEnabled) {
            e("ContentCapture disabled.");
        }
    }

    @Override // org.chromium.components.content_capture.ContentCaptureController
    public void pullAllowlist() {
        boolean[] zArr;
        Set<ContentCaptureCondition> contentCaptureConditions = this.d.getContentCaptureConditions();
        String[] strArr = null;
        if (contentCaptureConditions != null) {
            strArr = new String[contentCaptureConditions.size()];
            zArr = new boolean[contentCaptureConditions.size()];
            int i = 0;
            for (ContentCaptureCondition contentCaptureCondition : contentCaptureConditions) {
                strArr[i] = contentCaptureCondition.getLocusId().getId();
                zArr[i] = (contentCaptureCondition.getFlags() & 2) != 0;
                i++;
            }
        } else {
            zArr = null;
        }
        N.MxBXA3uk(this.b, this, strArr, zArr);
    }
}
