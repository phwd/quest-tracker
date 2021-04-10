package com.oculus.horizon.vr_lifecycle;

import X.AbstractC06640p5;
import X.AnonymousClass0NO;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C03080c5;
import X.C08780ya;
import android.content.Context;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableList;
import com.oculus.horizon.vr_lifecycle.query.GraphQLVRLifecycleParams;
import com.oculus.horizon.vr_lifecycle.query.GraphQLVRLifecycleQuery;
import com.oculus.horizon.vr_lifecycle.query.GraphQLVRLifecycleResponse;
import com.oculus.horizon.vr_lifecycle.query.VRLifecycleMethods;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.http.core.base.ApiError;
import com.oculus.util.device.DeviceUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_VRLifecycleJobScheduler_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_query_VRLifecycleMethods_ULSEP_BINDING_ID"})
@ApplicationScoped
public class VRLifecycleSessionManager {
    public static final C08780ya GSON_CONVERTER = new C08780ya();
    public static final String SESSION_INFO_PATH = "sessionInfo";
    public static final String TAG = "VRLifecycleSessionManager";
    public static volatile VRLifecycleSessionManager _UL__ULSEP_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_VRLifecycleSessionManager_ULSEP_INSTANCE;
    public final Context mContext;
    public boolean mIsSessionActive = false;
    public final AnonymousClass0p1<VRLifecycleJobScheduler> mJobSchedulerLazy;
    @Nullable
    public String mLastAppActive = null;
    public Map<String, AppUsageInfo> mPackageNameToAppUsageInfo = Collections.synchronizedMap(new HashMap());
    public long mSessionStartTime = 0;
    @Inject
    @Eager
    public final VRLifecycleMethods mVRLifecycleMethods;

    public interface MarkSessionEndCallback {
        void A5z(ApiError apiError);

        void A6L();

        void A75(GraphQLVRLifecycleResponse graphQLVRLifecycleResponse);

        void A76();
    }

