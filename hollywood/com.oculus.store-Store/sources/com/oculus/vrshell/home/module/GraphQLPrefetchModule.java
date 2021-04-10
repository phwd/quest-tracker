package com.oculus.vrshell.home.module;

import android.content.Context;
import android.util.Log;
import com.facebook.debug.log.BLog;
import com.oculus.modules.GraphQLPrefetch;
import com.oculus.utils.GraphQLPrefetchQuery;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class GraphQLPrefetchModule extends GraphQLPrefetch {
    private static String MODULE_NAME = GraphQLPrefetch.class.getSimpleName();
    private static String TAG = GraphQLPrefetch.class.getSimpleName();
    private final GraphQLPrefetchQuery[] mAllQueries;
    private String mProcessName;

    public GraphQLPrefetchModule(Context context, GraphQLPrefetchQuery[] allQueries) {
        super(context);
        this.mAllQueries = allQueries;
    }

    public void setProcessName(String processName) {
        this.mProcessName = processName;
    }

    @Override // com.oculus.modules.GraphQLPrefetch
    public String getModuleName() {
        return MODULE_NAME;
    }

    @Override // com.oculus.modules.GraphQLPrefetch
    public void shutdownModule() {
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.GraphQLPrefetch
    public List<GraphQLPrefetchQuery> getQueries() {
        ArrayList<GraphQLPrefetchQuery> queriesForActiveService = new ArrayList<>();
        String[] parts = this.mProcessName.split(":");
        if (parts.length < 2) {
            Log.e(TAG, String.format("Can't extract service name from the process name, no query prefetching will be done: %s", this.mProcessName));
        } else {
            String currentService = parts[1].toUpperCase();
            GraphQLPrefetchQuery[] graphQLPrefetchQueryArr = this.mAllQueries;
            for (GraphQLPrefetchQuery query : graphQLPrefetchQueryArr) {
                try {
                    JSONObject json = query.getExtra();
                    if (json != null && json.has("services")) {
                        JSONArray services = json.getJSONArray("services");
                        for (int j = 0; j < services.length(); j++) {
                            if (currentService.equals(services.get(j).toString())) {
                                queriesForActiveService.add(query);
                            }
                        }
                    }
                } catch (JSONException e) {
                    BLog.e(TAG, "Exception unpacking extras");
                }
            }
        }
        return queriesForActiveService;
    }
}
