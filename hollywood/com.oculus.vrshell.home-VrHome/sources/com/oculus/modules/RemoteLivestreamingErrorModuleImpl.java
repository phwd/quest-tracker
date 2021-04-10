package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.debug.log.BLog;
import com.oculus.modules.codegen.RemoteLivestreamingErrorModule;

public class RemoteLivestreamingErrorModuleImpl extends RemoteLivestreamingErrorModule {
    private static final String LIVESTREAM_FATAL_ERROR = "livestream_fatal_error";
    private static final String OCULUS_NOTIFICATION_TYPE = "oculus_notification_type";
    private static final String SOCIAL_NOTIFICATION_ACTION = "com.oculus.SEE_SOCIAL_NOTIFICATIONS";

    public RemoteLivestreamingErrorModuleImpl(Context context) {
        super(context);
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public IntentFilter getIntentFilter() {
        return new IntentFilter(SOCIAL_NOTIFICATION_ACTION);
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public void processIntent(Intent intent, boolean forceNativeUpdate) {
        BLog.d(MODULE_NAME, "processIntent()");
        if (LIVESTREAM_FATAL_ERROR.equals(intent.getStringExtra(OCULUS_NOTIFICATION_TYPE))) {
            emitOnLivestreamingError("{}");
        }
    }
}
