package com.oculus.localmedia;

import X.AnonymousClass006;
import android.util.Log;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MediaSmartQueryUtils {
    public static final long DAY_SECS = 86400;
    public static final HashMap<MediaSmartQueryType, List<MediaSmartQueryType>> HIERARCHY = new HashMap<MediaSmartQueryType, List<MediaSmartQueryType>>() {
        /* class com.oculus.localmedia.MediaSmartQueryUtils.AnonymousClass1 */

        {
            MediaSmartQueryType mediaSmartQueryType = MediaSmartQueryType.ROOT;
            MediaSmartQueryType mediaSmartQueryType2 = MediaSmartQueryType.RECENT;
            MediaSmartQueryType mediaSmartQueryType3 = MediaSmartQueryType.ATOZ;
            MediaSmartQueryType mediaSmartQueryType4 = MediaSmartQueryType.ALBUMS;
            put(mediaSmartQueryType, Arrays.asList(mediaSmartQueryType2, mediaSmartQueryType3, mediaSmartQueryType4));
            put(mediaSmartQueryType2, Arrays.asList(MediaSmartQueryType.RECENT_TODAY, MediaSmartQueryType.RECENT_LAST_WEEK, MediaSmartQueryType.RECENT_LAST_MONTH, MediaSmartQueryType.RECENT_LAST_YEAR, MediaSmartQueryType.RECENT_OLDER));
            put(mediaSmartQueryType3, Arrays.asList(MediaSmartQueryType.ATOZ_AF, MediaSmartQueryType.ATOZ_GK, MediaSmartQueryType.ATOZ_LP, MediaSmartQueryType.ATOZ_QU, MediaSmartQueryType.ATOZ_VZ));
            MediaSmartQueryType mediaSmartQueryType5 = MediaSmartQueryType.ALBUMS_PHOTOS;
            MediaSmartQueryType mediaSmartQueryType6 = MediaSmartQueryType.ALBUMS_VIDEOS;
            MediaSmartQueryType mediaSmartQueryType7 = MediaSmartQueryType.ALBUMS_360;
            MediaSmartQueryType mediaSmartQueryType8 = MediaSmartQueryType.ALBUMS_180;
            MediaSmartQueryType mediaSmartQueryType9 = MediaSmartQueryType.ALBUMS_3D;
            put(mediaSmartQueryType4, Arrays.asList(mediaSmartQueryType5, mediaSmartQueryType6, mediaSmartQueryType7, mediaSmartQueryType8, mediaSmartQueryType9));
            put(MediaSmartQueryType.VR, Arrays.asList(mediaSmartQueryType7, mediaSmartQueryType8, mediaSmartQueryType9));
        }
    };
    public static final long MONTH_SECS = 2678400;
    public static final long WEEK_SECS = 2073600;
    public static final long YEAR_SECS = 31536000;

    public static MediaSmartQueryType getSmartQueryType(String str) {
        if (str != null && str.startsWith("*")) {
            try {
                return MediaSmartQueryType.valueOf(str.substring(1));
            } catch (IllegalArgumentException e) {
                Log.e(LocalMediaManager.TAG, "Smart query path unsupported", e);
            }
        }
        return null;
    }

    /* renamed from: com.oculus.localmedia.MediaSmartQueryUtils$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$localmedia$MediaSmartQueryType;

        /* JADX WARNING: Can't wrap try/catch for region: R(38:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|(3:37|38|40)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(40:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|40) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0066 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0070 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x008e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0098 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00a2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00ac */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00b6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
            // Method dump skipped, instructions count: 193
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.MediaSmartQueryUtils.AnonymousClass2.<clinit>():void");
        }
    }

    public static void addFilter(MediaQuery mediaQuery, String str) {
        String str2 = mediaQuery.Filters;
        if (str2 != null && !str2.isEmpty()) {
            str = AnonymousClass006.A09(str2, ",", str);
        }
        mediaQuery.Filters = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00f3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.oculus.localmedia.MediaQuery rewrite(com.oculus.localmedia.MediaQuery r20) {
        /*
        // Method dump skipped, instructions count: 338
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.MediaSmartQueryUtils.rewrite(com.oculus.localmedia.MediaQuery):com.oculus.localmedia.MediaQuery");
    }

    public static List<MediaSmartQueryType> getFoldersForPath(String str) {
        MediaSmartQueryType smartQueryType = getSmartQueryType(str);
        if (HIERARCHY.containsKey(smartQueryType)) {
            return HIERARCHY.get(smartQueryType);
        }
        return null;
    }
}
