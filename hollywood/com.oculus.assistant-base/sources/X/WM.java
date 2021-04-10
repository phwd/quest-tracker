package X;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;

public final class WM implements Runnable {
    public static final String __redex_internal_original_name = "com.oculus.assistant.nux.AssistantNuxController$onValidCommand$$inlined$let$lambda$1";
    public final /* synthetic */ Resources A00;
    public final /* synthetic */ WO A01;
    public final /* synthetic */ AbstractC1279wZ A02;

    public WM(AbstractC1279wZ wZVar, WO wo, Resources resources) {
        this.A02 = wZVar;
        this.A01 = wo;
        this.A00 = resources;
    }

    public final void run() {
        WO wo = this.A01;
        AbstractC1279wZ wZVar = this.A02;
        WO.A01(wo, wZVar.A08(this.A00, AnonymousClass09.A00), new WK(wo));
        AssetFileDescriptor assetFileDescriptor = ((AbstractC0400Wb) wZVar).A00;
        if (assetFileDescriptor != null) {
            assetFileDescriptor.close();
        }
        ((AbstractC0400Wb) wZVar).A00 = null;
    }
}
