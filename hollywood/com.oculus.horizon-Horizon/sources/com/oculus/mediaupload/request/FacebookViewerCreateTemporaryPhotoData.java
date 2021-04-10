package com.oculus.mediaupload.request;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.common.graphql.GraphQLParamsHelper;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FacebookViewerCreateTemporaryPhotoData {
    public final String actorId;
    public final String source = "MOBILE";

    public FacebookViewerCreateTemporaryPhotoData(String str) {
        this.actorId = str;
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("actor_id", this.actorId);
        A01.put("source", "MOBILE");
        return GraphQLParamsHelper.A01(A01.build());
    }
}
