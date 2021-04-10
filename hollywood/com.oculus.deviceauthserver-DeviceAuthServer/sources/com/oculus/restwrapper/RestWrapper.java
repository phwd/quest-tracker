package com.oculus.restwrapper;

import android.content.Context;
import android.util.Log;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.oculus.restwrapper.ErrorResponse;
import java.util.concurrent.TimeUnit;
import oculus.internal.gson.Base64EncodedData;
import org.json.JSONException;
import org.json.JSONObject;

public class RestWrapper {
    private static final int RETRIES = 5;
    private static final String TAG = "RestWrapper";
    private static final int TIMEOUT_IN_MS = ((int) TimeUnit.SECONDS.toMillis(30));
    private Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Base64EncodedData.class, new Base64EncodedData.GsonTypeAdapter()).create();
    private RequestQueue requestQueue;
    private String serverHost;
    private int serverPort;

    public interface Callback<T> {
        void call(T t);
    }

    public RestWrapper(Context context, String serverHost2, int serverPort2) {
        this.serverHost = serverHost2;
        this.serverPort = serverPort2;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public <V, D> void send(String path, Class<V> responseClass, D postData, Callback<V> callback, ErrorCallback<Void> errorCallback) throws JSONException {
        send(path, responseClass, null, postData, callback, errorCallback);
    }

    public <V, D, E> void send(String path, Class<V> responseClass, Class<E> errorDataClass, D postData, Callback<V> callback, ErrorCallback<E> errorCallback) throws JSONException {
        String url = getUrl(path);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, new JSONObject(this.gson.toJson((Object) postData)), genResponseListener(url, responseClass, callback), genErrorListener(url, errorDataClass, errorCallback));
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_IN_MS, 5, 1.0f));
        this.requestQueue.add(jsonObjectRequest);
    }

    private <V> Response.Listener<JSONObject> genResponseListener(String url, final Class<V> responseClass, final Callback<V> callback) {
        return new Response.Listener<JSONObject>() {
            /* class com.oculus.restwrapper.RestWrapper.AnonymousClass1 */

            public void onResponse(JSONObject response) {
                if (response == null) {
                    callback.call(null);
                    return;
                }
                callback.call(RestWrapper.this.gson.fromJson(response.toString(), responseClass));
            }
        };
    }

    private <E> Response.ErrorListener genErrorListener(final String url, final Class<E> errorDataClass, final ErrorCallback<E> errorCallback) {
        return new Response.ErrorListener() {
            /* class com.oculus.restwrapper.RestWrapper.AnonymousClass2 */

            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError error) {
                int i;
                String errorMessage;
                ErrorResponse.Error responseError;
                String title;
                String message;
                int errorSubcode = 0;
                Object obj = null;
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse == null || networkResponse.data == null) {
                    i = 1;
                    errorMessage = String.format("(%s) %s", error.getClass().getSimpleName(), url);
                } else {
                    errorMessage = String.format("(Error %d) %s", Integer.valueOf(networkResponse.statusCode), url);
                    String data = new String(networkResponse.data);
                    try {
                        ErrorResponse errorResponse = (ErrorResponse) RestWrapper.this.gson.fromJson(data, ErrorResponse.class);
                        if (!(errorResponse == null || (responseError = errorResponse.error) == null)) {
                            errorSubcode = responseError.error_subcode;
                            if (responseError.error_user_title != null) {
                                title = responseError.error_user_title;
                            } else {
                                title = "Unknown Error";
                            }
                            if (responseError.error_user_message != null) {
                                message = responseError.error_user_message;
                            } else {
                                message = "(no additional details)";
                            }
                            errorMessage = errorMessage + String.format("\n(Subcode %d) %s: %s", Integer.valueOf(errorSubcode), title, message);
                            if (!(errorDataClass == null || responseError.error_data == null)) {
                                obj = RestWrapper.this.gson.fromJson(responseError.error_data, errorDataClass);
                            }
                        }
                    } catch (JsonSyntaxException e) {
                        errorMessage = errorMessage + String.format("\n%s", data);
                    }
                    i = 1;
                }
                if (error.getMessage() != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(errorMessage);
                    Object[] objArr = new Object[i];
                    objArr[0] = error.getMessage();
                    sb.append(String.format("\n%s", objArr));
                    errorMessage = sb.toString();
                }
                Log.e(RestWrapper.TAG, errorMessage);
                errorCallback.call(errorMessage, error, errorSubcode, obj);
            }
        };
    }

    private String getUrl(String path) {
        return String.format("https://%s:%d%s", this.serverHost, Integer.valueOf(this.serverPort), path);
    }
}
