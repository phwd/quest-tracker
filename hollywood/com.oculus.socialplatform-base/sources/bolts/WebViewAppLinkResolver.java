package bolts;

import android.content.Context;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import bolts.AppLink;
import com.oculus.horizon.logging.LoggingKeys;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebViewAppLinkResolver implements AppLinkResolver {
    public static final String KEY_AL_VALUE = "value";
    public static final String KEY_ANDROID = "android";
    public static final String KEY_APP_NAME = "app_name";
    public static final String KEY_CLASS = "class";
    public static final String KEY_PACKAGE = "package";
    public static final String KEY_SHOULD_FALLBACK = "should_fallback";
    public static final String KEY_URL = "url";
    public static final String KEY_WEB = "web";
    public static final String KEY_WEB_URL = "url";
    public static final String META_TAG_PREFIX = "al";
    public static final String PREFER_HEADER = "Prefer-Html-Meta-Tags";
    public static final String TAG_EXTRACTION_JAVASCRIPT = "javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())";
    public final Context context;

    public static AppLink makeAppLinkFromAlData(Map<String, Object> map, Uri uri) {
        Uri uri2;
        ArrayList arrayList = new ArrayList();
        List list = (List) map.get(KEY_ANDROID);
        if (list == null) {
            list = Collections.emptyList();
        }
        Iterator it = list.iterator();
        while (true) {
            uri2 = null;
            if (!it.hasNext()) {
                break;
            }
            Map map2 = (Map) it.next();
            List<Map<String, Object>> alList = getAlList(map2, "url");
            List<Map<String, Object>> alList2 = getAlList(map2, "package");
            List<Map<String, Object>> alList3 = getAlList(map2, KEY_CLASS);
            List<Map<String, Object>> alList4 = getAlList(map2, "app_name");
            int max = Math.max(alList.size(), Math.max(alList2.size(), Math.max(alList3.size(), alList4.size())));
            for (int i = 0; i < max; i++) {
                Uri tryCreateUrl = tryCreateUrl((String) (alList.size() > i ? alList.get(i).get("value") : null));
                arrayList.add(new AppLink.Target((String) (alList2.size() > i ? alList2.get(i).get("value") : null), (String) (alList3.size() > i ? alList3.get(i).get("value") : null), tryCreateUrl, (String) (alList4.size() > i ? alList4.get(i).get("value") : null)));
            }
        }
        List list2 = (List) map.get(KEY_WEB);
        if (list2 == null || list2.size() <= 0) {
            uri2 = uri;
        } else {
            Map map3 = (Map) list2.get(0);
            List list3 = (List) map3.get("url");
            List list4 = (List) map3.get(KEY_SHOULD_FALLBACK);
            if (list4 == null || list4.size() <= 0 || !Arrays.asList("no", "false", "0").contains(((String) ((Map) list4.get(0)).get("value")).toLowerCase())) {
                uri2 = uri;
                if (!(uri == null || list3 == null || list3.size() <= 0)) {
                    uri2 = tryCreateUrl((String) ((Map) list3.get(0)).get("value"));
                }
            }
        }
        return new AppLink(uri, arrayList, uri2);
    }

    public static Map<String, Object> parseAlData(JSONArray jSONArray) throws JSONException {
        Map hashMap = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            Object[] split = jSONObject.getString("property").split(":");
            if (split[0].equals(META_TAG_PREFIX)) {
                Map map = hashMap;
                for (int i2 = 1; i2 < split.length; i2++) {
                    List list = (List) map.get(split[i2]);
                    if (list == null) {
                        list = new ArrayList();
                        map.put(split[i2], list);
                    }
                    if (list.size() <= 0 || (map = (Map) list.get(list.size() - 1)) == null || i2 == split.length - 1) {
                        map = new HashMap();
                        list.add(map);
                    }
                }
                if (jSONObject.has(LoggingKeys.REFERRER_CONTENT)) {
                    if (jSONObject.isNull(LoggingKeys.REFERRER_CONTENT)) {
                        map.put("value", null);
                    } else {
                        map.put("value", jSONObject.getString(LoggingKeys.REFERRER_CONTENT));
                    }
                }
            }
        }
        return hashMap;
    }

    public static String readFromConnection(URLConnection uRLConnection) throws IOException {
        InputStream inputStream;
        int i;
        if (uRLConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
            try {
                inputStream = uRLConnection.getInputStream();
            } catch (Exception unused) {
                inputStream = httpURLConnection.getErrorStream();
            }
        } else {
            inputStream = uRLConnection.getInputStream();
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                i = 0;
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            String contentEncoding = uRLConnection.getContentEncoding();
            if (contentEncoding == null) {
                String[] split = uRLConnection.getContentType().split(";");
                int length = split.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    String trim = split[i].trim();
                    if (trim.startsWith("charset=")) {
                        contentEncoding = trim.substring(8);
                        break;
                    }
                    i++;
                }
                if (contentEncoding == null) {
                    contentEncoding = "UTF-8";
                }
            }
            return new String(byteArrayOutputStream.toByteArray(), contentEncoding);
        } finally {
            inputStream.close();
        }
    }

    public static Uri tryCreateUrl(String str) {
        if (str == null) {
            return null;
        }
        return Uri.parse(str);
    }

    @Override // bolts.AppLinkResolver
    public Task<AppLink> getAppLinkFromUrlInBackground(final Uri uri) {
        final Capture capture = new Capture();
        final Capture capture2 = new Capture();
        return Task.callInBackground(new Callable<Void>() {
            /* class bolts.WebViewAppLinkResolver.AnonymousClass3 */

            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                URL url = new URL(uri.toString());
                while (true) {
                    URLConnection openConnection = url.openConnection();
                    if (openConnection instanceof HttpURLConnection) {
                        ((HttpURLConnection) openConnection).setInstanceFollowRedirects(true);
                    }
                    openConnection.setRequestProperty(WebViewAppLinkResolver.PREFER_HEADER, WebViewAppLinkResolver.META_TAG_PREFIX);
                    openConnection.connect();
                    if (openConnection instanceof HttpURLConnection) {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                        if (httpURLConnection.getResponseCode() >= 300 && httpURLConnection.getResponseCode() < 400) {
                            url = new URL(httpURLConnection.getHeaderField("Location"));
                            httpURLConnection.disconnect();
                        }
                    }
                    try {
                        capture.value = (T) WebViewAppLinkResolver.readFromConnection(openConnection);
                        capture2.value = (T) openConnection.getContentType();
                        return null;
                    } finally {
                        if (openConnection instanceof HttpURLConnection) {
                            ((HttpURLConnection) openConnection).disconnect();
                        }
                    }
                }
            }
        }).onSuccessTask(new Continuation<Void, Task<JSONArray>>() {
            /* class bolts.WebViewAppLinkResolver.AnonymousClass2 */

            @Override // bolts.Continuation
            public Task<JSONArray> then(Task<Void> task) throws Exception {
                String str;
                final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
                WebView webView = new WebView(WebViewAppLinkResolver.this.context);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setNetworkAvailable(false);
                webView.setWebViewClient(new WebViewClient() {
                    /* class bolts.WebViewAppLinkResolver.AnonymousClass2.AnonymousClass1 */
                    public boolean loaded = false;

                    private void runJavaScript(WebView webView) {
                        if (!this.loaded) {
                            this.loaded = true;
                            webView.loadUrl(WebViewAppLinkResolver.TAG_EXTRACTION_JAVASCRIPT);
                        }
                    }

                    public void onLoadResource(WebView webView, String str) {
                        super.onLoadResource(webView, str);
                        runJavaScript(webView);
                    }

                    public void onPageFinished(WebView webView, String str) {
                        super.onPageFinished(webView, str);
                        runJavaScript(webView);
                    }
                });
                webView.addJavascriptInterface(new Object() {
                    /* class bolts.WebViewAppLinkResolver.AnonymousClass2.AnonymousClass2 */

                    @JavascriptInterface
                    public void setValue(String str) {
                        try {
                            taskCompletionSource.trySetResult(new JSONArray(str));
                        } catch (JSONException e) {
                            taskCompletionSource.trySetError(e);
                        }
                    }
                }, "boltsWebViewAppLinkResolverResult");
                T t = capture2.value;
                if (t != null) {
                    str = t.split(";")[0];
                } else {
                    str = null;
                }
                webView.loadDataWithBaseURL(uri.toString(), capture.value, str, null, null);
                return taskCompletionSource.task;
            }
        }, Task.UI_THREAD_EXECUTOR).onSuccess(new Continuation<JSONArray, AppLink>() {
            /* class bolts.WebViewAppLinkResolver.AnonymousClass1 */

            @Override // bolts.Continuation
            public AppLink then(Task<JSONArray> task) throws Exception {
                return WebViewAppLinkResolver.makeAppLinkFromAlData(WebViewAppLinkResolver.parseAlData(task.getResult()), uri);
            }
        });
    }

    public WebViewAppLinkResolver(Context context2) {
        this.context = context2;
    }

    public static List<Map<String, Object>> getAlList(Map<String, Object> map, String str) {
        List<Map<String, Object>> list = (List) map.get(str);
        if (list == null) {
            return Collections.emptyList();
        }
        return list;
    }
}
