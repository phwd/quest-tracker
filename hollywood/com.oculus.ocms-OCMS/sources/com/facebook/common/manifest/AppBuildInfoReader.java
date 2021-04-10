package com.facebook.common.manifest;

import android.content.Context;
import com.facebook.common.manifest.ManifestConstants;
import com.facebook.infer.annotation.Nullsafe;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Inject;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class AppBuildInfoReader {
    private static final Pattern BUILD_TIME_FORMAT = Pattern.compile("^[0-9]+L$");
    private static AppBuildInfo sBuildInfo;
    private final Context mContext;
    private final String mCurrentPackageName;
    private final ManifestReader mManifestReader;

    @Inject
    public AppBuildInfoReader(Context context, ManifestReader manifestReader) {
        this.mContext = context;
        if (manifestReader != null) {
            this.mManifestReader = manifestReader;
            this.mCurrentPackageName = this.mContext.getPackageName();
            return;
        }
        throw new NullPointerException();
    }

    public AppBuildInfo getAppBuildInfo() {
        return getAppBuildInfo(this.mCurrentPackageName);
    }

    public AppBuildInfo getAppBuildInfo(String str) {
        String str2;
        long j;
        String valueForKeyFromManifest = getValueForKeyFromManifest(ManifestConstants.Metadata.KEY_VERSION_CONTROL_REVISION, str);
        String valueForKeyFromManifest2 = getValueForKeyFromManifest(ManifestConstants.Metadata.KEY_VERSION_CONTROL_BRANCH, str);
        String valueForKeyFromManifest3 = getValueForKeyFromManifest(ManifestConstants.Metadata.KEY_BUILD_TIME, str);
        if (timeStampMatchesCurrentFormat(valueForKeyFromManifest3)) {
            j = parseTimeStampString(valueForKeyFromManifest3);
            str2 = convertUTCTimestampToPacificTimeString(j);
        } else {
            j = 0;
            str2 = "";
        }
        return new AppBuildInfo(valueForKeyFromManifest, valueForKeyFromManifest2, j, str2);
    }

    static String convertUTCTimestampToPacificTimeString(long j) {
        DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(1, 0, Locale.US);
        dateTimeInstance.setTimeZone(TimeZone.getTimeZone("PST8PDT"));
        return dateTimeInstance.format(new Date(j));
    }

    static boolean timeStampMatchesCurrentFormat(String str) {
        return str != null && BUILD_TIME_FORMAT.matcher(str).matches();
    }

    private static long parseTimeStampString(String str) {
        return Long.parseLong(str.substring(0, str.length() - 1));
    }

    private String getValueForKeyFromManifest(String str, String str2) {
        String metaDataValueForKey = this.mManifestReader.getMetaDataValueForKey(str, str2);
        return metaDataValueForKey == null ? "" : metaDataValueForKey;
    }

    @Deprecated
    public static AppBuildInfo getAppBuildInfo(Context context) {
        if (sBuildInfo == null) {
            sBuildInfo = new AppBuildInfoReader(context, new ManifestReader(context)).getAppBuildInfo();
        }
        return sBuildInfo;
    }
}
