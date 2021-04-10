package com.facebook.acra;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

public class AppComponentStats {
    public static final String ANDROID_MANIFEST_FILENAME = "AndroidManifest.xml";
    public static final String ANDROID_XML_NS = "http://schemas.android.com/apk/res/android";
    public static final String ATTRIBUTE_NAME = "name";
    public static final String TAG_ACTIVITY = "activity";
    public static final String TAG_ACTIVITY_ALIAS = "activity-alias";
    public static final String TAG_APPLICATION = "application";
    public static final String TAG_MANIFEST = "manifest";
    public static final String TAG_PROVIDER = "provider";
    public static final String TAG_RECEIVER = "receiver";
    public static final String TAG_SERVICE = "service";
    public final Context mContext;

    public class Stats {
        public final List defaultComponents;
        public final int defaultCount;
        public final List disabledComponents;
        public final int disabledCount;
        public final int enabledCount;
        public final int flagState;
        public final int totalCount;

        public Stats(int i, int i2, int i3, int i4, int i5, List list, List list2) {
            this.totalCount = i;
            this.enabledCount = i2;
            this.disabledCount = i3;
            this.defaultCount = i4;
            this.flagState = i5;
            this.disabledComponents = list;
            this.defaultComponents = list2;
        }
    }

    private void parseAndroidManifest(List list) {
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

    public Stats getStats() {
        int i;
        ArrayList arrayList = new ArrayList();
        parseAndroidManifest(arrayList);
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        PackageManager packageManager = this.mContext.getPackageManager();
        Iterator it = arrayList.iterator();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
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

    public AppComponentStats(Context context) {
        this.mContext = context;
    }

    private void parseApplication(XmlPullParser xmlPullParser, List list) {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3) {
                if (xmlPullParser.getDepth() == depth) {
                    return;
                }
            } else if (next == 2) {
                String name = xmlPullParser.getName();
                if (name.equals(TAG_ACTIVITY) || name.equals(TAG_ACTIVITY_ALIAS) || name.equals("receiver") || name.equals(TAG_SERVICE) || name.equals(TAG_PROVIDER)) {
                    list.add(xmlPullParser.getAttributeValue(ANDROID_XML_NS, ATTRIBUTE_NAME));
                }
            }
        }
    }

    private void parseManifestBody(XmlPullParser xmlPullParser, List list) {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3) {
                if (xmlPullParser.getDepth() == depth) {
                    return;
                }
            } else if (next == 2 && xmlPullParser.getName().equals(TAG_APPLICATION)) {
                parseApplication(xmlPullParser, list);
            }
        }
    }
}
