package com.oculus.vrshell.home.module;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import com.google.common.base.Strings;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public class ShutdownIntentModule extends RCTBaseJavaModule {
    private static String MODULE_NAME = new String("ShutdownIntentModule");
    private boolean hasShutdownIntent = false;
    private final Context mContext;
    private String shutdownAction = "";
    private String shutdownPackage = "";
    private String shutdownURI = "";

    public ShutdownIntentModule(Context context) {
        this.mContext = context;
    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public void setShutdownIntent(String packageName, String action, String uri) {
        this.hasShutdownIntent = true;
        this.shutdownURI = uri;
        this.shutdownAction = action;
        this.shutdownPackage = packageName;
    }

    public void clearShutdownIntent() {
        this.hasShutdownIntent = false;
        this.shutdownURI = "";
        this.shutdownAction = "";
        this.shutdownPackage = "";
    }

    public void shutdownModule() {
        if (this.hasShutdownIntent) {
            Log.i("ShutdownIntentModule", "Sending activity '" + this.shutdownAction + "' or URI " + this.shutdownURI + " to " + this.shutdownPackage);
            Intent intent = new Intent();
            if (!Strings.isNullOrEmpty(this.shutdownURI)) {
                intent.setData(Uri.parse(this.shutdownURI));
            } else {
                intent.setAction(this.shutdownAction);
            }
            intent.setPackage(this.shutdownPackage);
            intent.addFlags(268435456);
            this.mContext.getApplicationContext().startActivity(intent);
            return;
        }
        Log.i("ShutdownIntentModule", "No intent to send");
    }

    public static List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("clearShutdownIntent", ""));
        list.add(new Pair<>("setShutdownIntent", "sss"));
        return list;
    }
}
