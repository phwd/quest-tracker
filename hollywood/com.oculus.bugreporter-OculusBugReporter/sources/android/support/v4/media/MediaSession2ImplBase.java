package android.support.v4.media;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.BaseMediaPlayer;
import android.support.v4.media.MediaController2;
import android.support.v4.media.MediaMetadata2;
import android.support.v4.media.MediaPlaylistAgent;
import android.support.v4.media.MediaSession2;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.util.ObjectsCompat;
import android.text.TextUtils;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;

/* access modifiers changed from: package-private */
@TargetApi(19)
public class MediaSession2ImplBase implements MediaSession2.SupportLibraryImpl {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    static final String TAG = "MS2ImplBase";
    private final AudioFocusHandler mAudioFocusHandler;
    private final AudioManager mAudioManager;
    private final MediaSession2.SessionCallback mCallback;
    private final Executor mCallbackExecutor;
    private final Context mContext;
    @GuardedBy("mLock")
    private MediaSession2.OnDataSourceMissingHelper mDsmHelper;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private final MediaSession2 mInstance;
    final Object mLock = new Object();
    @GuardedBy("mLock")
    private MediaController2.PlaybackInfo mPlaybackInfo;
    @GuardedBy("mLock")
    private BaseMediaPlayer mPlayer;
    private final BaseMediaPlayer.PlayerEventCallback mPlayerEventCallback;
    @GuardedBy("mLock")
    private MediaPlaylistAgent mPlaylistAgent;
    private final MediaPlaylistAgent.PlaylistEventCallback mPlaylistEventCallback;
    private final MediaSession2Stub mSession2Stub;
    private final PendingIntent mSessionActivity;
    private final MediaSessionCompat mSessionCompat;
    private final MediaSessionLegacyStub mSessionLegacyStub;
    @GuardedBy("mLock")
    private SessionPlaylistAgentImplBase mSessionPlaylistAgent;
    private final SessionToken2 mSessionToken;
    @GuardedBy("mLock")
    private VolumeProviderCompat mVolumeProvider;

    /* access modifiers changed from: package-private */
    @FunctionalInterface
    public interface NotifyRunnable {
        void run(MediaSession2.ControllerCb controllerCb) throws RemoteException;
    }

