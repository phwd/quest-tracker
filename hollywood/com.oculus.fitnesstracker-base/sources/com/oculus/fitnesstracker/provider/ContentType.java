package com.oculus.fitnesstracker.provider;

import android.content.UriMatcher;
import android.net.Uri;

public class ContentType {
    public final String authority;
    public final String mimeType;
    public final String path;
    public final QueryMethod queryMethod;
    public final Uri uri;

    public enum QueryMethod {
        EFFORT(0),
        CALORIES_DAILY_SUMMARY(1);
        
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
        int[] iArr = AnonymousClass1.$SwitchMap$com$oculus$fitnesstracker$provider$ContentType$QueryMethod;
        queryMethod2.ordinal();
        this.mimeType = "vnd.android.cursor." + "dir/" + str + "." + str2 + "." + queryMethod2;
    }

    /* renamed from: com.oculus.fitnesstracker.provider.ContentType$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$fitnesstracker$provider$ContentType$QueryMethod = new int[QueryMethod.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.fitnesstracker.provider.ContentType$QueryMethod[] r0 = com.oculus.fitnesstracker.provider.ContentType.QueryMethod.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.fitnesstracker.provider.ContentType.AnonymousClass1.$SwitchMap$com$oculus$fitnesstracker$provider$ContentType$QueryMethod = r0
                int[] r0 = com.oculus.fitnesstracker.provider.ContentType.AnonymousClass1.$SwitchMap$com$oculus$fitnesstracker$provider$ContentType$QueryMethod     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.fitnesstracker.provider.ContentType$QueryMethod r1 = com.oculus.fitnesstracker.provider.ContentType.QueryMethod.CALORIES_DAILY_SUMMARY     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.fitnesstracker.provider.ContentType.AnonymousClass1.$SwitchMap$com$oculus$fitnesstracker$provider$ContentType$QueryMethod     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.fitnesstracker.provider.ContentType$QueryMethod r1 = com.oculus.fitnesstracker.provider.ContentType.QueryMethod.EFFORT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.provider.ContentType.AnonymousClass1.<clinit>():void");
        }
    }

    public void addToMatcher(UriMatcher uriMatcher) {
        uriMatcher.addURI(this.authority, this.path, this.queryMethod.value);
    }

    public String toString() {
        return this.uri.toString();
    }
}
