package com.oculus.panelapp.socialandroidbackpanel.graphql;

import android.app.Application;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.ocauth.OVRAuthHelper;
import java.util.LinkedList;
import java.util.List;

public class GraphQLRequestAuthTokenFactory implements OVRAuthHelper.OvrAuthTokenCallback {
    public static final String TAG = LoggingUtil.tag(GraphQLRequestAuthTokenFactory.class);
    @Nullable
    public String mAccessToken;
    public final OVRAuthHelper mAuthHelper;
    public List<AuthTokenCallback> mAuthTokenCallbackListeners = new LinkedList();

    public interface AuthTokenCallback {
        void onAccessTokenReceived(String str);
    }

    public void destroy() {
        this.mAuthTokenCallbackListeners = null;
        this.mAuthHelper.removeAccessTokenListener(this);
    }

    public synchronized void registerQueryWithAccessToken(AuthTokenCallback authTokenCallback) {
        if (!TextUtils.isEmpty(this.mAccessToken)) {
            authTokenCallback.onAccessTokenReceived(this.mAccessToken);
        } else {
            this.mAuthTokenCallbackListeners.add(authTokenCallback);
        }
    }

    private void notifyAccessTokenListeners(String str) {
        for (AuthTokenCallback authTokenCallback : this.mAuthTokenCallbackListeners) {
            authTokenCallback.onAccessTokenReceived(str);
        }
        this.mAuthTokenCallbackListeners.clear();
    }

    @Override // com.oculus.common.ocauth.OVRAuthHelper.OvrAuthTokenCallback
    public void onAccessTokenReceived(String str) {
        this.mAccessToken = str;
        notifyAccessTokenListeners(str);
    }

    public GraphQLRequestAuthTokenFactory(Application application) {
        OVRAuthHelper oVRAuthHelper = new OVRAuthHelper(application);
        this.mAuthHelper = oVRAuthHelper;
        oVRAuthHelper.registerAccessTokenListener(this);
        this.mAuthHelper.fetchAccessToken();
    }
}
