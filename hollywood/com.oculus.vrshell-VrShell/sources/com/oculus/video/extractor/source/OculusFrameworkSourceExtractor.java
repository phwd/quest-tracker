package com.oculus.video.extractor.source;

import android.content.Context;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.net.Uri;
import android.util.Log;
import com.facebook.debug.log.LoggingUtil;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.extractor.Extractor;
import com.oculus.android.exoplayer2.extractor.ExtractorInput;
import com.oculus.android.exoplayer2.extractor.ExtractorOutput;
import com.oculus.android.exoplayer2.extractor.PositionHolder;
import com.oculus.android.exoplayer2.extractor.SeekMap;
import com.oculus.android.exoplayer2.extractor.SeekPoint;
import com.oculus.android.exoplayer2.extractor.TrackOutput;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import com.oculus.video.analytics.VideoPlayerAnalytics;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OculusFrameworkSourceExtractor implements Extractor, SeekMap {
    private static final int BUFFER_FLAG_NONE = 0;
    private static final long DURATION_US_UNSET = Long.MAX_VALUE;
    private static final String MIME_TYPE_UNKNOWN = "unknown";
    private static final String TAG = "FrameworkExtractor";
    private Context context;
    private MediaExtractor extractor;
    private ExtractorOutput extractorOutput;
    private int maxInputSize = 0;
    private boolean prepared = false;
    private TrackInfo[] trackInfos;
    private int[] trackStates;

    @Override // com.oculus.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return false;
    }

    public boolean sniff(Context context2, Uri uri) throws IOException, InterruptedException {
        this.context = context2;
        this.extractor = new MediaExtractor();
        try {
            this.extractor.setDataSource(this.context, uri, (Map<String, String>) null);
            int trackCount = this.extractor.getTrackCount();
            if (trackCount <= 0) {
                release();
                return false;
            }
            for (int i = 0; i < trackCount; i++) {
                String string = this.extractor.getTrackFormat(i).getString("mime");
                if (string == null || string.isEmpty() || string.equals("unknown")) {
                    Log.w(TAG, "Unrecognized track mime type");
                    release();
                    return false;
                }
            }
            return true;
        } catch (IOException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error set DataSource with uri: ");
            sb.append(uri == null ? LoggingUtil.NO_HASHCODE : uri.toString());
            Log.w(TAG, sb.toString());
            release();
            return false;
        }
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput2) {
        Assertions.checkState(!this.prepared);
        this.extractorOutput = extractorOutput2;
        this.trackStates = new int[this.extractor.getTrackCount()];
        this.trackInfos = new TrackInfo[this.trackStates.length];
        for (int i = 0; i < this.trackStates.length; i++) {
            MediaFormat trackFormat = this.extractor.getTrackFormat(i);
            long j = trackFormat.containsKey("durationUs") ? trackFormat.getLong("durationUs") : C.TIME_UNSET;
            String string = trackFormat.getString("mime");
            int trackType = getTrackType(string);
            if (trackType != -1) {
                this.trackInfos[i] = new TrackInfo(string, j, this.extractorOutput.track(i, trackType));
                this.trackInfos[i].trackOutput.format(getFormat(i, trackFormat, trackType));
                this.extractor.selectTrack(i);
            }
        }
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(this);
        this.prepared = true;
        Log.d(TAG, "Android MediaExtractor is selected");
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        if (!this.prepared) {
            return 1;
        }
        int sampleTrackIndex = this.extractor.getSampleTrackIndex();
        if (sampleTrackIndex == -1) {
            return -1;
        }
        TrackOutput trackOutput = this.trackInfos[sampleTrackIndex].trackOutput;
        ByteBuffer wrap = ByteBuffer.wrap(new byte[this.maxInputSize]);
        int readSampleData = this.extractor.readSampleData(wrap, 0);
        trackOutput.sampleData(new ParsableByteArray(wrap.array(), readSampleData), readSampleData);
        long sampleTime = this.extractor.getSampleTime();
        int sampleFlags = this.extractor.getSampleFlags();
        if (this.trackInfos[sampleTrackIndex].waitFirstSample) {
            TrackInfo[] trackInfoArr = this.trackInfos;
            trackInfoArr[sampleTrackIndex].waitFirstSample = false;
            if (sampleFlags == 0) {
                trackInfoArr[sampleTrackIndex].forceSyncFrame = true;
            }
        }
        trackOutput.sampleMetadata(sampleTime, sampleFlags | (this.trackInfos[sampleTrackIndex].forceSyncFrame ? 1 : 0), readSampleData, 0, null);
        this.extractor.advance();
        return 0;
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        if (this.prepared) {
            this.extractor.seekTo(j, 0);
        }
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public void release() {
        MediaExtractor mediaExtractor = this.extractor;
        if (mediaExtractor != null) {
            mediaExtractor.release();
            this.extractor = null;
        }
    }

    private int getTrackType(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        }
        if (str.startsWith("video/")) {
            return 2;
        }
        if (str.startsWith("audio/")) {
            return 1;
        }
        return -1;
    }

    private Format getFormat(int i, MediaFormat mediaFormat, int i2) {
        int i3 = 2;
        if (i2 == 1) {
            String string = mediaFormat.getString("mime");
            int maybeSetInteger = maybeSetInteger(mediaFormat, "max-input-size");
            this.maxInputSize = Math.max(this.maxInputSize, maybeSetInteger);
            int maybeSetInteger2 = maybeSetInteger(mediaFormat, "channel-count");
            int maybeSetInteger3 = maybeSetInteger(mediaFormat, "sample-rate");
            String maybeSetString = maybeSetString(mediaFormat, "language");
            List<byte[]> maybeSetCsd = maybeSetCsd(mediaFormat);
            if (!MimeTypes.AUDIO_RAW.equals(string)) {
                i3 = -1;
            }
            return Format.createAudioSampleFormat(Integer.toString(i), string, null, -1, maybeSetInteger, maybeSetInteger2, maybeSetInteger3, i3, maybeSetCsd, null, 0, maybeSetString);
        } else if (i2 != 2) {
            return null;
        } else {
            String string2 = mediaFormat.getString("mime");
            int maybeSetInteger4 = maybeSetInteger(mediaFormat, "max-input-size");
            this.maxInputSize = Math.max(this.maxInputSize, maybeSetInteger4);
            int maybeSetInteger5 = maybeSetInteger(mediaFormat, VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_WIDTH);
            int maybeSetInteger6 = maybeSetInteger(mediaFormat, VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_HEIGHT);
            int maybeSetInteger7 = maybeSetInteger(mediaFormat, "rotation-degrees");
            return Format.createVideoSampleFormat(Integer.toString(i), string2, null, -1, maybeSetInteger4, maybeSetInteger5, maybeSetInteger6, -1.0f, maybeSetCsd(mediaFormat), maybeSetInteger7, 1.0f, null, -1, null, null);
        }
    }

    private int maybeSetInteger(MediaFormat mediaFormat, String str) {
        if (mediaFormat.containsKey(str)) {
            return mediaFormat.getInteger(str);
        }
        return -1;
    }

    private String maybeSetString(MediaFormat mediaFormat, String str) {
        if (mediaFormat.containsKey(str)) {
            return mediaFormat.getString(str);
        }
        return null;
    }

    private List<byte[]> maybeSetCsd(MediaFormat mediaFormat) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            if (!mediaFormat.containsKey("csd-" + i)) {
                return arrayList;
            }
            arrayList.add(mediaFormat.getByteBuffer("csd-" + i).array());
            i++;
        }
    }

    @Override // com.oculus.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        long j = Long.MAX_VALUE;
        for (TrackInfo trackInfo : this.trackInfos) {
            j = Math.min(j, trackInfo.durationUs);
        }
        if (j == Long.MAX_VALUE) {
            return 0;
        }
        return j;
    }

    @Override // com.oculus.android.exoplayer2.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j) {
        return new SeekMap.SeekPoints(new SeekPoint(j, Math.max(0L, Math.min(j, getDurationUs()))));
    }

    public long getSyncFrmPosition(long j) {
        if (this.prepared) {
            this.extractor.seekTo(j, 2);
            j = this.extractor.getSampleTime();
        }
        return Math.max(0L, Math.min(j, getDurationUs()));
    }

    /* access modifiers changed from: private */
    public final class TrackInfo {
        public final long durationUs;
        public boolean forceSyncFrame = false;
        public final String mimeType;
        public final TrackOutput trackOutput;
        public boolean waitFirstSample = true;

        public TrackInfo(String str, long j, TrackOutput trackOutput2) {
            this.mimeType = str;
            this.durationUs = j;
            this.trackOutput = trackOutput2;
        }
    }
}
