package com.oculus.ocms.library.provider;

import android.content.UriMatcher;
import android.net.Uri;
import com.oculus.ocms.library.provider.ContentType;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import com.oculus.ocms.library.provider.contract.OCMSPublicLibraryContract;
import java.util.HashMap;
import javax.annotation.Nullable;

public final class Constants {
    public static final HashMap<ContentType.QueryMethod, ContentType> CONTENT = new HashMap<>();
    public static final UriMatcher CONTENT_URI_MATCHER = new UriMatcher(-1);
    public static final HashMap<ContentType.QueryMethod, ContentType> PUBLICCONTENT = new HashMap<>();
    public static final UriMatcher PUBLIC_CONTENT_URI_MATCHER = new UriMatcher(-1);

    static {
        ContentType contentType = new ContentType(OCMSLibraryContract.AUTHORITY, OCMSLibraryContract.MATCHER_PATH_ASSET_BY_ID, ContentType.QueryMethod.ASSETS_SINGLE_BY_ID);
        ContentType contentType2 = new ContentType(OCMSLibraryContract.AUTHORITY, OCMSLibraryContract.MATCHER_PATH_ASSET_BY_FILENAME, ContentType.QueryMethod.ASSETS_SINGLE_BY_FILENAME);
        ContentType contentType3 = new ContentType(OCMSLibraryContract.AUTHORITY, OCMSLibraryContract.MATCHER_PATH_ASSETS, ContentType.QueryMethod.ASSETS_MULTIPLE);
        CONTENT.put(contentType.queryMethod, contentType);
        CONTENT.put(contentType2.queryMethod, contentType2);
        CONTENT.put(contentType3.queryMethod, contentType3);
        contentType.addToMatcher(CONTENT_URI_MATCHER);
        contentType2.addToMatcher(CONTENT_URI_MATCHER);
        contentType3.addToMatcher(CONTENT_URI_MATCHER);
        ContentType contentType4 = new ContentType(OCMSLibraryContract.AUTHORITY, "apps/*", ContentType.QueryMethod.APPS_SINGLE);
        ContentType contentType5 = new ContentType(OCMSLibraryContract.AUTHORITY, "apps", ContentType.QueryMethod.APPS_MULTIPLE);
        CONTENT.put(contentType4.queryMethod, contentType4);
        CONTENT.put(contentType5.queryMethod, contentType5);
        contentType4.addToMatcher(CONTENT_URI_MATCHER);
        contentType5.addToMatcher(CONTENT_URI_MATCHER);
        ContentType contentType6 = new ContentType(OCMSPublicLibraryContract.AUTHORITY, "apps/*", ContentType.QueryMethod.APPS_SINGLE);
        ContentType contentType7 = new ContentType(OCMSPublicLibraryContract.AUTHORITY, "apps", ContentType.QueryMethod.APPS_MULTIPLE);
        PUBLICCONTENT.put(contentType6.queryMethod, contentType6);
        PUBLICCONTENT.put(contentType7.queryMethod, contentType7);
        contentType6.addToMatcher(PUBLIC_CONTENT_URI_MATCHER);
        contentType7.addToMatcher(PUBLIC_CONTENT_URI_MATCHER);
    }

    public static ContentType getContentTypeFor(Uri uri) {
        return CONTENT.get(ContentType.QueryMethod.fromValue(CONTENT_URI_MATCHER.match(uri)));
    }

    @Nullable
    public static ContentType getPublicContentTypeFor(Uri uri) {
        return PUBLICCONTENT.get(ContentType.QueryMethod.fromValue(PUBLIC_CONTENT_URI_MATCHER.match(uri)));
    }
}
