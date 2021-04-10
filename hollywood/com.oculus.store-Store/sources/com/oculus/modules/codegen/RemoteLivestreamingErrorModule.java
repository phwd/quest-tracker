package com.oculus.modules.codegen;

import android.content.Context;
import android.util.Pair;
import com.oculus.panellib.modules.AbstractBroadcastReceiverModule;
import java.util.List;

public abstract class RemoteLivestreamingErrorModule extends AbstractBroadcastReceiverModule {
    protected static final String MODULE_NAME = RemoteLivestreamingErrorModule.class.getSimpleName();

    public RemoteLivestreamingErrorModule(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        return AbstractBroadcastReceiverModule.describe();
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void emitOnLivestreamingError(String data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "RemoteLivestreamingErrorModule_onLivestreamingError", String.valueOf(data));
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule, com.oculus.panellib.modules.EarlyDestroyable
    public final void shutdownModule() {
        super.shutdownModule();
        shutdownModuleImpl();
    }

    /* access modifiers changed from: protected */
    public void shutdownModuleImpl() {
    }
}
