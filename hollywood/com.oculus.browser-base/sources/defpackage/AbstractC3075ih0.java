package defpackage;

import android.content.IntentFilter;
import android.media.MediaRoute2Info;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/* renamed from: ih0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3075ih0 {
    public static List a(List list) {
        if (list == null) {
            return new ArrayList();
        }
        return (List) list.stream().filter(new C2563fh0()).map(new C2734gh0()).collect(Collectors.toList());
    }

    public static C0869Of0 b(MediaRoute2Info mediaRoute2Info) {
        ArrayList<? extends Parcelable> arrayList = null;
        if (mediaRoute2Info == null) {
            return null;
        }
        String id = mediaRoute2Info.getId();
        String charSequence = mediaRoute2Info.getName().toString();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("name", charSequence);
        bundle.putInt("connectionState", mediaRoute2Info.getConnectionState());
        bundle.putInt("volumeHandling", mediaRoute2Info.getVolumeHandling());
        bundle.putInt("volumeMax", mediaRoute2Info.getVolumeMax());
        bundle.putInt("volume", mediaRoute2Info.getVolume());
        bundle.putBundle("extras", mediaRoute2Info.getExtras());
        bundle.putBoolean("enabled", true);
        bundle.putBoolean("canDisconnect", false);
        CharSequence description = mediaRoute2Info.getDescription();
        if (description != null) {
            bundle.putString("status", description.toString());
        }
        Uri iconUri = mediaRoute2Info.getIconUri();
        if (iconUri != null) {
            bundle.putString("iconUri", iconUri.toString());
        }
        Bundle extras = mediaRoute2Info.getExtras();
        if (extras == null || !extras.containsKey("androidx.mediarouter.media.KEY_EXTRAS") || !extras.containsKey("androidx.mediarouter.media.KEY_DEVICE_TYPE") || !extras.containsKey("androidx.mediarouter.media.KEY_CONTROL_FILTERS")) {
            return null;
        }
        bundle.putBundle("extras", extras.getBundle("androidx.mediarouter.media.KEY_EXTRAS"));
        bundle.putInt("deviceType", extras.getInt("androidx.mediarouter.media.KEY_DEVICE_TYPE", 0));
        bundle.putInt("playbackType", extras.getInt("androidx.mediarouter.media.KEY_PLAYBACK_TYPE", 1));
        ArrayList<IntentFilter> parcelableArrayList = extras.getParcelableArrayList("androidx.mediarouter.media.KEY_CONTROL_FILTERS");
        if (parcelableArrayList != null && !parcelableArrayList.isEmpty()) {
            for (IntentFilter intentFilter : parcelableArrayList) {
                if (intentFilter != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    if (!arrayList.contains(intentFilter)) {
                        arrayList.add(intentFilter);
                    }
                } else {
                    throw new IllegalArgumentException("filter must not be null");
                }
            }
        }
        if (arrayList != null) {
            bundle.putParcelableArrayList("controlFilters", arrayList);
        }
        return new C0869Of0(bundle);
    }
}
