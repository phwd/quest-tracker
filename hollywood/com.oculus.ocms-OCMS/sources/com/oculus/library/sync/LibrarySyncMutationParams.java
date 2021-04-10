package com.oculus.library.sync;

import com.google.common.collect.ImmutableMap;
import com.oculus.http.common.graphql.GraphQLParamsHelper;
import com.oculus.library.model.App;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LibrarySyncMutationParams {
    private static final String PARAM_APP_ID = "appID";
    private static final String PARAM_APP_LIBRARY = "app_library";
    private static final String PARAM_APP_STATUS = "appStatus";
    private static final String PARAM_DEVICE_ID = "device_id";
    private static final String PARAM_DEVICE_NAME = "device_name";
    private static final String PARAM_DEVICE_SERIAL = "device_serial";
    private static final String PARAM_LAST_USED_TIME = "lastUsedTime";
    private static final String PARAM_PLAY_TIME = "playTime";
    private static final String PARAM_PROGRESS = "progress";
    private static final String PARAM_PROGRESS_MAX = "progressMax";
    private static final String PARAM_USER_AGENT = "user_agent";
    private static final String PARAM_VERSION_CODE = "versionCode";
    private final ImmutableMap mParams;

    public static LibrarySyncMutationParams getParamsForLibrarySync(String str, String str2, String str3, String str4, List<App> list) {
        ArrayList arrayList = new ArrayList();
        for (App app : list) {
            arrayList.add(serializeApp(app));
        }
        return new LibrarySyncMutationParams(ImmutableMap.builder().put(PARAM_DEVICE_ID, str).put(PARAM_DEVICE_SERIAL, str2).put(PARAM_DEVICE_NAME, str3).put(PARAM_USER_AGENT, str4).put(PARAM_APP_LIBRARY, arrayList).build());
    }

    private LibrarySyncMutationParams(ImmutableMap immutableMap) {
        this.mParams = immutableMap;
    }

    private static String serializeApp(App app) {
        return GraphQLParamsHelper.encodeParams(ImmutableMap.builder().put(PARAM_APP_ID, app.id).put(PARAM_VERSION_CODE, Long.valueOf(app.versionCode)).put(PARAM_APP_STATUS, app.status).put(PARAM_LAST_USED_TIME, Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(app.recentActivityMs))).put(PARAM_PLAY_TIME, Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(app.totalActivityMs))).put("progress", Long.valueOf(app.downloadedSizeBytes)).put(PARAM_PROGRESS_MAX, Long.valueOf(app.downloadSizeBytes)).build());
    }

    public String toString() {
        return GraphQLParamsHelper.encodeMutationParams(this.mParams);
    }
}
