package com.oculus.modules.codegen;

import android.content.Context;
import android.util.Pair;
import androidx.core.app.NotificationCompat;
import com.oculus.panellib.modules.AbstractBroadcastReceiverModule;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class FirstTimeNuxOtaModule extends AbstractBroadcastReceiverModule {
    protected static final String MODULE_NAME = FirstTimeNuxOtaModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void fetchOtaProgressImpl(Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void setOkayToRebootImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void skipNuxImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void startProgressChecksImpl(Resolver<Boolean> resolver);

    public FirstTimeNuxOtaModule(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = AbstractBroadcastReceiverModule.describe();
        list.add(new Pair<>("fetchOtaProgress", "+ii"));
        list.add(new Pair<>("setOkayToReboot", "+ii"));
        list.add(new Pair<>("skipNux", "+ii"));
        list.add(new Pair<>("startProgressChecks", "+ii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void emitOnOTAProgressUpdated(OTAProgressUpdate data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "FirstTimeNuxOtaModule_onOTAProgressUpdated", String.valueOf(data.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void fetchOtaProgress(int resolveID, int rejectID) {
        fetchOtaProgressImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setOkayToReboot(int resolveID, int rejectID) {
        setOkayToRebootImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void skipNux(int resolveID, int rejectID) {
        skipNuxImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void startProgressChecks(int resolveID, int rejectID) {
        startProgressChecksImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule, com.oculus.panellib.modules.EarlyDestroyable
    public final void shutdownModule() {
        super.shutdownModule();
        shutdownModuleImpl();
    }

    /* access modifiers changed from: protected */
    public void shutdownModuleImpl() {
    }

    public static class OTAProgressUpdate extends NativeModuleParcel {
        public double progress;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put(NotificationCompat.CATEGORY_PROGRESS, this.progress);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.progress = json.optDouble(NotificationCompat.CATEGORY_PROGRESS, 0.0d);
        }

        public static final OTAProgressUpdate makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            OTAProgressUpdate result = new OTAProgressUpdate();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
