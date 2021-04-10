package com.facebook.internal;

import com.facebook.FacebookRequestError;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public final class FacebookRequestErrorClassification {
    public static final int EC_APP_TOO_MANY_CALLS = 4;
    public static final int EC_INVALID_SESSION = 102;
    public static final int EC_INVALID_TOKEN = 190;
    public static final int EC_RATE = 9;
    public static final int EC_SERVICE_UNAVAILABLE = 2;
    public static final int EC_TOO_MANY_USER_ACTION_CALLS = 341;
    public static final int EC_USER_TOO_MANY_CALLS = 17;
    public static final String KEY_LOGIN_RECOVERABLE = "login_recoverable";
    public static final String KEY_NAME = "name";
    public static final String KEY_OTHER = "other";
    public static final String KEY_RECOVERY_MESSAGE = "recovery_message";
    public static final String KEY_TRANSIENT = "transient";
    public static FacebookRequestErrorClassification defaultInstance;
    public final Map<Integer, Set<Integer>> loginRecoverableErrors;
    public final String loginRecoverableRecoveryMessage;
    public final Map<Integer, Set<Integer>> otherErrors;
    public final String otherRecoveryMessage;
    public final Map<Integer, Set<Integer>> transientErrors;
    public final String transientRecoveryMessage;

    public static FacebookRequestErrorClassification createFromJSON(JSONArray jSONArray) {
        String optString;
        if (jSONArray == null) {
            return null;
        }
        Map<Integer, Set<Integer>> map = null;
        Map<Integer, Set<Integer>> map2 = null;
        Map<Integer, Set<Integer>> map3 = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (!(optJSONObject == null || (optString = optJSONObject.optString("name")) == null)) {
                if (optString.equalsIgnoreCase(KEY_OTHER)) {
                    str = optJSONObject.optString(KEY_RECOVERY_MESSAGE, null);
                    map = parseJSONDefinition(optJSONObject);
                } else if (optString.equalsIgnoreCase(KEY_TRANSIENT)) {
                    str2 = optJSONObject.optString(KEY_RECOVERY_MESSAGE, null);
                    map2 = parseJSONDefinition(optJSONObject);
                } else if (optString.equalsIgnoreCase(KEY_LOGIN_RECOVERABLE)) {
                    str3 = optJSONObject.optString(KEY_RECOVERY_MESSAGE, null);
                    map3 = parseJSONDefinition(optJSONObject);
                }
            }
        }
        return new FacebookRequestErrorClassification(map, map2, map3, str, str2, str3);
    }

    /* renamed from: com.facebook.internal.FacebookRequestErrorClassification$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$com$facebook$FacebookRequestError$Category;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                com.facebook.FacebookRequestError$Category[] r0 = com.facebook.FacebookRequestError.Category.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.facebook.internal.FacebookRequestErrorClassification.AnonymousClass3.$SwitchMap$com$facebook$FacebookRequestError$Category = r2
                com.facebook.FacebookRequestError$Category r0 = com.facebook.FacebookRequestError.Category.OTHER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.facebook.FacebookRequestError$Category r0 = com.facebook.FacebookRequestError.Category.LOGIN_RECOVERABLE     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.facebook.FacebookRequestError$Category r0 = com.facebook.FacebookRequestError.Category.TRANSIENT     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.FacebookRequestErrorClassification.AnonymousClass3.<clinit>():void");
        }
    }

    public static synchronized FacebookRequestErrorClassification getDefaultErrorClassification() {
        FacebookRequestErrorClassification facebookRequestErrorClassification;
        synchronized (FacebookRequestErrorClassification.class) {
            facebookRequestErrorClassification = defaultInstance;
            if (facebookRequestErrorClassification == null) {
                facebookRequestErrorClassification = getDefaultErrorClassificationImpl();
                defaultInstance = facebookRequestErrorClassification;
            }
        }
        return facebookRequestErrorClassification;
    }

    public static FacebookRequestErrorClassification getDefaultErrorClassificationImpl() {
        return new FacebookRequestErrorClassification(null, new HashMap<Integer, Set<Integer>>() {
            /* class com.facebook.internal.FacebookRequestErrorClassification.AnonymousClass1 */

            {
                put(2, null);
                put(4, null);
                put(9, null);
                put(17, null);
                put(Integer.valueOf((int) FacebookRequestErrorClassification.EC_TOO_MANY_USER_ACTION_CALLS), null);
            }
        }, new HashMap<Integer, Set<Integer>>() {
            /* class com.facebook.internal.FacebookRequestErrorClassification.AnonymousClass2 */

            {
                put(Integer.valueOf((int) FacebookRequestErrorClassification.EC_INVALID_SESSION), null);
                put(190, null);
            }
        }, null, null, null);
    }

    public static Map<Integer, Set<Integer>> parseJSONDefinition(JSONObject jSONObject) {
        int optInt;
        HashSet hashSet;
        JSONArray optJSONArray = jSONObject.optJSONArray("items");
        if (optJSONArray.length() == 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (!(optJSONObject == null || (optInt = optJSONObject.optInt("code")) == 0)) {
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("subcodes");
                if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
                    hashSet = null;
                } else {
                    hashSet = new HashSet();
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        int optInt2 = optJSONArray2.optInt(i2);
                        if (optInt2 != 0) {
                            hashSet.add(Integer.valueOf(optInt2));
                        }
                    }
                }
                hashMap.put(Integer.valueOf(optInt), hashSet);
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006c, code lost:
        if (r1.contains(java.lang.Integer.valueOf(r4)) != false) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0022, code lost:
        if (r1.contains(java.lang.Integer.valueOf(r4)) != false) goto L_0x0024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.FacebookRequestError.Category classify(int r3, int r4, boolean r5) {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.FacebookRequestErrorClassification.classify(int, int, boolean):com.facebook.FacebookRequestError$Category");
    }

    public FacebookRequestErrorClassification(Map<Integer, Set<Integer>> map, Map<Integer, Set<Integer>> map2, Map<Integer, Set<Integer>> map3, String str, String str2, String str3) {
        this.otherErrors = map;
        this.transientErrors = map2;
        this.loginRecoverableErrors = map3;
        this.otherRecoveryMessage = str;
        this.transientRecoveryMessage = str2;
        this.loginRecoverableRecoveryMessage = str3;
    }

    public Map<Integer, Set<Integer>> getLoginRecoverableErrors() {
        return this.loginRecoverableErrors;
    }

    public Map<Integer, Set<Integer>> getOtherErrors() {
        return this.otherErrors;
    }

    public String getRecoveryMessage(FacebookRequestError.Category category) {
        switch (category.ordinal()) {
            case 0:
                return this.loginRecoverableRecoveryMessage;
            case 1:
                return this.otherRecoveryMessage;
            case 2:
                return this.transientRecoveryMessage;
            default:
                return null;
        }
    }

    public Map<Integer, Set<Integer>> getTransientErrors() {
        return this.transientErrors;
    }
}
