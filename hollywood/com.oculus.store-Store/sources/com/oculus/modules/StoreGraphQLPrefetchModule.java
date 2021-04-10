package com.oculus.modules;

import android.content.Context;
import com.oculus.store.GraphQLPrefetchConstants;
import com.oculus.utils.GraphQLPrefetchQuery;
import java.util.Arrays;
import java.util.List;

public class StoreGraphQLPrefetchModule extends GraphQLPrefetch {
    public StoreGraphQLPrefetchModule(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.GraphQLPrefetch
    public List<GraphQLPrefetchQuery> getQueries() {
        return Arrays.asList(GraphQLPrefetchConstants.getQueries());
    }
}
