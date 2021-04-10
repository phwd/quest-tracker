package com.facebook.acra;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import com.oculus.auth.service.contract.ServiceContract;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AppComponentStats {
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
        XmlResourceParser parser = this.mContext.getAssets().openXmlResourceParser("AndroidManifest.xml");
        while (true) {
            try {
                int type = parser.next();
                if (type == 1) {
                    return;
                }
                if (type == 2 && parser.getName().equals("manifest")) {
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
                if (type == 2 && parser.getName().equals("application")) {
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
                    if (tagName.equals("activity") || tagName.equals("activity-alias") || tagName.equals(ServiceContract.EXTRA_RECEIVER) || tagName.equals("service") || tagName.equals("provider")) {
                        components.add(parser.getAttributeValue("http://schemas.android.com/apk/res/android", "name"));
                    }
                }
            } else {
                return;
            }
        }
    }
}
