package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ToasterModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = ToasterModule.class.getSimpleName();

    public enum ToastDuration {
        DEFAULT,
        LONG,
        SHORT
    }

    public enum ToastIcon {
        chat,
        check_alt,
        download,
        fitness,
        gear,
        home,
        info,
        library,
        mediagallery,
        microphone,
        pacific_controller,
        party,
        phone,
        store,
        tv
    }

    /* access modifiers changed from: protected */
    public abstract void createToastImpl(double d, Bread bread, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("createToast", "+djii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void createToast(double toastID, JSONObject bread, int resolveID, int rejectID) {
        createToastImpl(toastID, Bread.makeFromJSONObject(bread), ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class Bread extends NativeModuleParcel {
        public ToastDuration duration;
        public ToastIcon icon;
        public String id;
        public String message;
        public Boolean suppressSound;
        public String title;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                if (this.duration != null) {
                    parcel.put("duration", this.duration.name());
                }
                if (this.icon != null) {
                    parcel.put("icon", this.icon.name());
                }
                parcel.put("id", this.id);
                if (this.message != null) {
                    parcel.put("message", this.message);
                }
                if (this.suppressSound != null) {
                    parcel.put("suppressSound", this.suppressSound);
                }
                parcel.put("title", this.title);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            Boolean bool = null;
            this.duration = json.isNull("duration") ? null : ToastDuration.valueOf(json.optString("duration"));
            this.icon = json.isNull("icon") ? null : ToastIcon.valueOf(json.optString("icon"));
            this.id = json.optString("id");
            this.message = json.isNull("message") ? null : json.optString("message");
            if (!json.isNull("suppressSound")) {
                bool = Boolean.valueOf(json.optBoolean("suppressSound"));
            }
            this.suppressSound = bool;
            this.title = json.optString("title");
        }

        public static final Bread makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            Bread result = new Bread();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
