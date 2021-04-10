package defpackage;

import android.content.Context;
import android.content.Intent;
import java.util.Objects;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* renamed from: QG  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class QG extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final TG f8750a;
    public final Context b;
    public final Intent c;
    public final long d;
    public final C0788My e;

    public QG(TG tg, Context context, Intent intent, long j, C0788My my) {
        this.f8750a = tg;
        this.b = context;
        this.c = intent;
        this.d = j;
        this.e = my;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        TG tg = this.f8750a;
        Context context = this.b;
        Intent intent = this.c;
        long j = this.d;
        C0788My my = this.e;
        Objects.requireNonNull(tg);
        if (((C1820bI) obj).d == null) {
            DownloadManagerService.F(context, 3);
        } else {
            tg.j(context, intent, j, my);
        }
    }
}
