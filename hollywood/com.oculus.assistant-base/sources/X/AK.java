package X;

import android.content.ContentResolver;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import com.facebook.acra.ACRA;
import java.util.List;
import java.util.Map;

public final class AK {
    public static final AI A03 = new AI();
    public AH A00;
    public final Context A01;
    public final UriMatcher A02;

    public static final C0668el A00(int i, String str, String str2) {
        C0668el elVar = new C0668el(C00799i.A00.mStructuredLogger.A00("assistant_user_settings"));
        AnonymousClass6D r2 = elVar.A00;
        if (r2.isSampled()) {
            if (str != null) {
                r2.A1D("assistant_user_id", str);
            }
            elVar.A00.A1B("setting_category", 1L);
            if (str2 == null) {
                str2 = C7.A00().toString();
                C0514bB.A01(str2, "SafeUUIDGenerator.randomUUID().toString()");
            }
            elVar.A00.A1D(ACRA.SESSION_ID_KEY, str2);
            elVar.A00.A1B("event", Long.valueOf((long) i));
        }
        return elVar;
    }

    public static final Object A01(List list, int i) {
        C0514bB.A02(list, "$this$getOrNull");
        C0514bB.A02(list, "$this$lastIndex");
        if (i <= list.size() - 1) {
            return list.get(i);
        }
        return null;
    }

    public static final String A02(Uri uri, AL al) {
        Object obj;
        int i = AJ.A04[al.ordinal()];
        if (i == 1) {
            List<String> pathSegments = uri.getPathSegments();
            C0514bB.A01(pathSegments, "uri.getPathSegments()");
            obj = A01(pathSegments, 3);
        } else if (i == 2) {
            List<String> pathSegments2 = uri.getPathSegments();
            C0514bB.A01(pathSegments2, "uri.getPathSegments()");
            obj = A01(pathSegments2, 4);
        } else if (i != 3) {
            throw new C0465aA();
        } else {
            throw new IllegalArgumentException("Could not parse sessionId for invalid uri");
        }
        String str = (String) obj;
        if (str != null) {
            return str;
        }
        String obj2 = C7.A00().toString();
        C0514bB.A01(obj2, "SafeUUIDGenerator.randomUUID().toString()");
        C0139Dd.A09("AssistantTtsContentProviderDetails", AnonymousClass08.A04("SessionId was not included in the Uri, generate one here: ", obj2));
        return obj2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0068, code lost:
        if (java.lang.Character.digit((int) r5.charAt(0), 10) < 0) goto L_0x006a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String A03(android.net.Uri r5, X.AL r6) {
        /*
        // Method dump skipped, instructions count: 120
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AK.A03(android.net.Uri, X.AL):java.lang.String");
    }

    public static final void A04(AK ak, String str, String str2) {
        Context context = ak.A01;
        if (context == null) {
            C0139Dd.A0B("AssistantTtsContentProviderDetails", "context is null, not notifying selection change");
            return;
        }
        C0139Dd.A0B("AssistantTtsContentProviderDetails", AnonymousClass08.A04("notifySelectionChange for user: ", str));
        ContentResolver contentResolver = context.getContentResolver();
        C0514bB.A02(str, "userId");
        Uri.Builder appendPath = AE.A00.buildUpon().appendPath(str);
        if (str2 != null) {
            appendPath.appendPath(str2);
        }
        Uri build = appendPath.build();
        C0514bB.A01(build, "uri.build()");
        contentResolver.notifyChange(build, null);
    }

    public AK(Context context) {
        this.A01 = context;
        UriMatcher uriMatcher = new UriMatcher(-1);
        uriMatcher.addURI("com.facebook.assistant.common.config.tts", "/*/tts_personas/selection/*", AL.PERSONAS_SELECTION.ordinal());
        uriMatcher.addURI("com.facebook.assistant.common.config.tts", "/*/tts_personas/selection/*/*", AL.PERSONAS_SELECTION.ordinal());
        uriMatcher.addURI("com.facebook.assistant.common.config.tts", "/*/tts_personas/*", AL.ALL_PERSONAS.ordinal());
        uriMatcher.addURI("com.facebook.assistant.common.config.tts", "/*/tts_personas/*/*", AL.ALL_PERSONAS.ordinal());
        this.A02 = uriMatcher;
    }

    public static final boolean A05(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        C0514bB.A01(pathSegments, "uri.getPathSegments()");
        String str = (String) A01(pathSegments, 0);
        if (str != null) {
            Integer A032 = C00080r.A03(str);
            if (A032 == null) {
                throw new IllegalArgumentException("Could not parse query version in invalid uri");
            } else if (A032.intValue() <= 1) {
                return false;
            } else {
                C0668el A002 = A00(7, null, null);
                AnonymousClass6D r2 = A002.A00;
                if (r2.isSampled()) {
                    r2.A1D("error_message", "Query version is higher than 1, aborted");
                    A002.A00();
                }
                return true;
            }
        } else {
            throw new IllegalArgumentException("Could not parse query version in invalid uri");
        }
    }

    public static final boolean A06(Map map, Map map2) {
        if (map.size() == map2.size()) {
            for (Map.Entry entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (map2.containsKey(key)) {
                    if (!C0514bB.A05(map2.get(key), value)) {
                    }
                }
            }
            return false;
        }
        return true;
    }
}
