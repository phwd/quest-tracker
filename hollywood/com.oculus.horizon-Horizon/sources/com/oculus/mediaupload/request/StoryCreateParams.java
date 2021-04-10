package com.oculus.mediaupload.request;

import X.AnonymousClass0NO;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat$CarExtender;
import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.service.result.SetRichPresenceResultBuilder;
import com.oculus.http.common.graphql.GraphQLParamsHelper;
import com.oculus.mediaupload.model.MediaMetadata;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class StoryCreateParams {
    public final String actor_id;
    public final StoryAttachmentParam attachment;
    public final StoryAudienceParam audience;
    @Nullable
    public final String description;
    public final String source = "MOBILE";
    @Nullable
    public final StoryVRMetadataParam vrMetadata;

    public static class StoryAudienceParam {
        public final FacebookShareDestinationType destination;
        @Nullable
        public final StoryPrivacyParam privacy;
        @Nullable
        public final String toId;

        public enum FacebookShareDestinationType {
            TIMELINE,
            GROUP,
            STORY
        }

        public final String toString() {
            String str;
            String str2;
            ImmutableMap.Builder A01 = ImmutableMap.A01();
            FacebookShareDestinationType facebookShareDestinationType = this.destination;
            switch (facebookShareDestinationType.ordinal()) {
                case 0:
                    StoryPrivacyParam storyPrivacyParam = this.privacy;
                    if (storyPrivacyParam == null) {
                        str = StoryAudienceParam.class.getName();
                        str2 = "privacy is not set for sharing to timeline";
                        AnonymousClass0NO.A08(str, str2);
                        return "";
                    }
                    A01.put("privacy", storyPrivacyParam.toString());
                    return GraphQLParamsHelper.GSON_CONVERTER.A06(A01.build());
                case 1:
                    String str3 = this.toId;
                    if (str3 == null) {
                        str = StoryAudienceParam.class.getName();
                        str2 = "group id is not set for sharing to group";
                        AnonymousClass0NO.A08(str, str2);
                        return "";
                    }
                    A01.put("to_id", str3);
                    return GraphQLParamsHelper.GSON_CONVERTER.A06(A01.build());
                case 2:
                    String str4 = this.toId;
                    if (str4 == null) {
                        str = StoryAudienceParam.class.getName();
                        str2 = "target id is not set for sharing to story";
                        AnonymousClass0NO.A08(str, str2);
                        return "";
                    }
                    ImmutableMap.Builder A012 = ImmutableMap.A01();
                    ImmutableMap.Builder A013 = ImmutableMap.A01();
                    A013.put("target_id", str4);
                    A012.put("self", A013.build());
                    A01.put("stories", A012.build());
                    return GraphQLParamsHelper.GSON_CONVERTER.A06(A01.build());
                default:
                    AnonymousClass0NO.A0E(StoryAudienceParam.class.getName(), "Unsupported sharing destination for audience field: ", facebookShareDestinationType);
                    return GraphQLParamsHelper.GSON_CONVERTER.A06(A01.build());
            }
        }

        public StoryAudienceParam(StoryPrivacyParam storyPrivacyParam) {
            this.privacy = storyPrivacyParam;
            this.toId = null;
            this.destination = FacebookShareDestinationType.TIMELINE;
        }

        public StoryAudienceParam(String str, boolean z) {
            this.privacy = null;
            this.toId = str;
            this.destination = z ? FacebookShareDestinationType.STORY : FacebookShareDestinationType.GROUP;
        }
    }

    /* renamed from: com.oculus.mediaupload.request.StoryCreateParams$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$mediaupload$request$StoryCreateParams$StoryAudienceParam$FacebookShareDestinationType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                com.oculus.mediaupload.request.StoryCreateParams$StoryAudienceParam$FacebookShareDestinationType[] r0 = com.oculus.mediaupload.request.StoryCreateParams.StoryAudienceParam.FacebookShareDestinationType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.mediaupload.request.StoryCreateParams.AnonymousClass1.$SwitchMap$com$oculus$mediaupload$request$StoryCreateParams$StoryAudienceParam$FacebookShareDestinationType = r2
                com.oculus.mediaupload.request.StoryCreateParams$StoryAudienceParam$FacebookShareDestinationType r0 = com.oculus.mediaupload.request.StoryCreateParams.StoryAudienceParam.FacebookShareDestinationType.TIMELINE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.mediaupload.request.StoryCreateParams$StoryAudienceParam$FacebookShareDestinationType r0 = com.oculus.mediaupload.request.StoryCreateParams.StoryAudienceParam.FacebookShareDestinationType.GROUP     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.mediaupload.request.StoryCreateParams$StoryAudienceParam$FacebookShareDestinationType r0 = com.oculus.mediaupload.request.StoryCreateParams.StoryAudienceParam.FacebookShareDestinationType.STORY     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.mediaupload.request.StoryCreateParams.AnonymousClass1.<clinit>():void");
        }
    }

    public static class StoryMessageParam {
        public final String message;

        public StoryMessageParam(String str) {
            this.message = str;
        }

        public final String toString() {
            ImmutableMap.Builder A01 = ImmutableMap.A01();
            A01.put(NotificationCompat$CarExtender.KEY_TEXT, this.message);
            return GraphQLParamsHelper.GSON_CONVERTER.A06(A01.build());
        }
    }

    public static class StoryVRMetadataParam {
        @Nullable
        public final String applicationID;
        @Nullable
        public final String richPresenceJSON;
        public final SourceType source;

        public enum SourceType {
            VR_LIVE_STREAMING,
            VR_PHOTO_SHARING,
            VR_VIDEO_SHARING
        }

        public StoryVRMetadataParam(MediaMetadata mediaMetadata, SourceType sourceType) {
            this.applicationID = mediaMetadata.appID;
            this.richPresenceJSON = mediaMetadata.richPresenceJSON;
            this.source = sourceType;
        }

        public final String toString() {
            ImmutableMap.Builder A01 = ImmutableMap.A01();
            if (!TextUtils.isEmpty(this.applicationID)) {
                A01.put("application_id", this.applicationID);
            }
            if (!TextUtils.isEmpty(this.richPresenceJSON)) {
                A01.put(SetRichPresenceResultBuilder.RICH_PRESENCE_JSON_ARG_KEY, this.richPresenceJSON);
            }
            A01.put("source", this.source.toString());
            return GraphQLParamsHelper.GSON_CONVERTER.A06(A01.build());
        }
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.attachment.toString());
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("source", "MOBILE");
        A01.put("attachments", arrayList);
        A01.put("actor_id", this.actor_id);
        StoryAudienceParam storyAudienceParam = this.audience;
        if (storyAudienceParam.destination == StoryAudienceParam.FacebookShareDestinationType.STORY) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(storyAudienceParam.toString());
            A01.put("audiences", arrayList2);
        } else {
            A01.put("audience", storyAudienceParam.toString());
        }
        String str = this.description;
        if (str != null) {
            A01.put("message", new StoryMessageParam(str).toString());
        }
        StoryVRMetadataParam storyVRMetadataParam = this.vrMetadata;
        if (storyVRMetadataParam != null) {
            A01.put("vr_metadata", storyVRMetadataParam.toString());
        }
        return GraphQLParamsHelper.A00(A01.build());
    }

    public StoryCreateParams(String str, StoryAttachmentParam storyAttachmentParam, StoryAudienceParam storyAudienceParam, @Nullable String str2, @Nullable StoryVRMetadataParam storyVRMetadataParam) {
        this.attachment = storyAttachmentParam;
        this.audience = storyAudienceParam;
        this.actor_id = str;
        this.description = str2;
        this.vrMetadata = storyVRMetadataParam;
    }
}
