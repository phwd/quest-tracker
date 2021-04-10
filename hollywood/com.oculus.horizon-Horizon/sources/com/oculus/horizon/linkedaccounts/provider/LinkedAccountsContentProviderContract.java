package com.oculus.horizon.linkedaccounts.provider;

import X.AnonymousClass006;
import android.net.Uri;
import com.oculus.horizon.linkedaccounts.database.contract.LinkedAccountsSQLiteContract;
import com.oculus.horizon.logging.LoggingKeys;

public final class LinkedAccountsContentProviderContract {
    public static final String AUTHORITY = AnonymousClass006.A05("com.oculus.horizon", ".linkedaccounts");
    public static final Uri CONTENT_URI = new Uri.Builder().scheme(LoggingKeys.REFERRER_CONTENT).authority(AUTHORITY).appendPath(LinkedAccountsSQLiteContract.TABLE_NAME).build();

    public static class Projection {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String SERVICE_PROVIDER = "service_provider";
        public static final String USER_ID = "user_id";
    }
}
