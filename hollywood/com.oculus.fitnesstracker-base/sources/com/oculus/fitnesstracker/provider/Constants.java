package com.oculus.fitnesstracker.provider;

import android.content.UriMatcher;
import android.net.Uri;
import com.oculus.fitnesstracker.provider.ContentType;
import java.util.HashMap;

public final class Constants {
    public static final HashMap<ContentType.QueryMethod, ContentType> CONTENT = new HashMap<>();
    public static final UriMatcher CONTENT_URI_MATCHER = new UriMatcher(-1);

    static {
        ContentType contentType = new ContentType(FitnessDataContract.AUTHORITY, "calories-daily-summary", ContentType.QueryMethod.CALORIES_DAILY_SUMMARY);
        CONTENT.put(contentType.queryMethod, contentType);
        contentType.addToMatcher(CONTENT_URI_MATCHER);
        ContentType contentType2 = new ContentType(FitnessDataContract.AUTHORITY, "effort", ContentType.QueryMethod.EFFORT);
        CONTENT.put(contentType2.queryMethod, contentType2);
        contentType2.addToMatcher(CONTENT_URI_MATCHER);
    }

    public static ContentType getContentTypeFor(Uri uri) {
        return CONTENT.get(ContentType.QueryMethod.fromValue(CONTENT_URI_MATCHER.match(uri)));
    }
}
