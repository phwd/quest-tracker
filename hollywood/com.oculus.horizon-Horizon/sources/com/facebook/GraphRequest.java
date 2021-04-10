package com.facebook;

import X.AnonymousClass006;
import X.AnonymousClass1eW;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.facebook.GraphRequestBatch;
import com.facebook.acra.util.HttpRequest;
import com.facebook.acra.util.HttpRequestMultipart;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.InternalSettings;
import com.facebook.internal.Logger;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.ShareOpenGraphValueContainer;
import com.facebook.share.model.SharePhoto;
import com.oculus.auth.e2etestharness.Constants;
import com.oculus.http.common.graphql.GraphQLQueryConstants;
import com.squareup.okhttp.internal.DiskLruCache;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphRequest {
    public static final String ACCEPT_LANGUAGE_HEADER = "Accept-Language";
    public static final String ACCESS_TOKEN_PARAM = "access_token";
    public static final String ATTACHED_FILES_PARAM = "attached_files";
    public static final String ATTACHMENT_FILENAME_PREFIX = "file";
    public static final String BATCH_APP_ID_PARAM = "batch_app_id";
    public static final String BATCH_BODY_PARAM = "body";
    public static final String BATCH_ENTRY_DEPENDS_ON_PARAM = "depends_on";
    public static final String BATCH_ENTRY_NAME_PARAM = "name";
    public static final String BATCH_ENTRY_OMIT_RESPONSE_ON_SUCCESS_PARAM = "omit_response_on_success";
    public static final String BATCH_METHOD_PARAM = "method";
    public static final String BATCH_PARAM = "batch";
    public static final String BATCH_RELATIVE_URL_PARAM = "relative_url";
    public static final String CAPTION_PARAM = "caption";
    public static final String CONTENT_ENCODING_HEADER = "Content-Encoding";
    public static final String CONTENT_TYPE_HEADER = "Content-Type";
    public static final String DEBUG_KEY = "__debug__";
    public static final String DEBUG_MESSAGES_KEY = "messages";
    public static final String DEBUG_MESSAGE_KEY = "message";
    public static final String DEBUG_MESSAGE_LINK_KEY = "link";
    public static final String DEBUG_MESSAGE_TYPE_KEY = "type";
    public static final String DEBUG_PARAM = "debug";
    public static final String DEBUG_SEVERITY_INFO = "info";
    public static final String DEBUG_SEVERITY_WARNING = "warning";
    public static final String FIELDS_PARAM = "fields";
    public static final String FORMAT_JSON = "json";
    public static final String FORMAT_PARAM = "format";
    public static final String GRAPH_PATH_FORMAT = "%s/%s";
    public static final String ISO_8601_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final int MAXIMUM_BATCH_SIZE = 50;
    public static final String ME = "me";
    public static final String MIME_BOUNDARY = "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f";
    public static final String MY_FRIENDS = "me/friends";
    public static final String MY_PHOTOS = "me/photos";
    public static final String PICTURE_PARAM = "picture";
    public static final String SDK_ANDROID = "android";
    public static final String SDK_PARAM = "sdk";
    public static final String SEARCH = "search";
    public static final String TAG = "GraphRequest";
    public static final String USER_AGENT_BASE = "FBAndroidSDK";
    public static final String USER_AGENT_HEADER = "User-Agent";
    public static final String VIDEOS_SUFFIX = "/videos";
    public static String defaultBatchApplicationId;
    public static volatile String userAgent;
    public static Pattern versionPattern = Pattern.compile("^/?v\\d+\\.\\d+/(.*)");
    public AccessToken accessToken;
    public String batchEntryDependsOn;
    public String batchEntryName;
    public boolean batchEntryOmitResultOnSuccess;
    public Callback callback;
    public JSONObject graphObject;
    public String graphPath;
    public HttpMethod httpMethod;
    public String overriddenURL;
    public Bundle parameters;
    public boolean skipClientToken;
    public Object tag;
    public String version;

    public interface Callback {
        void onCompleted(GraphResponse graphResponse);
    }

    public interface GraphJSONArrayCallback {
        void onCompleted(JSONArray jSONArray, GraphResponse graphResponse);
    }

    public interface GraphJSONObjectCallback {
        void onCompleted(JSONObject jSONObject, GraphResponse graphResponse);
    }

    public interface KeyValueSerializer {
        void writeString(String str, String str2) throws IOException;
    }

    public interface OnProgressCallback extends Callback {
        void onProgress(long j, long j2);
    }

    public static class ParcelableResourceWithMimeType<RESOURCE extends Parcelable> implements Parcelable {
        public static final Parcelable.Creator<ParcelableResourceWithMimeType> CREATOR = new Parcelable.Creator<ParcelableResourceWithMimeType>() {
            /* class com.facebook.GraphRequest.ParcelableResourceWithMimeType.AnonymousClass1 */

            @Override // android.os.Parcelable.Creator
            public ParcelableResourceWithMimeType createFromParcel(Parcel parcel) {
                return new ParcelableResourceWithMimeType(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public ParcelableResourceWithMimeType[] newArray(int i) {
                return new ParcelableResourceWithMimeType[i];
            }
        };
        public final String mimeType;
        public final RESOURCE resource;

        public int describeContents() {
            return 1;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mimeType);
            parcel.writeParcelable(this.resource, i);
        }

        public String getMimeType() {
            return this.mimeType;
        }

        public RESOURCE getResource() {
            return this.resource;
        }

        public ParcelableResourceWithMimeType(Parcel parcel) {
            this.mimeType = parcel.readString();
            Validate.sdkInitialized();
            this.resource = (RESOURCE) parcel.readParcelable(FacebookSdk.applicationContext.getClassLoader());
        }

        public ParcelableResourceWithMimeType(RESOURCE resource2, String str) {
            this.mimeType = str;
            this.resource = resource2;
        }
    }

    public static class Serializer implements KeyValueSerializer {
        public boolean firstWrite = true;
        public final Logger logger;
        public final OutputStream outputStream;
        public boolean useUrlEncode = false;

        @Override // com.facebook.GraphRequest.KeyValueSerializer
        public void writeString(String str, String str2) throws IOException {
            writeContentDisposition(str, null, null);
            writeLine("%s", str2);
            writeRecordBoundary();
            Logger logger2 = this.logger;
            if (logger2 != null) {
                logger2.appendKeyValue(AnonymousClass006.A05("    ", str), str2);
            }
        }

        private RuntimeException getInvalidTypeError() {
            return new IllegalArgumentException("value is not a supported type.");
        }

        public void write(String str, Object... objArr) throws IOException {
            OutputStream outputStream2;
            String encode;
            if (!this.useUrlEncode) {
                if (this.firstWrite) {
                    this.outputStream.write("--".getBytes());
                    this.outputStream.write(GraphRequest.MIME_BOUNDARY.getBytes());
                    this.outputStream.write(HttpRequestMultipart.LINE_FEED.getBytes());
                    this.firstWrite = false;
                }
                outputStream2 = this.outputStream;
                encode = String.format(str, objArr);
            } else {
                outputStream2 = this.outputStream;
                encode = URLEncoder.encode(String.format(Locale.US, str, objArr), "UTF-8");
            }
            outputStream2.write(encode.getBytes());
        }

        public void writeBitmap(String str, Bitmap bitmap) throws IOException {
            writeContentDisposition(str, str, "image/png");
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, this.outputStream);
            writeLine("", new Object[0]);
            writeRecordBoundary();
            Logger logger2 = this.logger;
            if (logger2 != null) {
                logger2.appendKeyValue(AnonymousClass006.A05("    ", str), "<Image>");
            }
        }

        public void writeBytes(String str, byte[] bArr) throws IOException {
            writeContentDisposition(str, str, "content/unknown");
            this.outputStream.write(bArr);
            writeLine("", new Object[0]);
            writeRecordBoundary();
            Logger logger2 = this.logger;
            if (logger2 != null) {
                logger2.appendKeyValue(AnonymousClass006.A05("    ", str), String.format(Locale.ROOT, "<Data: %d>", Integer.valueOf(bArr.length)));
            }
        }

        public void writeContentDisposition(String str, String str2, String str3) throws IOException {
            if (!this.useUrlEncode) {
                write("Content-Disposition: form-data; name=\"%s\"", str);
                if (str2 != null) {
                    write("; filename=\"%s\"", str2);
                }
                writeLine("", new Object[0]);
                if (str3 != null) {
                    writeLine("%s: %s", "Content-Type", str3);
                }
                writeLine("", new Object[0]);
                return;
            }
            this.outputStream.write(String.format("%s=", str).getBytes());
        }

        public void writeContentUri(String str, Uri uri, String str2) throws IOException {
            int copyAndCloseInputStream;
            if (str2 == null) {
                str2 = "content/unknown";
            }
            writeContentDisposition(str, str, str2);
            Validate.sdkInitialized();
            InputStream openInputStream = FacebookSdk.applicationContext.getContentResolver().openInputStream(uri);
            OutputStream outputStream2 = this.outputStream;
            if (outputStream2 instanceof ProgressNoopOutputStream) {
                ((ProgressNoopOutputStream) this.outputStream).addProgress(Utility.getContentSize(uri));
                copyAndCloseInputStream = 0;
            } else {
                copyAndCloseInputStream = Utility.copyAndCloseInputStream(openInputStream, outputStream2) + 0;
            }
            writeLine("", new Object[0]);
            writeRecordBoundary();
            Logger logger2 = this.logger;
            if (logger2 != null) {
                logger2.appendKeyValue(AnonymousClass006.A05("    ", str), String.format(Locale.ROOT, "<Data: %d>", Integer.valueOf(copyAndCloseInputStream)));
            }
        }

        public void writeFile(String str, ParcelFileDescriptor parcelFileDescriptor, String str2) throws IOException {
            int copyAndCloseInputStream;
            if (str2 == null) {
                str2 = "content/unknown";
            }
            writeContentDisposition(str, str, str2);
            OutputStream outputStream2 = this.outputStream;
            if (outputStream2 instanceof ProgressNoopOutputStream) {
                ((ProgressNoopOutputStream) outputStream2).addProgress(parcelFileDescriptor.getStatSize());
                copyAndCloseInputStream = 0;
            } else {
                copyAndCloseInputStream = Utility.copyAndCloseInputStream(new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor), this.outputStream) + 0;
            }
            writeLine("", new Object[0]);
            writeRecordBoundary();
            Logger logger2 = this.logger;
            if (logger2 != null) {
                logger2.appendKeyValue(AnonymousClass006.A05("    ", str), String.format(Locale.ROOT, "<Data: %d>", Integer.valueOf(copyAndCloseInputStream)));
            }
        }

        public void writeObject(String str, Object obj, GraphRequest graphRequest) throws IOException {
            OutputStream outputStream2 = this.outputStream;
            if (outputStream2 instanceof RequestOutputStream) {
                ((RequestOutputStream) outputStream2).setCurrentRequest(graphRequest);
            }
            if (GraphRequest.isSupportedParameterType(obj)) {
                writeString(str, GraphRequest.parameterToString(obj));
            } else if (obj instanceof Bitmap) {
                writeBitmap(str, (Bitmap) obj);
            } else if (obj instanceof byte[]) {
                writeBytes(str, (byte[]) obj);
            } else if (obj instanceof Uri) {
                writeContentUri(str, (Uri) obj, null);
            } else if (obj instanceof ParcelFileDescriptor) {
                writeFile(str, (ParcelFileDescriptor) obj, null);
            } else {
                if (obj instanceof ParcelableResourceWithMimeType) {
                    ParcelableResourceWithMimeType parcelableResourceWithMimeType = (ParcelableResourceWithMimeType) obj;
                    RESOURCE resource = parcelableResourceWithMimeType.resource;
                    String str2 = parcelableResourceWithMimeType.mimeType;
                    if (resource instanceof ParcelFileDescriptor) {
                        writeFile(str, (ParcelFileDescriptor) resource, str2);
                        return;
                    } else if (resource instanceof Uri) {
                        writeContentUri(str, (Uri) resource, str2);
                        return;
                    }
                }
                throw getInvalidTypeError();
            }
        }

        public void writeRecordBoundary() throws IOException {
            if (!this.useUrlEncode) {
                writeLine("--%s", GraphRequest.MIME_BOUNDARY);
                return;
            }
            this.outputStream.write("&".getBytes());
        }

        public void writeRequestsAsJson(String str, JSONArray jSONArray, Collection<GraphRequest> collection) throws IOException, JSONException {
            String str2;
            OutputStream outputStream2 = this.outputStream;
            if (!(outputStream2 instanceof RequestOutputStream)) {
                writeString(str, jSONArray.toString());
                return;
            }
            RequestOutputStream requestOutputStream = (RequestOutputStream) outputStream2;
            writeContentDisposition(str, null, null);
            write("[", new Object[0]);
            int i = 0;
            for (GraphRequest graphRequest : collection) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                requestOutputStream.setCurrentRequest(graphRequest);
                Object[] objArr = {jSONObject.toString()};
                if (i > 0) {
                    str2 = ",%s";
                } else {
                    str2 = "%s";
                }
                write(str2, objArr);
                i++;
            }
            write("]", new Object[0]);
            Logger logger2 = this.logger;
            if (logger2 != null) {
                logger2.appendKeyValue(AnonymousClass006.A05("    ", str), jSONArray.toString());
            }
        }

        public Serializer(OutputStream outputStream2, Logger logger2, boolean z) {
            this.outputStream = outputStream2;
            this.logger = logger2;
            this.useUrlEncode = z;
        }

        public void writeLine(String str, Object... objArr) throws IOException {
            write(str, objArr);
            if (!this.useUrlEncode) {
                write(HttpRequestMultipart.LINE_FEED, new Object[0]);
            }
        }
    }

    public static String getDefaultPhotoPathIfNull(String str) {
        return str == null ? MY_PHOTOS : str;
    }

    public static String getMimeContentType() {
        return String.format("multipart/form-data; boundary=%s", MIME_BOUNDARY);
    }

    public static GraphRequest newGraphPathRequest(AccessToken accessToken2, String str, Callback callback2) {
        return new GraphRequest(accessToken2, str, null, null, callback2);
    }

    public final GraphRequestAsyncTask executeAsync() {
        return executeBatchAsync(this);
    }

    public static class Attachment {
        public final GraphRequest request;
        public final Object value;

        public Attachment(GraphRequest graphRequest, Object obj) {
            this.request = graphRequest;
            this.value = obj;
        }

        public GraphRequest getRequest() {
            return this.request;
        }

        public Object getValue() {
            return this.value;
        }
    }

    public static Object A00(@Nullable Object obj, AnonymousClass7 r6) throws JSONException {
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long)) {
            return obj;
        }
        if (obj instanceof SharePhoto) {
            return r6.toJSONObject((SharePhoto) obj);
        }
        if (obj instanceof ShareOpenGraphObject) {
            JSONObject jSONObject = new JSONObject();
            Bundle bundle = ((ShareOpenGraphValueContainer) obj).A00;
            for (String str : bundle.keySet()) {
                jSONObject.put(str, A00(bundle.get(str), r6));
            }
            return jSONObject;
        } else if (obj instanceof List) {
            JSONArray jSONArray = new JSONArray();
            for (Object obj2 : (List) obj) {
                jSONArray.put(A00(obj2, r6));
            }
            return jSONArray;
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A05("Invalid object found for JSON serialization: ", obj.toString()));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addCommonParameters() {
        /*
        // Method dump skipped, instructions count: 116
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.GraphRequest.addCommonParameters():void");
    }

    private String appendParametersToBaseUrl(String str) {
        Uri.Builder encodedPath = new Uri.Builder().encodedPath(str);
        for (String str2 : this.parameters.keySet()) {
            Object obj = this.parameters.get(str2);
            if (obj == null) {
                obj = "";
            }
            if (isSupportedParameterType(obj)) {
                encodedPath.appendQueryParameter(str2, parameterToString(obj));
            } else if (this.httpMethod == HttpMethod.GET) {
                throw new IllegalArgumentException(String.format(Locale.US, "Unsupported parameter type for GET request: %s", obj.getClass().getSimpleName()));
            }
        }
        return encodedPath.toString();
    }

    public static GraphRequest createOpenGraphObject(ShareOpenGraphObject shareOpenGraphObject) throws FacebookException {
        Bundle bundle = shareOpenGraphObject.A00;
        String string = bundle.getString("type");
        if (string == null && (string = bundle.getString("og:type")) == null) {
            throw new FacebookException("Open graph object type cannot be null");
        }
        try {
            Object A00 = A00(shareOpenGraphObject, new Object() {
                /* class com.facebook.GraphRequest.AnonymousClass7 */

                public JSONObject toJSONObject(SharePhoto sharePhoto) {
                    Uri uri = sharePhoto.A00;
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("url", uri.toString());
                        return jSONObject;
                    } catch (Exception e) {
                        throw new FacebookException("Unable to attach images", e);
                    }
                }
            });
            Bundle bundle2 = new Bundle();
            bundle2.putString("object", A00.toString());
            return new GraphRequest(AccessTokenManager.getInstance().currentAccessToken, String.format(Locale.ROOT, GRAPH_PATH_FORMAT, "me", AnonymousClass006.A05("objects/", string)), bundle2, HttpMethod.POST);
        } catch (JSONException e) {
            throw new FacebookException(e.getMessage());
        }
    }

    public static String getBatchAppId(GraphRequestBatch graphRequestBatch) {
        String str = graphRequestBatch.batchApplicationId;
        if (Utility.isNullOrEmpty(str)) {
            Iterator it = graphRequestBatch.iterator();
            while (it.hasNext()) {
                AccessToken accessToken2 = ((GraphRequest) it.next()).accessToken;
                if (accessToken2 == null || (str = accessToken2.applicationId) == null) {
                }
            }
            String str2 = defaultBatchApplicationId;
            if (!Utility.isNullOrEmpty(str2)) {
                return str2;
            }
            Validate.sdkInitialized();
            return FacebookSdk.applicationId;
        }
        return str;
    }

    private String getGraphPathWithVersion() {
        if (versionPattern.matcher(this.graphPath).matches()) {
            return this.graphPath;
        }
        return String.format(GRAPH_PATH_FORMAT, this.version, this.graphPath);
    }

    public static String getUserAgent() {
        if (userAgent == null) {
            userAgent = String.format("%s.%s", USER_AGENT_BASE, FacebookSdkVersion.BUILD);
            String str = InternalSettings.mCustomUserAgent;
            if (!Utility.isNullOrEmpty(str)) {
                userAgent = String.format(Locale.ROOT, GRAPH_PATH_FORMAT, userAgent, str);
            }
        }
        return userAgent;
    }

    public static boolean hasOnProgressCallbacks(GraphRequestBatch graphRequestBatch) {
        for (GraphRequestBatch.Callback callback2 : graphRequestBatch.callbacks) {
            if (callback2 instanceof GraphRequestBatch.OnProgressCallback) {
                return true;
            }
        }
        Iterator it = graphRequestBatch.iterator();
        while (it.hasNext()) {
            if (((GraphRequest) it.next()).callback instanceof OnProgressCallback) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMeRequest(String str) {
        Matcher matcher = versionPattern.matcher(str);
        if (matcher.matches()) {
            str = matcher.group(1);
        }
        if (str.startsWith("me/") || str.startsWith("/me/")) {
            return true;
        }
        return false;
    }

    public static boolean isSupportedAttachmentType(Object obj) {
        if ((obj instanceof Bitmap) || (obj instanceof byte[]) || (obj instanceof Uri) || (obj instanceof ParcelFileDescriptor) || (obj instanceof ParcelableResourceWithMimeType)) {
            return true;
        }
        return false;
    }

    public static boolean isSupportedParameterType(Object obj) {
        if ((obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Number) || (obj instanceof Date)) {
            return true;
        }
        return false;
    }

    public static GraphRequest newDeleteObjectRequest(AccessToken accessToken2, String str, Callback callback2) {
        return new GraphRequest(accessToken2, str, null, HttpMethod.DELETE, callback2);
    }

    public static GraphRequest newMeRequest(AccessToken accessToken2, final GraphJSONObjectCallback graphJSONObjectCallback) {
        return new GraphRequest(accessToken2, "me", null, null, new Callback() {
            /* class com.facebook.GraphRequest.AnonymousClass1 */

            @Override // com.facebook.GraphRequest.Callback
            public void onCompleted(GraphResponse graphResponse) {
                GraphJSONObjectCallback graphJSONObjectCallback = graphJSONObjectCallback;
                if (graphJSONObjectCallback != null) {
                    graphJSONObjectCallback.onCompleted(graphResponse.graphObject, graphResponse);
                }
            }
        });
    }

    public static GraphRequest newMyFriendsRequest(AccessToken accessToken2, final GraphJSONArrayCallback graphJSONArrayCallback) {
        return new GraphRequest(accessToken2, MY_FRIENDS, null, null, new Callback() {
            /* class com.facebook.GraphRequest.AnonymousClass2 */

            @Override // com.facebook.GraphRequest.Callback
            public void onCompleted(GraphResponse graphResponse) {
                JSONArray jSONArray;
                if (graphJSONArrayCallback != null) {
                    JSONObject jSONObject = graphResponse.graphObject;
                    if (jSONObject != null) {
                        jSONArray = jSONObject.optJSONArray("data");
                    } else {
                        jSONArray = null;
                    }
                    graphJSONArrayCallback.onCompleted(jSONArray, graphResponse);
                }
            }
        });
    }

    public static GraphRequest newPlacesSearchRequest(AccessToken accessToken2, Location location, int i, int i2, String str, final GraphJSONArrayCallback graphJSONArrayCallback) {
        if (location != null || !Utility.isNullOrEmpty(str)) {
            Bundle bundle = new Bundle(5);
            bundle.putString("type", "place");
            bundle.putInt("limit", i2);
            if (location != null) {
                bundle.putString("center", String.format(Locale.US, "%f,%f", Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude())));
                bundle.putInt("distance", i);
            }
            if (!Utility.isNullOrEmpty(str)) {
                bundle.putString(GraphQLQueryConstants.GRAPHQL_QUERY_KEY, str);
            }
            return new GraphRequest(accessToken2, SEARCH, bundle, HttpMethod.GET, new Callback() {
                /* class com.facebook.GraphRequest.AnonymousClass3 */

                @Override // com.facebook.GraphRequest.Callback
                public void onCompleted(GraphResponse graphResponse) {
                    JSONArray jSONArray;
                    if (graphJSONArrayCallback != null) {
                        JSONObject jSONObject = graphResponse.graphObject;
                        if (jSONObject != null) {
                            jSONArray = jSONObject.optJSONArray("data");
                        } else {
                            jSONArray = null;
                        }
                        graphJSONArrayCallback.onCompleted(jSONArray, graphResponse);
                    }
                }
            });
        }
        throw new FacebookException("Either location or searchText must be specified.");
    }

    public static GraphRequest newPostRequest(AccessToken accessToken2, String str, JSONObject jSONObject, Callback callback2) {
        GraphRequest graphRequest = new GraphRequest(accessToken2, str, null, HttpMethod.POST, callback2);
        graphRequest.graphObject = jSONObject;
        return graphRequest;
    }

    public static String parameterToString(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if ((obj instanceof Boolean) || (obj instanceof Number)) {
            return obj.toString();
        }
        if (obj instanceof Date) {
            return new SimpleDateFormat(ISO_8601_FORMAT_STRING, Locale.US).format(obj);
        }
        throw new IllegalArgumentException("Unsupported parameter type.");
    }

    public static void processRequest(GraphRequestBatch graphRequestBatch, Logger logger, int i, URL url, OutputStream outputStream, boolean z) throws IOException, JSONException {
        Serializer serializer = new Serializer(outputStream, logger, z);
        if (i == 1) {
            GraphRequest graphRequest = graphRequestBatch.get(0);
            HashMap hashMap = new HashMap();
            for (String str : graphRequest.parameters.keySet()) {
                Object obj = graphRequest.parameters.get(str);
                if (isSupportedAttachmentType(obj)) {
                    hashMap.put(str, new Attachment(graphRequest, obj));
                }
            }
            if (logger != null) {
                logger.append("  Parameters:\n");
            }
            serializeParameters(graphRequest.parameters, serializer, graphRequest);
            if (logger != null) {
                logger.append("  Attachments:\n");
            }
            serializeAttachments(hashMap, serializer);
            JSONObject jSONObject = graphRequest.graphObject;
            if (jSONObject != null) {
                processGraphObject(jSONObject, url.getPath(), serializer);
                return;
            }
            return;
        }
        String batchAppId = getBatchAppId(graphRequestBatch);
        if (!Utility.isNullOrEmpty(batchAppId)) {
            serializer.writeString(BATCH_APP_ID_PARAM, batchAppId);
            HashMap hashMap2 = new HashMap();
            serializeRequestsAsJSON(serializer, graphRequestBatch, hashMap2);
            if (logger != null) {
                logger.append("  Attachments:\n");
            }
            serializeAttachments(hashMap2, serializer);
            return;
        }
        throw new FacebookException("App ID was not specified at the request or Settings.");
    }

    public static void serializeRequestsAsJSON(Serializer serializer, Collection<GraphRequest> collection, Map<String, Attachment> map) throws JSONException, IOException {
        JSONArray jSONArray = new JSONArray();
        for (GraphRequest graphRequest : collection) {
            graphRequest.serializeToBatch(jSONArray, map);
        }
        serializer.writeRequestsAsJson(BATCH_PARAM, jSONArray, collection);
    }

    private void serializeToBatch(JSONArray jSONArray, Map<String, Attachment> map) throws JSONException, IOException {
        JSONObject jSONObject = new JSONObject();
        String str = this.batchEntryName;
        if (str != null) {
            jSONObject.put("name", str);
            jSONObject.put(BATCH_ENTRY_OMIT_RESPONSE_ON_SUCCESS_PARAM, this.batchEntryOmitResultOnSuccess);
        }
        String str2 = this.batchEntryDependsOn;
        if (str2 != null) {
            jSONObject.put(BATCH_ENTRY_DEPENDS_ON_PARAM, str2);
        }
        String urlForBatchedRequest = getUrlForBatchedRequest();
        jSONObject.put(BATCH_RELATIVE_URL_PARAM, urlForBatchedRequest);
        jSONObject.put("method", this.httpMethod);
        AccessToken accessToken2 = this.accessToken;
        if (accessToken2 != null) {
            Logger.registerAccessToken(accessToken2.token);
        }
        ArrayList arrayList = new ArrayList();
        for (String str3 : this.parameters.keySet()) {
            Object obj = this.parameters.get(str3);
            if (isSupportedAttachmentType(obj)) {
                String format = String.format(Locale.ROOT, "%s%d", ATTACHMENT_FILENAME_PREFIX, Integer.valueOf(map.size()));
                arrayList.add(format);
                map.put(format, new Attachment(this, obj));
            }
        }
        if (!arrayList.isEmpty()) {
            jSONObject.put(ATTACHED_FILES_PARAM, TextUtils.join(",", arrayList));
        }
        JSONObject jSONObject2 = this.graphObject;
        if (jSONObject2 != null) {
            final ArrayList arrayList2 = new ArrayList();
            processGraphObject(jSONObject2, urlForBatchedRequest, new KeyValueSerializer() {
                /* class com.facebook.GraphRequest.AnonymousClass6 */

                @Override // com.facebook.GraphRequest.KeyValueSerializer
                public void writeString(String str, String str2) throws IOException {
                    arrayList2.add(String.format(Locale.US, "%s=%s", str, URLEncoder.encode(str2, "UTF-8")));
                }
            });
            jSONObject.put("body", TextUtils.join("&", arrayList2));
        }
        jSONArray.put(jSONObject);
    }

    public static final void serializeToUrlConnection(GraphRequestBatch graphRequestBatch, HttpURLConnection httpURLConnection) throws IOException, JSONException {
        HttpMethod httpMethod2;
        OutputStream outputStream;
        Throwable th;
        Logger logger = new Logger(LoggingBehavior.REQUESTS, "Request");
        int size = graphRequestBatch.size();
        boolean isGzipCompressible = isGzipCompressible(graphRequestBatch);
        if (size == 1) {
            httpMethod2 = graphRequestBatch.get(0).httpMethod;
        } else {
            httpMethod2 = HttpMethod.POST;
        }
        httpURLConnection.setRequestMethod(httpMethod2.name());
        setConnectionContentType(httpURLConnection, isGzipCompressible);
        URL url = httpURLConnection.getURL();
        logger.append("Request:\n");
        logger.appendKeyValue("Id", graphRequestBatch.id);
        logger.appendKeyValue("URL", url);
        logger.appendKeyValue(Constants.EXTRA_KEY_METHOD, httpURLConnection.getRequestMethod());
        logger.appendKeyValue("User-Agent", httpURLConnection.getRequestProperty("User-Agent"));
        logger.appendKeyValue("Content-Type", httpURLConnection.getRequestProperty("Content-Type"));
        httpURLConnection.setConnectTimeout(graphRequestBatch.timeoutInMilliseconds);
        httpURLConnection.setReadTimeout(graphRequestBatch.timeoutInMilliseconds);
        if (httpMethod2 == HttpMethod.POST) {
            httpURLConnection.setDoOutput(true);
            OutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
            if (isGzipCompressible) {
                try {
                    outputStream = new GZIPOutputStream(bufferedOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    outputStream = bufferedOutputStream;
                }
            } else {
                outputStream = bufferedOutputStream;
            }
            try {
                if (hasOnProgressCallbacks(graphRequestBatch)) {
                    ProgressNoopOutputStream progressNoopOutputStream = new ProgressNoopOutputStream(graphRequestBatch.callbackHandler);
                    processRequest(graphRequestBatch, null, size, url, progressNoopOutputStream, isGzipCompressible);
                    outputStream = new ProgressOutputStream(outputStream, graphRequestBatch, progressNoopOutputStream.progressMap, (long) progressNoopOutputStream.batchMax);
                }
                processRequest(graphRequestBatch, logger, size, url, outputStream, isGzipCompressible);
                outputStream.close();
            } catch (Throwable th3) {
                th = th3;
                outputStream.close();
                throw th;
            }
        }
        logger.log();
    }

    public static void setConnectionContentType(HttpURLConnection httpURLConnection, boolean z) {
        String mimeContentType;
        String str = "Content-Type";
        if (z) {
            httpURLConnection.setRequestProperty(str, HttpRequest.POST_CONTENT_TYPE_FORM_URLENCODED);
            str = "Content-Encoding";
            mimeContentType = "gzip";
        } else {
            mimeContentType = getMimeContentType();
        }
        httpURLConnection.setRequestProperty(str, mimeContentType);
    }

    public static final boolean shouldWarnOnMissingFieldsParam(GraphRequest graphRequest) {
        String str = graphRequest.version;
        if (!Utility.isNullOrEmpty(str)) {
            if (str.startsWith("v")) {
                str = str.substring(1);
            }
            String[] split = str.split("\\.");
            if ((split.length < 2 || Integer.parseInt(split[0]) <= 2) && (Integer.parseInt(split[0]) < 2 || Integer.parseInt(split[1]) < 4)) {
                return false;
            }
        }
        return true;
    }

    public final String getUrlForBatchedRequest() {
        if (this.overriddenURL == null) {
            String graphPathWithVersion = getGraphPathWithVersion();
            addCommonParameters();
            return appendParametersToBaseUrl(graphPathWithVersion);
        }
        throw new FacebookException("Can't override URL for a batch request");
    }

    public final String getUrlForSingleRequest() {
        String graphUrlBase;
        String str;
        String str2 = this.overriddenURL;
        if (str2 != null) {
            return str2;
        }
        if (this.httpMethod != HttpMethod.POST || (str = this.graphPath) == null || !str.endsWith(VIDEOS_SUFFIX)) {
            graphUrlBase = ServerProtocol.getGraphUrlBase();
        } else {
            graphUrlBase = ServerProtocol.getGraphVideoUrlBase();
        }
        String format = String.format(GRAPH_PATH_FORMAT, graphUrlBase, getGraphPathWithVersion());
        addCommonParameters();
        return appendParametersToBaseUrl(format);
    }

    public final void setCallback(final Callback callback2) {
        if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_INFO) || FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            this.callback = new Callback() {
                /* class com.facebook.GraphRequest.AnonymousClass4 */

                @Override // com.facebook.GraphRequest.Callback
                public void onCompleted(GraphResponse graphResponse) {
                    JSONObject optJSONObject;
                    JSONArray optJSONArray;
                    JSONObject jSONObject = graphResponse.graphObject;
                    if (!(jSONObject == null || (optJSONObject = jSONObject.optJSONObject(GraphRequest.DEBUG_KEY)) == null || (optJSONArray = optJSONObject.optJSONArray("messages")) == null)) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                            if (optJSONObject2 != null) {
                                String optString = optJSONObject2.optString("message");
                                String optString2 = optJSONObject2.optString("type");
                                String optString3 = optJSONObject2.optString("link");
                                if (!(optString == null || optString2 == null)) {
                                    LoggingBehavior loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_INFO;
                                    if (optString2.equals(GraphRequest.DEBUG_SEVERITY_WARNING)) {
                                        loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_WARNING;
                                    }
                                    if (!Utility.isNullOrEmpty(optString3)) {
                                        optString = AnonymousClass006.A07(optString, " Link: ", optString3);
                                    }
                                    Logger.log(loggingBehavior, GraphRequest.TAG, optString);
                                }
                            }
                        }
                    }
                    Callback callback = callback2;
                    if (callback != null) {
                        callback.onCompleted(graphResponse);
                    }
                }
            };
        } else {
            this.callback = callback2;
        }
    }

    public final void setHttpMethod(HttpMethod httpMethod2) {
        if (this.overriddenURL == null || httpMethod2 == HttpMethod.GET) {
            if (httpMethod2 == null) {
                httpMethod2 = HttpMethod.GET;
            }
            this.httpMethod = httpMethod2;
            return;
        }
        throw new FacebookException("Can't change HTTP method on request with overridden URL.");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{Request: ");
        sb.append(" accessToken: ");
        Object obj = this.accessToken;
        if (obj == null) {
            obj = "null";
        }
        sb.append(obj);
        sb.append(", graphPath: ");
        sb.append(this.graphPath);
        sb.append(", graphObject: ");
        sb.append(this.graphObject);
        sb.append(", httpMethod: ");
        sb.append(this.httpMethod);
        sb.append(", parameters: ");
        sb.append(this.parameters);
        sb.append("}");
        return sb.toString();
    }

    public static HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("User-Agent", getUserAgent());
        httpURLConnection.setRequestProperty(ACCEPT_LANGUAGE_HEADER, Locale.getDefault().toString());
        httpURLConnection.setChunkedStreamingMode(0);
        return httpURLConnection;
    }

    public static final String getDefaultBatchApplicationId() {
        return defaultBatchApplicationId;
    }

    public static boolean isGzipCompressible(GraphRequestBatch graphRequestBatch) {
        Iterator it = graphRequestBatch.iterator();
        while (it.hasNext()) {
            GraphRequest graphRequest = (GraphRequest) it.next();
            Iterator<String> it2 = graphRequest.parameters.keySet().iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (isSupportedAttachmentType(graphRequest.parameters.get(it2.next()))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void processGraphObject(org.json.JSONObject r6, java.lang.String r7, com.facebook.GraphRequest.KeyValueSerializer r8) throws java.io.IOException {
        /*
            boolean r0 = isMeRequest(r7)
            if (r0 == 0) goto L_0x003f
            java.lang.String r0 = ":"
            int r2 = r7.indexOf(r0)
            java.lang.String r0 = "?"
            int r1 = r7.indexOf(r0)
            r0 = 3
            if (r2 <= r0) goto L_0x003f
            r0 = -1
            if (r1 == r0) goto L_0x001a
            if (r2 >= r1) goto L_0x003f
        L_0x001a:
            r5 = 1
        L_0x001b:
            java.util.Iterator r4 = r6.keys()
        L_0x001f:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0041
            java.lang.Object r3 = r4.next()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r2 = r6.opt(r3)
            if (r5 == 0) goto L_0x003a
            java.lang.String r0 = "image"
            boolean r1 = r3.equalsIgnoreCase(r0)
            r0 = 1
            if (r1 != 0) goto L_0x003b
        L_0x003a:
            r0 = 0
        L_0x003b:
            processGraphObjectProperty(r3, r2, r8, r0)
            goto L_0x001f
        L_0x003f:
            r5 = 0
            goto L_0x001b
        L_0x0041:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.GraphRequest.processGraphObject(org.json.JSONObject, java.lang.String, com.facebook.GraphRequest$KeyValueSerializer):void");
    }

    public static void processGraphObjectProperty(String str, Object obj, KeyValueSerializer keyValueSerializer, boolean z) throws IOException {
        String obj2;
        String optString;
        Class<?> cls = obj.getClass();
        if (JSONObject.class.isAssignableFrom(cls)) {
            JSONObject jSONObject = (JSONObject) obj;
            if (z) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    processGraphObjectProperty(String.format("%s[%s]", str, next), jSONObject.opt(next), keyValueSerializer, z);
                }
                return;
            }
            String str2 = "id";
            if (!jSONObject.has(str2)) {
                str2 = "url";
                if (!jSONObject.has(str2)) {
                    if (jSONObject.has(NativeProtocol.OPEN_GRAPH_CREATE_OBJECT_KEY)) {
                        optString = jSONObject.toString();
                        processGraphObjectProperty(str, optString, keyValueSerializer, z);
                    }
                    return;
                }
            }
            optString = jSONObject.optString(str2);
            processGraphObjectProperty(str, optString, keyValueSerializer, z);
        } else if (JSONArray.class.isAssignableFrom(cls)) {
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                processGraphObjectProperty(String.format(Locale.ROOT, "%s[%d]", str, Integer.valueOf(i)), jSONArray.opt(i), keyValueSerializer, z);
            }
        } else {
            if (String.class.isAssignableFrom(cls) || Number.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls)) {
                obj2 = obj.toString();
            } else if (Date.class.isAssignableFrom(cls)) {
                obj2 = new SimpleDateFormat(ISO_8601_FORMAT_STRING, Locale.US).format((Date) obj);
            } else {
                return;
            }
            keyValueSerializer.writeString(str, obj2);
        }
    }

    public static void runCallbacks(final GraphRequestBatch graphRequestBatch, List<GraphResponse> list) {
        int size = graphRequestBatch.size();
        final ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            Callback callback2 = graphRequestBatch.get(i).callback;
            if (callback2 != null) {
                arrayList.add(new Pair(callback2, list.get(i)));
            }
        }
        if (arrayList.size() > 0) {
            AnonymousClass5 r1 = new Runnable() {
                /* class com.facebook.GraphRequest.AnonymousClass5 */

                public void run() {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        Pair pair = (Pair) it.next();
                        ((Callback) pair.first).onCompleted((GraphResponse) pair.second);
                    }
                    for (GraphRequestBatch.Callback callback : graphRequestBatch.callbacks) {
                        callback.onBatchCompleted(graphRequestBatch);
                    }
                }
            };
            Handler handler = graphRequestBatch.callbackHandler;
            if (handler == null) {
                r1.run();
            } else {
                handler.post(r1);
            }
        }
    }

    public static void serializeAttachments(Map<String, Attachment> map, Serializer serializer) throws IOException {
        for (String str : map.keySet()) {
            Attachment attachment = map.get(str);
            Object obj = attachment.value;
            if (isSupportedAttachmentType(obj)) {
                serializer.writeObject(str, obj, attachment.request);
            }
        }
    }

    public static void serializeParameters(Bundle bundle, Serializer serializer, GraphRequest graphRequest) throws IOException {
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (isSupportedParameterType(obj)) {
                serializer.writeObject(str, obj, graphRequest);
            }
        }
    }

    public static final void validateFieldsParamForGetRequests(GraphRequestBatch graphRequestBatch) {
        Iterator it = graphRequestBatch.iterator();
        while (it.hasNext()) {
            GraphRequest graphRequest = (GraphRequest) it.next();
            if (HttpMethod.GET.equals(graphRequest.httpMethod) && shouldWarnOnMissingFieldsParam(graphRequest)) {
                Bundle bundle = graphRequest.parameters;
                if (!bundle.containsKey("fields") || Utility.isNullOrEmpty(bundle.getString("fields"))) {
                    Logger.log(LoggingBehavior.DEVELOPER_ERRORS, 5, "Request", "starting with Graph API v2.4, GET requests for /%s should contain an explicit \"fields\" parameter.", graphRequest.graphPath);
                }
            }
        }
    }

    public final AccessToken getAccessToken() {
        return this.accessToken;
    }

    public final String getBatchEntryDependsOn() {
        return this.batchEntryDependsOn;
    }

    public final String getBatchEntryName() {
        return this.batchEntryName;
    }

    public final boolean getBatchEntryOmitResultOnSuccess() {
        return this.batchEntryOmitResultOnSuccess;
    }

    public final Callback getCallback() {
        return this.callback;
    }

    public final JSONObject getGraphObject() {
        return this.graphObject;
    }

    public final String getGraphPath() {
        return this.graphPath;
    }

    public final HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public final Bundle getParameters() {
        return this.parameters;
    }

    public final Object getTag() {
        return this.tag;
    }

    public final String getVersion() {
        return this.version;
    }

    public static final void setDefaultBatchApplicationId(String str) {
        defaultBatchApplicationId = str;
    }

    public final void setAccessToken(AccessToken accessToken2) {
        this.accessToken = accessToken2;
    }

    public final void setBatchEntryDependsOn(String str) {
        this.batchEntryDependsOn = str;
    }

    public final void setBatchEntryName(String str) {
        this.batchEntryName = str;
    }

    public final void setBatchEntryOmitResultOnSuccess(boolean z) {
        this.batchEntryOmitResultOnSuccess = z;
    }

    public final void setGraphObject(JSONObject jSONObject) {
        this.graphObject = jSONObject;
    }

    public final void setGraphPath(String str) {
        this.graphPath = str;
    }

    public final void setParameters(Bundle bundle) {
        this.parameters = bundle;
    }

    public final void setSkipClientToken(boolean z) {
        this.skipClientToken = z;
    }

    public final void setTag(Object obj) {
        this.tag = obj;
    }

    public final void setVersion(String str) {
        this.version = str;
    }

    public GraphRequest() {
        this(null, null, null, null, null);
    }

    public GraphRequest(AccessToken accessToken2, String str) {
        this(accessToken2, str, null, null, null);
    }

    public GraphRequest(AccessToken accessToken2, String str, Bundle bundle, HttpMethod httpMethod2) {
        this(accessToken2, str, bundle, httpMethod2, null);
    }

    public GraphRequest(AccessToken accessToken2, String str, Bundle bundle, HttpMethod httpMethod2, Callback callback2) {
        this(accessToken2, str, bundle, httpMethod2, callback2, null);
    }

    public GraphRequest(AccessToken accessToken2, String str, Bundle bundle, HttpMethod httpMethod2, Callback callback2, String str2) {
        Bundle bundle2;
        this.batchEntryOmitResultOnSuccess = true;
        this.skipClientToken = false;
        this.accessToken = accessToken2;
        this.graphPath = str;
        this.version = str2;
        setCallback(callback2);
        setHttpMethod(httpMethod2);
        if (bundle != null) {
            bundle2 = new Bundle(bundle);
        } else {
            bundle2 = new Bundle();
        }
        this.parameters = bundle2;
        if (this.version == null) {
            this.version = ServerProtocol.GRAPH_API_VERSION;
        }
    }

    public GraphRequest(AccessToken accessToken2, URL url) {
        this.batchEntryOmitResultOnSuccess = true;
        this.skipClientToken = false;
        this.accessToken = accessToken2;
        this.overriddenURL = url.toString();
        setHttpMethod(HttpMethod.GET);
        this.parameters = new Bundle();
    }

    public static GraphResponse executeAndWait(GraphRequest graphRequest) {
        List<GraphResponse> executeBatchAndWait = executeBatchAndWait(graphRequest);
        if (executeBatchAndWait != null && executeBatchAndWait.size() == 1) {
            return executeBatchAndWait.get(0);
        }
        throw new FacebookException("invalid state: expected a single response");
    }

    public static List<GraphResponse> executeBatchAndWait(GraphRequestBatch graphRequestBatch) {
        Validate.notEmptyAndContainsNoNulls(graphRequestBatch, "requests");
        try {
            HttpURLConnection httpConnection = toHttpConnection(graphRequestBatch);
            try {
                return executeConnectionAndWait(httpConnection, graphRequestBatch);
            } finally {
                Utility.disconnectQuietly(httpConnection);
            }
        } catch (Exception e) {
            List<GraphResponse> constructErrorResponses = GraphResponse.constructErrorResponses(graphRequestBatch.requests, null, new FacebookException(e));
            runCallbacks(graphRequestBatch, constructErrorResponses);
            return constructErrorResponses;
        }
    }

    public static List<GraphResponse> executeBatchAndWait(Collection<GraphRequest> collection) {
        return executeBatchAndWait(new GraphRequestBatch(collection));
    }

    public static List<GraphResponse> executeBatchAndWait(GraphRequest... graphRequestArr) {
        Validate.notNull(graphRequestArr, "requests");
        return executeBatchAndWait(Arrays.asList(graphRequestArr));
    }

    public static GraphRequestAsyncTask executeBatchAsync(GraphRequestBatch graphRequestBatch) {
        Validate.notEmptyAndContainsNoNulls(graphRequestBatch, "requests");
        GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequestAsyncTask((HttpURLConnection) null, graphRequestBatch);
        graphRequestAsyncTask.executeOnExecutor(FacebookSdk.getExecutor(), null);
        return graphRequestAsyncTask;
    }

    public static GraphRequestAsyncTask executeBatchAsync(Collection<GraphRequest> collection) {
        return executeBatchAsync(new GraphRequestBatch(collection));
    }

    public static GraphRequestAsyncTask executeBatchAsync(GraphRequest... graphRequestArr) {
        Validate.notNull(graphRequestArr, "requests");
        return executeBatchAsync(Arrays.asList(graphRequestArr));
    }

    public static List<GraphResponse> executeConnectionAndWait(HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
        List<GraphResponse> fromHttpConnection = GraphResponse.fromHttpConnection(httpURLConnection, graphRequestBatch);
        Utility.disconnectQuietly(httpURLConnection);
        int size = graphRequestBatch.size();
        if (size == fromHttpConnection.size()) {
            runCallbacks(graphRequestBatch, fromHttpConnection);
            AccessTokenManager.getInstance().extendAccessTokenIfNeeded();
            return fromHttpConnection;
        }
        throw new FacebookException(String.format(Locale.US, "Received %d responses while expecting %d", Integer.valueOf(fromHttpConnection.size()), Integer.valueOf(size)));
    }

    public static List<GraphResponse> executeConnectionAndWait(HttpURLConnection httpURLConnection, Collection<GraphRequest> collection) {
        return executeConnectionAndWait(httpURLConnection, new GraphRequestBatch(collection));
    }

    public static GraphRequestAsyncTask executeConnectionAsync(Handler handler, HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
        Validate.notNull(httpURLConnection, AnonymousClass1eW.HEADER_CONNECTION);
        GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequestAsyncTask(httpURLConnection, graphRequestBatch);
        graphRequestBatch.callbackHandler = handler;
        graphRequestAsyncTask.executeOnExecutor(FacebookSdk.getExecutor(), null);
        return graphRequestAsyncTask;
    }

    public static GraphRequestAsyncTask executeConnectionAsync(HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
        return executeConnectionAsync(null, httpURLConnection, graphRequestBatch);
    }

    public static GraphRequest newCustomAudienceThirdPartyIdRequest(AccessToken accessToken2, Context context, Callback callback2) {
        return newCustomAudienceThirdPartyIdRequest(accessToken2, context, null, callback2);
    }

    public static GraphRequest newCustomAudienceThirdPartyIdRequest(AccessToken accessToken2, Context context, String str, Callback callback2) {
        String str2;
        if (str == null && ((accessToken2 == null || (str = accessToken2.applicationId) == null) && (str = Utility.getMetadataApplicationId(context)) == null)) {
            str2 = "Facebook App ID cannot be determined";
        } else {
            String A05 = AnonymousClass006.A05(str, "/custom_audience_third_party_id");
            AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(context);
            Bundle bundle = new Bundle();
            if (accessToken2 == null) {
                if (attributionIdentifiers != null) {
                    String str3 = attributionIdentifiers.attributionId;
                    if (str3 == null) {
                        str3 = attributionIdentifiers.androidAdvertiserId;
                    }
                    if (str3 != null) {
                        bundle.putString("udid", str3);
                    }
                } else {
                    str2 = "There is no access token and attribution identifiers could not be retrieved";
                }
            }
            if (FacebookSdk.getLimitEventAndDataUsage(context) || (attributionIdentifiers != null && attributionIdentifiers.limitTracking)) {
                bundle.putString("limit_event_usage", DiskLruCache.VERSION_1);
            }
            return new GraphRequest(accessToken2, A05, bundle, HttpMethod.GET, callback2);
        }
        throw new FacebookException(str2);
    }

    public static GraphRequest newUploadPhotoRequest(AccessToken accessToken2, String str, Bitmap bitmap, String str2, Bundle bundle, Callback callback2) {
        String str3 = str;
        if (str == null) {
            str3 = MY_PHOTOS;
        }
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putParcelable(PICTURE_PARAM, bitmap);
        if (str2 != null && !str2.isEmpty()) {
            bundle2.putString(CAPTION_PARAM, str2);
        }
        return new GraphRequest(accessToken2, str3, bundle2, HttpMethod.POST, callback2);
    }

    public static GraphRequest newUploadPhotoRequest(AccessToken accessToken2, String str, Uri uri, String str2, Bundle bundle, Callback callback2) throws FileNotFoundException {
        String str3 = str;
        if (str == null) {
            str3 = MY_PHOTOS;
        }
        if (Utility.isFileUri(uri)) {
            return newUploadPhotoRequest(accessToken2, str3, new File(uri.getPath()), str2, bundle, callback2);
        }
        if (Utility.isContentUri(uri)) {
            Bundle bundle2 = new Bundle();
            if (bundle != null) {
                bundle2.putAll(bundle);
            }
            bundle2.putParcelable(PICTURE_PARAM, uri);
            return new GraphRequest(accessToken2, str3, bundle2, HttpMethod.POST, callback2);
        }
        throw new FacebookException("The photo Uri must be either a file:// or content:// Uri");
    }

    public static GraphRequest newUploadPhotoRequest(AccessToken accessToken2, String str, File file, String str2, Bundle bundle, Callback callback2) throws FileNotFoundException {
        String str3 = str;
        if (str == null) {
            str3 = MY_PHOTOS;
        }
        ParcelFileDescriptor open = ParcelFileDescriptor.open(file, 268435456);
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putParcelable(PICTURE_PARAM, open);
        if (str2 != null && !str2.isEmpty()) {
            bundle2.putString(CAPTION_PARAM, str2);
        }
        return new GraphRequest(accessToken2, str3, bundle2, HttpMethod.POST, callback2);
    }

    public static HttpURLConnection toHttpConnection(GraphRequestBatch graphRequestBatch) {
        Throwable e;
        String str;
        URL url;
        validateFieldsParamForGetRequests(graphRequestBatch);
        try {
            if (graphRequestBatch.size() == 1) {
                url = new URL(graphRequestBatch.get(0).getUrlForSingleRequest());
            } else {
                url = new URL(ServerProtocol.getGraphUrlBase());
            }
            try {
                HttpURLConnection createConnection = createConnection(url);
                serializeToUrlConnection(graphRequestBatch, createConnection);
                return createConnection;
            } catch (IOException | JSONException e2) {
                e = e2;
                Utility.disconnectQuietly(null);
                str = "could not construct request body";
                throw new FacebookException(str, e);
            }
        } catch (MalformedURLException e3) {
            e = e3;
            str = "could not construct URL for request";
            throw new FacebookException(str, e);
        }
    }

    public static HttpURLConnection toHttpConnection(Collection<GraphRequest> collection) {
        Validate.notEmptyAndContainsNoNulls(collection, "requests");
        return toHttpConnection(new GraphRequestBatch(collection));
    }

    public static HttpURLConnection toHttpConnection(GraphRequest... graphRequestArr) {
        return toHttpConnection(Arrays.asList(graphRequestArr));
    }

    public final GraphResponse executeAndWait() {
        return executeAndWait(this);
    }
}
