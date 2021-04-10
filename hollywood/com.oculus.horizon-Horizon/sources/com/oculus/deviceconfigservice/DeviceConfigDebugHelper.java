package com.oculus.deviceconfigservice;

import X.AnonymousClass006;
import X.AnonymousClass0NO;
import X.AnonymousClass0Rj;
import X.AnonymousClass1am;
import X.AnonymousClass1ao;
import X.AnonymousClass1b4;
import X.AnonymousClass1b5;
import X.AnonymousClass1bA;
import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigservice.MobileConfigAppAwareAccessorDecorator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigDebugHelper {
    public static final String CONFIG_PARAM_INNER_SEPARATOR = ":";
    public static final int CONFIG_PARAM_NAME_INDEX = AnonymousClass1b5.CONFIG_PARAM_NAME.ordinal();
    public static final String CONFIG_PARAM_SEPARATOR = ",";
    public static final int DEBUG_CACHED_VALUE_INDEX = AnonymousClass1b4.CACHED_VALUE.ordinal();
    public static final int DEBUG_CONFIG_PARAM_NAME_INDEX = AnonymousClass1b4.CONFIG_PARAM_NAME.ordinal();
    public static final int DEBUG_IS_SESSIONLESS_INDEX = AnonymousClass1b4.IS_SESSIONLESS.ordinal();
    public static final int DEBUG_LOGGING_ID_INDEX = AnonymousClass1b4.LOGGING_ID.ordinal();
    public static final int DEBUG_MC_VALUE_SOURCE_INDEX = AnonymousClass1b4.MC_VALUE_SOURCE.ordinal();
    public static final int DEBUG_OVERRIDDEN_VALUE_INDEX = AnonymousClass1b4.OVERRIDDEN_VALUE.ordinal();
    public static final int DEBUG_TYPE_INDEX = AnonymousClass1b4.TYPE.ordinal();
    public static final int DEBUG_VALUE_INDEX = AnonymousClass1b4.VALUE.ordinal();
    public static final String DEVICE_CONFIG_DEBUG_DIRECTORY = "device_config_debug";
    public static final String DUMP_FILE_NAME = "device_config_debug_values.txt";
    public static final String DUMP_FILE_VERSION_1 = "dc_dump:v1";
    public static final String EMPTY_CONFIG_PARAM = "__DEVICE_CONFIG_EMPTY_VALUE__";
    public static final String EMPTY_CONFIG_PARAM_LABEL = "N/A";
    public static final int IS_SESSIONLESS_INDEX = AnonymousClass1b5.IS_SESSIONLESS.ordinal();
    public static final int LOGGING_ID_INDEX = AnonymousClass1b5.LOGGING_ID.ordinal();
    public static final int MAX_MESSAGE_LENGTH = 4000;
    public static final String TAG = "DeviceConfigDebugHelper";
    public static final int TYPE_INDEX = AnonymousClass1b5.TYPE.ordinal();
    public static final int VALUE_INDEX = AnonymousClass1b5.VALUE.ordinal();
    public static final int VALUE_SOURCE_INDEX = AnonymousClass1b5.SOURCE.ordinal();
    public static DeviceConfigDebugHelper sInstance;
    public static Boolean sIsAccessAllowed;
    public final Map<String, MobileConfigAppAwareAccessorDecorator.ValueInfo<Boolean>> mCurrentBooleanValues;
    public final Map<String, MobileConfigAppAwareAccessorDecorator.ValueInfo<Double>> mCurrentDoubleValues;
    public final Map<String, MobileConfigAppAwareAccessorDecorator.ValueInfo<Long>> mCurrentLongValues;
    public final Map<String, MobileConfigAppAwareAccessorDecorator.ValueInfo<String>> mCurrentStringValues;
    public final AnonymousClass1ao mMobileConfigAccessor;
    public final Map<String, Boolean> mOverriddenBooleanValues = new HashMap();
    public final Map<String, Double> mOverriddenDoubleValues = new HashMap();
    public final Map<String, Long> mOverriddenLongValues = new HashMap();
    public final Map<String, String> mOverriddenStringValues = new HashMap();

    public final synchronized List<String[]> A05(@Nullable String str, @Nullable String str2) {
        ArrayList arrayList;
        List<String[]> A02;
        int i;
        int i2;
        String str3;
        String str4;
        int i3;
        String str5;
        String str6;
        Type type;
        String str7;
        arrayList = new ArrayList();
        AnonymousClass1ao r3 = this.mMobileConfigAccessor;
        synchronized (r3) {
            ArrayList arrayList2 = new ArrayList();
            for (AnonymousClass1am r0 : r3.A01.values()) {
                arrayList2.add(AnonymousClass006.A07(r0.A06, ":", r0.A07));
            }
            A02 = r3.A02((String[]) arrayList2.toArray(new String[0]));
        }
        for (String[] strArr : A02) {
            String[] strArr2 = new String[AnonymousClass1b4.values().length];
            String str8 = strArr[CONFIG_PARAM_NAME_INDEX];
            if (str != null) {
                String[] split = str8.split(":", 2);
                if (split.length != 2) {
                    AnonymousClass0NO.A0E(TAG, "Unexpected config param name found: %s. It should be in format of <config name>:<param name>", str8);
                } else if (str.equals(split[0])) {
                    if (str2 != null && !str2.equals(split[1])) {
                    }
                }
            }
            String str9 = strArr[TYPE_INDEX];
            try {
                i = Integer.parseInt(str9);
            } catch (NumberFormatException unused) {
                i = 0;
            }
            strArr2[DEBUG_CONFIG_PARAM_NAME_INDEX] = str8;
            strArr2[DEBUG_TYPE_INDEX] = str9;
            strArr2[DEBUG_VALUE_INDEX] = strArr[VALUE_INDEX];
            strArr2[DEBUG_LOGGING_ID_INDEX] = strArr[LOGGING_ID_INDEX];
            strArr2[DEBUG_IS_SESSIONLESS_INDEX] = strArr[IS_SESSIONLESS_INDEX];
            strArr2[DEBUG_MC_VALUE_SOURCE_INDEX] = strArr[VALUE_SOURCE_INDEX];
            if (i != 1) {
                if (i == 2) {
                    MobileConfigAppAwareAccessorDecorator.ValueInfo<Long> valueInfo = this.mCurrentLongValues.get(str8);
                    int i4 = DEBUG_CACHED_VALUE_INDEX;
                    if (valueInfo != null) {
                        Type type2 = valueInfo.Value;
                        if (type2 != null) {
                            str6 = Long.toString(type2.longValue());
                        } else {
                            str6 = "__DEVICE_CONFIG_EMPTY_VALUE__";
                        }
                    } else {
                        str6 = "__DEVICE_CONFIG_EMPTY_VALUE__";
                    }
                    strArr2[i4] = str6;
                    Long l = this.mOverriddenLongValues.get(str8);
                    i3 = DEBUG_OVERRIDDEN_VALUE_INDEX;
                    if (l != null) {
                        str5 = Long.toString(l.longValue());
                    } else {
                        str5 = "__DEVICE_CONFIG_EMPTY_VALUE__";
                    }
                } else if (i == 3) {
                    MobileConfigAppAwareAccessorDecorator.ValueInfo<String> valueInfo2 = this.mCurrentStringValues.get(str8);
                    int i5 = DEBUG_CACHED_VALUE_INDEX;
                    if (valueInfo2 != null) {
                        type = valueInfo2.Value;
                        if (type == null) {
                            type = "__DEVICE_CONFIG_EMPTY_VALUE__";
                        }
                    } else {
                        type = "__DEVICE_CONFIG_EMPTY_VALUE__";
                    }
                    strArr2[i5] = type;
                    str3 = this.mOverriddenStringValues.get(str8);
                    i2 = DEBUG_OVERRIDDEN_VALUE_INDEX;
                    if (str3 == null) {
                        str3 = "__DEVICE_CONFIG_EMPTY_VALUE__";
                    }
                } else if (i != 4) {
                    AnonymousClass0NO.A0E(TAG, "Unknown type %s. Will return latest value.", str9);
                    arrayList.add(strArr2);
                } else {
                    MobileConfigAppAwareAccessorDecorator.ValueInfo<Double> valueInfo3 = this.mCurrentDoubleValues.get(str8);
                    int i6 = DEBUG_CACHED_VALUE_INDEX;
                    if (valueInfo3 != null) {
                        Type type3 = valueInfo3.Value;
                        if (type3 != null) {
                            str7 = Double.toString(type3.doubleValue());
                        } else {
                            str7 = "__DEVICE_CONFIG_EMPTY_VALUE__";
                        }
                    } else {
                        str7 = "__DEVICE_CONFIG_EMPTY_VALUE__";
                    }
                    strArr2[i6] = str7;
                    Double d = this.mOverriddenDoubleValues.get(str8);
                    i3 = DEBUG_OVERRIDDEN_VALUE_INDEX;
                    if (d != null) {
                        str5 = Double.toString(d.doubleValue());
                    } else {
                        str5 = "__DEVICE_CONFIG_EMPTY_VALUE__";
                    }
                }
                strArr2[i3] = str5;
                arrayList.add(strArr2);
            } else {
                MobileConfigAppAwareAccessorDecorator.ValueInfo<Boolean> valueInfo4 = this.mCurrentBooleanValues.get(str8);
                int i7 = DEBUG_CACHED_VALUE_INDEX;
                if (valueInfo4 != null) {
                    Type type4 = valueInfo4.Value;
                    if (type4 != null) {
                        str4 = Boolean.toString(type4.booleanValue());
                    } else {
                        str4 = "__DEVICE_CONFIG_EMPTY_VALUE__";
                    }
                } else {
                    str4 = "__DEVICE_CONFIG_EMPTY_VALUE__";
                }
                strArr2[i7] = str4;
                Boolean bool = this.mOverriddenBooleanValues.get(str8);
                i2 = DEBUG_OVERRIDDEN_VALUE_INDEX;
                if (bool != null) {
                    str3 = Boolean.toString(bool.booleanValue());
                } else {
                    str3 = "__DEVICE_CONFIG_EMPTY_VALUE__";
                }
            }
            strArr2[i2] = str3;
            arrayList.add(strArr2);
        }
        return arrayList;
    }

    public final synchronized void A06(@Nullable String str, @Nullable String str2) {
        for (String A01 = A01(this, str, str2, true); A01.length() > 4000; A01 = A01.substring(4000)) {
            A01.substring(0, 4000);
        }
    }

    @Nullable
    public static synchronized DeviceConfigDebugHelper A00() {
        DeviceConfigDebugHelper deviceConfigDebugHelper;
        synchronized (DeviceConfigDebugHelper.class) {
            Boolean bool = sIsAccessAllowed;
            if (bool == null || bool.booleanValue()) {
                if (sInstance == null) {
                    AnonymousClass0NO.A08(TAG, "helper instance is not ready. Wait until mobile config service is initiated.");
                }
                deviceConfigDebugHelper = sInstance;
            } else {
                deviceConfigDebugHelper = null;
            }
        }
        return deviceConfigDebugHelper;
    }

    public static String A01(@Nullable DeviceConfigDebugHelper deviceConfigDebugHelper, @Nullable String str, String str2, boolean z) {
        AnonymousClass0Rj r9;
        int i;
        String str3;
        StringBuilder sb = new StringBuilder();
        for (String[] strArr : deviceConfigDebugHelper.A05(str, str2)) {
            String str4 = strArr[DEBUG_CONFIG_PARAM_NAME_INDEX];
            String str5 = strArr[DEBUG_OVERRIDDEN_VALUE_INDEX];
            String str6 = strArr[DEBUG_CACHED_VALUE_INDEX];
            String str7 = strArr[DEBUG_VALUE_INDEX];
            String str8 = strArr[DEBUG_MC_VALUE_SOURCE_INDEX];
            try {
                r9 = AnonymousClass0Rj.fromInt(Integer.parseInt(str8));
            } catch (NumberFormatException unused) {
                AnonymousClass0NO.A0E(TAG, "Failed to parse Mobile Config value source for %s: %s", str4, str8);
                r9 = AnonymousClass0Rj.UNKNOWN;
            }
            String str9 = str5;
            DeviceConfigValueSource deviceConfigValueSource = DeviceConfigValueSource.MOBILE_CONFIG_SERVICE_VALUE;
            if (str5 != null && !"__DEVICE_CONFIG_EMPTY_VALUE__".equals(str5)) {
                deviceConfigValueSource = DeviceConfigValueSource.OVERRIDDEN_VALUE;
            } else if (str6 == null || "__DEVICE_CONFIG_EMPTY_VALUE__".equals(str6)) {
                str9 = str7;
            } else {
                deviceConfigValueSource = DeviceConfigValueSource.DEVICE_WIDE_CACHE_VALUE;
                str9 = str6;
            }
            DeviceConfigOverriddenValueInfo deviceConfigOverriddenValueInfo = new DeviceConfigOverriddenValueInfo(str4, str9, deviceConfigValueSource);
            sb.append(AnonymousClass006.A07(str4, ":", deviceConfigOverriddenValueInfo.mValue.toString().replace("__DEVICE_CONFIG_EMPTY_VALUE__", "N/A")));
            if (z) {
                sb.append(AnonymousClass006.A05(",VS:", deviceConfigOverriddenValueInfo.mValueSource.toString()));
                StringBuilder sb2 = new StringBuilder(",MCVS:");
                sb2.append(r9);
                sb.append(sb2.toString());
                try {
                    i = Integer.parseInt(strArr[DEBUG_TYPE_INDEX]);
                } catch (NumberFormatException unused2) {
                    i = 0;
                }
                if (i == 1) {
                    str3 = "BOOLEAN";
                } else if (i == 2) {
                    str3 = "LONG";
                } else if (i == 3) {
                    str3 = "STRING";
                } else if (i != 4) {
                    str3 = "UNKNOWN";
                } else {
                    str3 = "DOUBLE";
                }
                sb.append(AnonymousClass006.A05(",T:", str3));
                sb.append(AnonymousClass006.A05(",O:", str5.replace("__DEVICE_CONFIG_EMPTY_VALUE__", "N/A")));
                sb.append(AnonymousClass006.A05(",DC:", str6.replace("__DEVICE_CONFIG_EMPTY_VALUE__", "N/A")));
                sb.append(AnonymousClass006.A05(",MC:", str7.replace("__DEVICE_CONFIG_EMPTY_VALUE__", "N/A")));
                sb.append(AnonymousClass006.A05(",LID:", strArr[DEBUG_LOGGING_ID_INDEX]));
                sb.append(AnonymousClass006.A05(",SL:", strArr[DEBUG_IS_SESSIONLESS_INDEX]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static <Type> Set<String> A02(Map<String, Type> map) {
        HashSet hashSet = new HashSet();
        for (String str : map.keySet()) {
            int indexOf = str.indexOf(":");
            if (indexOf >= 0) {
                hashSet.add(str.substring(0, indexOf));
            }
        }
        return hashSet;
    }

    public static void A03(DeviceConfigDebugHelper deviceConfigDebugHelper, Context context) {
        if (!deviceConfigDebugHelper.mOverriddenBooleanValues.isEmpty() || !deviceConfigDebugHelper.mOverriddenDoubleValues.isEmpty() || !deviceConfigDebugHelper.mOverriddenLongValues.isEmpty() || !deviceConfigDebugHelper.mOverriddenStringValues.isEmpty()) {
            for (Map.Entry<String, Boolean> entry : deviceConfigDebugHelper.mOverriddenBooleanValues.entrySet()) {
                DeviceConfigOverriddenValueInfo<Boolean> deviceConfigOverriddenValueInfo = new DeviceConfigOverriddenValueInfo<>(entry.getKey(), entry.getValue(), DeviceConfigValueSource.OVERRIDDEN_VALUE);
                synchronized (DeviceConfigOverrideUtil.class) {
                    DeviceConfigOverrideUtil.mBooleanValueInfos.add(deviceConfigOverriddenValueInfo);
                }
            }
            for (Map.Entry<String, Double> entry2 : deviceConfigDebugHelper.mOverriddenDoubleValues.entrySet()) {
                DeviceConfigOverriddenValueInfo<Double> deviceConfigOverriddenValueInfo2 = new DeviceConfigOverriddenValueInfo<>(entry2.getKey(), entry2.getValue(), DeviceConfigValueSource.OVERRIDDEN_VALUE);
                synchronized (DeviceConfigOverrideUtil.class) {
                    DeviceConfigOverrideUtil.mDoubleValueInfos.add(deviceConfigOverriddenValueInfo2);
                }
            }
            for (Map.Entry<String, Long> entry3 : deviceConfigDebugHelper.mOverriddenLongValues.entrySet()) {
                DeviceConfigOverriddenValueInfo<Long> deviceConfigOverriddenValueInfo3 = new DeviceConfigOverriddenValueInfo<>(entry3.getKey(), entry3.getValue(), DeviceConfigValueSource.OVERRIDDEN_VALUE);
                synchronized (DeviceConfigOverrideUtil.class) {
                    DeviceConfigOverrideUtil.mLongValueInfos.add(deviceConfigOverriddenValueInfo3);
                }
            }
            for (Map.Entry<String, String> entry4 : deviceConfigDebugHelper.mOverriddenStringValues.entrySet()) {
                DeviceConfigOverriddenValueInfo<String> deviceConfigOverriddenValueInfo4 = new DeviceConfigOverriddenValueInfo<>(entry4.getKey(), entry4.getValue(), DeviceConfigValueSource.OVERRIDDEN_VALUE);
                synchronized (DeviceConfigOverrideUtil.class) {
                    DeviceConfigOverrideUtil.mStringValueInfos.add(deviceConfigOverriddenValueInfo4);
                }
            }
            synchronized (DeviceConfigOverrideUtil.class) {
                JSONObject jSONObject = new JSONObject();
                ArrayList<DeviceConfigOverriddenValueInfo<Boolean>> arrayList = DeviceConfigOverrideUtil.mBooleanValueInfos;
                if (!arrayList.isEmpty()) {
                    jSONObject = DeviceConfigOverrideUtil.A00(arrayList, jSONObject);
                    DeviceConfigOverrideUtil.A01(context, DeviceConfigOverrideUtil.PREF_BOOLEAN_VALUES, arrayList);
                }
                ArrayList<DeviceConfigOverriddenValueInfo<Double>> arrayList2 = DeviceConfigOverrideUtil.mDoubleValueInfos;
                if (!arrayList2.isEmpty()) {
                    jSONObject = DeviceConfigOverrideUtil.A00(arrayList2, jSONObject);
                    DeviceConfigOverrideUtil.A01(context, DeviceConfigOverrideUtil.PREF_DOUBLE_VALUES, arrayList2);
                }
                ArrayList<DeviceConfigOverriddenValueInfo<Long>> arrayList3 = DeviceConfigOverrideUtil.mLongValueInfos;
                if (!arrayList3.isEmpty()) {
                    jSONObject = DeviceConfigOverrideUtil.A00(arrayList3, jSONObject);
                    DeviceConfigOverrideUtil.A01(context, DeviceConfigOverrideUtil.PREF_LONG_VALUES, arrayList3);
                }
                ArrayList<DeviceConfigOverriddenValueInfo<String>> arrayList4 = DeviceConfigOverrideUtil.mStringValueInfos;
                if (!arrayList4.isEmpty()) {
                    jSONObject = DeviceConfigOverrideUtil.A00(arrayList4, jSONObject);
                    DeviceConfigOverrideUtil.A01(context, DeviceConfigOverrideUtil.PREF_STRING_VALUES, arrayList4);
                }
                DeviceConfigSharedPreferencesNames deviceConfigSharedPreferencesNames = DeviceConfigSharedPreferencesNames.VALUE_SOURCE;
                context.getSharedPreferences(deviceConfigSharedPreferencesNames.toString(), 0).edit().putString(DeviceConfigOverrideUtil.PREF_VALUE_SOURCES, jSONObject.toString()).commit();
            }
            return;
        }
        synchronized (DeviceConfigOverrideUtil.class) {
            DeviceConfigOverrideUtil.mBooleanValueInfos.clear();
            DeviceConfigOverrideUtil.mDoubleValueInfos.clear();
            DeviceConfigOverrideUtil.mLongValueInfos.clear();
            DeviceConfigOverrideUtil.mStringValueInfos.clear();
            context.getSharedPreferences(DeviceConfigSharedPreferencesNames.OVERRIDDEN_VALUE.toString(), 0).edit().clear().commit();
            context.getSharedPreferences(DeviceConfigSharedPreferencesNames.VALUE_SOURCE.toString(), 0).edit().clear().commit();
        }
    }

    public final synchronized List<String[]> A04(Context context, String[] strArr) {
        ArrayList arrayList;
        String str;
        String str2;
        Object[] objArr;
        AnonymousClass1am r0;
        arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        for (String str3 : strArr) {
            String[] split = str3.split(":", 3);
            String str4 = "__DEVICE_CONFIG_EMPTY_VALUE__";
            int length = split.length;
            if (length == 3) {
                str4 = split[2].replace("\\,", ",");
            } else if (length != 2) {
                str = TAG;
                str2 = "Invalid overridden value received \"%s\".";
                objArr = new Object[]{str3};
                AnonymousClass0NO.A0E(str, str2, objArr);
            }
            String str5 = split[0];
            String A07 = AnonymousClass006.A07(str5, ":", split[1]);
            AnonymousClass1ao r1 = this.mMobileConfigAccessor;
            synchronized (r1) {
                r0 = r1.A01.get(A07);
            }
            if (r0 == null) {
                str = TAG;
                str2 = "Did not find the param name %s received in mobile config service.";
                objArr = new Object[]{A07};
            } else {
                hashSet.add(str5);
                int i = r0.A04;
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i == 4) {
                                    if (str4.equals(str4)) {
                                        this.mOverriddenDoubleValues.remove(A07);
                                    } else {
                                        this.mOverriddenDoubleValues.put(A07, Double.valueOf(Double.parseDouble(str4)));
                                    }
                                }
                            } else if (str4.equals(str4)) {
                                this.mOverriddenStringValues.remove(A07);
                            } else {
                                this.mOverriddenStringValues.put(A07, str4);
                            }
                        } else if (str4.equals(str4)) {
                            this.mOverriddenLongValues.remove(A07);
                        } else {
                            this.mOverriddenLongValues.put(A07, Long.valueOf(Long.parseLong(str4)));
                        }
                    } else if (str4.equals(str4)) {
                        this.mOverriddenBooleanValues.remove(A07);
                    } else {
                        this.mOverriddenBooleanValues.put(A07, Boolean.valueOf(Boolean.parseBoolean(str4)));
                    }
                    String[] strArr2 = new String[AnonymousClass1bA.values().length];
                    strArr2[AnonymousClass1bA.CONFIG_PARAM_NAME.ordinal()] = A07;
                    strArr2[AnonymousClass1bA.OVERRIDDEN_VALUE.ordinal()] = str4;
                    arrayList.add(strArr2);
                } else {
                    str = TAG;
                    str2 = "Unsupported config param type for %s. Will not override anything";
                    objArr = new Object[]{A07};
                }
            }
            AnonymousClass0NO.A0E(str, str2, objArr);
        }
        A03(this, context);
        if (!hashSet.isEmpty()) {
            this.mMobileConfigAccessor.A09.onConfigChanged((String[]) hashSet.toArray(new String[hashSet.size()]));
        }
        return arrayList;
    }

    public DeviceConfigDebugHelper(AnonymousClass1ao r2, Map<String, MobileConfigAppAwareAccessorDecorator.ValueInfo<Boolean>> map, Map<String, MobileConfigAppAwareAccessorDecorator.ValueInfo<Double>> map2, Map<String, MobileConfigAppAwareAccessorDecorator.ValueInfo<Long>> map3, Map<String, MobileConfigAppAwareAccessorDecorator.ValueInfo<String>> map4) {
        this.mMobileConfigAccessor = r2;
        this.mCurrentBooleanValues = map;
        this.mCurrentDoubleValues = map2;
        this.mCurrentLongValues = map3;
        this.mCurrentStringValues = map4;
    }
}
