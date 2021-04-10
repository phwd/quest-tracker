package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaMetadata extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new CD1();
    public static final String[] F = {null, "String", "int", "double", "ISO-8601 date String"};
    public static final C4263pe0 G;
    public final List H;
    public final Bundle I;

    /* renamed from: J  reason: collision with root package name */
    public int f9643J;

    static {
        C4263pe0 pe0 = new C4263pe0();
        pe0.a("com.google.android.gms.cast.metadata.CREATION_DATE", "creationDateTime", 4);
        pe0.a("com.google.android.gms.cast.metadata.RELEASE_DATE", "releaseDate", 4);
        pe0.a("com.google.android.gms.cast.metadata.BROADCAST_DATE", "originalAirdate", 4);
        pe0.a("com.google.android.gms.cast.metadata.TITLE", "title", 1);
        pe0.a("com.google.android.gms.cast.metadata.SUBTITLE", "subtitle", 1);
        pe0.a("com.google.android.gms.cast.metadata.ARTIST", "artist", 1);
        pe0.a("com.google.android.gms.cast.metadata.ALBUM_ARTIST", "albumArtist", 1);
        pe0.a("com.google.android.gms.cast.metadata.ALBUM_TITLE", "albumName", 1);
        pe0.a("com.google.android.gms.cast.metadata.COMPOSER", "composer", 1);
        pe0.a("com.google.android.gms.cast.metadata.DISC_NUMBER", "discNumber", 2);
        pe0.a("com.google.android.gms.cast.metadata.TRACK_NUMBER", "trackNumber", 2);
        pe0.a("com.google.android.gms.cast.metadata.SEASON_NUMBER", "season", 2);
        pe0.a("com.google.android.gms.cast.metadata.EPISODE_NUMBER", "episode", 2);
        pe0.a("com.google.android.gms.cast.metadata.SERIES_TITLE", "seriesTitle", 1);
        pe0.a("com.google.android.gms.cast.metadata.STUDIO", "studio", 1);
        pe0.a("com.google.android.gms.cast.metadata.WIDTH", "width", 2);
        pe0.a("com.google.android.gms.cast.metadata.HEIGHT", "height", 2);
        pe0.a("com.google.android.gms.cast.metadata.LOCATION_NAME", "location", 1);
        pe0.a("com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "latitude", 3);
        pe0.a("com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "longitude", 3);
        pe0.a("com.google.android.gms.cast.metadata.SECTION_DURATION", "sectionDuration", 5);
        pe0.a("com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_MEDIA", "sectionStartTimeInMedia", 5);
        pe0.a("com.google.android.gms.cast.metadata.SECTION_START_ABSOLUTE_TIME", "sectionStartAbsoluteTime", 5);
        pe0.a("com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_CONTAINER", "sectionStartTimeInContainer", 5);
        pe0.a("com.google.android.gms.cast.metadata.QUEUE_ITEM_ID", "queueItemId", 2);
        pe0.a("com.google.android.gms.cast.metadata.BOOK_TITLE", "bookTitle", 1);
        pe0.a("com.google.android.gms.cast.metadata.CHAPTER_NUMBER", "chapterNumber", 2);
        pe0.a("com.google.android.gms.cast.metadata.CHAPTER_TITLE", "chapterTitle", 1);
        G = pe0;
    }

    public MediaMetadata(List list, Bundle bundle, int i) {
        this.H = list;
        this.I = bundle;
        this.f9643J = i;
    }

    public final JSONObject A() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("metadataType", this.f9643J);
        } catch (JSONException unused) {
        }
        JSONArray b = UF1.b(this.H);
        if (!(b == null || b.length() == 0)) {
            try {
                jSONObject.put("images", b);
            } catch (JSONException unused2) {
            }
        }
        ArrayList arrayList = new ArrayList();
        int i = this.f9643J;
        if (i == 0) {
            Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE");
        } else if (i == 1) {
            Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.STUDIO", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE");
        } else if (i == 2) {
            Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.SERIES_TITLE", "com.google.android.gms.cast.metadata.SEASON_NUMBER", "com.google.android.gms.cast.metadata.EPISODE_NUMBER", "com.google.android.gms.cast.metadata.BROADCAST_DATE");
        } else if (i == 3) {
            Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.ALBUM_TITLE", "com.google.android.gms.cast.metadata.ALBUM_ARTIST", "com.google.android.gms.cast.metadata.COMPOSER", "com.google.android.gms.cast.metadata.TRACK_NUMBER", "com.google.android.gms.cast.metadata.DISC_NUMBER", "com.google.android.gms.cast.metadata.RELEASE_DATE");
        } else if (i == 4) {
            Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.LOCATION_NAME", "com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "com.google.android.gms.cast.metadata.WIDTH", "com.google.android.gms.cast.metadata.HEIGHT", "com.google.android.gms.cast.metadata.CREATION_DATE");
        } else if (i == 5) {
            Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.CHAPTER_TITLE", "com.google.android.gms.cast.metadata.CHAPTER_NUMBER", "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.BOOK_TITLE", "com.google.android.gms.cast.metadata.SUBTITLE");
        }
        Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.SECTION_DURATION", "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_MEDIA", "com.google.android.gms.cast.metadata.SECTION_START_ABSOLUTE_TIME", "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_CONTAINER", "com.google.android.gms.cast.metadata.QUEUE_ITEM_ID");
        try {
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                i2++;
                String str = (String) obj;
                if (this.I.containsKey(str)) {
                    C4263pe0 pe0 = G;
                    int c = pe0.c(str);
                    if (c != 1) {
                        if (c == 2) {
                            jSONObject.put(pe0.b(str), this.I.getInt(str));
                        } else if (c == 3) {
                            jSONObject.put(pe0.b(str), this.I.getDouble(str));
                        } else if (c != 4) {
                            if (c == 5) {
                                jSONObject.put(pe0.b(str), ((double) this.I.getLong(str)) / 1000.0d);
                            }
                        }
                    }
                    jSONObject.put(pe0.b(str), this.I.getString(str));
                }
            }
            for (String str2 : this.I.keySet()) {
                if (!str2.startsWith("com.google.")) {
                    Object obj2 = this.I.get(str2);
                    if (obj2 instanceof String) {
                        jSONObject.put(str2, obj2);
                    } else if (obj2 instanceof Integer) {
                        jSONObject.put(str2, obj2);
                    } else if (obj2 instanceof Double) {
                        jSONObject.put(str2, obj2);
                    }
                }
            }
        } catch (JSONException unused3) {
        }
        return jSONObject;
    }

    public final boolean B(Bundle bundle, Bundle bundle2) {
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            Object obj2 = bundle2.get(str);
            if ((obj instanceof Bundle) && (obj2 instanceof Bundle) && !B((Bundle) obj, (Bundle) obj2)) {
                return false;
            }
            if (obj == null) {
                if (obj2 != null || !bundle2.containsKey(str)) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }

    public final void C(JSONObject jSONObject) {
        this.I.clear();
        this.H.clear();
        this.f9643J = 0;
        try {
            this.f9643J = jSONObject.getInt("metadataType");
        } catch (JSONException unused) {
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("images");
        if (optJSONArray != null) {
            UF1.a(this.H, optJSONArray);
        }
        ArrayList arrayList = new ArrayList();
        int i = this.f9643J;
        if (i == 0) {
            Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE");
        } else if (i == 1) {
            Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.STUDIO", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE");
        } else if (i == 2) {
            Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.SERIES_TITLE", "com.google.android.gms.cast.metadata.SEASON_NUMBER", "com.google.android.gms.cast.metadata.EPISODE_NUMBER", "com.google.android.gms.cast.metadata.BROADCAST_DATE");
        } else if (i == 3) {
            Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ALBUM_TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.ALBUM_ARTIST", "com.google.android.gms.cast.metadata.COMPOSER", "com.google.android.gms.cast.metadata.TRACK_NUMBER", "com.google.android.gms.cast.metadata.DISC_NUMBER", "com.google.android.gms.cast.metadata.RELEASE_DATE");
        } else if (i == 4) {
            Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.LOCATION_NAME", "com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "com.google.android.gms.cast.metadata.WIDTH", "com.google.android.gms.cast.metadata.HEIGHT", "com.google.android.gms.cast.metadata.CREATION_DATE");
        } else if (i == 5) {
            Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.CHAPTER_TITLE", "com.google.android.gms.cast.metadata.CHAPTER_NUMBER", "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.BOOK_TITLE", "com.google.android.gms.cast.metadata.SUBTITLE");
        }
        if (KF1.b) {
            Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.SECTION_DURATION", "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_MEDIA", "com.google.android.gms.cast.metadata.SECTION_START_ABSOLUTE_TIME", "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_CONTAINER");
        }
        Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.QUEUE_ITEM_ID");
        HashSet hashSet = new HashSet(arrayList);
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!"metadataType".equals(next)) {
                    C4263pe0 pe0 = G;
                    String str = (String) pe0.b.get(next);
                    if (str == null) {
                        Object obj = jSONObject.get(next);
                        if (obj instanceof String) {
                            this.I.putString(next, (String) obj);
                        } else if (obj instanceof Integer) {
                            this.I.putInt(next, ((Integer) obj).intValue());
                        } else if (obj instanceof Double) {
                            this.I.putDouble(next, ((Double) obj).doubleValue());
                        }
                    } else if (hashSet.contains(str)) {
                        try {
                            Object obj2 = jSONObject.get(next);
                            if (obj2 != null) {
                                int c = pe0.c(str);
                                if (c != 1) {
                                    if (c != 2) {
                                        if (c == 3) {
                                            double optDouble = jSONObject.optDouble(next);
                                            if (!Double.isNaN(optDouble)) {
                                                this.I.putDouble(str, optDouble);
                                            }
                                        } else if (c != 4) {
                                            if (c == 5) {
                                                this.I.putLong(str, (long) (((double) jSONObject.optLong(next)) * 1000.0d));
                                            }
                                        } else if ((obj2 instanceof String) && UF1.c((String) obj2) != null) {
                                            this.I.putString(str, (String) obj2);
                                        }
                                    } else if (obj2 instanceof Integer) {
                                        this.I.putInt(str, ((Integer) obj2).intValue());
                                    }
                                } else if (obj2 instanceof String) {
                                    this.I.putString(str, (String) obj2);
                                }
                            }
                        } catch (JSONException unused2) {
                        }
                    }
                }
            }
        } catch (JSONException unused3) {
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaMetadata)) {
            return false;
        }
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        return B(this.I, mediaMetadata.I) && this.H.equals(mediaMetadata.H);
    }

    public int hashCode() {
        int i = 17;
        for (String str : this.I.keySet()) {
            i = (i * 31) + this.I.get(str).hashCode();
        }
        return this.H.hashCode() + (i * 31);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.k(parcel, 2, this.H, false);
        AbstractC5758yO0.a(parcel, 3, this.I, false);
        int i2 = this.f9643J;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.n(parcel, l);
    }

    public String x(String str) {
        if (!TextUtils.isEmpty(str)) {
            int c = G.c(str);
            if (c == 1 || c == 0) {
                return this.I.getString(str);
            }
            String str2 = F[1];
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + str.length() + 21);
            sb.append("Value for ");
            sb.append(str);
            sb.append(" must be a ");
            sb.append(str2);
            throw new IllegalArgumentException(sb.toString());
        }
        throw new IllegalArgumentException("null and empty keys are not allowed");
    }

    public MediaMetadata() {
        ArrayList arrayList = new ArrayList();
        Bundle bundle = new Bundle();
        this.H = arrayList;
        this.I = bundle;
        this.f9643J = 0;
    }

    public MediaMetadata(int i) {
        ArrayList arrayList = new ArrayList();
        Bundle bundle = new Bundle();
        this.H = arrayList;
        this.I = bundle;
        this.f9643J = i;
    }
}
