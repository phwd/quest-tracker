package X;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.List;

public final class WO {
    public static final WI A07 = new WI();
    public int A00 = -1;
    public AbstractC0400Wb A01;
    public boolean A02;
    public MediaPlayer A03 = new MediaPlayer();
    public final Handler A04 = new Handler(Looper.getMainLooper());
    public final WS A05 = new WS();
    public final List A06;

    public WO(List list) {
        C0514bB.A02(list, "nuxSteps");
        this.A06 = list;
    }

    public static final void A00(WO wo) {
        if (wo.A03.isPlaying()) {
            wo.A03.stop();
        }
        wo.A03.release();
        wo.A03 = new MediaPlayer();
    }

    public static final void A02(WO wo, AbstractC1279wZ wZVar, Resources resources, Integer num) {
        C0514bB.A02(wZVar, "voiceCommandStep");
        C0514bB.A02(resources, "resources");
        C0514bB.A02(num, "audioType");
        int i = wZVar.A00;
        if (i > 0) {
            wZVar.A00 = i - 1;
            C0410Wn.A00.logNuxEvent("nux_vc_retry_voice_command");
            C00859p A09 = wZVar.A09();
            WS ws = wo.A05;
            ws.A00 = true;
            ws.A01.cancel();
            HandlerC0422Wz.A06.A09(A09, false);
            A01(wo, wZVar.A08(resources, num), new WK(wo));
        } else {
            C0410Wn.A00.logNuxEvent("nux_vc_skip_voice_command_on_error");
            A01(wo, wZVar.A08(resources, AnonymousClass09.A0J), WN.A00);
        }
        AssetFileDescriptor assetFileDescriptor = ((AbstractC0400Wb) wZVar).A00;
        if (assetFileDescriptor != null) {
            assetFileDescriptor.close();
        }
        ((AbstractC0400Wb) wZVar).A00 = null;
    }

    public final Iterable A03() {
        ArrayList arrayList = new ArrayList();
        for (AbstractC0400Wb wb : this.A06) {
            C1300wu A022 = wb.A02();
            if (A022 != null) {
                arrayList.add(A022);
            }
            C00859p A012 = wb.A01();
            if (A012 != null) {
                arrayList.add(A012);
            }
            if (C0514bB.A05("voice_command_step", wb.A03())) {
                AbstractC1279wZ wZVar = (AbstractC1279wZ) wb;
                arrayList.add(wZVar.A09());
                arrayList.add(wZVar.A0A());
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A04(android.content.res.Resources r11) {
        /*
        // Method dump skipped, instructions count: 192
        */
        throw new UnsupportedOperationException("Method not decompiled: X.WO.A04(android.content.res.Resources):void");
    }

    public static final void A01(WO wo, AssetFileDescriptor assetFileDescriptor, MediaPlayer.OnCompletionListener onCompletionListener) {
        A00(wo);
        if (assetFileDescriptor != null) {
            if (Build.VERSION.SDK_INT >= 24) {
                wo.A03.setDataSource(assetFileDescriptor);
            } else {
                wo.A03.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            }
            wo.A03.prepare();
            wo.A03.start();
            wo.A03.setOnCompletionListener(onCompletionListener);
        }
    }
}
