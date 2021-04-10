package bolts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public final class AppLinks {
    public static final String KEY_NAME_APPLINK_DATA = "al_applink_data";
    public static final String KEY_NAME_EXTRAS = "extras";
    public static final String KEY_NAME_TARGET = "target_url";

    public static Bundle getAppLinkData(Intent intent) {
        return intent.getBundleExtra(KEY_NAME_APPLINK_DATA);
    }

    public static Bundle getAppLinkExtras(Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra(KEY_NAME_APPLINK_DATA);
        if (bundleExtra == null) {
            return null;
        }
        return bundleExtra.getBundle(KEY_NAME_EXTRAS);
    }

    public static Uri getTargetUrl(Intent intent) {
        String string;
        Bundle bundleExtra = intent.getBundleExtra(KEY_NAME_APPLINK_DATA);
        if (bundleExtra == null || (string = bundleExtra.getString(KEY_NAME_TARGET)) == null) {
            return intent.getData();
        }
        return Uri.parse(string);
    }

    public static Uri getTargetUrlFromInboundIntent(Context context, Intent intent) {
        String string;
        Bundle bundleExtra = intent.getBundleExtra(KEY_NAME_APPLINK_DATA);
        if (bundleExtra == null || (string = bundleExtra.getString(KEY_NAME_TARGET)) == null) {
            return null;
        }
        MeasurementEvent.sendBroadcastEvent(context, MeasurementEvent.APP_LINK_NAVIGATE_IN_EVENT_NAME, intent, null);
        return Uri.parse(string);
    }
}