    MediaSession2ImplBase(MediaSession2 instance, Context context, String id, BaseMediaPlayer player, MediaPlaylistAgent playlistAgent, VolumeProviderCompat volumeProvider, PendingIntent sessionActivity, Executor callbackExecutor, MediaSession2.SessionCallback callback) {
        this.mContext = context;
        this.mInstance = instance;
        this.mHandlerThread = new HandlerThread("MediaController2_Thread");
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mSession2Stub = new MediaSession2Stub(this);
        this.mSessionLegacyStub = new MediaSessionLegacyStub(this);
        this.mSessionActivity = sessionActivity;
        this.mCallback = callback;
        this.mCallbackExecutor = callbackExecutor;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mPlayerEventCallback = new MyPlayerEventCallback();
        this.mPlaylistEventCallback = new MyPlaylistEventCallback();
        this.mAudioFocusHandler = new AudioFocusHandler(context, getInstance());
        String libraryService = getServiceName(context, MediaLibraryService2.SERVICE_INTERFACE, id);
        String sessionService = getServiceName(context, MediaSessionService2.SERVICE_INTERFACE, id);
        if (sessionService == null || libraryService == null) {
            if (libraryService != null) {
                this.mSessionToken = new SessionToken2(new SessionToken2ImplBase(Process.myUid(), 2, context.getPackageName(), libraryService, id, this.mSession2Stub));
            } else if (sessionService != null) {
                this.mSessionToken = new SessionToken2(new SessionToken2ImplBase(Process.myUid(), 1, context.getPackageName(), sessionService, id, this.mSession2Stub));
            } else {
                this.mSessionToken = new SessionToken2(new SessionToken2ImplBase(Process.myUid(), 0, context.getPackageName(), null, id, this.mSession2Stub));
            }
            this.mSessionCompat = new MediaSessionCompat(context, id, this.mSessionToken);
            this.mSessionCompat.setCallback(this.mSessionLegacyStub, this.mHandler);
            this.mSessionCompat.setSessionActivity(sessionActivity);
            updatePlayer(player, playlistAgent, volumeProvider);
            return;
        }
        throw new IllegalArgumentException("Ambiguous session type. Multiple session services define the same id=" + id);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0049, code lost:
        if (r12 != null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x004b, code lost:
        r9.mSessionCompat.setPlaybackToLocal(getLegacyStreamType(r10.getAudioAttributes()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0058, code lost:
        if (r10 == r5) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x005a, code lost:
        r10.registerPlayerEventCallback(r9.mCallbackExecutor, r9.mPlayerEventCallback);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0061, code lost:
        if (r5 == null) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0063, code lost:
        r5.unregisterPlayerEventCallback(r9.mPlayerEventCallback);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0068, code lost:
        if (r11 == r2) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006a, code lost:
        r11.registerPlaylistEventCallback(r9.mCallbackExecutor, r9.mPlaylistEventCallback);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0071, code lost:
        if (r2 == null) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0073, code lost:
        r2.unregisterPlaylistEventCallback(r9.mPlaylistEventCallback);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0078, code lost:
        if (r5 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x007a, code lost:
        if (r6 == false) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x007c, code lost:
        notifyAgentUpdatedNotLocked(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x007f, code lost:
        if (r4 == false) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0081, code lost:
        notifyPlayerUpdatedNotLocked(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0084, code lost:
        if (r3 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0086, code lost:
        notifyToAllControllers(new android.support.v4.media.MediaSession2ImplBase.AnonymousClass1(r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        return;
     */
    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updatePlayer(@android.support.annotation.NonNull android.support.v4.media.BaseMediaPlayer r10, @android.support.annotation.Nullable android.support.v4.media.MediaPlaylistAgent r11, @android.support.annotation.Nullable android.support.v4.media.VolumeProviderCompat r12) {
        /*
        // Method dump skipped, instructions count: 167
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.updatePlayer(android.support.v4.media.BaseMediaPlayer, android.support.v4.media.MediaPlaylistAgent, android.support.v4.media.VolumeProviderCompat):void");
    }

    /* JADX INFO: Multiple debug info for r0v3 android.support.v4.media.MediaController2$PlaybackInfo: [D('stream' int), D('info' android.support.v4.media.MediaController2$PlaybackInfo)] */
    private MediaController2.PlaybackInfo createPlaybackInfo(VolumeProviderCompat volumeProvider, AudioAttributesCompat attrs) {
        if (volumeProvider != null) {
            return MediaController2.PlaybackInfo.createPlaybackInfo(2, attrs, volumeProvider.getVolumeControl(), volumeProvider.getMaxVolume(), volumeProvider.getCurrentVolume());
        }
        int stream = getLegacyStreamType(attrs);
        int controlType = 2;
        if (Build.VERSION.SDK_INT >= 21 && this.mAudioManager.isVolumeFixed()) {
            controlType = 0;
        }
        return MediaController2.PlaybackInfo.createPlaybackInfo(1, attrs, controlType, this.mAudioManager.getStreamMaxVolume(stream), this.mAudioManager.getStreamVolume(stream));
    }

    private int getLegacyStreamType(@Nullable AudioAttributesCompat attrs) {
        int stream;
        if (attrs == null || (stream = attrs.getLegacyStreamType()) == Integer.MIN_VALUE) {
            return 3;
        }
        return stream;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        synchronized (this.mLock) {
            if (this.mPlayer != null) {
                this.mAudioFocusHandler.close();
                this.mPlayer.unregisterPlayerEventCallback(this.mPlayerEventCallback);
                this.mPlayer = null;
                this.mSessionCompat.release();
                notifyToAllControllers(new NotifyRunnable() {
                    /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass2 */

                    @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                    public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                        callback.onDisconnected();
                    }
                });
                this.mHandler.removeCallbacksAndMessages(null);
                if (this.mHandlerThread.isAlive()) {
                    if (Build.VERSION.SDK_INT >= 18) {
                        this.mHandlerThread.quitSafely();
                    } else {
                        this.mHandlerThread.quit();
                    }
                }
            }
        }
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    @NonNull
    public BaseMediaPlayer getPlayer() {
        BaseMediaPlayer baseMediaPlayer;
        synchronized (this.mLock) {
            baseMediaPlayer = this.mPlayer;
        }
        return baseMediaPlayer;
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    @NonNull
    public MediaPlaylistAgent getPlaylistAgent() {
        MediaPlaylistAgent mediaPlaylistAgent;
        synchronized (this.mLock) {
            mediaPlaylistAgent = this.mPlaylistAgent;
        }
        return mediaPlaylistAgent;
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    @Nullable
    public VolumeProviderCompat getVolumeProvider() {
        VolumeProviderCompat volumeProviderCompat;
        synchronized (this.mLock) {
            volumeProviderCompat = this.mVolumeProvider;
        }
        return volumeProviderCompat;
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    @NonNull
    public SessionToken2 getToken() {
        return this.mSessionToken;
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    @NonNull
    public List<MediaSession2.ControllerInfo> getConnectedControllers() {
        return this.mSession2Stub.getConnectedControllers();
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    public void setCustomLayout(@NonNull MediaSession2.ControllerInfo controller, @NonNull final List<MediaSession2.CommandButton> layout) {
        if (controller == null) {
            throw new IllegalArgumentException("controller shouldn't be null");
        } else if (layout != null) {
            notifyToController(controller, new NotifyRunnable() {
                /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass3 */

                @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                    callback.onCustomLayoutChanged(layout);
                }
            });
        } else {
            throw new IllegalArgumentException("layout shouldn't be null");
        }
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    public void setAllowedCommands(@NonNull MediaSession2.ControllerInfo controller, @NonNull final SessionCommandGroup2 commands) {
        if (controller == null) {
            throw new IllegalArgumentException("controller shouldn't be null");
        } else if (commands != null) {
            this.mSession2Stub.setAllowedCommands(controller, commands);
            notifyToController(controller, new NotifyRunnable() {
                /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass4 */

                @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                    callback.onAllowedCommandsChanged(commands);
                }
            });
        } else {
            throw new IllegalArgumentException("commands shouldn't be null");
        }
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    public void sendCustomCommand(@NonNull final SessionCommand2 command, @Nullable final Bundle args) {
        if (command != null) {
            notifyToAllControllers(new NotifyRunnable() {
                /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass5 */

                @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                    callback.onCustomCommand(command, args, null);
                }
            });
            return;
        }
        throw new IllegalArgumentException("command shouldn't be null");
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    public void sendCustomCommand(@NonNull MediaSession2.ControllerInfo controller, @NonNull final SessionCommand2 command, @Nullable final Bundle args, @Nullable final ResultReceiver receiver) {
        if (controller == null) {
            throw new IllegalArgumentException("controller shouldn't be null");
        } else if (command != null) {
            notifyToController(controller, new NotifyRunnable() {
                /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass6 */

                @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                    callback.onCustomCommand(command, args, receiver);
                }
            });
        } else {
            throw new IllegalArgumentException("command shouldn't be null");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        android.util.Log.w(android.support.v4.media.MediaSession2ImplBase.TAG, "play() wouldn't be called because of the failure in audio focus");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000e, code lost:
        if (r4.mAudioFocusHandler.onPlayRequested() == false) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r1.play();
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void play() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.BaseMediaPlayer r1 = r4.mPlayer     // Catch:{ all -> 0x002f }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            if (r1 == 0) goto L_0x001c
            android.support.v4.media.AudioFocusHandler r0 = r4.mAudioFocusHandler
            boolean r0 = r0.onPlayRequested()
            if (r0 == 0) goto L_0x0014
            r1.play()
            goto L_0x002c
        L_0x0014:
            java.lang.String r0 = "MS2ImplBase"
            java.lang.String r2 = "play() wouldn't be called because of the failure in audio focus"
            android.util.Log.w(r0, r2)
            goto L_0x002c
        L_0x001c:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x002c
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x002c:
            return
        L_0x002d:
            r2 = move-exception
            goto L_0x0031
        L_0x002f:
            r2 = move-exception
            r1 = 0
        L_0x0031:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.play():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        android.util.Log.w(android.support.v4.media.MediaSession2ImplBase.TAG, "pause() wouldn't be called of the failure in audio focus");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000e, code lost:
        if (r4.mAudioFocusHandler.onPauseRequested() == false) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r1.pause();
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void pause() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.BaseMediaPlayer r1 = r4.mPlayer     // Catch:{ all -> 0x002f }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            if (r1 == 0) goto L_0x001c
            android.support.v4.media.AudioFocusHandler r0 = r4.mAudioFocusHandler
            boolean r0 = r0.onPauseRequested()
            if (r0 == 0) goto L_0x0014
            r1.pause()
            goto L_0x002c
        L_0x0014:
            java.lang.String r0 = "MS2ImplBase"
            java.lang.String r2 = "pause() wouldn't be called of the failure in audio focus"
            android.util.Log.w(r0, r2)
            goto L_0x002c
        L_0x001c:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x002c
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x002c:
            return
        L_0x002d:
            r2 = move-exception
            goto L_0x0031
        L_0x002f:
            r2 = move-exception
            r1 = 0
        L_0x0031:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.pause():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0008, code lost:
        r1.reset();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void reset() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.BaseMediaPlayer r1 = r4.mPlayer     // Catch:{ all -> 0x001f }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x000c
            r1.reset()
            goto L_0x001c
        L_0x000c:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001c
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001c:
            return
        L_0x001d:
            r2 = move-exception
            goto L_0x0021
        L_0x001f:
            r2 = move-exception
            r1 = 0
        L_0x0021:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.reset():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0008, code lost:
        r1.prepare();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void prepare() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.BaseMediaPlayer r1 = r4.mPlayer     // Catch:{ all -> 0x001f }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x000c
            r1.prepare()
            goto L_0x001c
        L_0x000c:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001c
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001c:
            return
        L_0x001d:
            r2 = move-exception
            goto L_0x0021
        L_0x001f:
            r2 = move-exception
            r1 = 0
        L_0x0021:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.prepare():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0008, code lost:
        r1.seekTo(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void seekTo(long r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.BaseMediaPlayer r1 = r4.mPlayer     // Catch:{ all -> 0x001f }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x000c
            r1.seekTo(r5)
            goto L_0x001c
        L_0x000c:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001c
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001c:
            return
        L_0x001d:
            r2 = move-exception
            goto L_0x0021
        L_0x001f:
            r2 = move-exception
            r1 = 0
        L_0x0021:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.seekTo(long):void");
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlayer
    public void skipForward() {
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlayer
    public void skipBackward() {
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlayer
    public void notifyError(final int errorCode, @Nullable final Bundle extras) {
        notifyToAllControllers(new NotifyRunnable() {
            /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass7 */

            @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
            public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                callback.onError(errorCode, extras);
            }
        });
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    public void notifyRoutesInfoChanged(@NonNull MediaSession2.ControllerInfo controller, @Nullable final List<Bundle> routes) {
        notifyToController(controller, new NotifyRunnable() {
            /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass8 */

            @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
            public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                callback.onRoutesInfoChanged(routes);
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x000f, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000c, code lost:
        return r1.getPlayerState();
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getPlayerState() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.BaseMediaPlayer r1 = r4.mPlayer     // Catch:{ all -> 0x0021 }
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x000d
            int r0 = r1.getPlayerState()
            return r0
        L_0x000d:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001d
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001d:
            r0 = 3
            return r0
        L_0x001f:
            r2 = move-exception
            goto L_0x0023
        L_0x0021:
            r2 = move-exception
            r1 = 0
        L_0x0023:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.getPlayerState():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x000f, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000c, code lost:
        return r1.getCurrentPosition();
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getCurrentPosition() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.BaseMediaPlayer r1 = r4.mPlayer     // Catch:{ all -> 0x0022 }
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x000d
            long r2 = r1.getCurrentPosition()
            return r2
        L_0x000d:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001d
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001d:
            r2 = -1
            return r2
        L_0x0020:
            r2 = move-exception
            goto L_0x0024
        L_0x0022:
            r2 = move-exception
            r1 = 0
        L_0x0024:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.getCurrentPosition():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x000f, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000c, code lost:
        return r1.getDuration();
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getDuration() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.BaseMediaPlayer r1 = r4.mPlayer     // Catch:{ all -> 0x0022 }
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x000d
            long r2 = r1.getDuration()
            return r2
        L_0x000d:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001d
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001d:
            r2 = -1
            return r2
        L_0x0020:
            r2 = move-exception
            goto L_0x0024
        L_0x0022:
            r2 = move-exception
            r1 = 0
        L_0x0024:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.getDuration():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x000f, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000c, code lost:
        return r1.getBufferedPosition();
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getBufferedPosition() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.BaseMediaPlayer r1 = r4.mPlayer     // Catch:{ all -> 0x0022 }
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x000d
            long r2 = r1.getBufferedPosition()
            return r2
        L_0x000d:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001d
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001d:
            r2 = -1
            return r2
        L_0x0020:
            r2 = move-exception
            goto L_0x0024
        L_0x0022:
            r2 = move-exception
            r1 = 0
        L_0x0024:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.getBufferedPosition():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x000f, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000c, code lost:
        return r1.getBufferingState();
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getBufferingState() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.BaseMediaPlayer r1 = r4.mPlayer     // Catch:{ all -> 0x0021 }
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x000d
            int r0 = r1.getBufferingState()
            return r0
        L_0x000d:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001d
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001d:
            r0 = 0
            return r0
        L_0x001f:
            r2 = move-exception
            goto L_0x0023
        L_0x0021:
            r2 = move-exception
            r1 = 0
        L_0x0023:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.getBufferingState():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x000f, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return 1.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return 1.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000c, code lost:
        return r1.getPlaybackSpeed();
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public float getPlaybackSpeed() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.BaseMediaPlayer r1 = r4.mPlayer     // Catch:{ all -> 0x0022 }
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x000d
            float r0 = r1.getPlaybackSpeed()
            return r0
        L_0x000d:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001d
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001d:
            r0 = 1065353216(0x3f800000, float:1.0)
            return r0
        L_0x0020:
            r2 = move-exception
            goto L_0x0024
        L_0x0022:
            r2 = move-exception
            r1 = 0
        L_0x0024:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.getPlaybackSpeed():float");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0008, code lost:
        r1.setPlaybackSpeed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setPlaybackSpeed(float r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.BaseMediaPlayer r1 = r4.mPlayer     // Catch:{ all -> 0x001f }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x000c
            r1.setPlaybackSpeed(r5)
            goto L_0x001c
        L_0x000c:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001c
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001c:
            return
        L_0x001d:
            r2 = move-exception
            goto L_0x0021
        L_0x001f:
            r2 = move-exception
            r1 = 0
        L_0x0021:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.setPlaybackSpeed(float):void");
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public void setOnDataSourceMissingHelper(@NonNull MediaSession2.OnDataSourceMissingHelper helper) {
        if (helper != null) {
            synchronized (this.mLock) {
                this.mDsmHelper = helper;
                if (this.mSessionPlaylistAgent != null) {
                    this.mSessionPlaylistAgent.setOnDataSourceMissingHelper(helper);
                }
            }
            return;
        }
        throw new IllegalArgumentException("helper shouldn't be null");
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public void clearOnDataSourceMissingHelper() {
        synchronized (this.mLock) {
            this.mDsmHelper = null;
            if (this.mSessionPlaylistAgent != null) {
                this.mSessionPlaylistAgent.clearOnDataSourceMissingHelper();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0010, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0012, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0007, code lost:
        if (r2 == null) goto L_0x000e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000d, code lost:
        return r2.getPlaylist();
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<android.support.v4.media.MediaItem2> getPlaylist() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            r1 = 0
            android.support.v4.media.MediaPlaylistAgent r2 = r6.mPlaylistAgent     // Catch:{ all -> 0x0021 }
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            if (r2 == 0) goto L_0x000e
            java.util.List r0 = r2.getPlaylist()
            return r0
        L_0x000e:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001e
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r3 = "MS2ImplBase"
            java.lang.String r4 = "API calls after the close()"
            android.util.Log.d(r3, r4, r0)
        L_0x001e:
            return r1
        L_0x001f:
            r1 = move-exception
            goto L_0x0025
        L_0x0021:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L_0x0025:
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.getPlaylist():java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0008, code lost:
        if (r1 == null) goto L_0x000e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000a, code lost:
        r1.setPlaylist(r5, r6);
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setPlaylist(@android.support.annotation.NonNull java.util.List<android.support.v4.media.MediaItem2> r5, @android.support.annotation.Nullable android.support.v4.media.MediaMetadata2 r6) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x0025
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.MediaPlaylistAgent r1 = r4.mPlaylistAgent     // Catch:{ all -> 0x0021 }
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x000e
            r1.setPlaylist(r5, r6)
            goto L_0x001e
        L_0x000e:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001e
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001e:
            return
        L_0x001f:
            r2 = move-exception
            goto L_0x0023
        L_0x0021:
            r2 = move-exception
            r1 = 0
        L_0x0023:
            monitor-exit(r0)
            throw r2
        L_0x0025:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "list shouldn't be null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.setPlaylist(java.util.List, android.support.v4.media.MediaMetadata2):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0008, code lost:
        if (r1 == null) goto L_0x000e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000a, code lost:
        r1.skipToPlaylistItem(r5);
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void skipToPlaylistItem(@android.support.annotation.NonNull android.support.v4.media.MediaItem2 r5) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x0025
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.MediaPlaylistAgent r1 = r4.mPlaylistAgent     // Catch:{ all -> 0x0021 }
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x000e
            r1.skipToPlaylistItem(r5)
            goto L_0x001e
        L_0x000e:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001e
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001e:
            return
        L_0x001f:
            r2 = move-exception
            goto L_0x0023
        L_0x0021:
            r2 = move-exception
            r1 = 0
        L_0x0023:
            monitor-exit(r0)
            throw r2
        L_0x0025:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "item shouldn't be null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.skipToPlaylistItem(android.support.v4.media.MediaItem2):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0008, code lost:
        r1.skipToPreviousItem();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void skipToPreviousItem() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.MediaPlaylistAgent r1 = r4.mPlaylistAgent     // Catch:{ all -> 0x001f }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x000c
            r1.skipToPreviousItem()
            goto L_0x001c
        L_0x000c:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001c
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001c:
            return
        L_0x001d:
            r2 = move-exception
            goto L_0x0021
        L_0x001f:
            r2 = move-exception
            r1 = 0
        L_0x0021:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.skipToPreviousItem():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0008, code lost:
        r1.skipToNextItem();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void skipToNextItem() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.MediaPlaylistAgent r1 = r4.mPlaylistAgent     // Catch:{ all -> 0x001f }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x000c
            r1.skipToNextItem()
            goto L_0x001c
        L_0x000c:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001c
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001c:
            return
        L_0x001d:
            r2 = move-exception
            goto L_0x0021
        L_0x001f:
            r2 = move-exception
            r1 = 0
        L_0x0021:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.skipToNextItem():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0010, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0012, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0007, code lost:
        if (r2 == null) goto L_0x000e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000d, code lost:
        return r2.getPlaylistMetadata();
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.support.v4.media.MediaMetadata2 getPlaylistMetadata() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            r1 = 0
            android.support.v4.media.MediaPlaylistAgent r2 = r6.mPlaylistAgent     // Catch:{ all -> 0x0021 }
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            if (r2 == 0) goto L_0x000e
            android.support.v4.media.MediaMetadata2 r0 = r2.getPlaylistMetadata()
            return r0
        L_0x000e:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001e
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r3 = "MS2ImplBase"
            java.lang.String r4 = "API calls after the close()"
            android.util.Log.d(r3, r4, r0)
        L_0x001e:
            return r1
        L_0x001f:
            r1 = move-exception
            goto L_0x0025
        L_0x0021:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L_0x0025:
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.getPlaylistMetadata():android.support.v4.media.MediaMetadata2");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000a, code lost:
        if (r1 == null) goto L_0x0010;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000c, code lost:
        r1.addPlaylistItem(r5, r6);
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addPlaylistItem(int r5, @android.support.annotation.NonNull android.support.v4.media.MediaItem2 r6) {
        /*
            r4 = this;
            if (r5 < 0) goto L_0x002f
            if (r6 == 0) goto L_0x0027
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.MediaPlaylistAgent r1 = r4.mPlaylistAgent     // Catch:{ all -> 0x0023 }
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            if (r1 == 0) goto L_0x0010
            r1.addPlaylistItem(r5, r6)
            goto L_0x0020
        L_0x0010:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x0020
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x0020:
            return
        L_0x0021:
            r2 = move-exception
            goto L_0x0025
        L_0x0023:
            r2 = move-exception
            r1 = 0
        L_0x0025:
            monitor-exit(r0)
            throw r2
        L_0x0027:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "item shouldn't be null"
            r0.<init>(r1)
            throw r0
        L_0x002f:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "index shouldn't be negative"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.addPlaylistItem(int, android.support.v4.media.MediaItem2):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0008, code lost:
        if (r1 == null) goto L_0x000e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000a, code lost:
        r1.removePlaylistItem(r5);
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removePlaylistItem(@android.support.annotation.NonNull android.support.v4.media.MediaItem2 r5) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x0025
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.MediaPlaylistAgent r1 = r4.mPlaylistAgent     // Catch:{ all -> 0x0021 }
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x000e
            r1.removePlaylistItem(r5)
            goto L_0x001e
        L_0x000e:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001e
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001e:
            return
        L_0x001f:
            r2 = move-exception
            goto L_0x0023
        L_0x0021:
            r2 = move-exception
            r1 = 0
        L_0x0023:
            monitor-exit(r0)
            throw r2
        L_0x0025:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "item shouldn't be null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.removePlaylistItem(android.support.v4.media.MediaItem2):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000a, code lost:
        if (r1 == null) goto L_0x0010;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000c, code lost:
        r1.replacePlaylistItem(r5, r6);
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void replacePlaylistItem(int r5, @android.support.annotation.NonNull android.support.v4.media.MediaItem2 r6) {
        /*
            r4 = this;
            if (r5 < 0) goto L_0x002f
            if (r6 == 0) goto L_0x0027
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.MediaPlaylistAgent r1 = r4.mPlaylistAgent     // Catch:{ all -> 0x0023 }
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            if (r1 == 0) goto L_0x0010
            r1.replacePlaylistItem(r5, r6)
            goto L_0x0020
        L_0x0010:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x0020
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x0020:
            return
        L_0x0021:
            r2 = move-exception
            goto L_0x0025
        L_0x0023:
            r2 = move-exception
            r1 = 0
        L_0x0025:
            monitor-exit(r0)
            throw r2
        L_0x0027:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "item shouldn't be null"
            r0.<init>(r1)
            throw r0
        L_0x002f:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "index shouldn't be negative"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.replacePlaylistItem(int, android.support.v4.media.MediaItem2):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0010, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0012, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0007, code lost:
        if (r2 == null) goto L_0x000e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000d, code lost:
        return r2.getCurrentMediaItem();
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.support.v4.media.MediaItem2 getCurrentMediaItem() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            r1 = 0
            android.support.v4.media.MediaPlaylistAgent r2 = r6.mPlaylistAgent     // Catch:{ all -> 0x0021 }
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            if (r2 == 0) goto L_0x000e
            android.support.v4.media.MediaItem2 r0 = r2.getCurrentMediaItem()
            return r0
        L_0x000e:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001e
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r3 = "MS2ImplBase"
            java.lang.String r4 = "API calls after the close()"
            android.util.Log.d(r3, r4, r0)
        L_0x001e:
            return r1
        L_0x001f:
            r1 = move-exception
            goto L_0x0025
        L_0x0021:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L_0x0025:
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.getCurrentMediaItem():android.support.v4.media.MediaItem2");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0008, code lost:
        r1.updatePlaylistMetadata(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updatePlaylistMetadata(@android.support.annotation.Nullable android.support.v4.media.MediaMetadata2 r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.MediaPlaylistAgent r1 = r4.mPlaylistAgent     // Catch:{ all -> 0x001f }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x000c
            r1.updatePlaylistMetadata(r5)
            goto L_0x001c
        L_0x000c:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001c
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001c:
            return
        L_0x001d:
            r2 = move-exception
            goto L_0x0021
        L_0x001f:
            r2 = move-exception
            r1 = 0
        L_0x0021:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.updatePlaylistMetadata(android.support.v4.media.MediaMetadata2):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x000f, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000c, code lost:
        return r1.getRepeatMode();
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getRepeatMode() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.MediaPlaylistAgent r1 = r4.mPlaylistAgent     // Catch:{ all -> 0x0021 }
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x000d
            int r0 = r1.getRepeatMode()
            return r0
        L_0x000d:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001d
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001d:
            r0 = 0
            return r0
        L_0x001f:
            r2 = move-exception
            goto L_0x0023
        L_0x0021:
            r2 = move-exception
            r1 = 0
        L_0x0023:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.getRepeatMode():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0008, code lost:
        r1.setRepeatMode(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRepeatMode(int r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.MediaPlaylistAgent r1 = r4.mPlaylistAgent     // Catch:{ all -> 0x001f }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x000c
            r1.setRepeatMode(r5)
            goto L_0x001c
        L_0x000c:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001c
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001c:
            return
        L_0x001d:
            r2 = move-exception
            goto L_0x0021
        L_0x001f:
            r2 = move-exception
            r1 = 0
        L_0x0021:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.setRepeatMode(int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x000f, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000c, code lost:
        return r1.getShuffleMode();
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getShuffleMode() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.MediaPlaylistAgent r1 = r4.mPlaylistAgent     // Catch:{ all -> 0x0021 }
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x000d
            int r0 = r1.getShuffleMode()
            return r0
        L_0x000d:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001d
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001d:
            r0 = 0
            return r0
        L_0x001f:
            r2 = move-exception
            goto L_0x0023
        L_0x0021:
            r2 = move-exception
            r1 = 0
        L_0x0023:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.getShuffleMode():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        android.util.Log.d(android.support.v4.media.MediaSession2ImplBase.TAG, "API calls after the close()", new java.lang.IllegalStateException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0006, code lost:
        if (r1 == null) goto L_0x000c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0008, code lost:
        r1.setShuffleMode(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        if (android.support.v4.media.MediaSession2ImplBase.DEBUG == false) goto L_?;
     */
    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setShuffleMode(int r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            android.support.v4.media.MediaPlaylistAgent r1 = r4.mPlaylistAgent     // Catch:{ all -> 0x001f }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x000c
            r1.setShuffleMode(r5)
            goto L_0x001c
        L_0x000c:
            boolean r0 = android.support.v4.media.MediaSession2ImplBase.DEBUG
            if (r0 == 0) goto L_0x001c
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            java.lang.String r2 = "MS2ImplBase"
            java.lang.String r3 = "API calls after the close()"
            android.util.Log.d(r2, r3, r0)
        L_0x001c:
            return
        L_0x001d:
            r2 = move-exception
            goto L_0x0021
        L_0x001f:
            r2 = move-exception
            r1 = 0
        L_0x0021:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2ImplBase.setShuffleMode(int):void");
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    @NonNull
    public MediaSession2 getInstance() {
        return this.mInstance;
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    @NonNull
    public IBinder getSessionBinder() {
        return this.mSession2Stub.asBinder();
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    public Context getContext() {
        return this.mContext;
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    public Executor getCallbackExecutor() {
        return this.mCallbackExecutor;
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    public MediaSession2.SessionCallback getCallback() {
        return this.mCallback;
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    public MediaSessionCompat getSessionCompat() {
        return this.mSessionCompat;
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    public AudioFocusHandler getAudioFocusHandler() {
        return this.mAudioFocusHandler;
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    public boolean isClosed() {
        return !this.mHandlerThread.isAlive();
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    public PlaybackStateCompat getPlaybackStateCompat() {
        PlaybackStateCompat build;
        synchronized (this.mLock) {
            build = new PlaybackStateCompat.Builder().setState(MediaUtils2.convertToPlaybackStateCompatState(getPlayerState(), getBufferingState()), getCurrentPosition(), getPlaybackSpeed()).setActions(3670015).setBufferedPosition(getBufferedPosition()).build();
        }
        return build;
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    public MediaController2.PlaybackInfo getPlaybackInfo() {
        MediaController2.PlaybackInfo playbackInfo;
        synchronized (this.mLock) {
            playbackInfo = this.mPlaybackInfo;
        }
        return playbackInfo;
    }

    @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
    public PendingIntent getSessionActivity() {
        return this.mSessionActivity;
    }

    private static String getServiceName(Context context, String serviceAction, String id) {
        PackageManager manager = context.getPackageManager();
        Intent serviceIntent = new Intent(serviceAction);
        serviceIntent.setPackage(context.getPackageName());
        List<ResolveInfo> services = manager.queryIntentServices(serviceIntent, 128);
        String serviceName = null;
        if (services != null) {
            for (int i = 0; i < services.size(); i++) {
                String serviceId = SessionToken2.getSessionId(services.get(i));
                if (!(serviceId == null || !TextUtils.equals(id, serviceId) || services.get(i).serviceInfo == null)) {
                    if (serviceName == null) {
                        serviceName = services.get(i).serviceInfo.name;
                    } else {
                        throw new IllegalArgumentException("Ambiguous session type. Multiple session services define the same id=" + id);
                    }
                }
            }
        }
        return serviceName;
    }

    private void notifyAgentUpdatedNotLocked(MediaPlaylistAgent oldAgent) {
        List<MediaItem2> oldPlaylist = oldAgent.getPlaylist();
        final List<MediaItem2> newPlaylist = getPlaylist();
        if (!ObjectsCompat.equals(oldPlaylist, newPlaylist)) {
            notifyToAllControllers(new NotifyRunnable() {
                /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass9 */

                @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                    callback.onPlaylistChanged(newPlaylist, MediaSession2ImplBase.this.getPlaylistMetadata());
                }
            });
        } else {
            MediaMetadata2 oldMetadata = oldAgent.getPlaylistMetadata();
            final MediaMetadata2 newMetadata = getPlaylistMetadata();
            if (!ObjectsCompat.equals(oldMetadata, newMetadata)) {
                notifyToAllControllers(new NotifyRunnable() {
                    /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass10 */

                    @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                    public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                        callback.onPlaylistMetadataChanged(newMetadata);
                    }
                });
            }
        }
        MediaItem2 oldCurrentItem = oldAgent.getCurrentMediaItem();
        final MediaItem2 newCurrentItem = getCurrentMediaItem();
        if (!ObjectsCompat.equals(oldCurrentItem, newCurrentItem)) {
            notifyToAllControllers(new NotifyRunnable() {
                /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass11 */

                @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                    callback.onCurrentMediaItemChanged(newCurrentItem);
                }
            });
        }
        final int repeatMode = getRepeatMode();
        if (oldAgent.getRepeatMode() != repeatMode) {
            notifyToAllControllers(new NotifyRunnable() {
                /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass12 */

                @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                    callback.onRepeatModeChanged(repeatMode);
                }
            });
        }
        final int shuffleMode = getShuffleMode();
        if (oldAgent.getShuffleMode() != shuffleMode) {
            notifyToAllControllers(new NotifyRunnable() {
                /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass13 */

                @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                    callback.onShuffleModeChanged(shuffleMode);
                }
            });
        }
    }

    private void notifyPlayerUpdatedNotLocked(BaseMediaPlayer oldPlayer) {
        final long currentTimeMs = SystemClock.elapsedRealtime();
        final long positionMs = getCurrentPosition();
        final int playerState = getPlayerState();
        notifyToAllControllers(new NotifyRunnable() {
            /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass14 */

            @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
            public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                callback.onPlayerStateChanged(currentTimeMs, positionMs, playerState);
            }
        });
        final MediaItem2 item = getCurrentMediaItem();
        if (item != null) {
            final int bufferingState = getBufferingState();
            final long bufferedPositionMs = getBufferedPosition();
            notifyToAllControllers(new NotifyRunnable() {
                /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass15 */

                @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                    callback.onBufferingStateChanged(item, bufferingState, bufferedPositionMs);
                }
            });
        }
        final float speed = getPlaybackSpeed();
        if (speed != oldPlayer.getPlaybackSpeed()) {
            notifyToAllControllers(new NotifyRunnable() {
                /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass16 */

                @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                    callback.onPlaybackSpeedChanged(currentTimeMs, positionMs, speed);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyPlaylistChangedOnExecutor(MediaPlaylistAgent playlistAgent, final List<MediaItem2> list, final MediaMetadata2 metadata) {
        synchronized (this.mLock) {
            if (playlistAgent == this.mPlaylistAgent) {
                this.mCallback.onPlaylistChanged(this.mInstance, playlistAgent, list, metadata);
                notifyToAllControllers(new NotifyRunnable() {
                    /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass17 */

                    @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                    public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                        callback.onPlaylistChanged(list, metadata);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyPlaylistMetadataChangedOnExecutor(MediaPlaylistAgent playlistAgent, final MediaMetadata2 metadata) {
        synchronized (this.mLock) {
            if (playlistAgent == this.mPlaylistAgent) {
                this.mCallback.onPlaylistMetadataChanged(this.mInstance, playlistAgent, metadata);
                notifyToAllControllers(new NotifyRunnable() {
                    /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass18 */

                    @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                    public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                        callback.onPlaylistMetadataChanged(metadata);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyRepeatModeChangedOnExecutor(MediaPlaylistAgent playlistAgent, final int repeatMode) {
        synchronized (this.mLock) {
            if (playlistAgent == this.mPlaylistAgent) {
                this.mCallback.onRepeatModeChanged(this.mInstance, playlistAgent, repeatMode);
                notifyToAllControllers(new NotifyRunnable() {
                    /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass19 */

                    @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                    public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                        callback.onRepeatModeChanged(repeatMode);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyShuffleModeChangedOnExecutor(MediaPlaylistAgent playlistAgent, final int shuffleMode) {
        synchronized (this.mLock) {
            if (playlistAgent == this.mPlaylistAgent) {
                this.mCallback.onShuffleModeChanged(this.mInstance, playlistAgent, shuffleMode);
                notifyToAllControllers(new NotifyRunnable() {
                    /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass20 */

                    @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                    public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                        callback.onShuffleModeChanged(shuffleMode);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyToController(@NonNull final MediaSession2.ControllerInfo controller, @NonNull NotifyRunnable runnable) {
        if (controller != null) {
            try {
                runnable.run(controller.getControllerCb());
            } catch (DeadObjectException e) {
                if (DEBUG) {
                    Log.d(TAG, controller.toString() + " is gone", e);
                }
                this.mSession2Stub.removeControllerInfo(controller);
                this.mCallbackExecutor.execute(new Runnable() {
                    /* class android.support.v4.media.MediaSession2ImplBase.AnonymousClass21 */

                    @Override // java.lang.Runnable
                    public void run() {
                        MediaSession2ImplBase.this.mCallback.onDisconnected(MediaSession2ImplBase.this.getInstance(), controller);
                    }
                });
            } catch (RemoteException e2) {
                Log.w(TAG, "Exception in " + controller.toString(), e2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyToAllControllers(@NonNull NotifyRunnable runnable) {
        List<MediaSession2.ControllerInfo> controllers = getConnectedControllers();
        for (int i = 0; i < controllers.size(); i++) {
            notifyToController(controllers.get(i), runnable);
        }
    }

    private static class MyPlayerEventCallback extends BaseMediaPlayer.PlayerEventCallback {
        private final WeakReference<MediaSession2ImplBase> mSession;

        private MyPlayerEventCallback(MediaSession2ImplBase session) {
            this.mSession = new WeakReference<>(session);
        }

        @Override // android.support.v4.media.BaseMediaPlayer.PlayerEventCallback
        public void onCurrentDataSourceChanged(final BaseMediaPlayer player, final DataSourceDesc dsd) {
            final MediaSession2ImplBase session = getSession();
            if (session != null) {
                session.getCallbackExecutor().execute(new Runnable() {
                    /* class android.support.v4.media.MediaSession2ImplBase.MyPlayerEventCallback.AnonymousClass1 */

                    @Override // java.lang.Runnable
                    public void run() {
                        final MediaItem2 item;
                        DataSourceDesc dataSourceDesc = dsd;
                        if (dataSourceDesc == null) {
                            item = null;
                        } else {
                            item = MyPlayerEventCallback.this.getMediaItem(session, dataSourceDesc);
                            if (item == null) {
                                Log.w(MediaSession2ImplBase.TAG, "Cannot obtain media item from the dsd=" + ((Object) dsd));
                                return;
                            }
                        }
                        session.getCallback().onCurrentMediaItemChanged(session.getInstance(), player, item);
                        session.notifyToAllControllers(new NotifyRunnable() {
                            /* class android.support.v4.media.MediaSession2ImplBase.MyPlayerEventCallback.AnonymousClass1.AnonymousClass1 */

                            @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                            public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                                callback.onCurrentMediaItemChanged(item);
                            }
                        });
                    }
                });
            }
        }

        @Override // android.support.v4.media.BaseMediaPlayer.PlayerEventCallback
        public void onMediaPrepared(final BaseMediaPlayer mpb, final DataSourceDesc dsd) {
            final MediaSession2ImplBase session = getSession();
            if (session != null && dsd != null) {
                session.getCallbackExecutor().execute(new Runnable() {
                    /* class android.support.v4.media.MediaSession2ImplBase.MyPlayerEventCallback.AnonymousClass2 */

                    @Override // java.lang.Runnable
                    public void run() {
                        MediaMetadata2 metadata;
                        MediaItem2 item = MyPlayerEventCallback.this.getMediaItem(session, dsd);
                        if (item != null) {
                            if (item.equals(session.getCurrentMediaItem())) {
                                long duration = session.getDuration();
                                if (duration >= 0) {
                                    MediaMetadata2 metadata2 = item.getMetadata();
                                    if (metadata2 == null) {
                                        metadata = new MediaMetadata2.Builder().putLong("android.media.metadata.DURATION", duration).putString("android.media.metadata.MEDIA_ID", item.getMediaId()).build();
                                    } else if (!metadata2.containsKey("android.media.metadata.DURATION")) {
                                        metadata = new MediaMetadata2.Builder(metadata2).putLong("android.media.metadata.DURATION", duration).build();
                                    } else {
                                        long durationFromMetadata = metadata2.getLong("android.media.metadata.DURATION");
                                        if (duration != durationFromMetadata) {
                                            Log.w(MediaSession2ImplBase.TAG, "duration mismatch for an item. duration from player=" + duration + " duration from metadata=" + durationFromMetadata + ". May be a timing issue?");
                                        }
                                        metadata = null;
                                    }
                                    if (metadata != null) {
                                        item.setMetadata(metadata);
                                        session.notifyToAllControllers(new NotifyRunnable() {
                                            /* class android.support.v4.media.MediaSession2ImplBase.MyPlayerEventCallback.AnonymousClass2.AnonymousClass1 */

                                            @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                                            public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                                                callback.onPlaylistChanged(session.getPlaylist(), session.getPlaylistMetadata());
                                            }
                                        });
                                    }
                                } else {
                                    return;
                                }
                            }
                            session.getCallback().onMediaPrepared(session.getInstance(), mpb, item);
                        }
                    }
                });
            }
        }

        @Override // android.support.v4.media.BaseMediaPlayer.PlayerEventCallback
        public void onPlayerStateChanged(final BaseMediaPlayer player, final int state) {
            final MediaSession2ImplBase session = getSession();
            if (session != null) {
                session.getCallbackExecutor().execute(new Runnable() {
                    /* class android.support.v4.media.MediaSession2ImplBase.MyPlayerEventCallback.AnonymousClass3 */

                    @Override // java.lang.Runnable
                    public void run() {
                        session.mAudioFocusHandler.onPlayerStateChanged(state);
                        session.getCallback().onPlayerStateChanged(session.getInstance(), player, state);
                        session.notifyToAllControllers(new NotifyRunnable() {
                            /* class android.support.v4.media.MediaSession2ImplBase.MyPlayerEventCallback.AnonymousClass3.AnonymousClass1 */

                            @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                            public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                                callback.onPlayerStateChanged(SystemClock.elapsedRealtime(), player.getCurrentPosition(), state);
                            }
                        });
                    }
                });
            }
        }

        @Override // android.support.v4.media.BaseMediaPlayer.PlayerEventCallback
        public void onBufferingStateChanged(final BaseMediaPlayer mpb, final DataSourceDesc dsd, final int state) {
            final MediaSession2ImplBase session = getSession();
            if (session != null && dsd != null) {
                session.getCallbackExecutor().execute(new Runnable() {
                    /* class android.support.v4.media.MediaSession2ImplBase.MyPlayerEventCallback.AnonymousClass4 */

                    @Override // java.lang.Runnable
                    public void run() {
                        final MediaItem2 item = MyPlayerEventCallback.this.getMediaItem(session, dsd);
                        if (item != null) {
                            session.getCallback().onBufferingStateChanged(session.getInstance(), mpb, item, state);
                            session.notifyToAllControllers(new NotifyRunnable() {
                                /* class android.support.v4.media.MediaSession2ImplBase.MyPlayerEventCallback.AnonymousClass4.AnonymousClass1 */

                                @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                                public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                                    callback.onBufferingStateChanged(item, state, mpb.getBufferedPosition());
                                }
                            });
                        }
                    }
                });
            }
        }

        @Override // android.support.v4.media.BaseMediaPlayer.PlayerEventCallback
        public void onPlaybackSpeedChanged(final BaseMediaPlayer mpb, final float speed) {
            final MediaSession2ImplBase session = getSession();
            if (session != null) {
                session.getCallbackExecutor().execute(new Runnable() {
                    /* class android.support.v4.media.MediaSession2ImplBase.MyPlayerEventCallback.AnonymousClass5 */

                    @Override // java.lang.Runnable
                    public void run() {
                        session.getCallback().onPlaybackSpeedChanged(session.getInstance(), mpb, speed);
                        session.notifyToAllControllers(new NotifyRunnable() {
                            /* class android.support.v4.media.MediaSession2ImplBase.MyPlayerEventCallback.AnonymousClass5.AnonymousClass1 */

                            @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                            public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                                callback.onPlaybackSpeedChanged(SystemClock.elapsedRealtime(), session.getCurrentPosition(), speed);
                            }
                        });
                    }
                });
            }
        }

        @Override // android.support.v4.media.BaseMediaPlayer.PlayerEventCallback
        public void onSeekCompleted(final BaseMediaPlayer mpb, final long position) {
            final MediaSession2ImplBase session = getSession();
            if (session != null) {
                session.getCallbackExecutor().execute(new Runnable() {
                    /* class android.support.v4.media.MediaSession2ImplBase.MyPlayerEventCallback.AnonymousClass6 */

                    @Override // java.lang.Runnable
                    public void run() {
                        session.getCallback().onSeekCompleted(session.getInstance(), mpb, position);
                        session.notifyToAllControllers(new NotifyRunnable() {
                            /* class android.support.v4.media.MediaSession2ImplBase.MyPlayerEventCallback.AnonymousClass6.AnonymousClass1 */

                            @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
                            public void run(MediaSession2.ControllerCb callback) throws RemoteException {
                                callback.onSeekCompleted(SystemClock.elapsedRealtime(), session.getCurrentPosition(), position);
                            }
                        });
                    }
                });
            }
        }

        private MediaSession2ImplBase getSession() {
            MediaSession2ImplBase session = this.mSession.get();
            if (session == null && MediaSession2ImplBase.DEBUG) {
                Log.d(MediaSession2ImplBase.TAG, "Session is closed", new IllegalStateException());
            }
            return session;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private MediaItem2 getMediaItem(MediaSession2ImplBase session, DataSourceDesc dsd) {
            MediaPlaylistAgent agent = session.getPlaylistAgent();
            if (agent != null) {
                MediaItem2 item = agent.getMediaItem(dsd);
                if (item == null && MediaSession2ImplBase.DEBUG) {
                    Log.d(MediaSession2ImplBase.TAG, "Could not find matching item for dsd=" + ((Object) dsd), new NoSuchElementException());
                }
                return item;
            } else if (!MediaSession2ImplBase.DEBUG) {
                return null;
            } else {
                Log.d(MediaSession2ImplBase.TAG, "Session is closed", new IllegalStateException());
                return null;
            }
        }
    }

    private static class MyPlaylistEventCallback extends MediaPlaylistAgent.PlaylistEventCallback {
        private final WeakReference<MediaSession2ImplBase> mSession;

        private MyPlaylistEventCallback(MediaSession2ImplBase session) {
            this.mSession = new WeakReference<>(session);
        }

        @Override // android.support.v4.media.MediaPlaylistAgent.PlaylistEventCallback
        public void onPlaylistChanged(MediaPlaylistAgent playlistAgent, List<MediaItem2> list, MediaMetadata2 metadata) {
            MediaSession2ImplBase session = this.mSession.get();
            if (session != null) {
                session.notifyPlaylistChangedOnExecutor(playlistAgent, list, metadata);
            }
        }

        @Override // android.support.v4.media.MediaPlaylistAgent.PlaylistEventCallback
        public void onPlaylistMetadataChanged(MediaPlaylistAgent playlistAgent, MediaMetadata2 metadata) {
            MediaSession2ImplBase session = this.mSession.get();
            if (session != null) {
                session.notifyPlaylistMetadataChangedOnExecutor(playlistAgent, metadata);
            }
        }

        @Override // android.support.v4.media.MediaPlaylistAgent.PlaylistEventCallback
        public void onRepeatModeChanged(MediaPlaylistAgent playlistAgent, int repeatMode) {
            MediaSession2ImplBase session = this.mSession.get();
            if (session != null) {
                session.notifyRepeatModeChangedOnExecutor(playlistAgent, repeatMode);
            }
        }

        @Override // android.support.v4.media.MediaPlaylistAgent.PlaylistEventCallback
        public void onShuffleModeChanged(MediaPlaylistAgent playlistAgent, int shuffleMode) {
            MediaSession2ImplBase session = this.mSession.get();
            if (session != null) {
                session.notifyShuffleModeChangedOnExecutor(playlistAgent, shuffleMode);
            }
        }
    }
}
