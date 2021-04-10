package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class AppLaunchModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = AppLaunchModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void broadcastAppLaunchImpl(String str, String str2);

    /* access modifiers changed from: protected */
    public abstract void joinRoomImpl(String str, String str2);

    /* access modifiers changed from: protected */
    public abstract void launchComponentImpl(String str);

    /* access modifiers changed from: protected */
    public abstract void requestsMicrophoneImpl(String str, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("broadcastAppLaunch", "ss"));
        list.add(new Pair<>("joinRoom", "ss"));
        list.add(new Pair<>("launchComponent", "s"));
        list.add(new Pair<>("requestsMicrophone", "+sii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void broadcastAppLaunch(String packageName, String intentCmd) {
        broadcastAppLaunchImpl(packageName, intentCmd);
    }

    /* access modifiers changed from: protected */
    public final void joinRoom(String packageName, String roomId) {
        joinRoomImpl(packageName, roomId);
    }

    /* access modifiers changed from: protected */
    public final void launchComponent(String componentNameFlattened) {
        launchComponentImpl(componentNameFlattened);
    }

    /* access modifiers changed from: protected */
    public final void requestsMicrophone(String packageName, int resolveID, int rejectID) {
        requestsMicrophoneImpl(packageName, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }
}
