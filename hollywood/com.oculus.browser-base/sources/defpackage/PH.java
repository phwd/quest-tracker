package defpackage;

import android.view.View;
import org.chromium.chrome.browser.download.dialogs.DownloadLaterDialogView;

/* renamed from: PH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class PH extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final DownloadLaterDialogView f8680a;

    public PH(DownloadLaterDialogView downloadLaterDialogView) {
        this.f8680a = downloadLaterDialogView;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        View view = (View) obj;
        this.f8680a.b();
    }
}
