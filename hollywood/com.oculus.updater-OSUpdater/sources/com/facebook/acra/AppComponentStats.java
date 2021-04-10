package com.facebook.acra;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AppComponentStats {
    private static final String ANDROID_MANIFEST_FILENAME = "AndroidManifest.xml";
    private static final String ANDROID_XML_NS = "http://schemas.android.com/apk/res/android";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String TAG_ACTIVITY = "activity";
    private static final String TAG_ACTIVITY_ALIAS = "activity-alias";
    private static final String TAG_APPLICATION = "application";
    private static final String TAG_MANIFEST = "manifest";
    private static final String TAG_PROVIDER = "provider";
    private static final String TAG_RECEIVER = "receiver";
    private static final String TAG_SERVICE = "service";
    private final Context mContext;

    public static class Stats {
        public final List<String> defaultComponents;
        public final int defaultCount;
        public final List<String> disabledComponents;
        public final int disabledCount;
        public final int enabledCount;
        public final int flagState;
        public final int totalCount;

        public Stats(int i, int i2, int i3, int i4, int i5, List<String> list, List<String> list2) {
            this.totalCount = i;
            this.enabledCount = i2;
            this.disabledCount = i3;
            this.defaultCount = i4;
            this.flagState = i5;
            this.disabledComponents = list;
            this.defaultComponents = list2;
        }
    }

    public AppComponentStats(Context context) {
        this.mContext = context;
    }

    public Stats getStats() throws IOException, XmlPullParserException {
        int i;
        ArrayList arrayList = new ArrayList();
        parseAndroidManifest(arrayList);
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        PackageManager packageManager = this.mContext.getPackageManager();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (String str : arrayList) {
            int componentEnabledSetting = packageManager.getComponentEnabledSetting(new ComponentName(this.mContext, str));
            if (componentEnabledSetting == 0) {
                i4++;
                arrayList3.add(str);
            } else if (componentEnabledSetting == 1) {
                i2++;
            } else if (componentEnabledSetting == 2 || componentEnabledSetting == 3 || componentEnabledSetting == 4) {
                i3++;
                arrayList2.add(str);
            }
        }
        try {
            i = this.mContext.getPackageManager().getComponentEnabledSetting(new ComponentName(this.mContext, "com.facebook.appcomponentmanager.IndicatorFlagReceiver"));
        } catch (Throwable unused) {
            i = Integer.MIN_VALUE;
        }
        Collections.sort(arrayList2);
        Collections.sort(arrayList3);
        return new Stats(size, i2, i3, i4, i, arrayList2, arrayList3);
    }

    private void parseAndroidManifest(List<String> list) throws IOException, XmlPullParserException {
        XmlResourceParser openXmlResourceParser = this.mContext.getAssets().openXmlResourceParser(ANDROID_MANIFEST_FILENAME);
        while (true) {
            try {
                int next = openXmlResourceParser.next();
                if (next == 1) {
                    return;
                }
                if (next == 2 && openXmlResourceParser.getName().equals(TAG_MANIFEST)) {
                    parseManifestBody(openXmlResourceParser, list);
                }
            } finally {
                openXmlResourceParser.close();
            }
        }
    }

    private void parseManifestBody(XmlPullParser xmlPullParser, List<String> list) throws IOException, XmlPullParserException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next != 3 || xmlPullParser.getDepth() != depth) {
                if (next == 2 && xmlPullParser.getName().equals(TAG_APPLICATION)) {
                    parseApplication(xmlPullParser, list);
                }
            } else {
                return;
            }
        }
    }

    private void parseApplication(XmlPullParser xmlPullParser, List<String> list) throws IOException, XmlPullParserException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next != 3 || xmlPullParser.getDepth() != depth) {
                if (next == 2) {
                    String name = xmlPullParser.getName();
                    if (name.equals(TAG_ACTIVITY) || name.equals(TAG_ACTIVITY_ALIAS) || name.equals(TAG_RECEIVER) || name.equals(TAG_SERVICE) || name.equals(TAG_PROVIDER)) {
                        list.add(xmlPullParser.getAttributeValue(ANDROID_XML_NS, ATTRIBUTE_NAME));
                    }
                }
            } else {
                return;
            }
        }
    }
}
