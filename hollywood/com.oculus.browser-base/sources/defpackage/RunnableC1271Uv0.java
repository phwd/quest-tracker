package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.chrome.browser.paint_preview.services.PaintPreviewTabService;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: Uv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1271Uv0 implements Runnable {
    public final PaintPreviewTabService F;
    public final AbstractC0124Ca1 G;

    public RunnableC1271Uv0(PaintPreviewTabService paintPreviewTabService, AbstractC0124Ca1 ca1) {
        this.F = paintPreviewTabService;
        this.G = ca1;
    }

    public void run() {
        PaintPreviewTabService paintPreviewTabService = this.F;
        AbstractC0124Ca1 ca1 = this.G;
        Objects.requireNonNull(paintPreviewTabService);
        TabModel l = ((AbstractC0246Ea1) ca1).l(false);
        int count = l.getCount();
        int[] iArr = new int[count];
        for (int i = 0; i < count; i++) {
            iArr[i] = l.getTabAt(i).getId();
        }
        long j = paintPreviewTabService.c;
        if (j != 0) {
            N.MTE3rHmH(j, iArr);
        }
    }
}
