package com.facebook.mobileconfig.troubleshooting;

import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.mobileconfig.MobileConfigManagerHolderImpl;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigQEInfoManagerHolder {
    public static final int QE_INFO_FETCH_TIMEOUT_MS = 20000;
    @DoNotStrip
    private final HybridData mHybridData;

    private static native HybridData initHybrid(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl);

    public native boolean getQEInfo(MobileConfigQEInfoQueryParamsHolder mobileConfigQEInfoQueryParamsHolder, MobileConfigResponseCallback mobileConfigResponseCallback, String str, String str2);

    public native String getRNQEInfo();

    public native boolean hasRefreshedQEInfo();

    static {
        SoLoader.loadLibrary("mobileconfigtroubleshooting-jni");
    }

    public MobileConfigQEInfoManagerHolder(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl) {
        this.mHybridData = initHybrid(mobileConfigManagerHolderImpl);
    }

    @Nullable
    public String fetchSearchInfo() {
        MobileConfigQEInfoQueryParamsHolder mobileConfigQEInfoQueryParamsHolder = new MobileConfigQEInfoQueryParamsHolder();
        mobileConfigQEInfoQueryParamsHolder.withCurrentExperimentGroup(false);
        mobileConfigQEInfoQueryParamsHolder.withParams(false);
        mobileConfigQEInfoQueryParamsHolder.withExperiments(false);
        mobileConfigQEInfoQueryParamsHolder.withGroupParams(false);
        mobileConfigQEInfoQueryParamsHolder.withGroups(false);
        mobileConfigQEInfoQueryParamsHolder.withGatekeepers(true);
        return fetchQEInfoSynchronously(mobileConfigQEInfoQueryParamsHolder, "", "");
    }

    @Nullable
    public String fetchFullSearchInfo() {
        MobileConfigQEInfoQueryParamsHolder mobileConfigQEInfoQueryParamsHolder = new MobileConfigQEInfoQueryParamsHolder();
        mobileConfigQEInfoQueryParamsHolder.withGatekeepers(true);
        mobileConfigQEInfoQueryParamsHolder.withGroups(true);
        mobileConfigQEInfoQueryParamsHolder.withGroupParams(true);
        mobileConfigQEInfoQueryParamsHolder.withExperiments(true);
        mobileConfigQEInfoQueryParamsHolder.withCurrentExperimentGroup(true);
        mobileConfigQEInfoQueryParamsHolder.withParams(true);
        return fetchQEInfoSynchronously(mobileConfigQEInfoQueryParamsHolder, "", "");
    }

    @Nullable
    public String fetchUniverseInfo(String str) {
        MobileConfigQEInfoQueryParamsHolder mobileConfigQEInfoQueryParamsHolder = new MobileConfigQEInfoQueryParamsHolder();
        mobileConfigQEInfoQueryParamsHolder.setUniverseRegex('^' + str + '$');
        mobileConfigQEInfoQueryParamsHolder.withUniverseParams(true);
        mobileConfigQEInfoQueryParamsHolder.withCurrentExperimentGroup(true);
        mobileConfigQEInfoQueryParamsHolder.withExperiments(true);
        mobileConfigQEInfoQueryParamsHolder.withParams(false);
        mobileConfigQEInfoQueryParamsHolder.withGroupParams(false);
        mobileConfigQEInfoQueryParamsHolder.withGroups(false);
        mobileConfigQEInfoQueryParamsHolder.withGatekeepers(false);
        return fetchQEInfoSynchronously(mobileConfigQEInfoQueryParamsHolder, "", "");
    }

    @Nullable
    public String fetchExperimentInfo(String str, String str2) {
        MobileConfigQEInfoQueryParamsHolder mobileConfigQEInfoQueryParamsHolder = new MobileConfigQEInfoQueryParamsHolder();
        mobileConfigQEInfoQueryParamsHolder.setUniverseRegex('^' + str + '$');
        mobileConfigQEInfoQueryParamsHolder.setExperimentName(str2);
        mobileConfigQEInfoQueryParamsHolder.withUniverseParams(false);
        mobileConfigQEInfoQueryParamsHolder.withCurrentExperimentGroup(true);
        mobileConfigQEInfoQueryParamsHolder.withExperiments(true);
        mobileConfigQEInfoQueryParamsHolder.withParams(true);
        mobileConfigQEInfoQueryParamsHolder.withGroupParams(true);
        mobileConfigQEInfoQueryParamsHolder.withGroups(true);
        mobileConfigQEInfoQueryParamsHolder.withGatekeepers(false);
        return fetchQEInfoSynchronously(mobileConfigQEInfoQueryParamsHolder, "", "");
    }

    @Nullable
    public String fetchGKInfo(String str) {
        MobileConfigQEInfoQueryParamsHolder mobileConfigQEInfoQueryParamsHolder = new MobileConfigQEInfoQueryParamsHolder();
        mobileConfigQEInfoQueryParamsHolder.setGatekeeperName(str);
        mobileConfigQEInfoQueryParamsHolder.withGatekeepers(true);
        return fetchQEInfoSynchronously(mobileConfigQEInfoQueryParamsHolder, "", "");
    }

    @Nullable
    public String fetchUniverseInfoForParam(String str, String str2) {
        MobileConfigQEInfoQueryParamsHolder mobileConfigQEInfoQueryParamsHolder = new MobileConfigQEInfoQueryParamsHolder();
        mobileConfigQEInfoQueryParamsHolder.withUniverseParams(false);
        mobileConfigQEInfoQueryParamsHolder.withGatekeepers(false);
        mobileConfigQEInfoQueryParamsHolder.withCurrentExperimentGroup(false);
        mobileConfigQEInfoQueryParamsHolder.withExperiments(false);
        mobileConfigQEInfoQueryParamsHolder.withParams(false);
        mobileConfigQEInfoQueryParamsHolder.withGroupParams(false);
        mobileConfigQEInfoQueryParamsHolder.withGroups(false);
        return fetchQEInfoSynchronously(mobileConfigQEInfoQueryParamsHolder, str, str2);
    }

    @Nullable
    public String fetchIdNameMappingOnly() {
        MobileConfigQEInfoQueryParamsHolder mobileConfigQEInfoQueryParamsHolder = new MobileConfigQEInfoQueryParamsHolder();
        mobileConfigQEInfoQueryParamsHolder.withCurrentExperimentGroup(false);
        mobileConfigQEInfoQueryParamsHolder.withParams(false);
        mobileConfigQEInfoQueryParamsHolder.withExperiments(false);
        mobileConfigQEInfoQueryParamsHolder.withGroupParams(false);
        mobileConfigQEInfoQueryParamsHolder.withGroups(false);
        mobileConfigQEInfoQueryParamsHolder.withGatekeepers(false);
        mobileConfigQEInfoQueryParamsHolder.setSkipQE(true);
        return fetchQEInfoSynchronously(mobileConfigQEInfoQueryParamsHolder, "", "");
    }

    @Nullable
    private String fetchQEInfoSynchronously(MobileConfigQEInfoQueryParamsHolder mobileConfigQEInfoQueryParamsHolder, String str, String str2) {
        MobileConfigResponseResult mobileConfigResponseResult = new MobileConfigResponseResult();
        getQEInfo(mobileConfigQEInfoQueryParamsHolder, mobileConfigResponseResult, str, str2);
        try {
            mobileConfigResponseResult.waitFor(20000);
            if (!mobileConfigResponseResult.isDone()) {
                BLog.e(MobileConfigTroubleShootingConstants.TAG, "Timed out fetching QE info");
                return null;
            } else if (mobileConfigResponseResult.isSuccess()) {
                return mobileConfigResponseResult.getResponse();
            } else {
                BLog.e(MobileConfigTroubleShootingConstants.TAG, "Failed to fetch QE info: %s", mobileConfigResponseResult.getResponse());
                return null;
            }
        } catch (InterruptedException e) {
            BLog.e(MobileConfigTroubleShootingConstants.TAG, "QE info fetch failed: interrupted", e);
            return null;
        }
    }
}
