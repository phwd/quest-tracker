package com.android.volley.toolbox;

import androidx.annotation.Nullable;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;

public class JsonArrayRequest extends JsonRequest<JSONArray> {
    public JsonArrayRequest(String url, Response.Listener<JSONArray> listener, @Nullable Response.ErrorListener errorListener) {
        super(0, url, null, listener, errorListener);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonArrayRequest(int method, String url, @Nullable JSONArray jsonRequest, Response.Listener<JSONArray> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, jsonRequest == null ? null : jsonRequest.toString(), listener, errorListener);
    }

    /* access modifiers changed from: protected */
    @Override // com.android.volley.Request, com.android.volley.toolbox.JsonRequest
    public Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {
            return Response.success(new JSONArray(new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"))), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
}
