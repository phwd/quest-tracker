package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class SystemActivitiesModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = SystemActivitiesModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract String marshallJSConstantIntentExitToHome();

    /* access modifiers changed from: protected */
    public abstract String marshallJSConstantIntentReorient();

    /* access modifiers changed from: protected */
    public abstract String marshallJSConstantIntentReturnToLauncher();

    /* access modifiers changed from: protected */
    public abstract void sendIntentImpl(String str);

    /* access modifiers changed from: protected */
    public abstract void sendIntentToPkgImpl(String str, String str2);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("sendIntent", "s"));
        list.add(new Pair<>("sendIntentToPkg", "ss"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        JSONObject result = new JSONObject();
        try {
            result.put("INTENT_EXIT_TO_HOME", marshallJSConstantIntentExitToHome());
            result.put("INTENT_REORIENT", marshallJSConstantIntentReorient());
            result.put("INTENT_RETURN_TO_LAUNCHER", marshallJSConstantIntentReturnToLauncher());
        } catch (JSONException e) {
        }
        return result.toString();
    }

    /* access modifiers changed from: protected */
    public final void sendIntent(String command) {
        sendIntentImpl(command);
    }

    /* access modifiers changed from: protected */
    public final void sendIntentToPkg(String command, String packageName) {
        sendIntentToPkgImpl(command, packageName);
    }

    public void shutdownModule() {
    }
}
