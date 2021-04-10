package com.oculus.modules.codegen;

import android.content.Context;
import android.util.Pair;
import com.oculus.panellib.modules.AbstractBroadcastReceiverModule;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class CompanionServerModule extends AbstractBroadcastReceiverModule {
    protected static final String MODULE_NAME = CompanionServerModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void isPinSetImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void setPinImpl(String str, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void updateHorizonStateImpl(Resolver<Boolean> resolver);

    public CompanionServerModule(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = AbstractBroadcastReceiverModule.describe();
        list.add(new Pair<>("isPinSet", "+ii"));
        list.add(new Pair<>("setPin", "+sii"));
        list.add(new Pair<>("updateHorizonState", "+ii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void emitOnLocaleUpdated(LocaleUpdate data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "CompanionServerModule_onLocaleUpdated", String.valueOf(data.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void isPinSet(int resolveID, int rejectID) {
        isPinSetImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setPin(String pin, int resolveID, int rejectID) {
        setPinImpl(pin, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void updateHorizonState(int resolveID, int rejectID) {
        updateHorizonStateImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public final void shutdownModule() {
        super.shutdownModule();
        shutdownModuleImpl();
    }

    /* access modifiers changed from: protected */
    public void shutdownModuleImpl() {
    }

    public static class LocaleUpdate extends NativeModuleParcel {
        public String country;
        public String language;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("country", this.country);
                parcel.put("language", this.language);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.country = json.optString("country");
            this.language = json.optString("language");
        }
    }
}
