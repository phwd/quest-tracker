package com.oculus.modules.codegen;

import android.content.Context;
import android.util.Pair;
import com.oculus.panellib.modules.AbstractBroadcastReceiverModule;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.List;

public abstract class RemoteLivestreamingErrorModule extends AbstractBroadcastReceiverModule {
    public static final String MODULE_NAME = "RemoteLivestreamingErrorModule";

    public final String marshallJSConstants() {
        return null;
    }

    public void shutdownModuleImpl() {
    }

    public final void emitOnLivestreamingError(String str) {
        RCTBaseJavaModule.nativeEmitEventWithJsonData(this.RVRCtxTag, "RemoteLivestreamingErrorModule_onLivestreamingError", String.valueOf(str));
    }

    public static final List<Pair<String, String>> describe() {
        return AbstractBroadcastReceiverModule.describe();
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public final String getModuleName() {
        return MODULE_NAME;
    }

    public RemoteLivestreamingErrorModule(Context context) {
        super(context);
    }
}
