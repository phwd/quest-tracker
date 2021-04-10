package com.oculus.localmedia;

import android.util.Log;
import com.facebook.breakpad.BreakpadManager;
import com.oculus.systemutilities.R;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MediaSmartQueryUtils {
    private static final HashMap<MediaSmartQueryType, List<MediaSmartQueryType>> HIERARCHY = new HashMap<MediaSmartQueryType, List<MediaSmartQueryType>>() {
        /* class com.oculus.localmedia.MediaSmartQueryUtils.AnonymousClass1 */

        {
            put(MediaSmartQueryType.ROOT, Arrays.asList(MediaSmartQueryType.RECENT, MediaSmartQueryType.ATOZ, MediaSmartQueryType.ALBUMS));
            put(MediaSmartQueryType.RECENT, Arrays.asList(MediaSmartQueryType.RECENT_TODAY, MediaSmartQueryType.RECENT_LAST_WEEK, MediaSmartQueryType.RECENT_LAST_MONTH, MediaSmartQueryType.RECENT_LAST_YEAR, MediaSmartQueryType.RECENT_OLDER));
            put(MediaSmartQueryType.ATOZ, Arrays.asList(MediaSmartQueryType.ATOZ_AF, MediaSmartQueryType.ATOZ_GK, MediaSmartQueryType.ATOZ_LP, MediaSmartQueryType.ATOZ_QU, MediaSmartQueryType.ATOZ_VZ));
            put(MediaSmartQueryType.ALBUMS, Arrays.asList(MediaSmartQueryType.ALBUMS_PHOTOS, MediaSmartQueryType.ALBUMS_VIDEOS, MediaSmartQueryType.ALBUMS_360, MediaSmartQueryType.ALBUMS_180, MediaSmartQueryType.ALBUMS_3D));
            put(MediaSmartQueryType.VR, Arrays.asList(MediaSmartQueryType.ALBUMS_360, MediaSmartQueryType.ALBUMS_180, MediaSmartQueryType.ALBUMS_3D));
        }
    };

    public static List<MediaSmartQueryType> getFoldersForPath(String queryPath) {
        MediaSmartQueryType type = getSmartQueryType(queryPath);
        if (HIERARCHY.containsKey(type)) {
            return HIERARCHY.get(type);
        }
        return null;
    }

    public static MediaSmartQueryType getSmartQueryType(String queryPath) {
        if (queryPath == null || !queryPath.startsWith("*")) {
            return null;
        }
        try {
            return MediaSmartQueryType.valueOf(queryPath.substring(1));
        } catch (IllegalArgumentException e) {
            Log.e(LocalMediaManager.TAG, "Smart query path unsupported", e);
            return null;
        }
    }

    public static MediaQuery rewrite(MediaQuery query) {
        MediaSmartQueryType type = getSmartQueryType(query.Path);
        if (type != null) {
            if (HIERARCHY.containsKey(type)) {
                query.Types = MediaType.FOLDER.getValue();
            } else {
                query.Types = MediaType.all();
                query.Path = null;
            }
            long startDate = 0;
            long endDate = 0;
            String startAlpha = null;
            String endAlpha = null;
            switch (AnonymousClass2.$SwitchMap$com$oculus$localmedia$MediaSmartQueryType[type.ordinal()]) {
                case 5:
                    endDate = (new Date().getTime() / 1000) - 86400;
                    break;
                case 6:
                    startDate = (new Date().getTime() / 1000) - 86400;
                    endDate = (new Date().getTime() / 1000) - 2073600;
                    break;
                case 7:
                    startDate = (new Date().getTime() / 1000) - 2073600;
                    endDate = (new Date().getTime() / 1000) - 2678400;
                    break;
                case 8:
                    startDate = (new Date().getTime() / 1000) - 2678400;
                    endDate = (new Date().getTime() / 1000) - 31536000;
                    break;
                case 9:
                    startDate = (new Date().getTime() / 1000) - 31536000;
                    break;
                case R.styleable.GradientColor_android_endX /*{ENCODED_INT: 10}*/:
                    endAlpha = "f";
                    break;
                case 11:
                    startAlpha = "g";
                    endAlpha = "k";
                    break;
                case 12:
                    startAlpha = "l";
                    endAlpha = "p";
                    break;
                case 13:
                    startAlpha = "q";
                    endAlpha = "u";
                    break;
                case 14:
                    startAlpha = "v";
                    break;
                case 15:
                    query.Types = MediaType.IMAGE.getValue();
                    break;
                case BreakpadManager.SIGSTKFLT:
                    query.Types = MediaType.VIDEO.getValue();
                    break;
                case 17:
                    addFilter(query, "projection=SPHERICAL");
                    addFilter(query, "fov!=180");
                    break;
                case 18:
                    addFilter(query, "fov=180");
                    break;
                case 19:
                    addFilter(query, "stereo!=UNKNOWN");
                    addFilter(query, "stereo!=2D");
                    break;
            }
            if (startDate > 0) {
                addFilter(query, "date<=" + startDate);
                query.Sort = MediaSort.DATE;
                query.IsSortAscending = false;
            }
            if (endDate > 0) {
                addFilter(query, "date>=" + endDate);
                query.Sort = MediaSort.DATE;
                query.IsSortAscending = false;
            }
            if (startAlpha != null) {
                addFilter(query, "alpha>=" + startAlpha);
                query.Sort = MediaSort.NAME;
                query.IsSortAscending = false;
            }
            if (endAlpha != null) {
                addFilter(query, "alpha<=" + endAlpha);
                query.Sort = MediaSort.NAME;
                query.IsSortAscending = false;
            }
        }
        return query;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.localmedia.MediaSmartQueryUtils$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$localmedia$MediaSmartQueryType = new int[MediaSmartQueryType.values().length];

        static {
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.ROOT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.RECENT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.ATOZ.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.ALBUMS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.RECENT_TODAY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.RECENT_LAST_WEEK.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.RECENT_LAST_MONTH.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.RECENT_LAST_YEAR.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.RECENT_OLDER.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.ATOZ_AF.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.ATOZ_GK.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.ATOZ_LP.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.ATOZ_QU.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.ATOZ_VZ.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.ALBUMS_PHOTOS.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.ALBUMS_VIDEOS.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.ALBUMS_360.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.ALBUMS_180.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                $SwitchMap$com$oculus$localmedia$MediaSmartQueryType[MediaSmartQueryType.ALBUMS_3D.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
        }
    }

    private static void addFilter(MediaQuery query, String filter) {
        if (query.Filters != null && !query.Filters.isEmpty()) {
            filter = query.Filters + "," + filter;
        }
        query.Filters = filter;
    }
}
