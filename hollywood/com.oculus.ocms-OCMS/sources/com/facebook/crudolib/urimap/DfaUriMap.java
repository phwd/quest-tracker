package com.facebook.crudolib.urimap;

import android.content.Context;
import android.content.Intent;
import com.facebook.crudolib.urimap.componenthelper.ComponentHelperFactory;
import com.facebook.crudolib.urimap.runtime.UriMapHelper;

public final class DfaUriMap {
    private static final String TAG = "UriMapGen";

    public static Intent getIntent(Context context, String str) {
        return getIntent(context, str, null);
    }

    public static Intent getIntent(Context context, String str, ComponentHelperFactory componentHelperFactory) {
        int indexOf = str.indexOf(":");
        if (indexOf < 0) {
            return null;
        }
        String substring = str.substring(0, indexOf);
        int i = 1;
        while (i <= 3 && (r6 = indexOf + i) < str.length() && str.charAt(r6) == '/') {
            i++;
        }
        int i2 = indexOf + i;
        if (i2 >= str.length()) {
            return null;
        }
        int length = str.endsWith("/") ? str.length() - 1 : str.length();
        char[] cArr = new char[(length - i2)];
        str.getChars(i2, length, cArr, 0);
        char c = 65535;
        if (substring.hashCode() == 95945896 && substring.equals("dummy")) {
            c = 0;
        }
        if (c != 0) {
            return null;
        }
        return getIntentForScheme_dummy(context, str, cArr, componentHelperFactory);
    }

    private static Intent getIntentForScheme_dummy(Context context, String str, char[] cArr, ComponentHelperFactory componentHelperFactory) {
        int length = cArr.length;
        if (!UriMapHelper.substringEquals(cArr, 0, "pattern")) {
            return null;
        }
        if (7 < length) {
            Intent matchPatternWithoutQueryParams = UriMapHelper.matchPatternWithoutQueryParams(context, "com.facebook.crudolib.urimap.runtime.DummyComponentMapActivity", str, cArr, 7, componentHelperFactory, false, null);
            if (matchPatternWithoutQueryParams == null) {
                return null;
            }
            matchPatternWithoutQueryParams.putExtra(UriMapModule.KEY_ACCESS_SCOPE, "SAME_APP");
            matchPatternWithoutQueryParams.putExtra(UriMapModule.KEY_ACCESS_DOMAINS, "[]");
            matchPatternWithoutQueryParams.putExtra(UriMapModule.KEY_CALLER_SCOPE, "PUBLIC");
            return matchPatternWithoutQueryParams;
        }
        Intent returnIntentForActivity = UriMapHelper.returnIntentForActivity(context, "com.facebook.crudolib.urimap.runtime.DummyComponentMapActivity", str, null, null, null, componentHelperFactory);
        if (returnIntentForActivity != null) {
            returnIntentForActivity.putExtra(UriMapModule.KEY_ACCESS_SCOPE, "SAME_APP");
            returnIntentForActivity.putExtra(UriMapModule.KEY_ACCESS_DOMAINS, "[]");
            returnIntentForActivity.putExtra(UriMapModule.KEY_CALLER_SCOPE, "PUBLIC");
        }
        return returnIntentForActivity;
    }
}
