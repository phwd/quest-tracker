package com.oculus.video.source.hls;

import android.net.Uri;
import android.util.Base64;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsPlaylist;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsPlaylistParser;
import com.oculus.android.exoplayer2.upstream.ParsingLoadable;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;

public final class OculusHlsPlaylistParser implements ParsingLoadable.Parser<HlsPlaylist> {
    private final String encryptionIv;
    private final String encryptionKey;
    private final HlsPlaylistParser parser = new HlsPlaylistParser();

    public OculusHlsPlaylistParser(String str, String str2) {
        this.encryptionKey = str;
        this.encryptionIv = str2;
    }

    @Override // com.oculus.android.exoplayer2.upstream.ParsingLoadable.Parser
    public HlsPlaylist parse(Uri uri, InputStream inputStream) throws IOException {
        OculusHlsPlaylistParser oculusHlsPlaylistParser = this;
        HlsPlaylist parse = oculusHlsPlaylistParser.parser.parse(uri, inputStream);
        if (!(parse instanceof HlsMediaPlaylist)) {
            return parse;
        }
        HlsMediaPlaylist hlsMediaPlaylist = (HlsMediaPlaylist) parse;
        String str = oculusHlsPlaylistParser.encryptionKey;
        if (str == null || str.isEmpty()) {
            return hlsMediaPlaylist;
        }
        byte[] byteArray = new BigInteger(oculusHlsPlaylistParser.encryptionKey, 16).toByteArray();
        byte[] bArr = new byte[16];
        int length = byteArray.length > 16 ? byteArray.length - 16 : 0;
        System.arraycopy(byteArray, length, bArr, (bArr.length - byteArray.length) + length, byteArray.length - length);
        String str2 = "data:text/plain;base64," + Base64.encodeToString(bArr, 2);
        ArrayList arrayList = new ArrayList();
        for (HlsMediaPlaylist.Segment segment : hlsMediaPlaylist.segments) {
            String str3 = segment.encryptionIV;
            String str4 = oculusHlsPlaylistParser.encryptionIv;
            if (str4 != null && !str4.isEmpty()) {
                str3 = oculusHlsPlaylistParser.encryptionIv;
            }
            arrayList.add(new HlsMediaPlaylist.Segment(segment.url, segment.durationUs, segment.relativeDiscontinuitySequence, segment.relativeStartTimeUs, str2, str3, segment.byterangeOffset, segment.byterangeLength, segment.hasGapTag));
            oculusHlsPlaylistParser = this;
            hlsMediaPlaylist = hlsMediaPlaylist;
            str2 = str2;
        }
        return new HlsMediaPlaylist(hlsMediaPlaylist.playlistType, hlsMediaPlaylist.baseUri, hlsMediaPlaylist.tags, hlsMediaPlaylist.startOffsetUs, hlsMediaPlaylist.startTimeUs, hlsMediaPlaylist.hasDiscontinuitySequence, hlsMediaPlaylist.discontinuitySequence, hlsMediaPlaylist.mediaSequence, hlsMediaPlaylist.version, hlsMediaPlaylist.targetDurationUs, hlsMediaPlaylist.hasIndependentSegmentsTag, hlsMediaPlaylist.hasEndTag, hlsMediaPlaylist.hasProgramDateTime, hlsMediaPlaylist.drmInitData, hlsMediaPlaylist.initializationSegment, arrayList);
    }
}
