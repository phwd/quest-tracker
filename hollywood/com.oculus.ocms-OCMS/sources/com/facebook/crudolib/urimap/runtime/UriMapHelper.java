package com.facebook.crudolib.urimap.runtime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.common.componentmap.ComponentMap;
import com.facebook.crudolib.urimap.UriMapModule;
import com.facebook.crudolib.urimap.componenthelper.ComponentHelper;
import com.facebook.crudolib.urimap.componenthelper.ComponentHelperFactory;
import com.facebook.debug.log.BLog;
import com.facebook.ipc.activity.ActivityConstants;
import com.facebook.ipc.activity.BaseActivityConstants;
import com.facebook.voltron.common.AppModuleConstants;
import com.facebook.voltron.metadata.VoltronModuleResolver;
import com.oculus.util.constants.OculusConstants;
import javax.annotation.Nullable;

public final class UriMapHelper {
    private static final String TAG = "UriMapHelper";

    private static boolean hasOnlyAllowedQueryParams(int i, int i2) {
        return (i2 & i) == i;
    }

    private static boolean hasRequiredQueryParams(int i, int i2) {
        return (i2 | i) == i;
    }

    @Nullable
    public static Boolean templateStringToBoolean(String str) {
        if (str == null) {
            return null;
        }
        if (str.equalsIgnoreCase("true") || str.equals(ActivityConstants.Extras.WATCH_FEED_INJECTION)) {
            return true;
        }
        if (str.equalsIgnoreCase("false") || str.equals(OculusConstants.DEFAULT_ENTERPRISE_USER_ID)) {
            return false;
        }
        return null;
    }

