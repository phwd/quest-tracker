package com.oculus.horizon.api.fbconnect;

import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import java.util.Collections;
import javax.annotation.concurrent.Immutable;

@Immutable
public class FBImportProfilePictureMutationParams {
    public String toString() {
        return GraphQLParamsHelper.encodeMutationParams(Collections.EMPTY_MAP);
    }
}
