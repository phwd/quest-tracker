package com.oculus.ocms.library.provider;

import android.content.UriMatcher;
import android.net.Uri;

public class ContentType {
    public final String authority;
    public final String mimeType;
    public final String path;
    public final QueryMethod queryMethod;
    public final Uri uri;

    public enum QueryMethod {
        APPS_SINGLE(0),
        APPS_MULTIPLE(1),
        ASSETS_SINGLE_BY_ID(2),
        ASSETS_SINGLE_BY_FILENAME(3),
        ASSETS_MULTIPLE(4);
        
        public final int value;

        private QueryMethod(int i) {
            this.value = i;
        }

        public static QueryMethod fromValue(int i) {
            QueryMethod[] values = values();
            for (QueryMethod queryMethod : values) {
                if (queryMethod.value == i) {
                    return queryMethod;
                }
            }
            return null;
        }
    }

    public ContentType(String str, String str2, QueryMethod queryMethod2) {
        this.authority = str;
        this.path = str2;
        this.queryMethod = queryMethod2;
        this.uri = Uri.parse("content://" + str + "/" + str2);
        StringBuilder sb = new StringBuilder();
        sb.append("vnd.android.cursor.");
        sb.append(queryMethod2 == QueryMethod.APPS_MULTIPLE ? "dir" : "item");
        sb.append("/");
        sb.append(str);
        sb.append(".");
        sb.append(str2);
        sb.append(".");
        sb.append(queryMethod2);
        this.mimeType = sb.toString();
    }

    public void addToMatcher(UriMatcher uriMatcher) {
        uriMatcher.addURI(this.authority, this.path, this.queryMethod.value);
    }

    public String toString() {
        return this.uri.toString();
    }
}
