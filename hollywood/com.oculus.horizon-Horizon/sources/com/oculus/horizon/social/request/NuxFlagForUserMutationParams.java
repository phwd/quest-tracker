package com.oculus.horizon.social.request;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
public class NuxFlagForUserMutationParams {
    public static final String ARGUMENT_NUX_FLAG = "nux_flag";
    public static final String ARGUMENT_NUX_VALUE = "nux_value";
    public final String nuxFlag;
    public final String nuxValue;

    public NuxFlagForUserMutationParams(String str, String str2) {
        this.nuxFlag = str;
        this.nuxValue = str2;
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("nux_flag", this.nuxFlag);
        A01.put("nux_value", this.nuxValue);
        return GraphQLParamsHelper.encodeMutationParams(A01.build());
    }
}
