package com.oculus.modules;

import android.content.Context;
import com.oculus.explore.GraphQLPrefetchConstants;
import com.oculus.utils.GraphQLPrefetchQuery;
import java.util.Arrays;
import java.util.List;

public class ExploreGraphQLPrefetchModule extends GraphQLPrefetch {
    public ExploreGraphQLPrefetchModule(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.GraphQLPrefetch
    public List<GraphQLPrefetchQuery> getQueries() {
        return Arrays.asList(GraphQLPrefetchConstants.getQueries());
    }
}
