package com.oculus.modules;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.oculus.device.AccessTokenForwarder;
import com.oculus.panellib.GraphQLUtil;
import com.oculus.panellib.PanelEnvironment;
import com.oculus.panellib.Qpl;
import com.oculus.panellib.Systrace;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import com.oculus.utils.GraphQLPrefetchQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class GraphQLPrefetch extends RCTBaseJavaModule implements AccessTokenForwarder.IListener, PanelEnvironment.Listener {
    private static String MODULE_NAME = GraphQLPrefetch.class.getSimpleName();
    private static String TAG = GraphQLPrefetch.class.getSimpleName();
    private String mAccessToken = null;
    private OkHttpClient mClient = null;
    private ConcurrentLinkedQueue<String> mCompletedPrefetchResponses = new ConcurrentLinkedQueue<>();
    private Context mContext = null;
    private List<GraphQLPrefetchQuery> mQueries = null;
    private boolean mSubscribedTo = false;

    /* access modifiers changed from: protected */
    public abstract List<GraphQLPrefetchQuery> getQueries();

    public GraphQLPrefetch(Context context) {
        this.mContext = context;
        AccessTokenForwarder forwarder = AccessTokenForwarder.getInstance();
        if (forwarder != null) {
            forwarder.addListener(this);
        }
        PanelEnvironment.addListener(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
        r3 = r2;
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0046, code lost:
        r2 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private okhttp3.OkHttpClient createHttpClient() {
        /*
            r7 = this;
            com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
            java.lang.String r2 = com.oculus.modules.GraphQLPrefetch.TAG
            java.lang.String r3 = "createHttpClient"
            r0.<init>(r2, r3)
            r3 = 0
            okhttp3.OkHttpClient$Builder r2 = new okhttp3.OkHttpClient$Builder     // Catch:{ Throwable -> 0x002f, all -> 0x0046 }
            r2.<init>()     // Catch:{ Throwable -> 0x002f, all -> 0x0046 }
            com.oculus.device.APIInterceptor r4 = new com.oculus.device.APIInterceptor     // Catch:{ Throwable -> 0x002f, all -> 0x0046 }
            android.content.Context r5 = r7.mContext     // Catch:{ Throwable -> 0x002f, all -> 0x0046 }
            r4.<init>(r5)     // Catch:{ Throwable -> 0x002f, all -> 0x0046 }
            okhttp3.OkHttpClient$Builder r2 = r2.addNetworkInterceptor(r4)     // Catch:{ Throwable -> 0x002f, all -> 0x0046 }
            okhttp3.OkHttpClient r1 = r2.build()     // Catch:{ Throwable -> 0x002f, all -> 0x0046 }
            if (r0 == 0) goto L_0x0025
            if (r3 == 0) goto L_0x002b
            r0.close()     // Catch:{ Throwable -> 0x0026 }
        L_0x0025:
            return r1
        L_0x0026:
            r2 = move-exception
            r3.addSuppressed(r2)
            goto L_0x0025
        L_0x002b:
            r0.close()
            goto L_0x0025
        L_0x002f:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r3 = move-exception
            r6 = r3
            r3 = r2
            r2 = r6
        L_0x0035:
            if (r0 == 0) goto L_0x003c
            if (r3 == 0) goto L_0x0042
            r0.close()     // Catch:{ Throwable -> 0x003d }
        L_0x003c:
            throw r2
        L_0x003d:
            r4 = move-exception
            r3.addSuppressed(r4)
            goto L_0x003c
        L_0x0042:
            r0.close()
            goto L_0x003c
        L_0x0046:
            r2 = move-exception
            goto L_0x0035
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.GraphQLPrefetch.createHttpClient():okhttp3.OkHttpClient");
    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public void shutdownModule() {
    }

    public static List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("onSubscribed", ""));
        return list;
    }

    @Override // com.oculus.panellib.PanelEnvironment.Listener
    public final void onPanelEnvironment(String[] env) {
        this.mQueries = getQueries();
        Double renderScale = null;
        int i = 0;
        while (true) {
            if (i >= env.length) {
                break;
            } else if (env[i].equals("_oc_shell_render_scale")) {
                renderScale = Double.valueOf(env[i + 1]);
                break;
            } else {
                i += 2;
            }
        }
        for (GraphQLPrefetchQuery query : this.mQueries) {
            JSONObject variables = query.getVariables();
            if (variables.has("imageScale") && renderScale != null) {
                try {
                    variables.put("imageScale", renderScale);
                } catch (JSONException e) {
                }
            }
        }
        if (this.mAccessToken != null) {
            fetchQueries();
        }
    }

    @Override // com.oculus.device.AccessTokenForwarder.IListener
    public final void onGetAccessToken(String accessToken) {
        this.mAccessToken = accessToken;
        if (this.mQueries != null) {
            fetchQueries();
        }
    }

    public final void onSubscribed() {
        this.mSubscribedTo = true;
        pushPrefetchQueries();
        tryPushPrefetchResponses();
        this.mQueries = null;
    }

    private final void pushPrefetchQueries() {
        try {
            JSONArray array = new JSONArray();
            for (GraphQLPrefetchQuery query : this.mQueries) {
                array.put(new JSONObject().put("docId", String.valueOf(query.getDocId())).put("variables", query.getVariables()));
            }
            nativeEmitEventWithJsonData(this.RVRCtxTag, "onGetPrefetchQueries", array.toString());
        } catch (JSONException e) {
            Log.e(TAG, "Failed to encode JSON for getPrefetchQueries " + e.toString());
            nativeEmitEventWithJsonData(this.RVRCtxTag, "onGetPrefetchQueries", "[]");
        }
    }

    public final void tryPushPrefetchResponses() {
        if (this.mSubscribedTo && !this.mCompletedPrefetchResponses.isEmpty()) {
            Log.d(TAG, "Pushing prefetch responses");
            while (!this.mCompletedPrefetchResponses.isEmpty()) {
                String data = this.mCompletedPrefetchResponses.poll();
                if (data != null) {
                    nativeEmitEventWithJsonData(this.RVRCtxTag, "onGetPrefetchResponse", data);
                } else {
                    Log.e(TAG, "Failed to send response!");
                }
            }
        }
    }

    private final void fetchQueries() {
        if (this.mClient == null && !this.mQueries.isEmpty()) {
            this.mClient = createHttpClient();
        }
        int queryIdx = 0;
        for (GraphQLPrefetchQuery query : this.mQueries) {
            final long docId = query.getDocId();
            final JSONObject variables = query.getVariables();
            final int block = Systrace.beginAsync("prefetch docId=" + docId);
            final String qplEventName = String.format("content_prefetch_%d", Integer.valueOf(queryIdx));
            queryIdx++;
            Qpl.markerPointStart(Qpl.QPL_MARKER_INIT, qplEventName);
            final long startTime = System.nanoTime();
            Log.d(TAG, String.format("Prefetching query. docId: %s, variables: %s", Long.valueOf(docId), variables));
            GraphQLUtil.query(this.mClient, docId, variables, this.mAccessToken, new GraphQLUtil.Callback() {
                /* class com.oculus.modules.GraphQLPrefetch.AnonymousClass1 */

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onFailure(String exception) {
                    Log.e(GraphQLPrefetch.TAG, "Failed prefetch " + exception);
                    GraphQLPrefetch.this.mCompletedPrefetchResponses.add(GraphQLPrefetch.this.getJSONString(docId, variables, null));
                    Systrace.end(block);
                }

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onSuccess(JSONObject response) {
                    long endTime = System.nanoTime();
                    Log.i(GraphQLPrefetch.TAG, String.format("Completed request for %s in %dms", Long.valueOf(docId), Long.valueOf((endTime - startTime) / 1000000)));
                    String data = GraphQLPrefetch.this.getJSONString(docId, variables, response);
                    if (data == null) {
                        data = GraphQLPrefetch.this.getJSONString(docId, variables, null);
                    }
                    GraphQLPrefetch.this.mCompletedPrefetchResponses.add(data);
                    GraphQLPrefetch.this.tryPushPrefetchResponses();
                    Systrace.end(block);
                    Qpl.markerPointEnd(Qpl.QPL_MARKER_INIT, qplEventName);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String getJSONString(long docId, JSONObject variables, JSONObject response) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("docId", String.valueOf(docId));
            obj.put("variables", variables);
            if (response != null) {
                obj.put("response", response);
            }
            return obj.toString();
        } catch (JSONException e) {
            Log.e(TAG, "Failed to add strings to JSONObject!");
            return null;
        }
    }
}
