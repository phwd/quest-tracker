package com.oculus.modules.codegen;

import android.content.Context;
import android.util.Pair;
import com.oculus.panellib.modules.AbstractBroadcastReceiverModule;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ConnectivityStateModule extends AbstractBroadcastReceiverModule {
    protected static final String MODULE_NAME = ConnectivityStateModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void isConnectedToWifiImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void startCheckingForInternetConnectionImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void stopCheckingForInternetConnectionImpl(Resolver<Boolean> resolver);

    public ConnectivityStateModule(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = AbstractBroadcastReceiverModule.describe();
        list.add(new Pair<>("isConnectedToWifi", "+ii"));
        list.add(new Pair<>("startCheckingForInternetConnection", "+ii"));
        list.add(new Pair<>("stopCheckingForInternetConnection", "+ii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void emitOnInternetConnectivityUpdated(ConnectivityUpdate data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "ConnectivityStateModule_onInternetConnectivityUpdated", String.valueOf(data.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void emitOnWiFiConnectivityUpdated(ConnectivityUpdate data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "ConnectivityStateModule_onWiFiConnectivityUpdated", String.valueOf(data.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void isConnectedToWifi(int resolveID, int rejectID) {
        isConnectedToWifiImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void startCheckingForInternetConnection(int resolveID, int rejectID) {
        startCheckingForInternetConnectionImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void stopCheckingForInternetConnection(int resolveID, int rejectID) {
        stopCheckingForInternetConnectionImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule, com.oculus.panellib.modules.EarlyDestroyable
    public final void shutdownModule() {
        super.shutdownModule();
        shutdownModuleImpl();
    }

    /* access modifiers changed from: protected */
    public void shutdownModuleImpl() {
    }

    public static class ConnectivityUpdate extends NativeModuleParcel {
        public boolean connected;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("connected", this.connected);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.connected = json.optBoolean("connected");
        }

        public static final ConnectivityUpdate makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            ConnectivityUpdate result = new ConnectivityUpdate();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