    @Nullable
    public static Long templateStringToNumber(String str) {
        if (str == null) {
            return null;
        }
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @SuppressLint({"BadMethodUse"})
    public static boolean substringEquals(char[] cArr, int i, String str) {
        int length = cArr.length;
        int length2 = str.length();
        if (length <= i) {
            return false;
        }
        int i2 = 0;
        while (i < length && i2 < length2) {
            if (cArr[i] != str.charAt(i2)) {
                return false;
            }
            i++;
            i2++;
        }
        if (length2 <= i2) {
            return true;
        }
        return false;
    }

    @Nullable
    public static int[] parseTemplate(char[] cArr, int i, String str, String str2, String str3, Bundle bundle) {
        int i2;
        Boolean templateStringToBoolean;
        char c;
        int length = cArr.length;
        long j = 0;
        boolean z = str2 != null;
        int i3 = i;
        while (length > i3 && (c = cArr[i3]) != '/' && c != '?') {
            if (z) {
                int digit = Character.digit(c, 10);
                if (digit != -1) {
                    j = (j * 10) + ((long) digit);
                } else {
                    z = false;
                }
            }
            i3++;
        }
        if (i3 == -1) {
            return null;
        }
        String str4 = new String(cArr, i, i3 - i);
        if (str == null || (templateStringToBoolean = templateStringToBoolean(str4)) == null) {
            i2 = -1;
        } else {
            bundle.putBoolean(str, templateStringToBoolean.booleanValue());
            i2 = 1;
        }
        if (i2 == -1 && z) {
            bundle.putLong(str2, j);
            i2 = 2;
        }
        if (i2 == -1 && str3 != null) {
            bundle.putString(str3, str4);
            i2 = 3;
        }
        if (i2 == -1) {
            return null;
        }
        return new int[]{i3, i2};
    }

    public static void putTemplateString(char[] cArr, int i, int i2, String str, Bundle bundle) {
        bundle.putString(str, new String(cArr, i, i2 - i));
    }

    @Nullable
    public static Intent returnIntentForActivity(Context context, String str, String str2, @Nullable String str3, @Nullable Bundle bundle, @Nullable Bundle bundle2, @Nullable ComponentHelperFactory componentHelperFactory) {
        return maybeTransformIntent(returnIntentForActivity(context, str, str2, str3, bundle, bundle2), str, 248, componentHelperFactory);
    }

    @Nullable
    private static Intent returnIntentForActivity(Context context, String str, String str2, @Nullable String str3, @Nullable Bundle bundle, @Nullable Bundle bundle2) {
        Intent className = new Intent().setClassName(context, str);
        if (bundle != null) {
            className.putExtras(bundle);
        }
        if (str3 != null) {
            if (bundle != null) {
                for (String str4 : bundle.keySet()) {
                    str3 = str3.replace("<" + str4 + ">", bundle.get(str4).toString());
                }
            }
            if (bundle2 != null) {
                boolean z = !str3.contains("?");
                StringBuilder sb = new StringBuilder(str3);
                for (String str5 : bundle2.keySet()) {
                    Object obj = bundle2.get(str5);
                    if (z) {
                        sb.append('?');
                        z = false;
                    } else {
                        sb.append('&');
                    }
                    sb.append(str5);
                    sb.append('=');
                    sb.append(obj);
                }
                str3 = sb.toString();
            }
            className.putExtra(UriMapModule.KEY_TEMPLATED_STRING, str3);
        }
        return className.putExtra("key_uri", str2);
    }

    @Nullable
    public static Intent returnIntentForFragment(Context context, int i, int i2, String str, @Nullable String str2, @Nullable Bundle bundle, @Nullable Bundle bundle2, @Nullable ComponentHelperFactory componentHelperFactory) {
        String nameForType = ComponentMap.getNameForType(i);
        if (nameForType == null) {
            BLog.wtf(TAG, "ComponentMap.getNameForType(%d) returned null", Integer.valueOf(i));
            return null;
        }
        Intent returnIntentForActivity = returnIntentForActivity(context, nameForType, str, str2, bundle, bundle2);
        if (returnIntentForActivity != null) {
            returnIntentForActivity.putExtra(BaseActivityConstants.Extras.TARGET_FRAGMENT, i2);
        }
        return maybeTransformIntent(returnIntentForActivity, null, i2, componentHelperFactory);
    }

    @Nullable
    private static Intent maybeTransformIntent(@Nullable Intent intent, @Nullable String str, int i, @Nullable ComponentHelperFactory componentHelperFactory) {
        String str2;
        ComponentHelper componentHelper;
        if (intent == null || componentHelperFactory == null) {
            return intent;
        }
        if (str != null) {
            str2 = componentHelperFactory.getHelperNameForActivity(str);
        } else {
            str2 = componentHelperFactory.getHelperNameForFragment(i);
        }
        if (VoltronModuleResolver.getModuleNameForClass(str2) != null) {
            return intent.putExtra(AppModuleConstants.EXTRA_COMPONENT_HELPER_NAME, str2);
        }
        if (str != null) {
            componentHelper = componentHelperFactory.getHelperForActivity(str);
        } else {
            componentHelper = componentHelperFactory.getHelperForFragment(i);
        }
        if (componentHelper == null) {
            return intent;
        }
        if (componentHelper.isEnabled()) {
            return componentHelper.transformIntent(intent);
        }
        return null;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Nullable
    public static Bundle parseIsTrackingKey(@Nullable Bundle bundle, String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1483898950:
                if (str.equals("__tn__")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -983972142:
                if (str.equals("pn_ref")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 2931858:
                if (str.equals("_ft_")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3151469:
                if (str.equals("fref")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0 && c != 1 && c != 2 && c != 3) {
            return null;
        }
        if (bundle == null) {
            bundle = new Bundle(2);
        }
        bundle.putString(str, str2);
        return bundle;
    }

    @Nullable
    public static Intent matchPatternWithoutQueryParams(Context context, String str, String str2, char[] cArr, int i, ComponentHelperFactory componentHelperFactory, boolean z, @Nullable String str3) {
        Object matchQueryParamsForPatternWithoutQueryParams = matchQueryParamsForPatternWithoutQueryParams(cArr, i, z);
        if (Boolean.FALSE.equals(matchQueryParamsForPatternWithoutQueryParams)) {
            return null;
        }
        return returnIntentForActivity(context, str, str2, str3, null, (Bundle) matchQueryParamsForPatternWithoutQueryParams, componentHelperFactory);
    }

    @Nullable
    public static Intent matchPatternWithoutQueryParams(Context context, int i, int i2, String str, char[] cArr, int i3, ComponentHelperFactory componentHelperFactory, boolean z, @Nullable String str2) {
        Object matchQueryParamsForPatternWithoutQueryParams = matchQueryParamsForPatternWithoutQueryParams(cArr, i3, z);
        if (Boolean.FALSE.equals(matchQueryParamsForPatternWithoutQueryParams)) {
            return null;
        }
        return returnIntentForFragment(context, i2, i, str, str2, null, (Bundle) matchQueryParamsForPatternWithoutQueryParams, componentHelperFactory);
    }

    public static boolean canAcceptQueryParams(int i, int i2, int i3, boolean z, boolean z2) {
        return hasRequiredQueryParams(i, i2) && (z2 || hasOnlyAllowedQueryParams(i, i3)) && (z2 || !z);
    }

    @Nullable
    private static Object matchQueryParamsForPatternWithoutQueryParams(char[] cArr, int i, boolean z) {
        int i2;
        String str;
        int findQueryParamStart = findQueryParamStart(cArr, i);
        if (findQueryParamStart < 0) {
            return Boolean.FALSE;
        }
        int length = cArr.length - findQueryParamStart;
        String str2 = new String(cArr, findQueryParamStart, length);
        int i3 = 0;
        Bundle bundle = null;
        boolean z2 = false;
        while (i3 < length) {
            int indexOf = str2.indexOf(61, i3);
            if (indexOf < 0) {
                return Boolean.FALSE;
            }
            String substring = str2.substring(i3, indexOf);
            int indexOf2 = str2.indexOf(38, indexOf);
            if (indexOf2 > 0) {
                str = str2.substring(indexOf + 1, indexOf2);
                i2 = indexOf2 + 1;
            } else {
                str = str2.substring(indexOf + 1);
                i2 = str2.length();
            }
            Bundle parseIsTrackingKey = parseIsTrackingKey(bundle, substring, str);
            if (parseIsTrackingKey != null) {
                bundle = parseIsTrackingKey;
            } else {
                z2 = true;
            }
            i3 = i2;
        }
        return (!z2 || z) ? bundle : Boolean.FALSE;
    }

    private static int findQueryParamStart(char[] cArr, int i) {
        int i2 = i + 1;
        if (i2 < cArr.length && cArr[i] == '/' && cArr[i2] == '?') {
            return i + 2;
        }
        if (i >= cArr.length || cArr[i] != '?') {
            return -1;
        }
        return i2;
    }
}
