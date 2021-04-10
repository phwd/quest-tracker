package com.oculus.vrshell.home.module;

import android.content.Context;
import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import com.oculus.vrshell.home.OverlaysInputBlocker;
import java.util.ArrayList;
import java.util.List;

public class OverlaysModule extends RCTBaseJavaModule {
    private static String MODULE_NAME = OverlaysModule.class.getSimpleName();
    private static final String TAG = MODULE_NAME;
    private Context mContext = null;

    public OverlaysModule(Context context) {
        this.mContext = context;
    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public void blockBackgroundInput(String inputBlockingType) {
        OverlaysInputBlocker.startBlockingInput(this.mContext, inputBlockingType);
    }

    public void unblockBackgroundInput() {
        OverlaysInputBlocker.stopBlockingInput(this.mContext);
    }

    public static List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("blockBackgroundInput", "s"));
        list.add(new Pair<>("unblockBackgroundInput", ""));
        return list;
    }

    public void shutdownModule() {
    }
}
