package com.oculus.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import com.oculus.android.exoplayer2.upstream.ParsingLoadable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public final class FilteringDashManifestParser implements ParsingLoadable.Parser<DashManifest> {
    private final DashManifestParser dashManifestParser = new DashManifestParser();
    private final ArrayList<RepresentationKey> filter;

    public FilteringDashManifestParser(ArrayList<RepresentationKey> arrayList) {
        this.filter = arrayList;
    }

    @Override // com.oculus.android.exoplayer2.upstream.ParsingLoadable.Parser
    public DashManifest parse(Uri uri, InputStream inputStream) throws IOException {
        return this.dashManifestParser.parse(uri, inputStream).copy(this.filter);
    }
}
