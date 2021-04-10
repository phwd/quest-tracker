package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class AppLaunchModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "AppLaunchModule";

    public abstract void broadcastAppLaunchImpl(String str, String str2);

    public abstract void joinRoomImpl(String str, String str2);

    public abstract void launchComponentImpl(String str);

    public final String marshallJSConstants() {
        return null;
    }

    public abstract void requestsMicrophoneImpl(String str, Resolver<Boolean> resolver);

    public void shutdownModule() {
    }

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("broadcastAppLaunch", "ss"));
        arrayList.add(new Pair("joinRoom", "ss"));
        arrayList.add(new Pair("launchComponent", "s"));
        arrayList.add(new Pair("requestsMicrophone", "+sii"));
        return arrayList;
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void requestsMicrophone(String str, int i, int i2) {
        requestsMicrophoneImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void launchComponent(String str) {
        launchComponentImpl(str);
    }

    public final void broadcastAppLaunch(String str, String str2) {
        broadcastAppLaunchImpl(str, str2);
    }

    public final void joinRoom(String str, String str2) {
        joinRoomImpl(str, str2);
    }
}
