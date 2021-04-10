package com.oculus.downloader.model;

import java.util.Collections;
import java.util.Map;
import javax.annotation.Nullable;

public class DownloadConfig {
    @Nullable
    public final String description;
    @Nullable
    public final String fileMimeType;
    public final String fileUri;
    public Map<String, String> headers;
    public final int networks;
    @Nullable
    public final String title;
    public final String uri;
    public final int visibility;

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)V */
    public DownloadConfig(String str, String str2) {
        if (str == null || str2 == null) {
            throw null;
        }
        this.uri = str;
        this.fileUri = str2;
        this.fileMimeType = null;
        this.networks = 3;
        this.visibility = 2;
        this.title = null;
        this.description = null;
        this.headers = Collections.emptyMap();
    }
}
