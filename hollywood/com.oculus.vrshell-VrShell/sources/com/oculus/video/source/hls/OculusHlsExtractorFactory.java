package com.oculus.video.source.hls;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.drm.DrmInitData;
import com.oculus.android.exoplayer2.extractor.Extractor;
import com.oculus.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.oculus.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.oculus.android.exoplayer2.extractor.ts.Ac3Extractor;
import com.oculus.android.exoplayer2.extractor.ts.AdtsExtractor;
import com.oculus.android.exoplayer2.extractor.ts.TsExtractor;
import com.oculus.android.exoplayer2.source.hls.HlsExtractorFactory;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.TimestampAdjuster;
import com.oculus.video.extractor.hls.OculusTsPayloadReaderFactory;
import java.util.Collections;
import java.util.List;

public final class OculusHlsExtractorFactory implements HlsExtractorFactory {
    public static final String AAC_FILE_EXTENSION = ".aac";
    public static final String AC3_FILE_EXTENSION = ".ac3";
    public static final String EC3_FILE_EXTENSION = ".ec3";
    public static final String M4_FILE_EXTENSION_PREFIX = ".m4";
    public static final String MP3_FILE_EXTENSION = ".mp3";
    public static final String MP4_FILE_EXTENSION = ".mp4";
    public static final String MP4_FILE_EXTENSION_PREFIX = ".mp4";
    public static final String VTT_FILE_EXTENSION = ".vtt";
    public static final String WEBVTT_FILE_EXTENSION = ".webvtt";

    @Override // com.oculus.android.exoplayer2.source.hls.HlsExtractorFactory
    public Pair<Extractor, Boolean> createExtractor(Extractor extractor, Uri uri, Format format, List<Format> list, DrmInitData drmInitData, TimestampAdjuster timestampAdjuster) {
        String lastPathSegment = uri.getLastPathSegment();
        boolean z = false;
        if (MimeTypes.TEXT_VTT.equals(format.sampleMimeType) || lastPathSegment.endsWith(".webvtt") || lastPathSegment.endsWith(".vtt")) {
            extractor = new WebvttExtractor(format.language, timestampAdjuster);
        } else {
            if (lastPathSegment.endsWith(".aac")) {
                extractor = new AdtsExtractor();
            } else if (lastPathSegment.endsWith(".ac3") || lastPathSegment.endsWith(".ec3")) {
                extractor = new Ac3Extractor();
            } else if (lastPathSegment.endsWith(".mp3")) {
                extractor = new Mp3Extractor(0, 0);
            } else if (extractor == null) {
                if (lastPathSegment.endsWith(".mp4") || lastPathSegment.startsWith(".m4", lastPathSegment.length() - 4) || lastPathSegment.startsWith(".mp4", lastPathSegment.length() - 5)) {
                    if (list == null) {
                        list = Collections.emptyList();
                    }
                    extractor = new FragmentedMp4Extractor(0, timestampAdjuster, null, drmInitData, list);
                } else {
                    int i = 16;
                    if (list != null) {
                        i = 48;
                    } else {
                        list = Collections.emptyList();
                    }
                    String str = format.codecs;
                    if (!TextUtils.isEmpty(str)) {
                        if (!MimeTypes.AUDIO_AAC.equals(MimeTypes.getAudioMediaMimeType(str))) {
                            i |= 2;
                        }
                        if (!MimeTypes.VIDEO_H264.equals(MimeTypes.getVideoMediaMimeType(str))) {
                            i |= 4;
                        }
                    }
                    extractor = new TsExtractor(2, timestampAdjuster, new OculusTsPayloadReaderFactory(i, list, format));
                }
            }
            z = true;
        }
        return Pair.create(extractor, Boolean.valueOf(z));
    }
}
