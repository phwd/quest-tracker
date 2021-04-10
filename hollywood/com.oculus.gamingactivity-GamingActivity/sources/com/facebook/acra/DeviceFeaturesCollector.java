package com.facebook.acra;

import android.content.Context;
import android.content.pm.PackageManager;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.metagen.TemplateMetadata;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceFeaturesCollector {
    public static String getFeatures(Context ctx) {
        StringBuffer result = new StringBuffer();
        try {
            Object[] features = (Object[]) PackageManager.class.getMethod("getSystemAvailableFeatures", null).invoke(ctx.getPackageManager(), new Object[0]);
            if (features != null) {
                for (Object feature : features) {
                    String featureName = (String) feature.getClass().getField(TemplateMetadata.NAME).get(feature);
                    if (featureName != null) {
                        result.append(featureName);
                    } else {
                        result.append("glEsVersion = ");
                        result.append((String) feature.getClass().getMethod("getGlEsVersion", null).invoke(feature, new Object[0]));
                    }
                    result.append("\n");
                }
            }
        } catch (Throwable e) {
            BLog.w(ACRA.LOG_TAG, e, "Couldn't retrieve device features for %s", ctx.getPackageName());
            result.append("Could not retrieve data: ");
            result.append(e.getMessage());
        }
        return result.toString();
    }
}
