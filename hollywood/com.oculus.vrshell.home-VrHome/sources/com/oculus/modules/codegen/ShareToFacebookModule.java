package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ShareToFacebookModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = ShareToFacebookModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract boolean canShareToBugReporterImpl();

    /* access modifiers changed from: protected */
    public abstract boolean canShareToFbStoriesImpl();

    /* access modifiers changed from: protected */
    public abstract void getMediaMetadataImpl(String str, Resolver<MediaMetadata> resolver);

    /* access modifiers changed from: protected */
    public abstract boolean isEmployeeImpl();

    /* access modifiers changed from: protected */
    public abstract boolean isRecentsEnabledImpl();

    /* access modifiers changed from: protected */
    public abstract void postPhotoToFacebookGroupImpl(String str, String str2, String str3, String str4, String str5, Resolver<MediaUploadResponse> resolver);

    /* access modifiers changed from: protected */
    public abstract void postPhotoToFacebookStoriesImpl(String str, String str2, String str3, String str4, Resolver<MediaUploadResponse> resolver);

    /* access modifiers changed from: protected */
    public abstract void postPhotoToFacebookTimelineImpl(String str, String str2, String str3, String str4, String str5, Resolver<MediaUploadResponse> resolver);

    /* access modifiers changed from: protected */
    public abstract void postPhotoToMessengerThreadImpl(String str, String str2, String str3, String str4, boolean z, String str5, Resolver<MediaUploadResponse> resolver);

    /* access modifiers changed from: protected */
    public abstract void postVideoToFacebookGroupImpl(String str, String str2, String str3, String str4, String str5, Resolver<MediaUploadResponse> resolver);

    /* access modifiers changed from: protected */
    public abstract void postVideoToFacebookStoriesImpl(String str, String str2, String str3, String str4, Resolver<MediaUploadResponse> resolver);

    /* access modifiers changed from: protected */
    public abstract void postVideoToFacebookTimelineImpl(String str, String str2, String str3, String str4, String str5, Resolver<MediaUploadResponse> resolver);

    /* access modifiers changed from: protected */
    public abstract void postVideoToMessengerThreadImpl(String str, String str2, String str3, String str4, boolean z, String str5, Resolver<MediaUploadResponse> resolver);

    /* access modifiers changed from: protected */
    public abstract void returnShareResultImpl(boolean z, String str, String str2, String str3);

    /* access modifiers changed from: protected */
    public abstract void startSyncImpl();

    /* access modifiers changed from: protected */
    public abstract void uploadToOculusImpl(boolean z, String str, Resolver<MediaUploadResponse> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("canShareToBugReporter", "-"));
        list.add(new Pair<>("canShareToFbStories", "-"));
        list.add(new Pair<>("getMediaMetadata", "+sii"));
        list.add(new Pair<>("isEmployee", "-"));
        list.add(new Pair<>("isRecentsEnabled", "-"));
        list.add(new Pair<>("postPhotoToFacebookGroup", "+sssssii"));
        list.add(new Pair<>("postPhotoToFacebookStories", "+ssssii"));
        list.add(new Pair<>("postPhotoToFacebookTimeline", "+sssssii"));
        list.add(new Pair<>("postPhotoToMessengerThread", "+ssssbsii"));
        list.add(new Pair<>("postVideoToFacebookGroup", "+sssssii"));
        list.add(new Pair<>("postVideoToFacebookStories", "+ssssii"));
        list.add(new Pair<>("postVideoToFacebookTimeline", "+sssssii"));
        list.add(new Pair<>("postVideoToMessengerThread", "+ssssbsii"));
        list.add(new Pair<>("returnShareResult", "bsss"));
        list.add(new Pair<>("startSync", ""));
        list.add(new Pair<>("uploadToOculus", "+bsii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final Object canShareToBugReporter() {
        return Boolean.valueOf(canShareToBugReporterImpl());
    }

    /* access modifiers changed from: protected */
    public final Object canShareToFbStories() {
        return Boolean.valueOf(canShareToFbStoriesImpl());
    }

    /* access modifiers changed from: protected */
    public final void getMediaMetadata(String filename, int resolveID, int rejectID) {
        getMediaMetadataImpl(filename, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final Object isEmployee() {
        return Boolean.valueOf(isEmployeeImpl());
    }

    /* access modifiers changed from: protected */
    public final Object isRecentsEnabled() {
        return Boolean.valueOf(isRecentsEnabledImpl());
    }

    /* access modifiers changed from: protected */
    public final void postPhotoToFacebookGroup(String accessToken, String description, String photoName, String groupID, String gameID, int resolveID, int rejectID) {
        postPhotoToFacebookGroupImpl(accessToken, description, photoName, groupID, gameID, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void postPhotoToFacebookStories(String accessToken, String description, String photoName, String gameID, int resolveID, int rejectID) {
        postPhotoToFacebookStoriesImpl(accessToken, description, photoName, gameID, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void postPhotoToFacebookTimeline(String accessToken, String description, String photoName, String privacy, String gameID, int resolveID, int rejectID) {
        postPhotoToFacebookTimelineImpl(accessToken, description, photoName, privacy, gameID, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void postPhotoToMessengerThread(String accessToken, String description, String photoName, String threadID, boolean isGroupThread, String notificationErrorMessage, int resolveID, int rejectID) {
        postPhotoToMessengerThreadImpl(accessToken, description, photoName, threadID, isGroupThread, notificationErrorMessage, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void postVideoToFacebookGroup(String accessToken, String description, String videoName, String groupID, String gameID, int resolveID, int rejectID) {
        postVideoToFacebookGroupImpl(accessToken, description, videoName, groupID, gameID, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void postVideoToFacebookStories(String accessToken, String description, String videoName, String gameID, int resolveID, int rejectID) {
        postVideoToFacebookStoriesImpl(accessToken, description, videoName, gameID, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void postVideoToFacebookTimeline(String accessToken, String description, String videoName, String privacy, String gameID, int resolveID, int rejectID) {
        postVideoToFacebookTimelineImpl(accessToken, description, videoName, privacy, gameID, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void postVideoToMessengerThread(String accessToken, String description, String videoName, String threadID, boolean isGroupThread, String notificationErrorMessage, int resolveID, int rejectID) {
        postVideoToMessengerThreadImpl(accessToken, description, videoName, threadID, isGroupThread, notificationErrorMessage, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void returnShareResult(boolean result, String response, String platformRequestId, String fromPkg) {
        returnShareResultImpl(result, response, platformRequestId, fromPkg);
    }

    /* access modifiers changed from: protected */
    public final void startSync() {
        startSyncImpl();
    }

    /* access modifiers changed from: protected */
    public final void uploadToOculus(boolean isVideo, String fileName, int resolveID, int rejectID) {
        uploadToOculusImpl(isVideo, fileName, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class MediaMetadata extends NativeModuleParcel {
        public String appID;
        public boolean isInstantReplay;
        public String richPresenceJSON;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("appID", this.appID);
                parcel.put("isInstantReplay", this.isInstantReplay);
                parcel.put("richPresenceJSON", this.richPresenceJSON);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.appID = json.optString("appID");
            this.isInstantReplay = json.optBoolean("isInstantReplay");
            this.richPresenceJSON = json.optString("richPresenceJSON");
        }

        public static final MediaMetadata makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            MediaMetadata result = new MediaMetadata();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class MediaUploadResponse extends NativeModuleParcel {
        public String error;
        public String objectId;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                if (this.error != null) {
                    parcel.put("error", this.error);
                }
                if (this.objectId != null) {
                    parcel.put("objectId", this.objectId);
                }
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.error = json.isNull("error") ? null : json.optString("error");
            if (!json.isNull("objectId")) {
                str = json.optString("objectId");
            }
            this.objectId = str;
        }

        public static final MediaUploadResponse makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            MediaUploadResponse result = new MediaUploadResponse();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
