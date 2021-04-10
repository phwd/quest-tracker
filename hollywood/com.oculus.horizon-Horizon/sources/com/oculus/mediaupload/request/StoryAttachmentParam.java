package com.oculus.mediaupload.request;

import com.facebook.internal.AnalyticsEvents;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.common.graphql.GraphQLParamsHelper;
import javax.annotation.Nullable;

public class StoryAttachmentParam {
    public final String attachmentId;
    @Nullable
    public final String gameId;
    public final FacebookShareAttachmentType type;

    public enum FacebookShareAttachmentType {
        PHOTO,
        VIDEO
    }

    /* renamed from: com.oculus.mediaupload.request.StoryAttachmentParam$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$mediaupload$request$StoryAttachmentParam$FacebookShareAttachmentType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.mediaupload.request.StoryAttachmentParam$FacebookShareAttachmentType[] r0 = com.oculus.mediaupload.request.StoryAttachmentParam.FacebookShareAttachmentType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.mediaupload.request.StoryAttachmentParam.AnonymousClass1.$SwitchMap$com$oculus$mediaupload$request$StoryAttachmentParam$FacebookShareAttachmentType = r2
                com.oculus.mediaupload.request.StoryAttachmentParam$FacebookShareAttachmentType r0 = com.oculus.mediaupload.request.StoryAttachmentParam.FacebookShareAttachmentType.PHOTO     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.mediaupload.request.StoryAttachmentParam$FacebookShareAttachmentType r0 = com.oculus.mediaupload.request.StoryAttachmentParam.FacebookShareAttachmentType.VIDEO     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.mediaupload.request.StoryAttachmentParam.AnonymousClass1.<clinit>():void");
        }
    }

    public StoryAttachmentParam(FacebookShareAttachmentType facebookShareAttachmentType, String str, @Nullable String str2) {
        this.attachmentId = str;
        this.type = facebookShareAttachmentType;
        this.gameId = str2;
    }

    public final String toString() {
        ImmutableMap immutableMap;
        String str;
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("id", this.attachmentId);
        ImmutableMap.Builder A012 = ImmutableMap.A01();
        switch (this.type.ordinal()) {
            case 0:
                immutableMap = A01.build();
                str = AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO;
                A012.put(str, immutableMap);
                break;
            case 1:
                String str2 = this.gameId;
                if (str2 != null) {
                    ImmutableMap.Builder A013 = ImmutableMap.A01();
                    A013.put("id", str2);
                    A01.put("game_metadata", A013.build());
                }
                immutableMap = A01.build();
                str = AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO;
                A012.put(str, immutableMap);
                break;
        }
        return GraphQLParamsHelper.GSON_CONVERTER.A06(A012.build());
    }
}
