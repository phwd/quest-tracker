package com.facebook.secure.switchoff;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class IntentCriteria {
    private final String mEndpointClassName;
    private final IntentFilter mIntentFilter;
    private final boolean mNegativeIntentFilter;
    private ContentResolver mResolver;

    public IntentCriteria() {
        this.mResolver = null;
        this.mEndpointClassName = "";
        this.mNegativeIntentFilter = false;
        this.mIntentFilter = null;
    }

    public IntentCriteria(ContentResolver contentResolver, String str, boolean z, IntentFilter intentFilter) {
        this.mResolver = contentResolver;
        this.mEndpointClassName = str;
        this.mNegativeIntentFilter = str != null && z;
        this.mIntentFilter = intentFilter;
    }

    public boolean matchesEndpointNameAndIntentFilter(Object obj, Intent intent) {
        return matchesEndpointName(obj) && matchesIntentFilter(intent);
    }

    public boolean matchesEndpointName(Object obj) {
        String str = this.mEndpointClassName;
        return str == null || str.equals(obj.getClass().getName());
    }

    private boolean matchesIntentFilter(Intent intent) {
        IntentFilter intentFilter = this.mIntentFilter;
        if (intentFilter == null) {
            return true;
        }
        boolean z = intentFilter.match(this.mResolver, intent, false, "TAG") > 0;
        if (!this.mNegativeIntentFilter) {
            return z;
        }
        if (!z) {
            return true;
        }
        return false;
    }

    public static IntentCriteria[] parseIntentCriteria(String str, Context context) {
        try {
            return parseMultiple(context.getContentResolver(), str);
        } catch (IOException | IllegalArgumentException e) {
            Log.e("IntentCriteria", "Error parsing switch-off criteria.", e);
            return new IntentCriteria[0];
        }
    }

    public static IntentCriteria[] parseMultiple(ContentResolver contentResolver, String str) throws IOException {
        if (TextUtils.isEmpty(str)) {
            return new IntentCriteria[0];
        }
        String[] split = str.split("\\^\\^\\^");
        IntentCriteria[] intentCriteriaArr = new IntentCriteria[split.length];
        for (int i = 0; i < intentCriteriaArr.length; i++) {
            intentCriteriaArr[i] = parse(contentResolver, split[i]);
        }
        return intentCriteriaArr;
    }

    public static IntentCriteria parse(ContentResolver contentResolver, String str) throws IOException {
        String str2;
        String str3;
        if (TextUtils.isEmpty(str)) {
            return new IntentCriteria();
        }
        boolean z = false;
        int codePointAt = str.codePointAt(0);
        if (codePointAt != 33) {
            if (codePointAt == 42) {
                str3 = null;
                str2 = str.substring(1);
                return new IntentCriteria(contentResolver, str3, z, createIntentFilter(str2));
            } else if (codePointAt != 58) {
                throw new IllegalArgumentException("Criteria specification is not valid");
            }
        }
        int indexOf = str.indexOf(codePointAt, 1);
        if (indexOf >= 0) {
            String substring = str.substring(1, indexOf);
            str2 = str.substring(indexOf + 1);
            if (codePointAt == 33) {
                z = true;
            }
            str3 = substring;
            return new IntentCriteria(contentResolver, str3, z, createIntentFilter(str2));
        }
        throw new IllegalArgumentException("Criteria specification is not valid");
    }

    private static IntentFilter createIntentFilter(String str) throws IOException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        IntentFilter intentFilter = new IntentFilter();
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            XmlPullParser newPullParser = newInstance.newPullParser();
            newPullParser.setInput(new StringReader(str));
            intentFilter.readFromXml(newPullParser);
            return intentFilter;
        } catch (XmlPullParserException e) {
            throw new IOException("Something went wrong with the parser", e);
        }
    }
}
