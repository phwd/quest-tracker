package com.facebook.secure.switchoff;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.os.Version;
import java.io.IOException;
import java.io.StringReader;
import javax.annotation.Nullable;
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

    public IntentCriteria(ContentResolver resolver, String endpointClassName, boolean negativeIntentFilter, IntentFilter filter) {
        this.mResolver = resolver;
        this.mEndpointClassName = endpointClassName;
        this.mNegativeIntentFilter = endpointClassName != null && negativeIntentFilter;
        this.mIntentFilter = filter;
    }

    public boolean matchesEndpointNameAndIntentFilter(Object endpoint, Intent intent) {
        return matchesEndpointName(endpoint) && matchesIntentFilter(intent);
    }

    public boolean matchesEndpointName(Object endpoint) {
        return this.mEndpointClassName == null || this.mEndpointClassName.equals(endpoint.getClass().getName());
    }

    private boolean matchesIntentFilter(Intent intent) {
        boolean matches;
        if (this.mIntentFilter == null) {
            return true;
        }
        if (this.mIntentFilter.match(this.mResolver, intent, false, "TAG") > 0) {
            matches = true;
        } else {
            matches = false;
        }
        if (this.mNegativeIntentFilter) {
            return !matches;
        }
        return matches;
    }

    public static IntentCriteria[] parseIntentCriteria(String criteriaString, Context context) {
        try {
            return parseMultiple(context.getContentResolver(), criteriaString);
        } catch (IOException | IllegalArgumentException e) {
            Log.e("IntentCriteria", "Error parsing switch-off criteria.", e);
            return new IntentCriteria[0];
        }
    }

    public static IntentCriteria[] parseMultiple(ContentResolver resolver, String value) throws IOException {
        if (TextUtils.isEmpty(value)) {
            return new IntentCriteria[0];
        }
        String[] criterias = value.split("\\^\\^\\^");
        IntentCriteria[] result = new IntentCriteria[criterias.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = parse(resolver, criterias[i]);
        }
        return result;
    }

    public static IntentCriteria parse(ContentResolver resolver, String value) throws IOException {
        String endpointClassName;
        String intentFilterString;
        boolean negative = true;
        if (TextUtils.isEmpty(value)) {
            return new IntentCriteria();
        }
        int type = value.codePointAt(0);
        switch (type) {
            case Version.VERSION_33 /*{ENCODED_INT: 33}*/:
            case Version.VERSION_58 /*{ENCODED_INT: 58}*/:
                int pos = value.indexOf(type, 1);
                if (pos >= 0) {
                    endpointClassName = value.substring(1, pos);
                    intentFilterString = value.substring(pos + 1);
                    if (type != 33) {
                        negative = false;
                        break;
                    }
                } else {
                    throw new IllegalArgumentException("Criteria specification is not valid");
                }
                break;
            case Version.VERSION_42 /*{ENCODED_INT: 42}*/:
                endpointClassName = null;
                intentFilterString = value.substring(1);
                negative = false;
                break;
            default:
                throw new IllegalArgumentException("Criteria specification is not valid");
        }
        return new IntentCriteria(resolver, endpointClassName, negative, createIntentFilter(intentFilterString));
    }

    @Nullable
    private static IntentFilter createIntentFilter(String filterString) throws IOException {
        if (TextUtils.isEmpty(filterString)) {
            return null;
        }
        IntentFilter result = new IntentFilter();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(filterString));
            result.readFromXml(xpp);
            return result;
        } catch (XmlPullParserException xppe) {
            throw new IOException("Something went wrong with the parser", xppe);
        }
    }
}
