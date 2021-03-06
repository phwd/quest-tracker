package com.oculus.android.exoplayer2.source.hls.playlist;

import java.util.Collections;
import java.util.List;

public abstract class HlsPlaylist {
    public final String baseUri;
    public final List<String> tags;

    protected HlsPlaylist(String str, List<String> list) {
        this.baseUri = str;
        this.tags = Collections.unmodifiableList(list);
    }
}
