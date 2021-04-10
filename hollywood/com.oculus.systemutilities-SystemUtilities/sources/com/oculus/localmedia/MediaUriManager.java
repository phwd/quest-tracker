package com.oculus.localmedia;

import android.net.Uri;

public interface MediaUriManager {
    Uri getMediaUrl(MediaItem mediaItem, String str);

    Uri getThumbnailUrl(MediaItem mediaItem, String str);
}
