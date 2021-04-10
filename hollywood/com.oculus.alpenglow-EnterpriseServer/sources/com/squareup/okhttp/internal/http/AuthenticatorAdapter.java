package com.squareup.okhttp.internal.http;

import com.oculus.alpenglow.http.DeviceAuthorizationInterceptor;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Challenge;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.List;

public final class AuthenticatorAdapter implements Authenticator {
    public static final Authenticator INSTANCE = new AuthenticatorAdapter();

    private InetAddress getConnectToInetAddress(Proxy proxy, HttpUrl httpUrl) throws IOException {
        if (proxy == null || proxy.type() == Proxy.Type.DIRECT) {
            return InetAddress.getByName(httpUrl.host);
        }
        return ((InetSocketAddress) proxy.address()).getAddress();
    }

    @Override // com.squareup.okhttp.Authenticator
    public Request authenticate(Proxy proxy, Response response) throws IOException {
        PasswordAuthentication requestPasswordAuthentication;
        List<Challenge> challenges = response.challenges();
        Request request = response.request;
        HttpUrl httpUrl = request.url;
        int size = challenges.size();
        for (int i = 0; i < size; i++) {
            Challenge challenge = challenges.get(i);
            if ("Basic".equalsIgnoreCase(challenge.scheme) && (requestPasswordAuthentication = java.net.Authenticator.requestPasswordAuthentication(httpUrl.host, getConnectToInetAddress(proxy, httpUrl), httpUrl.port, httpUrl.scheme, challenge.realm, challenge.scheme, httpUrl.url(), Authenticator.RequestorType.SERVER)) != null) {
                String basic = Credentials.basic(requestPasswordAuthentication.getUserName(), new String(requestPasswordAuthentication.getPassword()));
                Request.Builder builder = new Request.Builder(request);
                builder.headers.set(DeviceAuthorizationInterceptor.HEADER_AUTHORIZATION, basic);
                return builder.build();
            }
        }
        return null;
    }

    @Override // com.squareup.okhttp.Authenticator
    public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
        List<Challenge> challenges = response.challenges();
        Request request = response.request;
        HttpUrl httpUrl = request.url;
        int size = challenges.size();
        for (int i = 0; i < size; i++) {
            Challenge challenge = challenges.get(i);
            if ("Basic".equalsIgnoreCase(challenge.scheme)) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) proxy.address();
                PasswordAuthentication requestPasswordAuthentication = java.net.Authenticator.requestPasswordAuthentication(inetSocketAddress.getHostName(), getConnectToInetAddress(proxy, httpUrl), inetSocketAddress.getPort(), httpUrl.scheme, challenge.realm, challenge.scheme, httpUrl.url(), Authenticator.RequestorType.PROXY);
                if (requestPasswordAuthentication != null) {
                    String basic = Credentials.basic(requestPasswordAuthentication.getUserName(), new String(requestPasswordAuthentication.getPassword()));
                    Request.Builder builder = new Request.Builder(request);
                    builder.headers.set("Proxy-Authorization", basic);
                    return builder.build();
                }
            }
        }
        return null;
    }
}
