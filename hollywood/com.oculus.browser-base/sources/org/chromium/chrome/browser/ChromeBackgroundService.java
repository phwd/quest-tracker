package org.chromium.chrome.browser;

import android.content.Context;
import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.background_sync.BackgroundSyncBackgroundTaskScheduler;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChromeBackgroundService extends YU {
    public String M = "pq";
    public C4298pq N;

    public ChromeBackgroundService() {
        C5271va vaVar = AbstractC2369eZ0.f9859a;
    }

    @Override // defpackage.YU
    public void a() {
        Objects.requireNonNull(this.N);
        BackgroundSyncBackgroundTaskScheduler.getInstance().scheduleOneOffTask(0, 360000);
    }

    public void attachBaseContext(Context context) {
        Context a2 = AbstractC2369eZ0.a(context);
        C4298pq pqVar = (C4298pq) AbstractC2369eZ0.b(a2, this.M);
        this.N = pqVar;
        pqVar.f11090a = this;
        super.attachBaseContext(a2);
    }

    @Override // defpackage.YU
    public int b(C2217df1 df1) {
        C4298pq pqVar = this.N;
        Objects.requireNonNull(pqVar);
        PostTask.c(Zo1.f9374a, new RunnableC4127oq(pqVar, df1.f9798a, pqVar.f11090a));
        return 0;
    }
}
