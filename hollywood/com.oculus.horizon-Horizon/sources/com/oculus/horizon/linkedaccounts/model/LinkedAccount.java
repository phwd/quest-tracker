package com.oculus.horizon.linkedaccounts.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.Arrays;
import javax.annotation.Nullable;

public class LinkedAccount {
    @Nullable
    public String mAccessToken;
    public ServiceProvider mServiceProvider;
    public String mUserID;

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.mUserID, this.mAccessToken, this.mServiceProvider});
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof LinkedAccount)) {
            return false;
        }
        LinkedAccount linkedAccount = (LinkedAccount) obj;
        if (!Objects.equal(this.mUserID, linkedAccount.mUserID) || !Objects.equal(this.mAccessToken, linkedAccount.mAccessToken) || !Objects.equal(this.mServiceProvider, linkedAccount.mServiceProvider)) {
            return false;
        }
        return true;
    }

    public final String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(LinkedAccount.class);
        stringHelper.add("user_id", this.mUserID);
        stringHelper.add("access_token", this.mAccessToken);
        stringHelper.add("service_provider", this.mServiceProvider);
        return stringHelper.toString();
    }

    public LinkedAccount(String str, @Nullable String str2, ServiceProvider serviceProvider) {
        this.mUserID = str;
        this.mAccessToken = str2;
        this.mServiceProvider = serviceProvider;
    }
}
