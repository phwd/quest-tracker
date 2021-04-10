package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.oculus.modules.codegen.RemoteLivestreamingErrorModule;

public class RemoteLivestreamingErrorModuleImpl extends RemoteLivestreamingErrorModule {
    public static final String LIVESTREAM_FATAL_ERROR = "livestream_fatal_error";
    public static final String OCULUS_NOTIFICATION_TYPE = "oculus_notification_type";
    public static final String SOCIAL_NOTIFICATION_ACTION = "com.oculus.SEE_SOCIAL_NOTIFICATIONS";

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public IntentFilter getIntentFilter() {
        return new IntentFilter("com.oculus.SEE_SOCIAL_NOTIFICATIONS");
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public void processIntent(Intent intent, boolean z) {
        if (LIVESTREAM_FATAL_ERROR.equals(intent.getStringExtra("oculus_notification_type"))) {
            emitOnLivestreamingError("{}");
        }
    }

    public RemoteLivestreamingErrorModuleImpl(Context context) {
        super(context);
    }
}
