package com.oculus.unifiedtelemetry.unifiedlogging;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.HY;
import X.HZ;
import X.J4;
import X.Lj;
import X.M4;
import X.M7;
import X.Mu;
import X.N5;
import X.OO;
import X.QC;
import X.TM;
import X.U0;
import X.lQ;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import androidx.annotation.VisibleForTesting;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressLint({"BadMethodUse-android.content.Context.getSharedPreferences", "BadMethodUse-java.util.concurrent.ConcurrentHashMap._Constructor", "FieldInjectedContext", "SharedPreferencesUse"})
@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_oculus_directboot_ForDeviceProtectedStorage_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID", "_UL__ULSEP_android_os_UserManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_android_os_internal_UserHandleInternal_ULSEP_BINDING_ID"})
@ApplicationScoped
public class SessionController {
    @VisibleForTesting
    public static final String KEY_SESSIONS = "sessions";
    public static final String OCULUS_UT_SESSION_EVENT = "oculus_ut_session_event";
    public static final String PERMISSION = "com.oculus.permission.UPDATE_TELEMETRY_SESSIONS";
    @VisibleForTesting
    public static final String PREFS_NAME = "oculus_session_controller";
    public static final String TAG = "SessionController";
    public static volatile SessionController _UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_SessionController_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    public final SharedPreferences mPrefs;
    public final ConcurrentHashMap<Long, UserSessionStore> mSessionCache;

    @VisibleForTesting
    public static class UserSessionStore {
        public final ConcurrentHashMap<String, String> mSessionMap;
        public final Map<String, String> mUnmodifiableSessionMap;

        public final String toString() {
            return this.mSessionMap.toString();
        }

        public UserSessionStore() {
            ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
            this.mSessionMap = concurrentHashMap;
            this.mUnmodifiableSessionMap = Collections.unmodifiableMap(concurrentHashMap);
        }
    }

    @SuppressLint({"ApplySharedPref"})
    public static void A01(SessionController sessionController) {
        HZ hz = new HZ();
        hz.A01(UserSessionStore.class, new OO<UserSessionStore>() {
            /* class com.oculus.unifiedtelemetry.unifiedlogging.SessionController.UserSessionStore.AnonymousClass1 */

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.reflect.Type, X.N5] */
            @Override // X.OO
            public final /* bridge */ /* synthetic */ M4 A4v(UserSessionStore userSessionStore, Type type, N5 n5) {
                HY A00 = new HZ().A00();
                Type type2 = new lQ<ConcurrentHashMap<String, String>>() {
                    /* class com.oculus.unifiedtelemetry.unifiedlogging.SessionController.UserSessionStore.AnonymousClass1.AnonymousClass1 */
                }.type;
                ConcurrentHashMap<String, String> concurrentHashMap = userSessionStore.mSessionMap;
                TM tm = new TM();
                HY.A02(A00, concurrentHashMap, type2, tm);
                return tm.A0I();
            }
        });
        sessionController.mPrefs.edit().putString(KEY_SESSIONS, hz.A00().A07(sessionController.mSessionCache)).apply();
    }

    public static void A02(SessionController sessionController, String str) {
        if (((Context) AbstractC0096Hu.A03(0, 99, sessionController._UL_mInjectionContext)).checkCallingOrSelfPermission(PERMISSION) != 0) {
            StringBuilder sb = new StringBuilder("Permission denied: ");
            sb.append(str);
            sb.append(" from pid=");
            sb.append(Binder.getCallingPid());
            sb.append(", uid=");
            sb.append(Binder.getCallingUid());
            sb.append(" requires permission: ");
            sb.append(PERMISSION);
            String obj = sb.toString();
            Mu.A00(TAG, obj);
            throw new SecurityException(obj);
        }
    }

    @Inject
    public SessionController(AbstractC0247Xu xu) {
        Object obj;
        QC qc = new QC(4, xu);
        this._UL_mInjectionContext = qc;
        SharedPreferences sharedPreferences = ((Context) AbstractC0096Hu.A03(0, 99, qc)).getSharedPreferences(PREFS_NAME, 0);
        this.mPrefs = sharedPreferences;
        String string = sharedPreferences.getString(KEY_SESSIONS, null);
        Type type = new lQ<ConcurrentHashMap<Long, UserSessionStore>>() {
            /* class com.oculus.unifiedtelemetry.unifiedlogging.SessionController.AnonymousClass1 */
        }.type;
        ConcurrentHashMap<Long, UserSessionStore> concurrentHashMap = new ConcurrentHashMap<>();
        try {
            HZ hz = new HZ();
            hz.A01(UserSessionStore.class, new Lj<UserSessionStore>() {
                /* class com.oculus.unifiedtelemetry.unifiedlogging.SessionController.UserSessionStore.AnonymousClass2 */

                /* Return type fixed from 'java.lang.Object' to match base method */
                @Override // X.Lj
                public final UserSessionStore A1p(M4 m4, Type type, J4 j4) throws M7 {
                    UserSessionStore userSessionStore = new UserSessionStore();
                    for (Map.Entry<String, M4> entry : m4.A02().A00.entrySet()) {
                        userSessionStore.mSessionMap.put(entry.getKey(), entry.getValue().A03());
                    }
                    return userSessionStore;
                }
            });
            HY A00 = hz.A00();
            if (string == null) {
                obj = null;
            } else {
                obj = A00.A05(new StringReader(string), type);
            }
            ConcurrentHashMap<Long, UserSessionStore> concurrentHashMap2 = (ConcurrentHashMap) obj;
            if (concurrentHashMap2 != null) {
                concurrentHashMap = concurrentHashMap2;
            }
        } catch (U0 unused) {
        }
        this.mSessionCache = concurrentHashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0061, code lost:
        if (r1 != null) goto L_0x0021;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String A00(com.oculus.unifiedtelemetry.unifiedlogging.SessionController r7, java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.unifiedlogging.SessionController.A00(com.oculus.unifiedtelemetry.unifiedlogging.SessionController, java.lang.String):java.lang.String");
    }
}
