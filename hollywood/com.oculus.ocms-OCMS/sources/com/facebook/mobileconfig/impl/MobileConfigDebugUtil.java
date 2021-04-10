package com.facebook.mobileconfig.impl;

import com.facebook.crudolib.params.ParamsCollectionMap;
import com.facebook.crudolib.params.ParamsCollectionPool;
import com.facebook.crudolib.params.ParamsJsonEncoder;
import com.facebook.mobileconfig.factory.MobileConfig;
import com.facebook.mobileconfig.factory.MobileConfigContext;
import com.facebook.mobileconfig.factory.MobileConfigOptions;
import com.facebook.mobileconfig.metadata.ParamsMapEntry;
import com.facebook.mobileconfig.metadata.ParamsMapList;
import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.inject.Provider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MobileConfigDebugUtil {
    public static final String PARAM_TRUNCATED_FLAG = "... (truncated)";
    public static final int RAGE_SHAKE_JSON_INDENT_SPACES = 2;
    public static final int RAGE_SHAKE_STRING_SIZE_LIMIT = 500;
    public static final int RAGE_SHAKE_TRUNCATED_STRING_SIZE = 25;
    private static final String TAG = "MobileConfigDebugUtil";
    private static final ParamsCollectionPool sParamsCollectionPool = ParamsCollectionPool.newDefaultInstance();
    private final Provider<MobileConfigManagerSingletonHolder> mMobileConfigManagerHolderProvider;
    private final Provider<MobileConfig> mMobileConfigProvider;
    private final Provider<MobileConfigManagerSingletonHolder> mSessionlessMobileConfigManagerHolderProvider;
    private final Provider<MobileConfig> mSessionlessMobileConfigProvider;
    private final boolean mUsedForService;

    public interface MobileConfigContextGetter {
        MobileConfigContext getContext(ParamsMapEntry paramsMapEntry);
    }

    public MobileConfigDebugUtil(Provider<MobileConfig> provider, Provider<MobileConfig> provider2, Provider<MobileConfigManagerSingletonHolder> provider3, Provider<MobileConfigManagerSingletonHolder> provider4, boolean z) {
        this.mMobileConfigProvider = provider;
        this.mSessionlessMobileConfigProvider = provider2;
        this.mMobileConfigManagerHolderProvider = provider3;
        this.mSessionlessMobileConfigManagerHolderProvider = provider4;
        this.mUsedForService = z;
    }

    public String getRageShakeLogString(List<ParamsMapEntry> list) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = null;
            MobileConfig mobileConfig = this.mMobileConfigProvider.get();
            MobileConfig mobileConfig2 = this.mSessionlessMobileConfigProvider.get();
            String str = "";
            if (!(mobileConfig instanceof MobileConfigFactoryImpl) || !(mobileConfig2 instanceof MobileConfigFactoryImpl)) {
                return str;
            }
            HashSet hashSet = new HashSet();
            for (int i : ((MobileConfigFactoryImpl) mobileConfig).accessedConfigs()) {
                hashSet.add(Integer.valueOf(i));
            }
            for (int i2 : ((MobileConfigFactoryImpl) mobileConfig2).accessedConfigs()) {
                hashSet.add(Integer.valueOf(i2));
            }
            int i3 = 0;
            for (ParamsMapEntry paramsMapEntry : list) {
                if (hashSet.contains(Integer.valueOf(paramsMapEntry.configIndex))) {
                    if (jSONArray == null || !str.equals(paramsMapEntry.configName) || i3 != paramsMapEntry.configKey) {
                        jSONArray = new JSONArray();
                        jSONObject.put(Integer.toString(paramsMapEntry.configKey) + ":" + paramsMapEntry.configName, jSONArray);
                    }
                    String mobileConfigFieldValueString = getMobileConfigFieldValueString(paramsMapEntry.isSessionless ? mobileConfig2 : mobileConfig, paramsMapEntry.getSpecifier(), false);
                    StringBuilder sb = new StringBuilder();
                    sb.append(Integer.toString(paramsMapEntry.key));
                    sb.append(": ");
                    sb.append(paramsMapEntry.paramName);
                    sb.append(": ");
                    if (mobileConfigFieldValueString.length() > 500) {
                        mobileConfigFieldValueString = mobileConfigFieldValueString.substring(0, 25) + PARAM_TRUNCATED_FLAG;
                    }
                    sb.append(mobileConfigFieldValueString);
                    jSONArray.put(sb.toString());
                    str = paramsMapEntry.configName;
                    i3 = paramsMapEntry.configKey;
                }
            }
            return jSONObject.toString(2);
        } catch (JSONException e) {
            return "Generating log in JSON format failed with error: " + e.getMessage();
        }
    }

    @Nullable
    public String getRageShakeLogAdditionalInfoString() {
        MobileConfigManagerSingletonHolder mobileConfigManagerSingletonHolder = this.mMobileConfigManagerHolderProvider.get();
        MobileConfigManagerSingletonHolder mobileConfigManagerSingletonHolder2 = this.mSessionlessMobileConfigManagerHolderProvider.get();
        if (!(mobileConfigManagerSingletonHolder == null || mobileConfigManagerSingletonHolder2 == null)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("Flags", "");
                jSONObject.put("Session-based Runtime Flags", mobileConfigManagerSingletonHolder.getConsistencyLoggingFlagsJSON());
                jSONObject.put("Sessionless Runtime Flags", mobileConfigManagerSingletonHolder2.getConsistencyLoggingFlagsJSON());
                return jSONObject.toString(2);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    @Nullable
    public String getConsistencyLoggingJson(List<ParamsMapEntry> list) {
        return getConsistencyLoggingJsonImpl(new MobileConfigContextGetter() {
            /* class com.facebook.mobileconfig.impl.MobileConfigDebugUtil.AnonymousClass1 */

            @Override // com.facebook.mobileconfig.impl.MobileConfigDebugUtil.MobileConfigContextGetter
            public MobileConfigContext getContext(ParamsMapEntry paramsMapEntry) {
                if (paramsMapEntry.isSessionless) {
                    return (MobileConfig) MobileConfigDebugUtil.this.mSessionlessMobileConfigProvider.get();
                }
                return (MobileConfig) MobileConfigDebugUtil.this.mMobileConfigProvider.get();
            }
        }, list);
    }

    @Nullable
    public static String getConsistencyLoggingJson(final MobileConfigContext mobileConfigContext, List<ParamsMapEntry> list) {
        return getConsistencyLoggingJsonImpl(new MobileConfigContextGetter() {
            /* class com.facebook.mobileconfig.impl.MobileConfigDebugUtil.AnonymousClass2 */

            @Override // com.facebook.mobileconfig.impl.MobileConfigDebugUtil.MobileConfigContextGetter
            public MobileConfigContext getContext(ParamsMapEntry paramsMapEntry) {
                return MobileConfigContext.this;
            }
        }, list);
    }

    public String getEmergencyPushPerConfigJson(List<MobileConfigEPDecisionResponse> list, ParamsMapList paramsMapList) {
        try {
            ParamsCollectionMap acquireMap = sParamsCollectionPool.acquireMap();
            ParamsCollectionMap acquireMapThenAdd = acquireMap.acquireMapThenAdd("configs");
            for (MobileConfigEPDecisionResponse mobileConfigEPDecisionResponse : list) {
                List<ParamsMapEntry> entriesOfConfig = paramsMapList.entriesOfConfig(mobileConfigEPDecisionResponse.configIndex);
                mobileConfigEPDecisionResponse.latestValues = getConsistencyLoggingJson(mobileConfigEPDecisionResponse.latestContext, entriesOfConfig);
                if (mobileConfigEPDecisionResponse.cachedContext != null) {
                    mobileConfigEPDecisionResponse.cachedValues = getConsistencyLoggingJson(mobileConfigEPDecisionResponse.cachedContext, entriesOfConfig);
                } else {
                    mobileConfigEPDecisionResponse.cachedValues = "";
                }
                mobileConfigEPDecisionResponse.serializeIntoParamsCollectionMap(acquireMapThenAdd.acquireMapThenAdd(mobileConfigEPDecisionResponse.name));
            }
            return paramsCollectionMapToString(acquireMap);
        } catch (IOException unused) {
            return "Unknown";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0155  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0160  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x018d A[SYNTHETIC] */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getConsistencyLoggingJsonImpl(com.facebook.mobileconfig.impl.MobileConfigDebugUtil.MobileConfigContextGetter r20, java.util.List<com.facebook.mobileconfig.metadata.ParamsMapEntry> r21) {
        /*
        // Method dump skipped, instructions count: 410
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfig.impl.MobileConfigDebugUtil.getConsistencyLoggingJsonImpl(com.facebook.mobileconfig.impl.MobileConfigDebugUtil$MobileConfigContextGetter, java.util.List):java.lang.String");
    }

    public static String getFlagsJson(Map<String, Object> map) {
        try {
            ParamsCollectionMap acquireMap = sParamsCollectionPool.acquireMap();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                acquireMap.add(entry.getKey(), entry.getValue().toString());
            }
            return paramsCollectionMapToString(acquireMap);
        } catch (IOException unused) {
            return "{}";
        }
    }

    public String getSimpleLogStringForParam(boolean z, boolean z2, ParamsMapEntry paramsMapEntry) {
        MobileConfigContextBase mobileConfigContextBase;
        MobileConfig mobileConfig = (z2 ? this.mSessionlessMobileConfigProvider : this.mMobileConfigProvider).get();
        if (!(mobileConfig instanceof MobileConfigFactoryImpl)) {
            return "";
        }
        if (this.mUsedForService) {
            mobileConfigContextBase = (MobileConfigContextBase) ((MobileConfigFactoryImpl) mobileConfig).latestContextForConfig(paramsMapEntry.configIndex);
        } else {
            mobileConfigContextBase = (MobileConfigContextBase) ((MobileConfigFactoryImpl) mobileConfig).contextForConfig(paramsMapEntry.configIndex);
        }
        return getMobileConfigFieldValueString(mobileConfigContextBase, paramsMapEntry.getSpecifier(), z);
    }

    public String getSimpleLogString(String str, boolean z, String str2, boolean z2, List<ParamsMapEntry> list) {
        MobileConfigContextBase mobileConfigContextBase;
        MobileConfig mobileConfig = (z2 ? this.mSessionlessMobileConfigProvider : this.mMobileConfigProvider).get();
        if (!(mobileConfig instanceof MobileConfigFactoryImpl)) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Pattern compile = str.isEmpty() ? null : Pattern.compile(str);
        for (ParamsMapEntry paramsMapEntry : list) {
            if (this.mUsedForService) {
                mobileConfigContextBase = (MobileConfigContextBase) ((MobileConfigFactoryImpl) mobileConfig).latestContextForConfig(paramsMapEntry.configIndex);
            } else {
                mobileConfigContextBase = (MobileConfigContextBase) ((MobileConfigFactoryImpl) mobileConfig).contextForConfig(paramsMapEntry.configIndex);
            }
            String format = String.format("%s.%s", paramsMapEntry.configName, paramsMapEntry.paramName);
            if (compile == null || compile.matcher(format).find()) {
                if (z) {
                    String loggingId = mobileConfigContextBase.getLoggingId(paramsMapEntry.getSpecifier());
                    if (loggingId != null) {
                        stringBuffer.append(String.format("%s%s.%s (%d) = %s (%s)\n", str2, paramsMapEntry.configName, paramsMapEntry.paramName, Long.valueOf(paramsMapEntry.getSpecifier()), getMobileConfigFieldValueString(mobileConfigContextBase, paramsMapEntry.getSpecifier(), z), loggingId));
                    } else {
                        stringBuffer.append(String.format("%s%s.%s (%d) = %s\n", str2, paramsMapEntry.configName, paramsMapEntry.paramName, Long.valueOf(paramsMapEntry.getSpecifier()), getMobileConfigFieldValueString(mobileConfigContextBase, paramsMapEntry.getSpecifier(), z)));
                    }
                } else {
                    stringBuffer.append(String.format("%s%s.%s = %s\n", str2, paramsMapEntry.configName, paramsMapEntry.paramName, getMobileConfigFieldValueString(mobileConfigContextBase, paramsMapEntry.getSpecifier(), z)));
                }
            }
        }
        return stringBuffer.toString();
    }

    static String getMobileConfigFieldValueString(MobileConfigContext mobileConfigContext, long j, boolean z) {
        Map<String, String> mobileConfigFieldValue = getMobileConfigFieldValue(mobileConfigContext, j);
        String str = mobileConfigFieldValue.get("value");
        if (MobileConfigSpecifierUtil.getParamType(j) == 3) {
            str = "\"" + str + "\"";
        }
        if (!z) {
            return str;
        }
        return str + " (Source: " + mobileConfigFieldValue.get("source") + ")";
    }

    static Map<String, String> getMobileConfigFieldValue(MobileConfigContext mobileConfigContext, long j) {
        String str;
        HashMap hashMap = new HashMap();
        Integer valueOf = Integer.valueOf(MobileConfigSpecifierUtil.getParamType(j));
        MobileConfigOptions requestForValueSource = MobileConfigOptions.WITHOUT_LOGGING.requestForValueSource();
        int intValue = valueOf.intValue();
        if (intValue == 1) {
            str = String.valueOf(mobileConfigContext.getBooleanWithOptions(j, requestForValueSource));
        } else if (intValue != 2) {
            str = intValue != 3 ? intValue != 4 ? "UNSUPPORTED TYPE" : String.valueOf(mobileConfigContext.getDoubleWithOptions(j, requestForValueSource)) : (!(mobileConfigContext instanceof MobileConfigContextV2Impl) || !MobileConfigSpecifierUtil.getRequireCallsiteDefault(j) || !((MobileConfigContextV2Impl) mobileConfigContext).shouldReturnNull(j)) ? mobileConfigContext.getStringWithOptions(j, MobileConfigOptions.WITHOUT_LOGGING) : "__fbt_null__";
        } else {
            str = String.valueOf(mobileConfigContext.getLongWithOptions(j, requestForValueSource));
        }
        hashMap.put("value", str);
        hashMap.put("source", requestForValueSource.getValueSource().toString());
        return hashMap;
    }

    private static String paramsCollectionMapToString(ParamsCollectionMap paramsCollectionMap) throws IOException {
        StringWriter stringWriter = new StringWriter();
        paramsCollectionMap.setEncoder(ParamsJsonEncoder.getInstance());
        paramsCollectionMap.encode(stringWriter);
        paramsCollectionMap.release();
        return stringWriter.toString();
    }
}
