package com.oculus.android.exoplayer2.source.smoothstreaming.manifest;

import android.net.Uri;
import com.oculus.android.exoplayer2.upstream.ParsingLoadable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class FilteringSsManifestParser implements ParsingLoadable.Parser<SsManifest> {
    private final List<TrackKey> filter;
    private final SsManifestParser ssManifestParser = new SsManifestParser();

    public FilteringSsManifestParser(List<TrackKey> list) {
        this.filter = list;
    }

    @Override // com.oculus.android.exoplayer2.upstream.ParsingLoadable.Parser
    public SsManifest parse(Uri uri, InputStream inputStream) throws IOException {
        return this.ssManifestParser.parse(uri, inputStream).copy(this.filter);
    }
}
