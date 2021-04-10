package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ToasterModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "ToasterModule";

    public static class Bread extends NativeModuleParcel {
        public ToastDuration duration;
        public ToastIcon icon;
        public String id;
        public String message;
        public Boolean suppressSound;
        public String title;

        public static final Bread makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            Bread bread = new Bread();
            bread.setFromJSONObject(jSONObject);
            return bread;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                ToastDuration toastDuration = this.duration;
                if (toastDuration != null) {
                    jSONObject.put("duration", toastDuration.name());
                }
                ToastIcon toastIcon = this.icon;
                if (toastIcon != null) {
                    jSONObject.put("icon", toastIcon.name());
                }
                jSONObject.put("id", this.id);
                String str = this.message;
                if (str != null) {
                    jSONObject.put("message", str);
                }
                Boolean bool = this.suppressSound;
                if (bool != null) {
                    jSONObject.put("suppressSound", bool);
                }
                jSONObject.put("title", this.title);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            ToastDuration valueOf;
            ToastIcon valueOf2;
            String optString;
            Boolean bool = null;
            if (jSONObject.isNull("duration")) {
                valueOf = null;
            } else {
                valueOf = ToastDuration.valueOf(jSONObject.optString("duration"));
            }
            this.duration = valueOf;
            if (jSONObject.isNull("icon")) {
                valueOf2 = null;
            } else {
                valueOf2 = ToastIcon.valueOf(jSONObject.optString("icon"));
            }
            this.icon = valueOf2;
            this.id = jSONObject.optString("id");
            if (jSONObject.isNull("message")) {
                optString = null;
            } else {
                optString = jSONObject.optString("message");
            }
            this.message = optString;
            if (!jSONObject.isNull("suppressSound")) {
                bool = Boolean.valueOf(jSONObject.optBoolean("suppressSound"));
            }
            this.suppressSound = bool;
            this.title = jSONObject.optString("title");
        }
    }

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

    public abstract void createToastImpl(double d, Bread bread, Resolver<Void> resolver);

    public final String marshallJSConstants() {
        return null;
    }

    public void shutdownModule() {
    }

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("createToast", "+djii"));
        return arrayList;
    }

    public final void createToast(double d, JSONObject jSONObject, int i, int i2) {
        createToastImpl(d, Bread.makeFromJSONObject(jSONObject), ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }
}
