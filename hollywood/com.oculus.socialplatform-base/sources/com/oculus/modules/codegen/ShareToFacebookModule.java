package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ShareToFacebookModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "ShareToFacebookModule";

    public static class MediaMetadata extends NativeModuleParcel {
        public String appID;
        public boolean isInstantReplay;
        public String richPresenceJSON;

        public static final MediaMetadata makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            MediaMetadata mediaMetadata = new MediaMetadata();
            mediaMetadata.setFromJSONObject(jSONObject);
            return mediaMetadata;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("appID", this.appID);
                jSONObject.put("isInstantReplay", this.isInstantReplay);
                jSONObject.put("richPresenceJSON", this.richPresenceJSON);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.appID = jSONObject.optString("appID");
            this.isInstantReplay = jSONObject.optBoolean("isInstantReplay");
            this.richPresenceJSON = jSONObject.optString("richPresenceJSON");
        }
    }

    public static class MediaUploadResponse extends NativeModuleParcel {
        public String error;
        public String objectId;

        public static final MediaUploadResponse makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            MediaUploadResponse mediaUploadResponse = new MediaUploadResponse();
            mediaUploadResponse.setFromJSONObject(jSONObject);
            return mediaUploadResponse;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                String str = this.error;
                if (str != null) {
                    jSONObject.put("error", str);
                }
                String str2 = this.objectId;
                if (str2 != null) {
                    jSONObject.put("objectId", str2);
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            String str = null;
            if (jSONObject.isNull("error")) {
                optString = null;
            } else {
                optString = jSONObject.optString("error");
            }
            this.error = optString;
            if (!jSONObject.isNull("objectId")) {
                str = jSONObject.optString("objectId");
            }
            this.objectId = str;
        }
    }

    public abstract void getMediaMetadataImpl(String str, Resolver<MediaMetadata> resolver);

    public final String marshallJSConstants() {
        return null;
    }

    public abstract void postPhotoToFacebookGroupImpl(String str, String str2, String str3, String str4, String str5, Resolver<MediaUploadResponse> resolver);

    public abstract void postPhotoToFacebookStoriesImpl(String str, String str2, String str3, String str4, Resolver<MediaUploadResponse> resolver);

    public abstract void postPhotoToFacebookTimelineImpl(String str, String str2, String str3, String str4, String str5, Resolver<MediaUploadResponse> resolver);

    public abstract void postPhotoToMessengerThreadImpl(String str, String str2, String str3, String str4, boolean z, String str5, Resolver<MediaUploadResponse> resolver);

    public abstract void postVideoToFacebookGroupImpl(String str, String str2, String str3, String str4, String str5, Resolver<MediaUploadResponse> resolver);

    public abstract void postVideoToFacebookStoriesImpl(String str, String str2, String str3, String str4, Resolver<MediaUploadResponse> resolver);

    public abstract void postVideoToFacebookTimelineImpl(String str, String str2, String str3, String str4, String str5, Resolver<MediaUploadResponse> resolver);

    public abstract void postVideoToMessengerThreadImpl(String str, String str2, String str3, String str4, boolean z, String str5, Resolver<MediaUploadResponse> resolver);

    public abstract void returnShareResultImpl(boolean z, String str, String str2, String str3);

    public void shutdownModule() {
    }

    public abstract void startSyncImpl();

    public abstract void uploadToOculusImpl(boolean z, String str, Resolver<MediaUploadResponse> resolver);

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("getMediaMetadata", "+sii"));
        arrayList.add(new Pair("postPhotoToFacebookGroup", "+sssssii"));
        arrayList.add(new Pair("postPhotoToFacebookStories", "+ssssii"));
        arrayList.add(new Pair("postPhotoToFacebookTimeline", "+sssssii"));
        arrayList.add(new Pair("postPhotoToMessengerThread", "+ssssbsii"));
        arrayList.add(new Pair("postVideoToFacebookGroup", "+sssssii"));
        arrayList.add(new Pair("postVideoToFacebookStories", "+ssssii"));
        arrayList.add(new Pair("postVideoToFacebookTimeline", "+sssssii"));
        arrayList.add(new Pair("postVideoToMessengerThread", "+ssssbsii"));
        arrayList.add(new Pair("returnShareResult", "bsss"));
        arrayList.add(new Pair("startSync", ""));
        arrayList.add(new Pair("uploadToOculus", "+bsii"));
        return arrayList;
    }

    public final void getMediaMetadata(String str, int i, int i2) {
        getMediaMetadataImpl(str, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void postPhotoToFacebookGroup(String str, String str2, String str3, String str4, String str5, int i, int i2) {
        postPhotoToFacebookGroupImpl(str, str2, str3, str4, str5, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void postPhotoToFacebookStories(String str, String str2, String str3, String str4, int i, int i2) {
        postPhotoToFacebookStoriesImpl(str, str2, str3, str4, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void postPhotoToFacebookTimeline(String str, String str2, String str3, String str4, String str5, int i, int i2) {
        postPhotoToFacebookTimelineImpl(str, str2, str3, str4, str5, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void postPhotoToMessengerThread(String str, String str2, String str3, String str4, boolean z, String str5, int i, int i2) {
        postPhotoToMessengerThreadImpl(str, str2, str3, str4, z, str5, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void postVideoToFacebookGroup(String str, String str2, String str3, String str4, String str5, int i, int i2) {
        postVideoToFacebookGroupImpl(str, str2, str3, str4, str5, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void postVideoToFacebookStories(String str, String str2, String str3, String str4, int i, int i2) {
        postVideoToFacebookStoriesImpl(str, str2, str3, str4, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void postVideoToFacebookTimeline(String str, String str2, String str3, String str4, String str5, int i, int i2) {
        postVideoToFacebookTimelineImpl(str, str2, str3, str4, str5, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void postVideoToMessengerThread(String str, String str2, String str3, String str4, boolean z, String str5, int i, int i2) {
        postVideoToMessengerThreadImpl(str, str2, str3, str4, z, str5, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void startSync() {
        startSyncImpl();
    }

    public final void uploadToOculus(boolean z, String str, int i, int i2) {
        uploadToOculusImpl(z, str, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void returnShareResult(boolean z, String str, String str2, String str3) {
        returnShareResultImpl(z, str, str2, str3);
    }
}
