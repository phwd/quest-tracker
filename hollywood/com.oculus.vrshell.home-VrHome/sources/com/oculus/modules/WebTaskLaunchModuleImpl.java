package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.oculus.modules.codegen.WebTaskLaunchModule;

public class WebTaskLaunchModuleImpl extends WebTaskLaunchModule {
    private static final String BROADCAST_TYPE_LAUNCH_INTENT = "com.oculus.vrshell.intent.action.LAUNCH";
    private static final String TAG = WebTaskLaunchModule.class.getSimpleName();
    private final Context mContext;

    public WebTaskLaunchModuleImpl(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.WebTaskLaunchModule
    public void broadcastWebTaskLaunchImpl(String uri) {
        Intent intent = new Intent(BROADCAST_TYPE_LAUNCH_INTENT);
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("uri", "ovrweb://webtask?uri=" + uri);
        intent.putExtra("intent_data", Uri.parse("systemux://browser"));
        intent.putExtra("blackscreen", false);
        this.mContext.sendBroadcast(intent);
    }
}
