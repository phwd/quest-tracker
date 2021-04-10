package com.oculus.mediaupload.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.http.common.graphql.GraphQLParamsHelper;
import com.oculus.mediaupload.model.FacebookShareRequest;

public class StoryPrivacyParam {
    public final FacebookShareRequest.FacebookSharePrivacy privacy;

    /* renamed from: com.oculus.mediaupload.request.StoryPrivacyParam$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$mediaupload$model$FacebookShareRequest$FacebookSharePrivacy;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        static {
            /*
                com.oculus.mediaupload.model.FacebookShareRequest$FacebookSharePrivacy[] r0 = com.oculus.mediaupload.model.FacebookShareRequest.FacebookSharePrivacy.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.mediaupload.request.StoryPrivacyParam.AnonymousClass1.$SwitchMap$com$oculus$mediaupload$model$FacebookShareRequest$FacebookSharePrivacy = r2
                com.oculus.mediaupload.model.FacebookShareRequest$FacebookSharePrivacy r0 = com.oculus.mediaupload.model.FacebookShareRequest.FacebookSharePrivacy.SELF     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.mediaupload.model.FacebookShareRequest$FacebookSharePrivacy r0 = com.oculus.mediaupload.model.FacebookShareRequest.FacebookSharePrivacy.ALL_FRIENDS     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.mediaupload.model.FacebookShareRequest$FacebookSharePrivacy r0 = com.oculus.mediaupload.model.FacebookShareRequest.FacebookSharePrivacy.FRIENDS_OF_FRIENDS     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.mediaupload.model.FacebookShareRequest$FacebookSharePrivacy r0 = com.oculus.mediaupload.model.FacebookShareRequest.FacebookSharePrivacy.EVERYONE     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.mediaupload.request.StoryPrivacyParam.AnonymousClass1.<clinit>():void");
        }
    }

    public StoryPrivacyParam(FacebookShareRequest.FacebookSharePrivacy facebookSharePrivacy) {
        this.privacy = facebookSharePrivacy;
    }

    public final String toString() {
        String str;
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        switch (this.privacy.ordinal()) {
            case 0:
                str = "SELF";
                A01.put("base_state", str);
                break;
            case 1:
                str = "FRIENDS";
                A01.put("base_state", str);
                break;
            case 2:
                str = "FRIENDS_OF_FRIENDS";
                A01.put("base_state", str);
                break;
            case 3:
                str = "EVERYONE";
                A01.put("base_state", str);
                break;
        }
        return GraphQLParamsHelper.GSON_CONVERTER.A06(A01.build());
    }
}
