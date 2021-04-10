package com.oculus.messenger.service;

import android.net.Uri;

public final class MessengerServiceProviderContract {
    public static final String AUTHORITY = "com.oculus.messenger.service.provider";
    public static final String AUTHORITY_SOCIALPLATFORM = "com.oculus.socialplatform.messenger.service.provider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://com.oculus.messenger.service.provider");
    public static final Uri AUTHORITY_URI_SOCIALPLATFORM = Uri.parse("content://com.oculus.socialplatform.messenger.service.provider");

    public static final class Status {
        public static final String COLUMN_ACTIVE = "active";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(MessengerServiceProviderContract.AUTHORITY_URI, "status");
        public static final Uri CONTENT_URI_SOCIALPLATFORM = Uri.withAppendedPath(MessengerServiceProviderContract.AUTHORITY_URI_SOCIALPLATFORM, "status");
        public static final int IS_ACTIVE = 1;
        public static final int IS_NOT_ACTIVE = 0;
    }
}
