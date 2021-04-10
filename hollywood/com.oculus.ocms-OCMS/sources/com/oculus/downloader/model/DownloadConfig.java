package com.oculus.downloader.model;

import com.google.common.base.Preconditions;
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

    public DownloadConfig(String str, String str2, @Nullable String str3, int i, int i2, @Nullable String str4, @Nullable String str5, @Nullable Map<String, String> map) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        this.uri = str;
        this.fileUri = str2;
        this.fileMimeType = str3;
        this.networks = i;
        this.visibility = i2;
        this.title = str4;
        this.description = str5;
        this.headers = map == null ? Collections.emptyMap() : map;
    }
}
