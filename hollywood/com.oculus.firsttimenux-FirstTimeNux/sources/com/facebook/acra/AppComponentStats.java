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

        public Stats(int totalCount2, int enabledCount2, int disabledCount2, int defaultCount2, int flagState2, List<String> disabledComponents2, List<String> defaultComponents2) {
            this.totalCount = totalCount2;
            this.enabledCount = enabledCount2;
            this.disabledCount = disabledCount2;
            this.defaultCount = defaultCount2;
            this.flagState = flagState2;
            this.disabledComponents = disabledComponents2;
            this.defaultComponents = defaultComponents2;
        }
    }

    public AppComponentStats(Context context) {
        this.mContext = context;
    }

    public Stats getStats() throws IOException, XmlPullParserException {
        int flagState;
        List<String> components = new ArrayList<>();
        parseAndroidManifest(components);
        int totalCount = components.size();
        int enabledCount = 0;
        int disabledCount = 0;
        int defaultCount = 0;
        List<String> disabledComponents = new ArrayList<>();
        List<String> defaultComponents = new ArrayList<>();
        PackageManager packageManager = this.mContext.getPackageManager();
        for (String name : components) {
            switch (packageManager.getComponentEnabledSetting(new ComponentName(this.mContext, name))) {
                case 0:
                    defaultCount++;
                    defaultComponents.add(name);
                    break;
                case 1:
                    enabledCount++;
                    break;
                case 2:
                case 3:
                case 4:
                    disabledCount++;
                    disabledComponents.add(name);
                    break;
            }
        }
        try {
            flagState = this.mContext.getPackageManager().getComponentEnabledSetting(new ComponentName(this.mContext, "com.facebook.appcomponentmanager.IndicatorFlagReceiver"));
        } catch (Throwable th) {
            flagState = Integer.MIN_VALUE;
        }
        Collections.sort(disabledComponents);
        Collections.sort(defaultComponents);
        return new Stats(totalCount, enabledCount, disabledCount, defaultCount, flagState, disabledComponents, defaultComponents);
    }

    private void parseAndroidManifest(List<String> components) throws IOException, XmlPullParserException {
        XmlResourceParser parser = this.mContext.getAssets().openXmlResourceParser(ANDROID_MANIFEST_FILENAME);
        while (true) {
            try {
                int type = parser.next();
                if (type == 1) {
                    return;
                }
                if (type == 2 && parser.getName().equals(TAG_MANIFEST)) {
                    parseManifestBody(parser, components);
                }
            } finally {
                parser.close();
            }
        }
    }

    private void parseManifestBody(XmlPullParser parser, List<String> components) throws IOException, XmlPullParserException {
        int depth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if (type == 1) {
                return;
            }
            if (type != 3 || parser.getDepth() != depth) {
                if (type == 2 && parser.getName().equals(TAG_APPLICATION)) {
                    parseApplication(parser, components);
                }
            } else {
                return;
            }
        }
    }

    private void parseApplication(XmlPullParser parser, List<String> components) throws IOException, XmlPullParserException {
        int depth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if (type == 1) {
                return;
            }
            if (type != 3 || parser.getDepth() != depth) {
                if (type == 2) {
                    String tagName = parser.getName();
                    if (tagName.equals(TAG_ACTIVITY) || tagName.equals(TAG_ACTIVITY_ALIAS) || tagName.equals("receiver") || tagName.equals(TAG_SERVICE) || tagName.equals(TAG_PROVIDER)) {
                        components.add(parser.getAttributeValue(ANDROID_XML_NS, "name"));
                    }
                }
            } else {
                return;
            }
        }
    }
}