    public final synchronized void A02(final MarkSessionEndCallback markSessionEndCallback) {
        SessionInfo A00 = A00(this.mContext);
        if (A00 == null) {
            AnonymousClass0NO.A09(TAG, "VRLifecycleJobService.markSessionEnd() missing session info");
            markSessionEndCallback.A6L();
        } else {
            long currentTimeMillis = System.currentTimeMillis() - A00.sessionEndTimestampMs;
            if (this.mIsSessionActive || currentTimeMillis < 600000) {
                AnonymousClass0NO.A09(TAG, "VRLifecycleJobService.markSessionEnd() session ended too recently");
                markSessionEndCallback.A76();
            } else {
                ImmutableList.Builder A02 = ImmutableList.A02();
                int i = 0;
                while (true) {
                    String[] strArr = A00.recentAppPackageNames;
                    if (i >= strArr.length) {
                        break;
                    }
                    A02.add((Object) new GraphQLVRLifecycleParams.RecentAppInfo(strArr[i], A00.recentAppTotalUsageTimes[i], A00.recentAppLastStartTimes[i], A00.recentAppLastUsageTimes[i]));
                    i++;
                }
                VRLifecycleMethods vRLifecycleMethods = this.mVRLifecycleMethods;
                String A03 = DeviceUtils.A03(this.mContext);
                ImmutableList build = A02.build();
                long j = A00.sessionLengthMs;
                vRLifecycleMethods.mMethods.markSessionEnd(GraphQLVRLifecycleQuery.SESSION_END_MUTATION, new GraphQLVRLifecycleParams(A03, ImmutableList.A0C(build), j, currentTimeMillis), "", new ApiCallback<GraphQLVRLifecycleResponse>() {
                    /* class com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.AnonymousClass1 */

                    @Override // com.oculus.http.core.base.ApiCallback
                    public final void onError(ApiError apiError) {
                        markSessionEndCallback.A5z(apiError);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                    @Override // com.oculus.http.core.base.ApiCallback
                    public final void onResponse(GraphQLVRLifecycleResponse graphQLVRLifecycleResponse) {
                        GraphQLVRLifecycleResponse graphQLVRLifecycleResponse2 = graphQLVRLifecycleResponse;
                        try {
                            File file = new File(VRLifecycleSessionManager.this.mContext.getFilesDir(), VRLifecycleSessionManager.SESSION_INFO_PATH);
                            if (file.exists() && !file.delete()) {
                                AnonymousClass0NO.A09(VRLifecycleSessionManager.TAG, "Failed to clear session info.");
                            }
                        } catch (SecurityException e) {
                            AnonymousClass0NO.A0H(VRLifecycleSessionManager.TAG, e, "Failed to clear session info.");
                        }
                        markSessionEndCallback.A75(graphQLVRLifecycleResponse2);
                    }
                });
            }
        }
    }

    public final synchronized void A03(@Nullable String str) {
        if (str != null) {
            if (this.mIsSessionActive) {
                String str2 = this.mLastAppActive;
                long currentTimeMillis = System.currentTimeMillis();
                if (!str.equals(str2)) {
                    if (str2 != null) {
                        AppUsageInfo appUsageInfo = this.mPackageNameToAppUsageInfo.get(str2);
                        if (appUsageInfo == null) {
                            AnonymousClass0NO.A09(TAG, "VRLifecycleSessionManager markAppEntry: Last active package missing from usage map");
                        } else {
                            appUsageInfo.mTotalUsageTime += currentTimeMillis - appUsageInfo.mLastEnteredTime;
                            appUsageInfo.mLastUsageTime = currentTimeMillis;
                            this.mPackageNameToAppUsageInfo.put(this.mLastAppActive, appUsageInfo);
                        }
                    }
                    if (this.mPackageNameToAppUsageInfo.containsKey(str)) {
                        AppUsageInfo appUsageInfo2 = this.mPackageNameToAppUsageInfo.get(str);
                        appUsageInfo2.mLastEnteredTime = currentTimeMillis;
                        appUsageInfo2.mLastUsageTime = currentTimeMillis;
                        this.mPackageNameToAppUsageInfo.put(str, appUsageInfo2);
                    } else {
                        this.mPackageNameToAppUsageInfo.put(str, new AppUsageInfo(0, currentTimeMillis, currentTimeMillis));
                    }
                } else if (this.mPackageNameToAppUsageInfo.containsKey(str)) {
                    AppUsageInfo appUsageInfo3 = this.mPackageNameToAppUsageInfo.get(str);
                    appUsageInfo3.mLastUsageTime = currentTimeMillis;
                    this.mPackageNameToAppUsageInfo.put(str, appUsageInfo3);
                }
                this.mLastAppActive = str;
                A01(this);
            }
        }
    }

    public static class AppUsageInfo {
        public long mLastEnteredTime;
        public long mLastUsageTime;
        public long mTotalUsageTime;

        public AppUsageInfo(long j, long j2, long j3) {
            this.mTotalUsageTime = j;
            this.mLastEnteredTime = j2;
            this.mLastUsageTime = j3;
        }
    }

    public static class SessionInfo {
        public long[] recentAppLastStartTimes;
        public long[] recentAppLastUsageTimes;
        public String[] recentAppPackageNames;
        public long[] recentAppTotalUsageTimes;
        public long sessionEndTimestampMs;
        public long sessionLengthMs;

        public SessionInfo(String[] strArr, long[] jArr, long[] jArr2, long[] jArr3, long j, long j2) {
            this.recentAppPackageNames = strArr;
            this.recentAppTotalUsageTimes = jArr;
            this.recentAppLastStartTimes = jArr2;
            this.recentAppLastUsageTimes = jArr3;
            this.sessionEndTimestampMs = j;
            this.sessionLengthMs = j2;
        }
    }

    public static void A01(VRLifecycleSessionManager vRLifecycleSessionManager) {
        AppUsageInfo appUsageInfo = vRLifecycleSessionManager.mPackageNameToAppUsageInfo.get(vRLifecycleSessionManager.mLastAppActive);
        long currentTimeMillis = System.currentTimeMillis();
        if (appUsageInfo != null) {
            appUsageInfo.mLastUsageTime = currentTimeMillis;
            vRLifecycleSessionManager.mPackageNameToAppUsageInfo.put(vRLifecycleSessionManager.mLastAppActive, appUsageInfo);
        }
        TreeSet treeSet = new TreeSet(new Comparator<String>() {
            /* class com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.AnonymousClass2 */

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            @Override // java.util.Comparator
            public final int compare(String str, String str2) {
                AppUsageInfo appUsageInfo = VRLifecycleSessionManager.this.mPackageNameToAppUsageInfo.get(str);
                AppUsageInfo appUsageInfo2 = VRLifecycleSessionManager.this.mPackageNameToAppUsageInfo.get(str2);
                if (appUsageInfo == null || appUsageInfo2 == null) {
                    return 0;
                }
                return (appUsageInfo.mLastUsageTime > appUsageInfo2.mLastUsageTime ? 1 : (appUsageInfo.mLastUsageTime == appUsageInfo2.mLastUsageTime ? 0 : -1));
            }
        });
        treeSet.addAll(vRLifecycleSessionManager.mPackageNameToAppUsageInfo.keySet());
        String[] strArr = (String[]) treeSet.toArray(new String[treeSet.size()]);
        long[] jArr = new long[treeSet.size()];
        long[] jArr2 = new long[treeSet.size()];
        long[] jArr3 = new long[treeSet.size()];
        for (int i = 0; i < strArr.length; i++) {
            AppUsageInfo appUsageInfo2 = vRLifecycleSessionManager.mPackageNameToAppUsageInfo.get(strArr[i]);
            if (appUsageInfo2 == null) {
                AnonymousClass0NO.A09(TAG, "VRLifecycleSessionManager storeSessionInfo: Package missing from usage map");
            } else {
                jArr[i] = appUsageInfo2.mTotalUsageTime;
                if (strArr[i].equals(vRLifecycleSessionManager.mLastAppActive)) {
                    jArr[i] = jArr[i] + (currentTimeMillis - appUsageInfo2.mLastEnteredTime);
                }
                jArr2[i] = appUsageInfo2.mLastEnteredTime;
                jArr3[i] = appUsageInfo2.mLastUsageTime;
            }
        }
        SessionInfo sessionInfo = new SessionInfo(strArr, jArr, jArr2, jArr3, currentTimeMillis, currentTimeMillis - vRLifecycleSessionManager.mSessionStartTime);
        try {
            FileOutputStream openFileOutput = vRLifecycleSessionManager.mContext.openFileOutput(SESSION_INFO_PATH, 0);
            openFileOutput.write(GSON_CONVERTER.A06(sessionInfo).getBytes("UTF-8"));
            openFileOutput.close();
        } catch (IOException e) {
            AnonymousClass0NO.A0H(TAG, e, "Failed to store session info.");
        }
    }

    @Inject
    public VRLifecycleSessionManager(AbstractC06640p5 r3, @ForAppContext Context context, AnonymousClass0p1<VRLifecycleJobScheduler> r5) {
        this.mVRLifecycleMethods = (VRLifecycleMethods) AnonymousClass117.A00(92, r3);
        this.mContext = context;
        this.mJobSchedulerLazy = r5;
    }

    @Nullable
    public static SessionInfo A00(Context context) {
        if (!new File(context.getFilesDir(), SESSION_INFO_PATH).exists()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream openFileInput = context.openFileInput(SESSION_INFO_PATH);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput, "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append("\n");
            }
            openFileInput.close();
        } catch (IOException e) {
            AnonymousClass0NO.A0H(TAG, e, "Failed to fetch session info.");
        }
        try {
            return (SessionInfo) GSON_CONVERTER.A05(sb.toString(), SessionInfo.class);
        } catch (C03080c5 e2) {
            AnonymousClass0NO.A0H(TAG, e2, "Failed to parse session info.");
            return null;
        }
    }
}
