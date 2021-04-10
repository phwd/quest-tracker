package com.oculus.vrshell.home.module;

import android.content.Context;
import com.oculus.vrshell.home.GraphQLPrefetchConstants;

public class HomeGraphQLPrefetchModule extends GraphQLPrefetchModule {
    public HomeGraphQLPrefetchModule(Context context) {
        super(context, GraphQLPrefetchConstants.getQueries());
    }
}
