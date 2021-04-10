package com.oculus.horizon.api.fbconnect;

import com.google.errorprone.annotations.Immutable;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import java.util.Collections;

@Immutable
public class FBUnsyncFriendsMutationParams {
    public String toString() {
        return GraphQLParamsHelper.encodeMutationParams(Collections.EMPTY_MAP);
    }
}
