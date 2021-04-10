package android.support.v4.media;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.media.DeniedByServerException;
import android.media.MediaDataSource;
import android.media.MediaDrm;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.media.MediaTimestamp;
import android.media.PlaybackParams;
import android.media.ResourceBusyException;
import android.media.SubtitleData;
import android.media.SyncParams;
import android.media.TimedMetaData;
import android.media.UnsupportedSchemeException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.media.BaseMediaPlayer;
import android.support.v4.media.MediaPlayer2;
import android.support.v4.media.PlaybackParams2;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Preconditions;
import android.util.Log;
import android.util.Pair;
import android.view.Surface;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

@TargetApi(28)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class MediaPlayer2Impl extends MediaPlayer2 {
    private static final int SOURCE_STATE_ERROR = -1;
    private static final int SOURCE_STATE_INIT = 0;
    private static final int SOURCE_STATE_PREPARED = 2;
    private static final int SOURCE_STATE_PREPARING = 1;
    private static final String TAG = "MediaPlayer2Impl";
    private static ArrayMap<Integer, Integer> sErrorEventMap = new ArrayMap<>();
    private static ArrayMap<Integer, Integer> sInfoEventMap = new ArrayMap<>();
    private static ArrayMap<Integer, Integer> sPrepareDrmStatusMap = new ArrayMap<>();
    private static ArrayMap<Integer, Integer> sStateMap = new ArrayMap<>();
    private BaseMediaPlayerImpl mBaseMediaPlayerImpl;
    @GuardedBy("mTaskLock")
    private Task mCurrentTask;
    private Pair<Executor, MediaPlayer2.DrmEventCallback> mDrmEventCallbackRecord;
    private final Handler mEndPositionHandler;
    private HandlerThread mHandlerThread = new HandlerThread("MediaPlayer2TaskThread");
    private final Object mLock = new Object();
    private Pair<Executor, MediaPlayer2.EventCallback> mMp2EventCallbackRecord;
    @GuardedBy("mTaskLock")
    private final ArrayDeque<Task> mPendingTasks = new ArrayDeque<>();
    private MediaPlayerSourceQueue mPlayer;
    private ArrayMap<BaseMediaPlayer.PlayerEventCallback, Executor> mPlayerEventCallbackMap = new ArrayMap<>();
    private final Handler mTaskHandler;
    private final Object mTaskLock = new Object();

    /* access modifiers changed from: private */
    public interface DrmEventNotifier {
        void notify(MediaPlayer2.DrmEventCallback drmEventCallback);
    }

    /* access modifiers changed from: private */
    public interface Mp2EventNotifier {
        void notify(MediaPlayer2.EventCallback eventCallback);
    }

    /* access modifiers changed from: private */
    public interface PlayerEventNotifier {
        void notify(BaseMediaPlayer.PlayerEventCallback playerEventCallback);
    }

    static {
        sInfoEventMap.put(1, 1);
        sInfoEventMap.put(2, 2);
        sInfoEventMap.put(3, 3);
        ArrayMap<Integer, Integer> arrayMap = sInfoEventMap;
        Integer valueOf = Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_VIDEO_TRACK_LAGGING);
        arrayMap.put(valueOf, valueOf);
        ArrayMap<Integer, Integer> arrayMap2 = sInfoEventMap;
        Integer valueOf2 = Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_BUFFERING_START);
        arrayMap2.put(valueOf2, valueOf2);
        ArrayMap<Integer, Integer> arrayMap3 = sInfoEventMap;
        Integer valueOf3 = Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_BUFFERING_END);
        arrayMap3.put(valueOf3, valueOf3);
        sInfoEventMap.put(Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_BAD_INTERLEAVING), Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_BAD_INTERLEAVING));
        sInfoEventMap.put(Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_NOT_SEEKABLE), Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_NOT_SEEKABLE));
        sInfoEventMap.put(Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_METADATA_UPDATE), Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_METADATA_UPDATE));
        sInfoEventMap.put(Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_AUDIO_NOT_PLAYING), Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_AUDIO_NOT_PLAYING));
        sInfoEventMap.put(Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_VIDEO_NOT_PLAYING), Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_VIDEO_NOT_PLAYING));
        sInfoEventMap.put(Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_UNSUPPORTED_SUBTITLE), Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_UNSUPPORTED_SUBTITLE));
        sInfoEventMap.put(Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_SUBTITLE_TIMED_OUT), Integer.valueOf((int) MediaPlayer2.MEDIA_INFO_SUBTITLE_TIMED_OUT));
        sErrorEventMap.put(1, 1);
        sErrorEventMap.put(200, 200);
        sErrorEventMap.put(Integer.valueOf((int) MediaPlayer2.MEDIA_ERROR_IO), Integer.valueOf((int) MediaPlayer2.MEDIA_ERROR_IO));
        sErrorEventMap.put(Integer.valueOf((int) MediaPlayer2.MEDIA_ERROR_MALFORMED), Integer.valueOf((int) MediaPlayer2.MEDIA_ERROR_MALFORMED));
        sErrorEventMap.put(Integer.valueOf((int) MediaPlayer2.MEDIA_ERROR_UNSUPPORTED), Integer.valueOf((int) MediaPlayer2.MEDIA_ERROR_UNSUPPORTED));
        sErrorEventMap.put(Integer.valueOf((int) MediaPlayer2.MEDIA_ERROR_TIMED_OUT), Integer.valueOf((int) MediaPlayer2.MEDIA_ERROR_TIMED_OUT));
        sPrepareDrmStatusMap.put(0, 0);
        sPrepareDrmStatusMap.put(1, 1);
        sPrepareDrmStatusMap.put(2, 2);
        sPrepareDrmStatusMap.put(2, 2);
        sStateMap.put(1001, 0);
        sStateMap.put(1002, 1);
        sStateMap.put(1003, 1);
        sStateMap.put(1004, 2);
        sStateMap.put(Integer.valueOf((int) MediaPlayer2.MEDIAPLAYER2_STATE_ERROR), 3);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleDataSourceError(final DataSourceError err) {
        if (err != null) {
            notifyMediaPlayer2Event(new Mp2EventNotifier() {
                /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass1 */

                @Override // android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier
                public void notify(MediaPlayer2.EventCallback callback) {
                    callback.onError(MediaPlayer2Impl.this, err.mDSD, err.mWhat, err.mExtra);
                }
            });
        }
    }

    public MediaPlayer2Impl() {
        this.mHandlerThread.start();
        Looper looper = this.mHandlerThread.getLooper();
        this.mEndPositionHandler = new Handler(looper);
        this.mTaskHandler = new Handler(looper);
        this.mPlayer = new MediaPlayerSourceQueue();
    }

    @Override // android.support.v4.media.MediaPlayer2
    public BaseMediaPlayer getBaseMediaPlayer() {
        BaseMediaPlayerImpl baseMediaPlayerImpl;
        synchronized (this.mLock) {
            if (this.mBaseMediaPlayerImpl == null) {
                this.mBaseMediaPlayerImpl = new BaseMediaPlayerImpl();
            }
            baseMediaPlayerImpl = this.mBaseMediaPlayerImpl;
        }
        return baseMediaPlayerImpl;
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void close() {
        this.mPlayer.release();
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.mHandlerThread = null;
        }
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void play() {
        addTask(new Task(5, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass2 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                MediaPlayer2Impl.this.mPlayer.play();
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void prepare() {
        addTask(new Task(6, true) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass3 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() throws IOException {
                MediaPlayer2Impl.this.mPlayer.prepareAsync();
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void pause() {
        addTask(new Task(4, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass4 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                MediaPlayer2Impl.this.mPlayer.pause();
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void skipToNext() {
        addTask(new Task(29, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass5 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                MediaPlayer2Impl.this.mPlayer.skipToNext();
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    public long getCurrentPosition() {
        return this.mPlayer.getCurrentPosition();
    }

    @Override // android.support.v4.media.MediaPlayer2
    public long getDuration() {
        return this.mPlayer.getDuration();
    }

    @Override // android.support.v4.media.MediaPlayer2
    public long getBufferedPosition() {
        return this.mPlayer.getBufferedPosition();
    }

    @Override // android.support.v4.media.MediaPlayer2
    public int getState() {
        return this.mPlayer.getMediaPlayer2State();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int getPlayerState() {
        return this.mPlayer.getPlayerState();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int getBufferingState() {
        return this.mPlayer.getBufferingState();
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void setAudioAttributes(@NonNull final AudioAttributesCompat attributes) {
        addTask(new Task(16, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass6 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                MediaPlayer2Impl.this.mPlayer.setAudioAttributes(attributes);
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    @NonNull
    public AudioAttributesCompat getAudioAttributes() {
        return this.mPlayer.getAudioAttributes();
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void setDataSource(@NonNull final DataSourceDesc dsd) {
        addTask(new Task(19, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass7 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                Preconditions.checkNotNull(dsd, "the DataSourceDesc cannot be null");
                try {
                    MediaPlayer2Impl.this.mPlayer.setFirst(dsd);
                } catch (IOException e) {
                    Log.e(MediaPlayer2Impl.TAG, "process: setDataSource", e);
                }
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void setNextDataSource(@NonNull final DataSourceDesc dsd) {
        addTask(new Task(22, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass8 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                Preconditions.checkNotNull(dsd, "the DataSourceDesc cannot be null");
                MediaPlayer2Impl mediaPlayer2Impl = MediaPlayer2Impl.this;
                mediaPlayer2Impl.handleDataSourceError(mediaPlayer2Impl.mPlayer.setNext(dsd));
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void setNextDataSources(@NonNull final List<DataSourceDesc> dsds) {
        addTask(new Task(23, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass9 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                List list = dsds;
                if (list == null || list.size() == 0) {
                    throw new IllegalArgumentException("data source list cannot be null or empty.");
                }
                for (DataSourceDesc dsd : dsds) {
                    if (dsd == null) {
                        throw new IllegalArgumentException("DataSourceDesc in the source list cannot be null.");
                    }
                }
                MediaPlayer2Impl mediaPlayer2Impl = MediaPlayer2Impl.this;
                mediaPlayer2Impl.handleDataSourceError(mediaPlayer2Impl.mPlayer.setNextMultiple(dsds));
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    @NonNull
    public DataSourceDesc getCurrentDataSource() {
        return this.mPlayer.getFirst().getDSD();
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void loopCurrent(final boolean loop) {
        addTask(new Task(3, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass10 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                MediaPlayer2Impl.this.mPlayer.setLooping(loop);
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void setPlayerVolume(final float volume) {
        addTask(new Task(26, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass11 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                MediaPlayer2Impl.this.mPlayer.setVolume(volume);
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    public float getPlayerVolume() {
        return this.mPlayer.getVolume();
    }

    @Override // android.support.v4.media.MediaPlayer2
    public float getMaxPlayerVolume() {
        return 1.0f;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void registerPlayerEventCallback(@NonNull Executor e, @NonNull BaseMediaPlayer.PlayerEventCallback cb) {
        if (cb == null) {
            throw new IllegalArgumentException("Illegal null PlayerEventCallback");
        } else if (e != null) {
            synchronized (this.mLock) {
                this.mPlayerEventCallbackMap.put(cb, e);
            }
        } else {
            throw new IllegalArgumentException("Illegal null Executor for the PlayerEventCallback");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void unregisterPlayerEventCallback(@NonNull BaseMediaPlayer.PlayerEventCallback cb) {
        if (cb != null) {
            synchronized (this.mLock) {
                this.mPlayerEventCallbackMap.remove(cb);
            }
            return;
        }
        throw new IllegalArgumentException("Illegal null PlayerEventCallback");
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void notifyWhenCommandLabelReached(final Object label) {
        addTask(new Task(1003, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass12 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass12.AnonymousClass1 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier
                    public void notify(MediaPlayer2.EventCallback cb) {
                        cb.onCommandLabelReached(MediaPlayer2Impl.this, label);
                    }
                });
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void setSurface(final Surface surface) {
        addTask(new Task(27, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass13 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                MediaPlayer2Impl.this.mPlayer.setSurface(surface);
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void clearPendingCommands() {
        synchronized (this.mTaskLock) {
            this.mPendingTasks.clear();
        }
    }

    private void addTask(Task task) {
        synchronized (this.mTaskLock) {
            this.mPendingTasks.add(task);
            processPendingTask_l();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @GuardedBy("mTaskLock")
    private void processPendingTask_l() {
        if (this.mCurrentTask == null && !this.mPendingTasks.isEmpty()) {
            Task task = this.mPendingTasks.removeFirst();
            this.mCurrentTask = task;
            this.mTaskHandler.post(task);
        }
    }

    /* access modifiers changed from: private */
    public static void handleDataSource(MediaPlayerSource src) throws IOException {
        final DataSourceDesc dsd = src.getDSD();
        Preconditions.checkNotNull(dsd, "the DataSourceDesc cannot be null");
        MediaPlayer player = src.mPlayer;
        int type = dsd.getType();
        if (type == 1) {
            player.setDataSource(new MediaDataSource() {
                /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass14 */
                Media2DataSource mDataSource = dsd.getMedia2DataSource();

                @Override // android.media.MediaDataSource
                public int readAt(long position, byte[] buffer, int offset, int size) throws IOException {
                    return this.mDataSource.readAt(position, buffer, offset, size);
                }

                @Override // android.media.MediaDataSource
                public long getSize() throws IOException {
                    return this.mDataSource.getSize();
                }

                @Override // java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    this.mDataSource.close();
                }
            });
        } else if (type == 2) {
            player.setDataSource(dsd.getFileDescriptor(), dsd.getFileDescriptorOffset(), dsd.getFileDescriptorLength());
        } else if (type == 3) {
            player.setDataSource(dsd.getUriContext(), dsd.getUri(), dsd.getUriHeaders(), dsd.getUriCookies());
        }
    }

    @Override // android.support.v4.media.MediaPlayer2
    public int getVideoWidth() {
        return this.mPlayer.getVideoWidth();
    }

    @Override // android.support.v4.media.MediaPlayer2
    public int getVideoHeight() {
        return this.mPlayer.getVideoHeight();
    }

    @Override // android.support.v4.media.MediaPlayer2
    public PersistableBundle getMetrics() {
        return this.mPlayer.getMetrics();
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void setPlaybackParams(@NonNull final PlaybackParams2 params) {
        addTask(new Task(24, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass15 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                MediaPlayer2Impl.this.setPlaybackParamsInternal(params.getPlaybackParams());
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    @NonNull
    public PlaybackParams2 getPlaybackParams() {
        return new PlaybackParams2.Builder(this.mPlayer.getPlaybackParams()).build();
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void seekTo(final long msec, final int mode) {
        addTask(new Task(14, true) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass16 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                MediaPlayer2Impl.this.mPlayer.seekTo(msec, mode);
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    @Nullable
    public MediaTimestamp2 getTimestamp() {
        return this.mPlayer.getTimestamp();
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void reset() {
        this.mPlayer.reset();
        synchronized (this.mLock) {
            this.mMp2EventCallbackRecord = null;
            this.mPlayerEventCallbackMap.clear();
            this.mDrmEventCallbackRecord = null;
        }
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void setAudioSessionId(final int sessionId) {
        addTask(new Task(17, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass17 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                MediaPlayer2Impl.this.mPlayer.setAudioSessionId(sessionId);
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    public int getAudioSessionId() {
        return this.mPlayer.getAudioSessionId();
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void attachAuxEffect(final int effectId) {
        addTask(new Task(1, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass18 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                MediaPlayer2Impl.this.mPlayer.attachAuxEffect(effectId);
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void setAuxEffectSendLevel(final float level) {
        addTask(new Task(18, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass19 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                MediaPlayer2Impl.this.mPlayer.setAuxEffectSendLevel(level);
            }
        });
    }

    public static final class TrackInfoImpl extends MediaPlayer2.TrackInfo {
        final MediaFormat mFormat;
        final int mTrackType;

        @Override // android.support.v4.media.MediaPlayer2.TrackInfo
        public int getTrackType() {
            return this.mTrackType;
        }

        @Override // android.support.v4.media.MediaPlayer2.TrackInfo
        public String getLanguage() {
            String language = this.mFormat.getString("language");
            return language == null ? "und" : language;
        }

        @Override // android.support.v4.media.MediaPlayer2.TrackInfo
        public MediaFormat getFormat() {
            int i = this.mTrackType;
            if (i == 3 || i == 4) {
                return this.mFormat;
            }
            return null;
        }

        TrackInfoImpl(int type, MediaFormat format) {
            this.mTrackType = type;
            this.mFormat = format;
        }

        @Override // android.support.v4.media.MediaPlayer2.TrackInfo
        public String toString() {
            StringBuilder out = new StringBuilder(128);
            out.append(getClass().getName());
            out.append('{');
            int i = this.mTrackType;
            if (i == 1) {
                out.append("VIDEO");
            } else if (i == 2) {
                out.append("AUDIO");
            } else if (i == 3) {
                out.append("TIMEDTEXT");
            } else if (i != 4) {
                out.append("UNKNOWN");
            } else {
                out.append("SUBTITLE");
            }
            out.append(", " + this.mFormat.toString());
            out.append("}");
            return out.toString();
        }
    }

    @Override // android.support.v4.media.MediaPlayer2
    public List<MediaPlayer2.TrackInfo> getTrackInfo() {
        MediaPlayer.TrackInfo[] list = this.mPlayer.getTrackInfo();
        List<MediaPlayer2.TrackInfo> trackList = new ArrayList<>();
        for (MediaPlayer.TrackInfo info : list) {
            trackList.add(new TrackInfoImpl(info.getTrackType(), info.getFormat()));
        }
        return trackList;
    }

    @Override // android.support.v4.media.MediaPlayer2
    public int getSelectedTrack(int trackType) {
        return this.mPlayer.getSelectedTrack(trackType);
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void selectTrack(final int index) {
        addTask(new Task(15, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass20 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                MediaPlayer2Impl.this.mPlayer.selectTrack(index);
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void deselectTrack(final int index) {
        addTask(new Task(2, false) {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass21 */

            /* access modifiers changed from: package-private */
            @Override // android.support.v4.media.MediaPlayer2Impl.Task
            public void process() {
                MediaPlayer2Impl.this.mPlayer.deselectTrack(index);
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void setEventCallback(@NonNull Executor executor, @NonNull MediaPlayer2.EventCallback eventCallback) {
        if (eventCallback == null) {
            throw new IllegalArgumentException("Illegal null EventCallback");
        } else if (executor != null) {
            synchronized (this.mLock) {
                this.mMp2EventCallbackRecord = new Pair<>(executor, eventCallback);
            }
        } else {
            throw new IllegalArgumentException("Illegal null Executor for the EventCallback");
        }
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void clearEventCallback() {
        synchronized (this.mLock) {
            this.mMp2EventCallbackRecord = null;
        }
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void setOnDrmConfigHelper(final MediaPlayer2.OnDrmConfigHelper listener) {
        this.mPlayer.setOnDrmConfigHelper(new MediaPlayer.OnDrmConfigHelper() {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass22 */

            public void onDrmConfig(MediaPlayer mp) {
                MediaPlayerSource src = MediaPlayer2Impl.this.mPlayer.getSourceForPlayer(mp);
                listener.onDrmConfig(MediaPlayer2Impl.this, src == null ? null : src.getDSD());
            }
        });
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void setDrmEventCallback(@NonNull Executor executor, @NonNull MediaPlayer2.DrmEventCallback eventCallback) {
        if (eventCallback == null) {
            throw new IllegalArgumentException("Illegal null EventCallback");
        } else if (executor != null) {
            synchronized (this.mLock) {
                this.mDrmEventCallbackRecord = new Pair<>(executor, eventCallback);
            }
        } else {
            throw new IllegalArgumentException("Illegal null Executor for the EventCallback");
        }
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void clearDrmEventCallback() {
        synchronized (this.mLock) {
            this.mDrmEventCallbackRecord = null;
        }
    }

    @Override // android.support.v4.media.MediaPlayer2
    public MediaPlayer2.DrmInfo getDrmInfo() {
        MediaPlayer.DrmInfo info = this.mPlayer.getDrmInfo();
        if (info == null) {
            return null;
        }
        return new DrmInfoImpl(info.getPssh(), info.getSupportedSchemes());
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void prepareDrm(@NonNull UUID uuid) throws UnsupportedSchemeException, ResourceBusyException, MediaPlayer2.ProvisioningNetworkErrorException, MediaPlayer2.ProvisioningServerErrorException {
        try {
            this.mPlayer.prepareDrm(uuid);
        } catch (MediaPlayer.ProvisioningNetworkErrorException e) {
            throw new MediaPlayer2.ProvisioningNetworkErrorException(e.getMessage());
        } catch (MediaPlayer.ProvisioningServerErrorException e2) {
            throw new MediaPlayer2.ProvisioningServerErrorException(e2.getMessage());
        }
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void releaseDrm() throws MediaPlayer2.NoDrmSchemeException {
        try {
            this.mPlayer.releaseDrm();
        } catch (MediaPlayer.NoDrmSchemeException e) {
            throw new MediaPlayer2.NoDrmSchemeException(e.getMessage());
        }
    }

    @Override // android.support.v4.media.MediaPlayer2
    @NonNull
    public MediaDrm.KeyRequest getDrmKeyRequest(@Nullable byte[] keySetId, @Nullable byte[] initData, @Nullable String mimeType, int keyType, @Nullable Map<String, String> optionalParameters) throws MediaPlayer2.NoDrmSchemeException {
        try {
            return this.mPlayer.getKeyRequest(keySetId, initData, mimeType, keyType, optionalParameters);
        } catch (MediaPlayer.NoDrmSchemeException e) {
            throw new MediaPlayer2.NoDrmSchemeException(e.getMessage());
        }
    }

    @Override // android.support.v4.media.MediaPlayer2
    public byte[] provideDrmKeyResponse(@Nullable byte[] keySetId, @NonNull byte[] response) throws MediaPlayer2.NoDrmSchemeException, DeniedByServerException {
        try {
            return this.mPlayer.provideKeyResponse(keySetId, response);
        } catch (MediaPlayer.NoDrmSchemeException e) {
            throw new MediaPlayer2.NoDrmSchemeException(e.getMessage());
        }
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void restoreDrmKeys(@NonNull byte[] keySetId) throws MediaPlayer2.NoDrmSchemeException {
        try {
            this.mPlayer.restoreKeys(keySetId);
        } catch (MediaPlayer.NoDrmSchemeException e) {
            throw new MediaPlayer2.NoDrmSchemeException(e.getMessage());
        }
    }

    @Override // android.support.v4.media.MediaPlayer2
    @NonNull
    public String getDrmPropertyString(@NonNull String propertyName) throws MediaPlayer2.NoDrmSchemeException {
        try {
            return this.mPlayer.getDrmPropertyString(propertyName);
        } catch (MediaPlayer.NoDrmSchemeException e) {
            throw new MediaPlayer2.NoDrmSchemeException(e.getMessage());
        }
    }

    @Override // android.support.v4.media.MediaPlayer2
    public void setDrmPropertyString(@NonNull String propertyName, @NonNull String value) throws MediaPlayer2.NoDrmSchemeException {
        try {
            this.mPlayer.setDrmPropertyString(propertyName, value);
        } catch (MediaPlayer.NoDrmSchemeException e) {
            throw new MediaPlayer2.NoDrmSchemeException(e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPlaybackParamsInternal(final PlaybackParams params) {
        PlaybackParams current = this.mPlayer.getPlaybackParams();
        this.mPlayer.setPlaybackParams(params);
        if (current.getSpeed() != params.getSpeed()) {
            notifyPlayerEvent(new PlayerEventNotifier() {
                /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass23 */

                @Override // android.support.v4.media.MediaPlayer2Impl.PlayerEventNotifier
                public void notify(BaseMediaPlayer.PlayerEventCallback cb) {
                    cb.onPlaybackSpeedChanged(MediaPlayer2Impl.this.mBaseMediaPlayerImpl, params.getSpeed());
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0008, code lost:
        ((java.util.concurrent.Executor) r1.first).execute(new android.support.v4.media.MediaPlayer2Impl.AnonymousClass24(r3));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void notifyMediaPlayer2Event(final android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            android.util.Pair<java.util.concurrent.Executor, android.support.v4.media.MediaPlayer2$EventCallback> r1 = r3.mMp2EventCallbackRecord     // Catch:{ all -> 0x0017 }
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            if (r1 == 0) goto L_0x0014
            java.lang.Object r0 = r1.first
            java.util.concurrent.Executor r0 = (java.util.concurrent.Executor) r0
            android.support.v4.media.MediaPlayer2Impl$24 r2 = new android.support.v4.media.MediaPlayer2Impl$24
            r2.<init>(r4, r1)
            r0.execute(r2)
        L_0x0014:
            return
        L_0x0015:
            r2 = move-exception
            goto L_0x0019
        L_0x0017:
            r2 = move-exception
            r1 = 0
        L_0x0019:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaPlayer2Impl.notifyMediaPlayer2Event(android.support.v4.media.MediaPlayer2Impl$Mp2EventNotifier):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        r4 = r2.keyAt(r2);
        r2.valueAt(r2).execute(new android.support.v4.media.MediaPlayer2Impl.AnonymousClass25(r6));
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000d, code lost:
        r0 = r2.size();
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        if (r2 >= r0) goto L_0x002b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void notifyPlayerEvent(final android.support.v4.media.MediaPlayer2Impl.PlayerEventNotifier r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            r1 = 0
            android.support.v4.util.ArrayMap r2 = new android.support.v4.util.ArrayMap     // Catch:{ all -> 0x002c }
            android.support.v4.util.ArrayMap<android.support.v4.media.BaseMediaPlayer$PlayerEventCallback, java.util.concurrent.Executor> r3 = r6.mPlayerEventCallbackMap     // Catch:{ all -> 0x002c }
            r2.<init>(r3)     // Catch:{ all -> 0x002c }
            r1 = r2
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            int r0 = r1.size()
            r2 = 0
        L_0x0012:
            if (r2 >= r0) goto L_0x002b
            java.lang.Object r3 = r1.valueAt(r2)
            java.util.concurrent.Executor r3 = (java.util.concurrent.Executor) r3
            java.lang.Object r4 = r1.keyAt(r2)
            android.support.v4.media.BaseMediaPlayer$PlayerEventCallback r4 = (android.support.v4.media.BaseMediaPlayer.PlayerEventCallback) r4
            android.support.v4.media.MediaPlayer2Impl$25 r5 = new android.support.v4.media.MediaPlayer2Impl$25
            r5.<init>(r7, r4)
            r3.execute(r5)
            int r2 = r2 + 1
            goto L_0x0012
        L_0x002b:
            return
        L_0x002c:
            r2 = move-exception
        L_0x002d:
            monitor-exit(r0)
            throw r2
        L_0x002f:
            r2 = move-exception
            goto L_0x002d
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaPlayer2Impl.notifyPlayerEvent(android.support.v4.media.MediaPlayer2Impl$PlayerEventNotifier):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0008, code lost:
        ((java.util.concurrent.Executor) r1.first).execute(new android.support.v4.media.MediaPlayer2Impl.AnonymousClass26(r3));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void notifyDrmEvent(final android.support.v4.media.MediaPlayer2Impl.DrmEventNotifier r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            android.util.Pair<java.util.concurrent.Executor, android.support.v4.media.MediaPlayer2$DrmEventCallback> r1 = r3.mDrmEventCallbackRecord     // Catch:{ all -> 0x0017 }
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            if (r1 == 0) goto L_0x0014
            java.lang.Object r0 = r1.first
            java.util.concurrent.Executor r0 = (java.util.concurrent.Executor) r0
            android.support.v4.media.MediaPlayer2Impl$26 r2 = new android.support.v4.media.MediaPlayer2Impl$26
            r2.<init>(r4, r1)
            r0.execute(r2)
        L_0x0014:
            return
        L_0x0015:
            r2 = move-exception
            goto L_0x0019
        L_0x0017:
            r2 = move-exception
            r1 = 0
        L_0x0019:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaPlayer2Impl.notifyDrmEvent(android.support.v4.media.MediaPlayer2Impl$DrmEventNotifier):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndPositionTimerIfNeeded(final MediaPlayer.OnCompletionListener completionListener, final MediaPlayerSource src, MediaTimestamp timedsd) {
        if (src == this.mPlayer.getFirst()) {
            this.mEndPositionHandler.removeCallbacksAndMessages(null);
            DataSourceDesc dsd = src.getDSD();
            if (dsd.getEndPosition() == 576460752303423487L) {
                return;
            }
            if (timedsd.getMediaClockRate() > 0.0f) {
                long anchorMediaTimeUs = timedsd.getAnchorMediaTimeUs();
                long timeLeftMs = (long) (((float) (dsd.getEndPosition() - ((anchorMediaTimeUs + ((System.nanoTime() - timedsd.getAnchorSytemNanoTime()) / 1000)) / 1000))) / timedsd.getMediaClockRate());
                Handler handler = this.mEndPositionHandler;
                AnonymousClass27 r12 = new Runnable() {
                    /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass27 */

                    public void run() {
                        if (MediaPlayer2Impl.this.mPlayer.getFirst() == src) {
                            MediaPlayer2Impl.this.mPlayer.pause();
                            completionListener.onCompletion(src.mPlayer);
                        }
                    }
                };
                long j = 0;
                if (timeLeftMs >= 0) {
                    j = timeLeftMs;
                }
                handler.postDelayed(r12, j);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUpListeners(final MediaPlayerSource src) {
        MediaPlayer p = src.mPlayer;
        final MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass28 */

            public void onPrepared(MediaPlayer mp) {
                MediaPlayer2Impl mediaPlayer2Impl = MediaPlayer2Impl.this;
                mediaPlayer2Impl.handleDataSourceError(mediaPlayer2Impl.mPlayer.onPrepared(mp));
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass28.AnonymousClass1 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier
                    public void notify(MediaPlayer2.EventCallback callback) {
                        callback.onInfo(MediaPlayer2Impl.this, src.getDSD(), 100, 0);
                    }
                });
                MediaPlayer2Impl.this.notifyPlayerEvent(new PlayerEventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass28.AnonymousClass2 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.PlayerEventNotifier
                    public void notify(BaseMediaPlayer.PlayerEventCallback cb) {
                        cb.onMediaPrepared(MediaPlayer2Impl.this.mBaseMediaPlayerImpl, src.getDSD());
                    }
                });
                synchronized (MediaPlayer2Impl.this.mTaskLock) {
                    if (MediaPlayer2Impl.this.mCurrentTask != null && MediaPlayer2Impl.this.mCurrentTask.mMediaCallType == 6 && MediaPlayer2Impl.this.mCurrentTask.mDSD == src.getDSD() && MediaPlayer2Impl.this.mCurrentTask.mNeedToWaitForEventToComplete) {
                        MediaPlayer2Impl.this.mCurrentTask.sendCompleteNotification(0);
                        MediaPlayer2Impl.this.mCurrentTask = null;
                        MediaPlayer2Impl.this.processPendingTask_l();
                    }
                }
            }
        };
        p.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass29 */

            public void onPrepared(MediaPlayer mp) {
                if (src.getDSD().getStartPosition() != 0) {
                    src.mPlayer.seekTo((long) ((int) src.getDSD().getStartPosition()), 3);
                } else {
                    preparedListener.onPrepared(mp);
                }
            }
        });
        p.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass30 */

            public void onVideoSizeChanged(MediaPlayer mp, final int width, final int height) {
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass30.AnonymousClass1 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier
                    public void notify(MediaPlayer2.EventCallback cb) {
                        cb.onVideoSizeChanged(MediaPlayer2Impl.this, src.getDSD(), width, height);
                    }
                });
            }
        });
        p.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass31 */

            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what == 3) {
                    MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                        /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass31.AnonymousClass1 */

                        @Override // android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier
                        public void notify(MediaPlayer2.EventCallback cb) {
                            cb.onInfo(MediaPlayer2Impl.this, src.getDSD(), 3, 0);
                        }
                    });
                    return false;
                } else if (what == 701) {
                    MediaPlayer2Impl.this.mPlayer.setBufferingState(mp, 2);
                    return false;
                } else if (what != 702) {
                    return false;
                } else {
                    MediaPlayer2Impl.this.mPlayer.setBufferingState(mp, 1);
                    return false;
                }
            }
        });
        final MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass32 */

            public void onCompletion(MediaPlayer mp) {
                MediaPlayer2Impl mediaPlayer2Impl = MediaPlayer2Impl.this;
                mediaPlayer2Impl.handleDataSourceError(mediaPlayer2Impl.mPlayer.onCompletion(mp));
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass32.AnonymousClass1 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier
                    public void notify(MediaPlayer2.EventCallback cb) {
                        cb.onInfo(MediaPlayer2Impl.this, src.getDSD(), 5, 0);
                    }
                });
            }
        };
        p.setOnCompletionListener(completionListener);
        p.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass33 */

            public boolean onError(MediaPlayer mp, final int what, final int extra) {
                MediaPlayer2Impl.this.mPlayer.onError(mp);
                synchronized (MediaPlayer2Impl.this.mTaskLock) {
                    if (MediaPlayer2Impl.this.mCurrentTask != null && MediaPlayer2Impl.this.mCurrentTask.mNeedToWaitForEventToComplete) {
                        MediaPlayer2Impl.this.mCurrentTask.sendCompleteNotification(Integer.MIN_VALUE);
                        MediaPlayer2Impl.this.mCurrentTask = null;
                        MediaPlayer2Impl.this.processPendingTask_l();
                    }
                }
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass33.AnonymousClass1 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier
                    public void notify(MediaPlayer2.EventCallback cb) {
                        cb.onError(MediaPlayer2Impl.this, src.getDSD(), ((Integer) MediaPlayer2Impl.sErrorEventMap.getOrDefault(Integer.valueOf(what), 1)).intValue(), extra);
                    }
                });
                return true;
            }
        });
        p.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass34 */

            public void onSeekComplete(MediaPlayer mp) {
                if (src.mMp2State != 1001 || src.getDSD().getStartPosition() == 0) {
                    synchronized (MediaPlayer2Impl.this.mTaskLock) {
                        if (MediaPlayer2Impl.this.mCurrentTask != null && MediaPlayer2Impl.this.mCurrentTask.mMediaCallType == 14 && MediaPlayer2Impl.this.mCurrentTask.mNeedToWaitForEventToComplete) {
                            MediaPlayer2Impl.this.mCurrentTask.sendCompleteNotification(0);
                            MediaPlayer2Impl.this.mCurrentTask = null;
                            MediaPlayer2Impl.this.processPendingTask_l();
                        }
                    }
                    final long seekPos = MediaPlayer2Impl.this.getCurrentPosition();
                    MediaPlayer2Impl.this.notifyPlayerEvent(new PlayerEventNotifier() {
                        /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass34.AnonymousClass1 */

                        @Override // android.support.v4.media.MediaPlayer2Impl.PlayerEventNotifier
                        public void notify(BaseMediaPlayer.PlayerEventCallback cb) {
                            cb.onSeekCompleted(MediaPlayer2Impl.this.mBaseMediaPlayerImpl, seekPos);
                        }
                    });
                    return;
                }
                preparedListener.onPrepared(mp);
            }
        });
        p.setOnTimedMetaDataAvailableListener(new MediaPlayer.OnTimedMetaDataAvailableListener() {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass35 */

            public void onTimedMetaDataAvailable(MediaPlayer mp, final TimedMetaData data) {
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass35.AnonymousClass1 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier
                    public void notify(MediaPlayer2.EventCallback cb) {
                        cb.onTimedMetaDataAvailable(MediaPlayer2Impl.this, src.getDSD(), data);
                    }
                });
            }
        });
        p.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass36 */

            public boolean onInfo(MediaPlayer mp, final int what, final int extra) {
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass36.AnonymousClass1 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier
                    public void notify(MediaPlayer2.EventCallback cb) {
                        cb.onInfo(MediaPlayer2Impl.this, src.getDSD(), ((Integer) MediaPlayer2Impl.sInfoEventMap.getOrDefault(Integer.valueOf(what), 1)).intValue(), extra);
                    }
                });
                return true;
            }
        });
        p.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass37 */

            public void onBufferingUpdate(MediaPlayer mp, final int percent) {
                if (percent >= 100) {
                    MediaPlayer2Impl.this.mPlayer.setBufferingState(mp, 3);
                }
                src.mBufferedPercentage.set(percent);
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass37.AnonymousClass1 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier
                    public void notify(MediaPlayer2.EventCallback cb) {
                        cb.onInfo(MediaPlayer2Impl.this, src.getDSD(), MediaPlayer2.MEDIA_INFO_BUFFERING_UPDATE, percent);
                    }
                });
            }
        });
        p.setOnMediaTimeDiscontinuityListener(new MediaPlayer.OnMediaTimeDiscontinuityListener() {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass38 */

            public void onMediaTimeDiscontinuity(MediaPlayer mp, final MediaTimestamp timestamp) {
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass38.AnonymousClass1 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier
                    public void notify(MediaPlayer2.EventCallback cb) {
                        cb.onMediaTimeDiscontinuity(MediaPlayer2Impl.this, src.getDSD(), new MediaTimestamp2(timestamp));
                    }
                });
                MediaPlayer2Impl.this.setEndPositionTimerIfNeeded(completionListener, src, timestamp);
            }
        });
        p.setOnSubtitleDataListener(new MediaPlayer.OnSubtitleDataListener() {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass39 */

            public void onSubtitleData(MediaPlayer mp, final SubtitleData data) {
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass39.AnonymousClass1 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier
                    public void notify(MediaPlayer2.EventCallback cb) {
                        cb.onSubtitleData(MediaPlayer2Impl.this, src.getDSD(), new SubtitleData2(data));
                    }
                });
            }
        });
        p.setOnDrmInfoListener(new MediaPlayer.OnDrmInfoListener() {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass40 */

            public void onDrmInfo(MediaPlayer mp, final MediaPlayer.DrmInfo drmInfo) {
                MediaPlayer2Impl.this.notifyDrmEvent(new DrmEventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass40.AnonymousClass1 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.DrmEventNotifier
                    public void notify(MediaPlayer2.DrmEventCallback cb) {
                        cb.onDrmInfo(MediaPlayer2Impl.this, src.getDSD(), new DrmInfoImpl(drmInfo.getPssh(), drmInfo.getSupportedSchemes()));
                    }
                });
            }
        });
        p.setOnDrmPreparedListener(new MediaPlayer.OnDrmPreparedListener() {
            /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass41 */

            public void onDrmPrepared(MediaPlayer mp, final int status) {
                MediaPlayer2Impl.this.notifyDrmEvent(new DrmEventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.AnonymousClass41.AnonymousClass1 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.DrmEventNotifier
                    public void notify(MediaPlayer2.DrmEventCallback cb) {
                        cb.onDrmPrepared(MediaPlayer2Impl.this, src.getDSD(), ((Integer) MediaPlayer2Impl.sPrepareDrmStatusMap.getOrDefault(Integer.valueOf(status), 3)).intValue());
                    }
                });
            }
        });
    }

    public static final class DrmInfoImpl extends MediaPlayer2.DrmInfo {
        private Map<UUID, byte[]> mMapPssh;
        private UUID[] mSupportedSchemes;

        @Override // android.support.v4.media.MediaPlayer2.DrmInfo
        public Map<UUID, byte[]> getPssh() {
            return this.mMapPssh;
        }

        @Override // android.support.v4.media.MediaPlayer2.DrmInfo
        public List<UUID> getSupportedSchemes() {
            return Arrays.asList(this.mSupportedSchemes);
        }

        private DrmInfoImpl(Map<UUID, byte[]> pssh, UUID[] supportedSchemes) {
            this.mMapPssh = pssh;
            this.mSupportedSchemes = supportedSchemes;
        }

        private DrmInfoImpl(Parcel parcel) {
            Log.v(MediaPlayer2Impl.TAG, "DrmInfoImpl(" + parcel + ") size " + parcel.dataSize());
            int psshsize = parcel.readInt();
            byte[] pssh = new byte[psshsize];
            parcel.readByteArray(pssh);
            Log.v(MediaPlayer2Impl.TAG, "DrmInfoImpl() PSSH: " + arrToHex(pssh));
            this.mMapPssh = parsePSSH(pssh, psshsize);
            Log.v(MediaPlayer2Impl.TAG, "DrmInfoImpl() PSSH: " + this.mMapPssh);
            int supportedDRMsCount = parcel.readInt();
            this.mSupportedSchemes = new UUID[supportedDRMsCount];
            for (int i = 0; i < supportedDRMsCount; i++) {
                byte[] uuid = new byte[16];
                parcel.readByteArray(uuid);
                this.mSupportedSchemes[i] = bytesToUUID(uuid);
                Log.v(MediaPlayer2Impl.TAG, "DrmInfoImpl() supportedScheme[" + i + "]: " + this.mSupportedSchemes[i]);
            }
            Log.v(MediaPlayer2Impl.TAG, "DrmInfoImpl() Parcel psshsize: " + psshsize + " supportedDRMsCount: " + supportedDRMsCount);
        }

        private DrmInfoImpl makeCopy() {
            return new DrmInfoImpl(this.mMapPssh, this.mSupportedSchemes);
        }

        private String arrToHex(byte[] bytes) {
            String out = "0x";
            for (int i = 0; i < bytes.length; i++) {
                out = out + String.format("%02x", Byte.valueOf(bytes[i]));
            }
            return out;
        }

        private UUID bytesToUUID(byte[] uuid) {
            long msb = 0;
            long lsb = 0;
            for (int i = 0; i < 8; i++) {
                msb |= (((long) uuid[i]) & 255) << ((7 - i) * 8);
                lsb |= (((long) uuid[i + 8]) & 255) << ((7 - i) * 8);
            }
            return new UUID(msb, lsb);
        }

        private Map<UUID, byte[]> parsePSSH(byte[] pssh, int psshsize) {
            int i;
            int i2;
            Map<UUID, byte[]> result = new HashMap<>();
            int len = psshsize;
            int numentries = 0;
            int i3 = 0;
            while (len > 0) {
                if (len < 16) {
                    Log.w(MediaPlayer2Impl.TAG, String.format("parsePSSH: len is too short to parse UUID: (%d < 16) pssh: %d", Integer.valueOf(len), Integer.valueOf(psshsize)));
                    return null;
                }
                UUID uuid = bytesToUUID(Arrays.copyOfRange(pssh, i3, i3 + 16));
                int i4 = i3 + 16;
                int len2 = len - 16;
                if (len2 < 4) {
                    Log.w(MediaPlayer2Impl.TAG, String.format("parsePSSH: len is too short to parse datalen: (%d < 4) pssh: %d", Integer.valueOf(len2), Integer.valueOf(psshsize)));
                    return null;
                }
                byte[] subset = Arrays.copyOfRange(pssh, i4, i4 + 4);
                if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
                    i2 = ((subset[3] & 255) << 24) | ((subset[2] & 255) << 16) | ((subset[1] & 255) << 8);
                    i = subset[0] & 255;
                } else {
                    i2 = ((subset[0] & 255) << 24) | ((subset[1] & 255) << 16) | ((subset[2] & 255) << 8);
                    i = subset[3] & 255;
                }
                int datalen = i2 | i;
                int i5 = i4 + 4;
                int len3 = len2 - 4;
                if (len3 < datalen) {
                    Log.w(MediaPlayer2Impl.TAG, String.format("parsePSSH: len is too short to parse data: (%d < %d) pssh: %d", Integer.valueOf(len3), Integer.valueOf(datalen), Integer.valueOf(psshsize)));
                    return null;
                }
                byte[] data = Arrays.copyOfRange(pssh, i5, i5 + datalen);
                i3 = i5 + datalen;
                len = len3 - datalen;
                Log.v(MediaPlayer2Impl.TAG, String.format("parsePSSH[%d]: <%s, %s> pssh: %d", Integer.valueOf(numentries), uuid, arrToHex(data), Integer.valueOf(psshsize)));
                numentries++;
                result.put(uuid, data);
            }
            return result;
        }
    }

    public static final class NoDrmSchemeExceptionImpl extends MediaPlayer2.NoDrmSchemeException {
        public NoDrmSchemeExceptionImpl(String detailMessage) {
            super(detailMessage);
        }
    }

    public static final class ProvisioningNetworkErrorExceptionImpl extends MediaPlayer2.ProvisioningNetworkErrorException {
        public ProvisioningNetworkErrorExceptionImpl(String detailMessage) {
            super(detailMessage);
        }
    }

    public static final class ProvisioningServerErrorExceptionImpl extends MediaPlayer2.ProvisioningServerErrorException {
        public ProvisioningServerErrorExceptionImpl(String detailMessage) {
            super(detailMessage);
        }
    }

    /* access modifiers changed from: private */
    public abstract class Task implements Runnable {
        private DataSourceDesc mDSD;
        private final int mMediaCallType;
        private final boolean mNeedToWaitForEventToComplete;

        /* access modifiers changed from: package-private */
        public abstract void process() throws IOException, MediaPlayer2.NoDrmSchemeException;

        Task(int mediaCallType, boolean needToWaitForEventToComplete) {
            this.mMediaCallType = mediaCallType;
            this.mNeedToWaitForEventToComplete = needToWaitForEventToComplete;
        }

        public void run() {
            int status = 0;
            try {
                process();
            } catch (IllegalStateException e) {
                status = 1;
            } catch (IllegalArgumentException e2) {
                status = 2;
            } catch (SecurityException e3) {
                status = 3;
            } catch (IOException e4) {
                status = 4;
            } catch (Exception e5) {
                status = Integer.MIN_VALUE;
            }
            this.mDSD = MediaPlayer2Impl.this.getCurrentDataSource();
            if (!this.mNeedToWaitForEventToComplete || status != 0) {
                sendCompleteNotification(status);
                synchronized (MediaPlayer2Impl.this.mTaskLock) {
                    MediaPlayer2Impl.this.mCurrentTask = null;
                    MediaPlayer2Impl.this.processPendingTask_l();
                }
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void sendCompleteNotification(final int status) {
            if (this.mMediaCallType != 1003) {
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.Task.AnonymousClass1 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier
                    public void notify(MediaPlayer2.EventCallback cb) {
                        cb.onCallCompleted(MediaPlayer2Impl.this, Task.this.mDSD, Task.this.mMediaCallType, status);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public static class DataSourceError {
        final DataSourceDesc mDSD;
        final int mExtra;
        final int mWhat;

        DataSourceError(DataSourceDesc dsd, int what, int extra) {
            this.mDSD = dsd;
            this.mWhat = what;
            this.mExtra = extra;
        }
    }

    /* access modifiers changed from: private */
    public class MediaPlayerSource {
        final AtomicInteger mBufferedPercentage = new AtomicInteger(0);
        int mBufferingState = 0;
        volatile DataSourceDesc mDSD;
        int mMp2State = 1001;
        boolean mPlayPending;
        final MediaPlayer mPlayer = new MediaPlayer();
        int mPlayerState = 0;
        int mSourceState = 0;

        MediaPlayerSource(DataSourceDesc dsd) {
            this.mDSD = dsd;
            MediaPlayer2Impl.this.setUpListeners(this);
        }

        /* access modifiers changed from: package-private */
        public DataSourceDesc getDSD() {
            return this.mDSD;
        }
    }

    /* access modifiers changed from: private */
    public class MediaPlayerSourceQueue {
        AudioAttributesCompat mAudioAttributes;
        Integer mAudioSessionId;
        Integer mAuxEffect;
        Float mAuxEffectSendLevel;
        PlaybackParams mPlaybackParams;
        List<MediaPlayerSource> mQueue = new ArrayList();
        Surface mSurface;
        SyncParams mSyncParams;
        Float mVolume = Float.valueOf(1.0f);

        MediaPlayerSourceQueue() {
            this.mQueue.add(new MediaPlayerSource(null));
        }

        /* access modifiers changed from: package-private */
        public synchronized MediaPlayer getCurrentPlayer() {
            return this.mQueue.get(0).mPlayer;
        }

        /* access modifiers changed from: package-private */
        public synchronized MediaPlayerSource getFirst() {
            return this.mQueue.get(0);
        }

        /* access modifiers changed from: package-private */
        public synchronized void setFirst(DataSourceDesc dsd) throws IOException {
            if (this.mQueue.isEmpty()) {
                this.mQueue.add(0, new MediaPlayerSource(dsd));
            } else {
                this.mQueue.get(0).mDSD = dsd;
                MediaPlayer2Impl.this.setUpListeners(this.mQueue.get(0));
            }
            MediaPlayer2Impl.handleDataSource(this.mQueue.get(0));
        }

        /* access modifiers changed from: package-private */
        public synchronized DataSourceError setNext(DataSourceDesc dsd) {
            MediaPlayerSource src = new MediaPlayerSource(dsd);
            if (this.mQueue.isEmpty()) {
                this.mQueue.add(src);
                return prepareAt(0);
            }
            this.mQueue.add(1, src);
            return prepareAt(1);
        }

        /* access modifiers changed from: package-private */
        public synchronized DataSourceError setNextMultiple(List<DataSourceDesc> descs) {
            List<MediaPlayerSource> sources = new ArrayList<>();
            for (DataSourceDesc dsd : descs) {
                sources.add(new MediaPlayerSource(dsd));
            }
            if (this.mQueue.isEmpty()) {
                this.mQueue.addAll(sources);
                return prepareAt(0);
            }
            this.mQueue.addAll(1, sources);
            return prepareAt(1);
        }

        /* access modifiers changed from: package-private */
        public synchronized void play() {
            MediaPlayerSource src = this.mQueue.get(0);
            if (src.mSourceState == 2) {
                src.mPlayer.start();
                setMp2State(src.mPlayer, 1004);
            } else {
                throw new IllegalStateException();
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void prepare() {
            getCurrentPlayer().prepareAsync();
        }

        /* access modifiers changed from: package-private */
        public synchronized void release() {
            getCurrentPlayer().release();
        }

        /* access modifiers changed from: package-private */
        public synchronized void prepareAsync() {
            MediaPlayer mp = getCurrentPlayer();
            mp.prepareAsync();
            setBufferingState(mp, 2);
        }

        /* access modifiers changed from: package-private */
        public synchronized void pause() {
            MediaPlayer mp = getCurrentPlayer();
            mp.pause();
            setMp2State(mp, 1003);
        }

        /* access modifiers changed from: package-private */
        public synchronized long getCurrentPosition() {
            return (long) getCurrentPlayer().getCurrentPosition();
        }

        /* access modifiers changed from: package-private */
        public synchronized long getDuration() {
            return (long) getCurrentPlayer().getDuration();
        }

        /* access modifiers changed from: package-private */
        public synchronized long getBufferedPosition() {
            MediaPlayerSource src;
            src = this.mQueue.get(0);
            return (((long) src.mPlayer.getDuration()) * ((long) src.mBufferedPercentage.get())) / 100;
        }

        /* access modifiers changed from: package-private */
        public synchronized void setAudioAttributes(AudioAttributesCompat attributes) {
            AudioAttributes attr;
            this.mAudioAttributes = attributes;
            if (this.mAudioAttributes == null) {
                attr = null;
            } else {
                attr = (AudioAttributes) this.mAudioAttributes.unwrap();
            }
            getCurrentPlayer().setAudioAttributes(attr);
        }

        /* access modifiers changed from: package-private */
        public synchronized AudioAttributesCompat getAudioAttributes() {
            return this.mAudioAttributes;
        }

        /* access modifiers changed from: package-private */
        public synchronized DataSourceError onPrepared(MediaPlayer mp) {
            for (int i = 0; i < this.mQueue.size(); i++) {
                MediaPlayerSource src = this.mQueue.get(i);
                if (mp == src.mPlayer) {
                    if (i == 0) {
                        if (src.mPlayPending) {
                            src.mPlayPending = false;
                            src.mPlayer.start();
                            setMp2State(src.mPlayer, 1004);
                        } else {
                            setMp2State(src.mPlayer, 1002);
                        }
                    }
                    src.mSourceState = 2;
                    setBufferingState(src.mPlayer, 1);
                    return prepareAt(i + 1);
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public synchronized DataSourceError onCompletion(MediaPlayer mp) {
            if (!this.mQueue.isEmpty() && mp == getCurrentPlayer()) {
                if (this.mQueue.size() == 1) {
                    setMp2State(mp, 1003);
                    final DataSourceDesc dsd = this.mQueue.get(0).getDSD();
                    MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                        /* class android.support.v4.media.MediaPlayer2Impl.MediaPlayerSourceQueue.AnonymousClass1 */

                        @Override // android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier
                        public void notify(MediaPlayer2.EventCallback callback) {
                            callback.onInfo(MediaPlayer2Impl.this, dsd, 6, 0);
                        }
                    });
                    return null;
                }
                moveToNext();
            }
            return playCurrent();
        }

        /* access modifiers changed from: package-private */
        public synchronized void moveToNext() {
            MediaPlayerSource src1 = this.mQueue.remove(0);
            src1.mPlayer.release();
            if (!this.mQueue.isEmpty()) {
                final MediaPlayerSource src2 = this.mQueue.get(0);
                if (src1.mPlayerState != src2.mPlayerState) {
                    MediaPlayer2Impl.this.notifyPlayerEvent(new PlayerEventNotifier() {
                        /* class android.support.v4.media.MediaPlayer2Impl.MediaPlayerSourceQueue.AnonymousClass2 */

                        @Override // android.support.v4.media.MediaPlayer2Impl.PlayerEventNotifier
                        public void notify(BaseMediaPlayer.PlayerEventCallback cb) {
                            cb.onPlayerStateChanged(MediaPlayer2Impl.this.mBaseMediaPlayerImpl, src2.mPlayerState);
                        }
                    });
                }
                MediaPlayer2Impl.this.notifyPlayerEvent(new PlayerEventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.MediaPlayerSourceQueue.AnonymousClass3 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.PlayerEventNotifier
                    public void notify(BaseMediaPlayer.PlayerEventCallback cb) {
                        cb.onCurrentDataSourceChanged(MediaPlayer2Impl.this.mBaseMediaPlayerImpl, src2.mDSD);
                    }
                });
            } else {
                throw new IllegalStateException("player/source queue emptied");
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized DataSourceError playCurrent() {
            DataSourceError err;
            err = null;
            final MediaPlayerSource src = this.mQueue.get(0);
            if (this.mSurface != null) {
                src.mPlayer.setSurface(this.mSurface);
            }
            if (this.mVolume != null) {
                src.mPlayer.setVolume(this.mVolume.floatValue(), this.mVolume.floatValue());
            }
            if (this.mAudioAttributes != null) {
                src.mPlayer.setAudioAttributes((AudioAttributes) this.mAudioAttributes.unwrap());
            }
            if (this.mAuxEffect != null) {
                src.mPlayer.attachAuxEffect(this.mAuxEffect.intValue());
            }
            if (this.mAuxEffectSendLevel != null) {
                src.mPlayer.setAuxEffectSendLevel(this.mAuxEffectSendLevel.floatValue());
            }
            if (this.mSyncParams != null) {
                src.mPlayer.setSyncParams(this.mSyncParams);
            }
            if (this.mPlaybackParams != null) {
                src.mPlayer.setPlaybackParams(this.mPlaybackParams);
            }
            if (src.mSourceState == 2) {
                src.mPlayer.start();
                setMp2State(src.mPlayer, 1004);
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    /* class android.support.v4.media.MediaPlayer2Impl.MediaPlayerSourceQueue.AnonymousClass4 */

                    @Override // android.support.v4.media.MediaPlayer2Impl.Mp2EventNotifier
                    public void notify(MediaPlayer2.EventCallback callback) {
                        callback.onInfo(MediaPlayer2Impl.this, src.getDSD(), 2, 0);
                    }
                });
            } else {
                if (src.mSourceState == 0) {
                    err = prepareAt(0);
                }
                src.mPlayPending = true;
            }
            return err;
        }

        /* access modifiers changed from: package-private */
        public synchronized void onError(MediaPlayer mp) {
            setMp2State(mp, MediaPlayer2.MEDIAPLAYER2_STATE_ERROR);
            setBufferingState(mp, 0);
        }

        /* access modifiers changed from: package-private */
        public synchronized DataSourceError prepareAt(int n) {
            if (n < this.mQueue.size() && this.mQueue.get(n).mSourceState == 0) {
                if (n == 0 || getPlayerState() != 0) {
                    MediaPlayerSource src = this.mQueue.get(n);
                    try {
                        if (this.mAudioSessionId != null) {
                            src.mPlayer.setAudioSessionId(this.mAudioSessionId.intValue());
                        }
                        src.mSourceState = 1;
                        MediaPlayer2Impl.handleDataSource(src);
                        src.mPlayer.prepareAsync();
                        return null;
                    } catch (Exception e) {
                        DataSourceDesc dsd = src.getDSD();
                        setMp2State(src.mPlayer, MediaPlayer2.MEDIAPLAYER2_STATE_ERROR);
                        return new DataSourceError(dsd, 1, MediaPlayer2.MEDIA_ERROR_UNSUPPORTED);
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public synchronized void skipToNext() {
            if (this.mQueue.size() > 1) {
                MediaPlayerSource src = this.mQueue.get(0);
                moveToNext();
                if (src.mPlayerState == 2 || src.mPlayPending) {
                    playCurrent();
                }
            } else {
                throw new IllegalStateException("No next source available");
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void setLooping(boolean loop) {
            getCurrentPlayer().setLooping(loop);
        }

        /* access modifiers changed from: package-private */
        public synchronized void setPlaybackParams(PlaybackParams playbackParams) {
            getCurrentPlayer().setPlaybackParams(playbackParams);
            this.mPlaybackParams = playbackParams;
        }

        /* access modifiers changed from: package-private */
        public synchronized float getVolume() {
            return this.mVolume.floatValue();
        }

        /* access modifiers changed from: package-private */
        public synchronized void setVolume(float volume) {
            this.mVolume = Float.valueOf(volume);
            getCurrentPlayer().setVolume(volume, volume);
        }

        /* access modifiers changed from: package-private */
        public synchronized void setSurface(Surface surface) {
            this.mSurface = surface;
            getCurrentPlayer().setSurface(surface);
        }

        /* access modifiers changed from: package-private */
        public synchronized int getVideoWidth() {
            return getCurrentPlayer().getVideoWidth();
        }

        /* access modifiers changed from: package-private */
        public synchronized int getVideoHeight() {
            return getCurrentPlayer().getVideoHeight();
        }

        /* access modifiers changed from: package-private */
        public synchronized PersistableBundle getMetrics() {
            return getCurrentPlayer().getMetrics();
        }

        /* access modifiers changed from: package-private */
        public synchronized PlaybackParams getPlaybackParams() {
            return getCurrentPlayer().getPlaybackParams();
        }

        /* access modifiers changed from: package-private */
        public synchronized void setSyncParams(SyncParams params) {
            getCurrentPlayer().setSyncParams(params);
            this.mSyncParams = params;
        }

        /* access modifiers changed from: package-private */
        public synchronized SyncParams getSyncParams() {
            return getCurrentPlayer().getSyncParams();
        }

        /* access modifiers changed from: package-private */
        public synchronized void seekTo(long msec, int mode) {
            getCurrentPlayer().seekTo(msec, mode);
        }

        /* access modifiers changed from: package-private */
        public synchronized void reset() {
            MediaPlayerSource src = this.mQueue.get(0);
            src.mPlayer.reset();
            src.mBufferedPercentage.set(0);
            this.mVolume = Float.valueOf(1.0f);
            this.mSurface = null;
            this.mAuxEffect = null;
            this.mAuxEffectSendLevel = null;
            this.mAudioAttributes = null;
            this.mAudioSessionId = null;
            this.mSyncParams = null;
            this.mPlaybackParams = null;
            setMp2State(src.mPlayer, 1001);
            setBufferingState(src.mPlayer, 0);
        }

        /* access modifiers changed from: package-private */
        public synchronized MediaTimestamp2 getTimestamp() {
            MediaTimestamp t;
            t = getCurrentPlayer().getTimestamp();
            return t == null ? null : new MediaTimestamp2(t);
        }

        /* access modifiers changed from: package-private */
        public synchronized void setAudioSessionId(int sessionId) {
            getCurrentPlayer().setAudioSessionId(sessionId);
        }

        /* access modifiers changed from: package-private */
        public synchronized int getAudioSessionId() {
            return getCurrentPlayer().getAudioSessionId();
        }

        /* access modifiers changed from: package-private */
        public synchronized void attachAuxEffect(int effectId) {
            getCurrentPlayer().attachAuxEffect(effectId);
            this.mAuxEffect = Integer.valueOf(effectId);
        }

        /* access modifiers changed from: package-private */
        public synchronized void setAuxEffectSendLevel(float level) {
            getCurrentPlayer().setAuxEffectSendLevel(level);
            this.mAuxEffectSendLevel = Float.valueOf(level);
        }

        /* access modifiers changed from: package-private */
        public synchronized MediaPlayer.TrackInfo[] getTrackInfo() {
            return getCurrentPlayer().getTrackInfo();
        }

        /* access modifiers changed from: package-private */
        public synchronized int getSelectedTrack(int trackType) {
            return getCurrentPlayer().getSelectedTrack(trackType);
        }

        /* access modifiers changed from: package-private */
        public synchronized void selectTrack(int index) {
            getCurrentPlayer().selectTrack(index);
        }

        /* access modifiers changed from: package-private */
        public synchronized void deselectTrack(int index) {
            getCurrentPlayer().deselectTrack(index);
        }

        /* access modifiers changed from: package-private */
        public synchronized MediaPlayer.DrmInfo getDrmInfo() {
            return getCurrentPlayer().getDrmInfo();
        }

        /* access modifiers changed from: package-private */
        public synchronized void prepareDrm(UUID uuid) throws ResourceBusyException, MediaPlayer.ProvisioningServerErrorException, MediaPlayer.ProvisioningNetworkErrorException, UnsupportedSchemeException {
            getCurrentPlayer().prepareDrm(uuid);
        }

        /* access modifiers changed from: package-private */
        public synchronized void releaseDrm() throws MediaPlayer.NoDrmSchemeException {
            getCurrentPlayer().stop();
            getCurrentPlayer().releaseDrm();
        }

        /* access modifiers changed from: package-private */
        public synchronized byte[] provideKeyResponse(byte[] keySetId, byte[] response) throws DeniedByServerException, MediaPlayer.NoDrmSchemeException {
            return getCurrentPlayer().provideKeyResponse(keySetId, response);
        }

        /* access modifiers changed from: package-private */
        public synchronized void restoreKeys(byte[] keySetId) throws MediaPlayer.NoDrmSchemeException {
            getCurrentPlayer().restoreKeys(keySetId);
        }

        /* access modifiers changed from: package-private */
        public synchronized String getDrmPropertyString(String propertyName) throws MediaPlayer.NoDrmSchemeException {
            return getCurrentPlayer().getDrmPropertyString(propertyName);
        }

        /* access modifiers changed from: package-private */
        public synchronized void setDrmPropertyString(String propertyName, String value) throws MediaPlayer.NoDrmSchemeException {
            getCurrentPlayer().setDrmPropertyString(propertyName, value);
        }

        /* access modifiers changed from: package-private */
        public synchronized void setOnDrmConfigHelper(MediaPlayer.OnDrmConfigHelper onDrmConfigHelper) {
            getCurrentPlayer().setOnDrmConfigHelper(onDrmConfigHelper);
        }

        /* access modifiers changed from: package-private */
        public synchronized MediaDrm.KeyRequest getKeyRequest(byte[] keySetId, byte[] initData, String mimeType, int keyType, Map<String, String> optionalParameters) throws MediaPlayer.NoDrmSchemeException {
            return getCurrentPlayer().getKeyRequest(keySetId, initData, mimeType, keyType, optionalParameters);
        }

        /* access modifiers changed from: package-private */
        public synchronized void setMp2State(MediaPlayer mp, int mp2State) {
            for (MediaPlayerSource src : this.mQueue) {
                if (src.mPlayer == mp) {
                    if (src.mMp2State != mp2State) {
                        src.mMp2State = mp2State;
                        final int playerState = ((Integer) MediaPlayer2Impl.sStateMap.get(Integer.valueOf(mp2State))).intValue();
                        if (src.mPlayerState != playerState) {
                            src.mPlayerState = playerState;
                            MediaPlayer2Impl.this.notifyPlayerEvent(new PlayerEventNotifier() {
                                /* class android.support.v4.media.MediaPlayer2Impl.MediaPlayerSourceQueue.AnonymousClass5 */

                                @Override // android.support.v4.media.MediaPlayer2Impl.PlayerEventNotifier
                                public void notify(BaseMediaPlayer.PlayerEventCallback cb) {
                                    cb.onPlayerStateChanged(MediaPlayer2Impl.this.mBaseMediaPlayerImpl, playerState);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void setBufferingState(MediaPlayer mp, final int state) {
            for (final MediaPlayerSource src : this.mQueue) {
                if (src.mPlayer == mp) {
                    if (src.mBufferingState != state) {
                        src.mBufferingState = state;
                        MediaPlayer2Impl.this.notifyPlayerEvent(new PlayerEventNotifier() {
                            /* class android.support.v4.media.MediaPlayer2Impl.MediaPlayerSourceQueue.AnonymousClass6 */

                            @Override // android.support.v4.media.MediaPlayer2Impl.PlayerEventNotifier
                            public void notify(BaseMediaPlayer.PlayerEventCallback cb) {
                                cb.onBufferingStateChanged(MediaPlayer2Impl.this.mBaseMediaPlayerImpl, src.getDSD(), state);
                            }
                        });
                        return;
                    }
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized int getMediaPlayer2State() {
            return this.mQueue.get(0).mMp2State;
        }

        /* access modifiers changed from: package-private */
        public synchronized int getBufferingState() {
            return this.mQueue.get(0).mBufferingState;
        }

        /* access modifiers changed from: package-private */
        public synchronized int getPlayerState() {
            return this.mQueue.get(0).mPlayerState;
        }

        /* access modifiers changed from: package-private */
        public synchronized MediaPlayerSource getSourceForPlayer(MediaPlayer mp) {
            for (MediaPlayerSource src : this.mQueue) {
                if (src.mPlayer == mp) {
                    return src;
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: private */
    public class BaseMediaPlayerImpl extends BaseMediaPlayer {
        private BaseMediaPlayerImpl() {
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public void play() {
            MediaPlayer2Impl.this.play();
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public void prepare() {
            MediaPlayer2Impl.this.prepare();
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public void pause() {
            MediaPlayer2Impl.this.pause();
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public void reset() {
            MediaPlayer2Impl.this.reset();
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public void skipToNext() {
            MediaPlayer2Impl.this.skipToNext();
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public void seekTo(long pos) {
            MediaPlayer2Impl.this.seekTo(pos);
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public long getCurrentPosition() {
            return MediaPlayer2Impl.this.getCurrentPosition();
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public long getDuration() {
            return MediaPlayer2Impl.this.getDuration();
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public long getBufferedPosition() {
            return MediaPlayer2Impl.this.getBufferedPosition();
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public int getPlayerState() {
            return MediaPlayer2Impl.this.getPlayerState();
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public int getBufferingState() {
            return MediaPlayer2Impl.this.getBufferingState();
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public void setAudioAttributes(AudioAttributesCompat attributes) {
            MediaPlayer2Impl.this.setAudioAttributes(attributes);
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public AudioAttributesCompat getAudioAttributes() {
            return MediaPlayer2Impl.this.getAudioAttributes();
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public void setDataSource(DataSourceDesc dsd) {
            MediaPlayer2Impl.this.setDataSource(dsd);
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public void setNextDataSource(DataSourceDesc dsd) {
            MediaPlayer2Impl.this.setNextDataSource(dsd);
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public void setNextDataSources(List<DataSourceDesc> dsds) {
            MediaPlayer2Impl.this.setNextDataSources(dsds);
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public DataSourceDesc getCurrentDataSource() {
            return MediaPlayer2Impl.this.getCurrentDataSource();
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public void loopCurrent(boolean loop) {
            MediaPlayer2Impl.this.loopCurrent(loop);
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public void setPlaybackSpeed(float speed) {
            MediaPlayer2Impl mediaPlayer2Impl = MediaPlayer2Impl.this;
            mediaPlayer2Impl.setPlaybackParams(new PlaybackParams2.Builder(mediaPlayer2Impl.getPlaybackParams().getPlaybackParams()).setSpeed(speed).build());
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public float getPlaybackSpeed() {
            return MediaPlayer2Impl.this.getPlaybackParams().getSpeed().floatValue();
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public void setPlayerVolume(float volume) {
            MediaPlayer2Impl.this.setPlayerVolume(volume);
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public float getPlayerVolume() {
            return MediaPlayer2Impl.this.getPlayerVolume();
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public void registerPlayerEventCallback(Executor e, BaseMediaPlayer.PlayerEventCallback cb) {
            MediaPlayer2Impl.this.registerPlayerEventCallback(e, cb);
        }

        @Override // android.support.v4.media.BaseMediaPlayer
        public void unregisterPlayerEventCallback(BaseMediaPlayer.PlayerEventCallback cb) {
            MediaPlayer2Impl.this.unregisterPlayerEventCallback(cb);
        }

        @Override // java.lang.AutoCloseable
        public void close() throws Exception {
            MediaPlayer2Impl.this.close();
        }
    }
}
