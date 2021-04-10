package com.oculus.horizon.auth.shared_datastore;

import X.AbstractC06640p5;
import X.AnonymousClass117;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsManager;
import com.oculus.auth.storage.AuthDatastore;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_auth_shared_ULUNDERSCORE_datastore_HorizonAuthDatastore_ULSEP_BINDING_ID"})
@ApplicationScoped
public class HorizonCredentialsManager implements CredentialsManager, AuthDatastore {
    public static volatile HorizonCredentialsManager _UL__ULSEP_com_oculus_horizon_auth_shared_ULUNDERSCORE_datastore_HorizonCredentialsManager_ULSEP_INSTANCE;
    @Inject
    @Eager
    public final HorizonAuthDatastore mAuthDatastore;
    @Nullable
    public Credentials mCachedCredentials;
    @Nullable
    public Long mCachedCredentialsUpdateTime;
    @Nullable
    public String mCachedUserId;

    @Override // com.oculus.auth.storage.AuthDatastore
    public final synchronized void clear() {
        this.mAuthDatastore.clear();
        this.mCachedCredentials = null;
        this.mCachedUserId = null;
        this.mCachedCredentialsUpdateTime = null;
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    public final synchronized void clearCredentials() {
        this.mAuthDatastore.clearCredentials();
        this.mCachedCredentials = null;
        this.mCachedUserId = null;
        this.mCachedCredentialsUpdateTime = null;
    }

    @Override // com.oculus.auth.credentials.CredentialsManager, com.oculus.auth.storage.AuthDatastore
    @Nullable
    public final synchronized Credentials getCredentials() {
        Credentials credentials;
        credentials = this.mCachedCredentials;
        if (credentials == null) {
            credentials = this.mAuthDatastore.getCredentials();
            this.mCachedCredentials = credentials;
        }
        return credentials;
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    public final synchronized long getCredentialsUpdateTimeMillis() {
        Long l;
        l = this.mCachedCredentialsUpdateTime;
        if (l == null) {
            l = new Long(this.mAuthDatastore.getCredentialsUpdateTimeMillis());
            this.mCachedCredentialsUpdateTime = l;
        }
        return l.longValue();
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    @Nullable
    public final synchronized String getUserId() {
        String str;
        str = this.mCachedUserId;
        if (str == null) {
            str = this.mAuthDatastore.getUserId();
            this.mCachedUserId = str;
        }
        return str;
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    public final synchronized void storeCredentials(Credentials credentials) {
        this.mAuthDatastore.storeCredentials(credentials);
        this.mCachedCredentials = null;
        this.mCachedUserId = null;
        this.mCachedCredentialsUpdateTime = null;
    }

    @Inject
    public HorizonCredentialsManager(AbstractC06640p5 r2) {
        this.mAuthDatastore = (HorizonAuthDatastore) AnonymousClass117.A00(414, r2);
    }
}
