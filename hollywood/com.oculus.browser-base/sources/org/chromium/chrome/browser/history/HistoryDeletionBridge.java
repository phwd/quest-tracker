package org.chromium.chrome.browser.history;

import J.N;
import java.util.Iterator;
import org.chromium.components.content_capture.ContentCaptureController;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HistoryDeletionBridge {

    /* renamed from: a  reason: collision with root package name */
    public static HistoryDeletionBridge f10679a;
    public final C1322Vq0 b = new C1322Vq0();

    public HistoryDeletionBridge() {
        N.M41yd4uo(this);
    }

    public void onURLsDeleted(HistoryDeletionInfo historyDeletionInfo) {
        Iterator it = this.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ContentCaptureController contentCaptureController = (ContentCaptureController) ((C0484Hy) uq0.next()).f8191a.get();
                if (contentCaptureController != null) {
                    if (N.MDNd$JT3(historyDeletionInfo.f10680a) || N.MBUJ8Aeh(historyDeletionInfo.f10680a)) {
                        contentCaptureController.a();
                    } else {
                        String[] MLyEE9$M = N.MLyEE9$M(historyDeletionInfo.f10680a);
                        if (MLyEE9$M.length > 0) {
                            try {
                                contentCaptureController.b(MLyEE9$M);
                            } catch (RuntimeException e) {
                                StringBuilder i = AbstractC2531fV.i("Deleted URLs length: ");
                                i.append(MLyEE9$M.length);
                                throw new RuntimeException(i.toString(), e);
                            }
                        }
                    }
                }
            } else {
                return;
            }
        }
    }
}
