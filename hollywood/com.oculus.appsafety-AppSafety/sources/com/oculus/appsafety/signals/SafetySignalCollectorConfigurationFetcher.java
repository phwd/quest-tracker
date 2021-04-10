package com.oculus.appsafety.signals;

import com.android.okhttp.HttpUrl;
import com.android.okhttp.MediaType;
import com.android.okhttp.OkHttpClient;
import com.android.okhttp.Protocol;
import com.android.okhttp.Request;
import com.android.okhttp.Response;
import java.io.IOException;
import java.util.Arrays;

public class SafetySignalCollectorConfigurationFetcher {
    private static final boolean DEBUG = false;
    private static final String HOST = "graph.facebook-hardware.com";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String PATH = "graphql";
    private static final String QUERY_KEY_DOC = "doc";
    private static final String QUERY_KEY_TOKEN = "access_token";
    private static final String QUERY_KEY_VARIABLES = "variables";
    private static final String SCHEME = "https";
    private static final String TAG = SafetySignalCollectorConfigurationFetcher.class.getSimpleName();
    private String SAFETY_SIGNAL_CONFIG_QUERY;
    private String mAccessToken;
    private OkHttpClient mClient;
    private String mVersion;

    public SafetySignalCollectorConfigurationFetcher(String accessToken, String version) {
        this(accessToken, version, new OkHttpClient());
    }

    SafetySignalCollectorConfigurationFetcher(String accessToken, String version, OkHttpClient client) {
        this.SAFETY_SIGNAL_CONFIG_QUERY = "query SafetySignalConfig($version: Int) { me {   hwm_device {     ... on HWMOculusDevice {       safety_signal_config(version: $version)     }   } }}";
        this.mAccessToken = accessToken;
        this.mVersion = version;
        client.setProtocols(Arrays.asList(Protocol.HTTP_1_1));
        this.mClient = client;
    }

    public SafetySignalCollectorConfiguration fetch() throws IOException {
        Response response = this.mClient.newCall(new Request.Builder().url(new HttpUrl.Builder().scheme(SCHEME).host(HOST).addPathSegment(PATH).addQueryParameter(QUERY_KEY_TOKEN, this.mAccessToken).addQueryParameter(QUERY_KEY_DOC, this.SAFETY_SIGNAL_CONFIG_QUERY).addQueryParameter(QUERY_KEY_VARIABLES, new SafetySignalCollectorConfigurationRequest(this.mVersion).toJson()).build().toString()).build()).execute();
        if (response.isSuccessful()) {
            return SafetySignalCollectorConfiguration.fromJson(response.body().string());
        }
        throw new RuntimeException(String.format("Failed to fetch configuration: HTTP response code = %d", Integer.valueOf(response.code())));
    }
}
