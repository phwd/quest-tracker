package com.facebook.acra;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import com.oculus.fitnesstracker.database.FitnessTrackerMoveContract;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

public final class AppComponentStats {
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

    public final Stats getStats() throws IOException, XmlPullParserException {
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
        XmlResourceParser openXmlResourceParser = this.mContext.getAssets().openXmlResourceParser("AndroidManifest.xml");
        while (true) {
            try {
                int next = openXmlResourceParser.next();
                if (next == 1) {
                    return;
                }
                if (next == 2 && openXmlResourceParser.getName().equals("manifest")) {
                    int depth = openXmlResourceParser.getDepth();
                    while (true) {
                        int next2 = openXmlResourceParser.next();
                        if (next2 == 1 || (next2 == 3 && openXmlResourceParser.getDepth() == depth)) {
                            break;
                        } else if (next2 == 2 && openXmlResourceParser.getName().equals("application")) {
                            int depth2 = openXmlResourceParser.getDepth();
                            while (true) {
                                int next3 = openXmlResourceParser.next();
                                if (next3 == 1 || (next3 == 3 && openXmlResourceParser.getDepth() == depth2)) {
                                    break;
                                } else if (next3 == 2) {
                                    String name = openXmlResourceParser.getName();
                                    if (name.equals(FitnessTrackerMoveContract.Session.ACTIVITY) || name.equals("activity-alias") || name.equals("receiver") || name.equals("service") || name.equals("provider")) {
                                        list.add(openXmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "name"));
                                    }
                                }
                            }
                        }
                    }
                }
            } finally {
                openXmlResourceParser.close();
            }
        }
    }
}
