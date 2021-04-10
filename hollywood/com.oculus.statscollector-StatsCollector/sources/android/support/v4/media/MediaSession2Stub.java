package android.support.v4.media;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.mediacompat.Rating2;
import android.support.v4.media.IMediaSession2;
import android.support.v4.media.MediaController2;
import android.support.v4.media.MediaLibraryService2;
import android.support.v4.media.MediaSession2;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* access modifiers changed from: package-private */
@TargetApi(19)
public class MediaSession2Stub extends IMediaSession2.Stub {
    private static final boolean DEBUG = true;
    private static final String TAG = "MediaSession2Stub";
    private static final SparseArray<SessionCommand2> sCommandsForOnCommandRequest = new SparseArray<>();
    @GuardedBy("mLock")
    private final ArrayMap<MediaSession2.ControllerInfo, SessionCommandGroup2> mAllowedCommandGroupMap = new ArrayMap<>();
    @GuardedBy("mLock")
    private final Set<IBinder> mConnectingControllers = new HashSet();
    final Context mContext;
    @GuardedBy("mLock")
    private final ArrayMap<IBinder, MediaSession2.ControllerInfo> mControllers = new ArrayMap<>();
    private final Object mLock = new Object();
    final MediaSession2.SupportLibraryImpl mSession;

    /* access modifiers changed from: private */
    @FunctionalInterface
    public interface SessionRunnable {
        void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException;
    }

    static {
        SessionCommandGroup2 group = new SessionCommandGroup2();
        group.addAllPlaybackCommands();
        group.addAllPlaylistCommands();
        group.addAllVolumeCommands();
        for (SessionCommand2 command : group.getCommands()) {
            sCommandsForOnCommandRequest.append(command.getCommandCode(), command);
        }
    }

    MediaSession2Stub(MediaSession2.SupportLibraryImpl session) {
        this.mSession = session;
        this.mContext = this.mSession.getContext();
    }

    /* access modifiers changed from: package-private */
    public List<MediaSession2.ControllerInfo> getConnectedControllers() {
        ArrayList<MediaSession2.ControllerInfo> controllers = new ArrayList<>();
        synchronized (this.mLock) {
            for (int i = 0; i < this.mControllers.size(); i++) {
                controllers.add(this.mControllers.valueAt(i));
            }
        }
        return controllers;
    }

    /* access modifiers changed from: package-private */
    public void setAllowedCommands(MediaSession2.ControllerInfo controller, SessionCommandGroup2 commands) {
        synchronized (this.mLock) {
            this.mAllowedCommandGroupMap.put(controller, commands);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        if (r2.hasCommand(r5) == false) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        return android.support.v4.media.MediaSession2Stub.DEBUG;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000e, code lost:
        if (r2 == null) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isAllowedCommand(android.support.v4.media.MediaSession2.ControllerInfo r4, android.support.v4.media.SessionCommand2 r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            r1 = 0
            android.support.v4.util.ArrayMap<android.support.v4.media.MediaSession2$ControllerInfo, android.support.v4.media.SessionCommandGroup2> r2 = r3.mAllowedCommandGroupMap     // Catch:{ all -> 0x001a }
            java.lang.Object r2 = r2.get(r4)     // Catch:{ all -> 0x001a }
            android.support.v4.media.SessionCommandGroup2 r2 = (android.support.v4.media.SessionCommandGroup2) r2     // Catch:{ all -> 0x001a }
            r1 = r2
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x0018
            boolean r0 = r1.hasCommand(r5)
            if (r0 == 0) goto L_0x0018
            r0 = 1
            goto L_0x0019
        L_0x0018:
            r0 = 0
        L_0x0019:
            return r0
        L_0x001a:
            r2 = move-exception
        L_0x001b:
            monitor-exit(r0)
            throw r2
        L_0x001d:
            r2 = move-exception
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2Stub.isAllowedCommand(android.support.v4.media.MediaSession2$ControllerInfo, android.support.v4.media.SessionCommand2):boolean");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        if (r2.hasCommand(r5) == false) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        return android.support.v4.media.MediaSession2Stub.DEBUG;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000e, code lost:
        if (r2 == null) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isAllowedCommand(android.support.v4.media.MediaSession2.ControllerInfo r4, int r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            r1 = 0
            android.support.v4.util.ArrayMap<android.support.v4.media.MediaSession2$ControllerInfo, android.support.v4.media.SessionCommandGroup2> r2 = r3.mAllowedCommandGroupMap     // Catch:{ all -> 0x001a }
            java.lang.Object r2 = r2.get(r4)     // Catch:{ all -> 0x001a }
            android.support.v4.media.SessionCommandGroup2 r2 = (android.support.v4.media.SessionCommandGroup2) r2     // Catch:{ all -> 0x001a }
            r1 = r2
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x0018
            boolean r0 = r1.hasCommand(r5)
            if (r0 == 0) goto L_0x0018
            r0 = 1
            goto L_0x0019
        L_0x0018:
            r0 = 0
        L_0x0019:
            return r0
        L_0x001a:
            r2 = move-exception
        L_0x001b:
            monitor-exit(r0)
            throw r2
        L_0x001d:
            r2 = move-exception
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2Stub.isAllowedCommand(android.support.v4.media.MediaSession2$ControllerInfo, int):boolean");
    }

    private void onSessionCommand(@NonNull IMediaController2 caller, int commandCode, @NonNull SessionRunnable runnable) {
        onSessionCommandInternal(caller, null, commandCode, runnable);
    }

    private void onSessionCommand(@NonNull IMediaController2 caller, @NonNull SessionCommand2 sessionCommand, @NonNull SessionRunnable runnable) {
        onSessionCommandInternal(caller, sessionCommand, 0, runnable);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        if (r9.mSession.isClosed() != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        if (r1 != null) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        r9.mSession.getCallbackExecutor().execute(new android.support.v4.media.MediaSession2Stub.AnonymousClass1(r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onSessionCommandInternal(@android.support.annotation.NonNull android.support.v4.media.IMediaController2 r10, @android.support.annotation.Nullable final android.support.v4.media.SessionCommand2 r11, final int r12, @android.support.annotation.NonNull final android.support.v4.media.MediaSession2Stub.SessionRunnable r13) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.mLock
            monitor-enter(r0)
            r1 = 0
            if (r10 != 0) goto L_0x0007
            goto L_0x0014
        L_0x0007:
            android.support.v4.util.ArrayMap<android.os.IBinder, android.support.v4.media.MediaSession2$ControllerInfo> r2 = r9.mControllers     // Catch:{ all -> 0x0038 }
            android.os.IBinder r3 = r10.asBinder()     // Catch:{ all -> 0x0038 }
            java.lang.Object r2 = r2.get(r3)     // Catch:{ all -> 0x0038 }
            android.support.v4.media.MediaSession2$ControllerInfo r2 = (android.support.v4.media.MediaSession2.ControllerInfo) r2     // Catch:{ all -> 0x0038 }
            r1 = r2
        L_0x0014:
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            android.support.v4.media.MediaSession2$SupportLibraryImpl r0 = r9.mSession
            boolean r0 = r0.isClosed()
            if (r0 != 0) goto L_0x0035
            if (r1 != 0) goto L_0x0020
            goto L_0x0035
        L_0x0020:
            android.support.v4.media.MediaSession2$SupportLibraryImpl r0 = r9.mSession
            java.util.concurrent.Executor r0 = r0.getCallbackExecutor()
            android.support.v4.media.MediaSession2Stub$1 r8 = new android.support.v4.media.MediaSession2Stub$1
            r2 = r8
            r3 = r9
            r4 = r1
            r5 = r11
            r6 = r12
            r7 = r13
            r2.<init>(r4, r5, r6, r7)
            r0.execute(r8)
            return
        L_0x0035:
            return
        L_0x0036:
            r2 = move-exception
            goto L_0x0039
        L_0x0038:
            r2 = move-exception
        L_0x0039:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2Stub.onSessionCommandInternal(android.support.v4.media.IMediaController2, android.support.v4.media.SessionCommand2, int, android.support.v4.media.MediaSession2Stub$SessionRunnable):void");
    }

    private void onBrowserCommand(@NonNull IMediaController2 caller, int commandCode, @NonNull SessionRunnable runnable) {
        if (this.mSession instanceof MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl) {
            onSessionCommandInternal(caller, null, commandCode, runnable);
            return;
        }
        throw new RuntimeException("MediaSession2 cannot handle MediaLibrarySession command");
    }

    /* access modifiers changed from: package-private */
    public void removeControllerInfo(MediaSession2.ControllerInfo controller) {
        synchronized (this.mLock) {
            Log.d(TAG, "releasing " + this.mControllers.remove(controller.getId()));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
        if (r5.mSession.isClosed() != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0030, code lost:
        if (r2 != null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
        r5.mSession.getCallbackExecutor().execute(new android.support.v4.media.MediaSession2Stub.AnonymousClass2(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0041, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void releaseController(android.support.v4.media.IMediaController2 r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            r1 = 0
            android.support.v4.util.ArrayMap<android.os.IBinder, android.support.v4.media.MediaSession2$ControllerInfo> r2 = r5.mControllers     // Catch:{ all -> 0x0043 }
            android.os.IBinder r3 = r6.asBinder()     // Catch:{ all -> 0x0043 }
            java.lang.Object r2 = r2.remove(r3)     // Catch:{ all -> 0x0043 }
            android.support.v4.media.MediaSession2$ControllerInfo r2 = (android.support.v4.media.MediaSession2.ControllerInfo) r2     // Catch:{ all -> 0x0043 }
            r1 = r2
            java.lang.String r2 = "MediaSession2Stub"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0046 }
            r3.<init>()     // Catch:{ all -> 0x0046 }
            java.lang.String r4 = "releasing "
            r3.append(r4)     // Catch:{ all -> 0x0046 }
            r3.append(r1)     // Catch:{ all -> 0x0046 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0046 }
            android.util.Log.d(r2, r3)     // Catch:{ all -> 0x0046 }
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            android.support.v4.media.MediaSession2$SupportLibraryImpl r0 = r5.mSession
            boolean r0 = r0.isClosed()
            if (r0 != 0) goto L_0x0042
            if (r1 != 0) goto L_0x0033
            goto L_0x0042
        L_0x0033:
            android.support.v4.media.MediaSession2$SupportLibraryImpl r0 = r5.mSession
            java.util.concurrent.Executor r0 = r0.getCallbackExecutor()
            android.support.v4.media.MediaSession2Stub$2 r2 = new android.support.v4.media.MediaSession2Stub$2
            r2.<init>(r1)
            r0.execute(r2)
            return
        L_0x0042:
            return
        L_0x0043:
            r2 = move-exception
        L_0x0044:
            monitor-exit(r0)
            throw r2
        L_0x0046:
            r2 = move-exception
            goto L_0x0044
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSession2Stub.releaseController(android.support.v4.media.IMediaController2):void");
    }

    @Override // android.support.v4.media.IMediaSession2
    public void connect(final IMediaController2 caller, String callingPackage) throws RuntimeException {
        final MediaSession2.ControllerInfo controllerInfo = new MediaSession2.ControllerInfo(callingPackage, Binder.getCallingPid(), Binder.getCallingUid(), new Controller2Cb(caller));
        this.mSession.getCallbackExecutor().execute(new Runnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass3 */

            public void run() {
                SessionCommandGroup2 allowedCommands;
                Bundle currentItem;
                if (!MediaSession2Stub.this.mSession.isClosed()) {
                    synchronized (MediaSession2Stub.this.mLock) {
                        MediaSession2Stub.this.mConnectingControllers.add(controllerInfo.getId());
                    }
                    SessionCommandGroup2 allowedCommands2 = MediaSession2Stub.this.mSession.getCallback().onConnect(MediaSession2Stub.this.mSession.getInstance(), controllerInfo);
                    if ((allowedCommands2 != null || controllerInfo.isTrusted()) ? MediaSession2Stub.DEBUG : false) {
                        Log.d(MediaSession2Stub.TAG, "Accepting connection, controllerInfo=" + controllerInfo + " allowedCommands=" + allowedCommands2);
                        if (allowedCommands2 == null) {
                            allowedCommands = new SessionCommandGroup2();
                        } else {
                            allowedCommands = allowedCommands2;
                        }
                        synchronized (MediaSession2Stub.this.mLock) {
                            MediaSession2Stub.this.mConnectingControllers.remove(controllerInfo.getId());
                            MediaSession2Stub.this.mControllers.put(controllerInfo.getId(), controllerInfo);
                            MediaSession2Stub.this.mAllowedCommandGroupMap.put(controllerInfo, allowedCommands);
                        }
                        int playerState = MediaSession2Stub.this.mSession.getPlayerState();
                        List<MediaItem2> playlist = null;
                        if (MediaSession2Stub.this.mSession.getCurrentMediaItem() == null) {
                            currentItem = null;
                        } else {
                            currentItem = MediaSession2Stub.this.mSession.getCurrentMediaItem().toBundle();
                        }
                        long positionEventTimeMs = SystemClock.elapsedRealtime();
                        long positionMs = MediaSession2Stub.this.mSession.getCurrentPosition();
                        float playbackSpeed = MediaSession2Stub.this.mSession.getPlaybackSpeed();
                        long bufferedPositionMs = MediaSession2Stub.this.mSession.getBufferedPosition();
                        Bundle playbackInfoBundle = MediaSession2Stub.this.mSession.getPlaybackInfo().toBundle();
                        int repeatMode = MediaSession2Stub.this.mSession.getRepeatMode();
                        int shuffleMode = MediaSession2Stub.this.mSession.getShuffleMode();
                        PendingIntent sessionActivity = MediaSession2Stub.this.mSession.getSessionActivity();
                        if (allowedCommands.hasCommand(18)) {
                            playlist = MediaSession2Stub.this.mSession.getPlaylist();
                        }
                        List<Bundle> playlistBundle = MediaUtils2.convertMediaItem2ListToBundleList(playlist);
                        if (!MediaSession2Stub.this.mSession.isClosed()) {
                            try {
                                caller.onConnected(MediaSession2Stub.this, allowedCommands.toBundle(), playerState, currentItem, positionEventTimeMs, positionMs, playbackSpeed, bufferedPositionMs, playbackInfoBundle, repeatMode, shuffleMode, playlistBundle, sessionActivity);
                            } catch (RemoteException e) {
                            }
                        }
                    } else {
                        synchronized (MediaSession2Stub.this.mLock) {
                            MediaSession2Stub.this.mConnectingControllers.remove(controllerInfo.getId());
                        }
                        Log.d(MediaSession2Stub.TAG, "Rejecting connection, controllerInfo=" + controllerInfo);
                        try {
                            caller.onDisconnected();
                        } catch (RemoteException e2) {
                        }
                    }
                }
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void release(IMediaController2 caller) throws RemoteException {
        releaseController(caller);
    }

    @Override // android.support.v4.media.IMediaSession2
    public void setVolumeTo(IMediaController2 caller, final int value, final int flags) throws RuntimeException {
        onSessionCommand(caller, 10, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass4 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                VolumeProviderCompat volumeProvider = MediaSession2Stub.this.mSession.getVolumeProvider();
                if (volumeProvider == null) {
                    MediaSessionCompat sessionCompat = MediaSession2Stub.this.mSession.getSessionCompat();
                    if (sessionCompat != null) {
                        sessionCompat.getController().setVolumeTo(value, flags);
                        return;
                    }
                    return;
                }
                volumeProvider.onSetVolumeTo(value);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void adjustVolume(IMediaController2 caller, final int direction, final int flags) throws RuntimeException {
        onSessionCommand(caller, 11, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass5 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                VolumeProviderCompat volumeProvider = MediaSession2Stub.this.mSession.getVolumeProvider();
                if (volumeProvider == null) {
                    MediaSessionCompat sessionCompat = MediaSession2Stub.this.mSession.getSessionCompat();
                    if (sessionCompat != null) {
                        sessionCompat.getController().adjustVolume(direction, flags);
                        return;
                    }
                    return;
                }
                volumeProvider.onAdjustVolume(direction);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void play(IMediaController2 caller) throws RuntimeException {
        onSessionCommand(caller, 1, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass6 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.play();
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void pause(IMediaController2 caller) throws RuntimeException {
        onSessionCommand(caller, 2, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass7 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.pause();
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void reset(IMediaController2 caller) throws RuntimeException {
        onSessionCommand(caller, 3, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass8 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.reset();
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void prepare(IMediaController2 caller) throws RuntimeException {
        onSessionCommand(caller, 6, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass9 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.prepare();
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void fastForward(IMediaController2 caller) {
        onSessionCommand(caller, 7, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass10 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getCallback().onFastForward(MediaSession2Stub.this.mSession.getInstance(), controller);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void rewind(IMediaController2 caller) {
        onSessionCommand(caller, 8, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass11 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getCallback().onRewind(MediaSession2Stub.this.mSession.getInstance(), controller);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void seekTo(IMediaController2 caller, final long pos) throws RuntimeException {
        onSessionCommand(caller, 9, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass12 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.seekTo(pos);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void sendCustomCommand(IMediaController2 caller, Bundle commandBundle, final Bundle args, final ResultReceiver receiver) {
        final SessionCommand2 command = SessionCommand2.fromBundle(commandBundle);
        onSessionCommand(caller, SessionCommand2.fromBundle(commandBundle), new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass13 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getCallback().onCustomCommand(MediaSession2Stub.this.mSession.getInstance(), controller, command, args, receiver);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void prepareFromUri(IMediaController2 caller, final Uri uri, final Bundle extras) {
        onSessionCommand(caller, 26, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass14 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (uri == null) {
                    Log.w(MediaSession2Stub.TAG, "prepareFromUri(): Ignoring null uri from " + controller);
                    return;
                }
                MediaSession2Stub.this.mSession.getCallback().onPrepareFromUri(MediaSession2Stub.this.mSession.getInstance(), controller, uri, extras);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void prepareFromSearch(IMediaController2 caller, final String query, final Bundle extras) {
        onSessionCommand(caller, 27, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass15 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (TextUtils.isEmpty(query)) {
                    Log.w(MediaSession2Stub.TAG, "prepareFromSearch(): Ignoring empty query from " + controller);
                    return;
                }
                MediaSession2Stub.this.mSession.getCallback().onPrepareFromSearch(MediaSession2Stub.this.mSession.getInstance(), controller, query, extras);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void prepareFromMediaId(IMediaController2 caller, final String mediaId, final Bundle extras) {
        onSessionCommand(caller, 25, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass16 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (mediaId == null) {
                    Log.w(MediaSession2Stub.TAG, "prepareFromMediaId(): Ignoring null mediaId from " + controller);
                    return;
                }
                MediaSession2Stub.this.mSession.getCallback().onPrepareFromMediaId(MediaSession2Stub.this.mSession.getInstance(), controller, mediaId, extras);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void playFromUri(IMediaController2 caller, final Uri uri, final Bundle extras) {
        onSessionCommand(caller, 23, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass17 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (uri == null) {
                    Log.w(MediaSession2Stub.TAG, "playFromUri(): Ignoring null uri from " + controller);
                    return;
                }
                MediaSession2Stub.this.mSession.getCallback().onPlayFromUri(MediaSession2Stub.this.mSession.getInstance(), controller, uri, extras);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void playFromSearch(IMediaController2 caller, final String query, final Bundle extras) {
        onSessionCommand(caller, 24, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass18 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (TextUtils.isEmpty(query)) {
                    Log.w(MediaSession2Stub.TAG, "playFromSearch(): Ignoring empty query from " + controller);
                    return;
                }
                MediaSession2Stub.this.mSession.getCallback().onPlayFromSearch(MediaSession2Stub.this.mSession.getInstance(), controller, query, extras);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void playFromMediaId(IMediaController2 caller, final String mediaId, final Bundle extras) {
        onSessionCommand(caller, 22, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass19 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (mediaId == null) {
                    Log.w(MediaSession2Stub.TAG, "playFromMediaId(): Ignoring null mediaId from " + controller);
                    return;
                }
                MediaSession2Stub.this.mSession.getCallback().onPlayFromMediaId(MediaSession2Stub.this.mSession.getInstance(), controller, mediaId, extras);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void setRating(IMediaController2 caller, final String mediaId, final Bundle ratingBundle) {
        onSessionCommand(caller, 28, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass20 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (mediaId == null) {
                    Log.w(MediaSession2Stub.TAG, "setRating(): Ignoring null mediaId from " + controller);
                    return;
                }
                Bundle bundle = ratingBundle;
                if (bundle == null) {
                    Log.w(MediaSession2Stub.TAG, "setRating(): Ignoring null ratingBundle from " + controller);
                    return;
                }
                Rating2 rating = Rating2.fromBundle(bundle);
                if (rating != null) {
                    MediaSession2Stub.this.mSession.getCallback().onSetRating(MediaSession2Stub.this.mSession.getInstance(), controller, mediaId, rating);
                } else if (ratingBundle == null) {
                    Log.w(MediaSession2Stub.TAG, "setRating(): Ignoring null rating from " + controller);
                }
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void setPlaybackSpeed(IMediaController2 caller, final float speed) {
        onSessionCommand(caller, 39, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass21 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().setPlaybackSpeed(speed);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void setPlaylist(IMediaController2 caller, final List<Bundle> playlist, final Bundle metadata) {
        onSessionCommand(caller, 19, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass22 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (playlist == null) {
                    Log.w(MediaSession2Stub.TAG, "setPlaylist(): Ignoring null playlist from " + controller);
                    return;
                }
                MediaSession2Stub.this.mSession.getInstance().setPlaylist(MediaUtils2.convertBundleListToMediaItem2List(playlist), MediaMetadata2.fromBundle(metadata));
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void updatePlaylistMetadata(IMediaController2 caller, final Bundle metadata) {
        onSessionCommand(caller, 21, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass23 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().updatePlaylistMetadata(MediaMetadata2.fromBundle(metadata));
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void addPlaylistItem(IMediaController2 caller, final int index, final Bundle mediaItem) {
        onSessionCommand(caller, 15, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass24 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().addPlaylistItem(index, MediaItem2.fromBundle(mediaItem, null));
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void removePlaylistItem(IMediaController2 caller, final Bundle mediaItem) {
        onSessionCommand(caller, 16, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass25 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().removePlaylistItem(MediaItem2.fromBundle(mediaItem));
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void replacePlaylistItem(IMediaController2 caller, final int index, final Bundle mediaItem) {
        onSessionCommand(caller, 17, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass26 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().replacePlaylistItem(index, MediaItem2.fromBundle(mediaItem, null));
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void skipToPlaylistItem(IMediaController2 caller, final Bundle mediaItem) {
        onSessionCommand(caller, 12, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass27 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (mediaItem == null) {
                    Log.w(MediaSession2Stub.TAG, "skipToPlaylistItem(): Ignoring null mediaItem from " + controller);
                }
                MediaSession2Stub.this.mSession.getInstance().skipToPlaylistItem(MediaItem2.fromBundle(mediaItem));
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void skipToPreviousItem(IMediaController2 caller) {
        onSessionCommand(caller, 5, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass28 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().skipToPreviousItem();
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void skipToNextItem(IMediaController2 caller) {
        onSessionCommand(caller, 4, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass29 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().skipToNextItem();
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void setRepeatMode(IMediaController2 caller, final int repeatMode) {
        onSessionCommand(caller, 14, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass30 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().setRepeatMode(repeatMode);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void setShuffleMode(IMediaController2 caller, final int shuffleMode) {
        onSessionCommand(caller, 13, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass31 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().setShuffleMode(shuffleMode);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void subscribeRoutesInfo(IMediaController2 caller) {
        onSessionCommand(caller, 36, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass32 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getCallback().onSubscribeRoutesInfo(MediaSession2Stub.this.mSession.getInstance(), controller);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void unsubscribeRoutesInfo(IMediaController2 caller) {
        onSessionCommand(caller, 37, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass33 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getCallback().onUnsubscribeRoutesInfo(MediaSession2Stub.this.mSession.getInstance(), controller);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void selectRoute(IMediaController2 caller, final Bundle route) {
        onSessionCommand(caller, 37, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass34 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getCallback().onSelectRoute(MediaSession2Stub.this.mSession.getInstance(), controller, route);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl getLibrarySession() {
        MediaSession2.SupportLibraryImpl supportLibraryImpl = this.mSession;
        if (supportLibraryImpl instanceof MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl) {
            return (MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl) supportLibraryImpl;
        }
        throw new RuntimeException("Session cannot be casted to library session");
    }

    @Override // android.support.v4.media.IMediaSession2
    public void getLibraryRoot(IMediaController2 caller, final Bundle rootHints) throws RuntimeException {
        onBrowserCommand(caller, 31, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass35 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.getLibrarySession().onGetLibraryRootOnExecutor(controller, rootHints);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void getItem(IMediaController2 caller, final String mediaId) throws RuntimeException {
        onBrowserCommand(caller, 30, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass36 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (mediaId == null) {
                    Log.w(MediaSession2Stub.TAG, "getItem(): Ignoring null mediaId from " + controller);
                    return;
                }
                MediaSession2Stub.this.getLibrarySession().onGetItemOnExecutor(controller, mediaId);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void getChildren(IMediaController2 caller, final String parentId, final int page, final int pageSize, final Bundle extras) throws RuntimeException {
        onBrowserCommand(caller, 29, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass37 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (parentId == null) {
                    Log.w(MediaSession2Stub.TAG, "getChildren(): Ignoring null parentId from " + controller);
                } else if (page < 1 || pageSize < 1) {
                    Log.w(MediaSession2Stub.TAG, "getChildren(): Ignoring page nor pageSize less than 1 from " + controller);
                } else {
                    MediaSession2Stub.this.getLibrarySession().onGetChildrenOnExecutor(controller, parentId, page, pageSize, extras);
                }
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void search(IMediaController2 caller, final String query, final Bundle extras) {
        onBrowserCommand(caller, 33, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass38 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (TextUtils.isEmpty(query)) {
                    Log.w(MediaSession2Stub.TAG, "search(): Ignoring empty query from " + controller);
                    return;
                }
                MediaSession2Stub.this.getLibrarySession().onSearchOnExecutor(controller, query, extras);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void getSearchResult(IMediaController2 caller, final String query, final int page, final int pageSize, final Bundle extras) {
        onBrowserCommand(caller, 32, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass39 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (TextUtils.isEmpty(query)) {
                    Log.w(MediaSession2Stub.TAG, "getSearchResult(): Ignoring empty query from " + controller);
                } else if (page < 1 || pageSize < 1) {
                    Log.w(MediaSession2Stub.TAG, "getSearchResult(): Ignoring page nor pageSize less than 1  from " + controller);
                } else {
                    MediaSession2Stub.this.getLibrarySession().onGetSearchResultOnExecutor(controller, query, page, pageSize, extras);
                }
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void subscribe(IMediaController2 caller, final String parentId, final Bundle option) {
        onBrowserCommand(caller, 34, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass40 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (parentId == null) {
                    Log.w(MediaSession2Stub.TAG, "subscribe(): Ignoring null parentId from " + controller);
                    return;
                }
                MediaSession2Stub.this.getLibrarySession().onSubscribeOnExecutor(controller, parentId, option);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void unsubscribe(IMediaController2 caller, final String parentId) {
        onBrowserCommand(caller, 35, new SessionRunnable() {
            /* class android.support.v4.media.MediaSession2Stub.AnonymousClass41 */

            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (parentId == null) {
                    Log.w(MediaSession2Stub.TAG, "unsubscribe(): Ignoring null parentId from " + controller);
                    return;
                }
                MediaSession2Stub.this.getLibrarySession().onUnsubscribeOnExecutor(controller, parentId);
            }
        });
    }

    static final class Controller2Cb extends MediaSession2.ControllerCb {
        private final IMediaController2 mIControllerCallback;

        Controller2Cb(@NonNull IMediaController2 callback) {
            this.mIControllerCallback = callback;
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        @NonNull
        public IBinder getId() {
            return this.mIControllerCallback.asBinder();
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onCustomLayoutChanged(List<MediaSession2.CommandButton> layout) throws RemoteException {
            this.mIControllerCallback.onCustomLayoutChanged(MediaUtils2.convertCommandButtonListToBundleList(layout));
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onPlaybackInfoChanged(MediaController2.PlaybackInfo info) throws RemoteException {
            this.mIControllerCallback.onPlaybackInfoChanged(info.toBundle());
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onAllowedCommandsChanged(SessionCommandGroup2 commands) throws RemoteException {
            this.mIControllerCallback.onAllowedCommandsChanged(commands.toBundle());
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onCustomCommand(SessionCommand2 command, Bundle args, ResultReceiver receiver) throws RemoteException {
            this.mIControllerCallback.onCustomCommand(command.toBundle(), args, receiver);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onPlayerStateChanged(long eventTimeMs, long positionMs, int playerState) throws RemoteException {
            this.mIControllerCallback.onPlayerStateChanged(eventTimeMs, positionMs, playerState);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onPlaybackSpeedChanged(long eventTimeMs, long positionMs, float speed) throws RemoteException {
            this.mIControllerCallback.onPlaybackSpeedChanged(eventTimeMs, positionMs, speed);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onBufferingStateChanged(MediaItem2 item, int state, long bufferedPositionMs) throws RemoteException {
            Bundle bundle;
            IMediaController2 iMediaController2 = this.mIControllerCallback;
            if (item == null) {
                bundle = null;
            } else {
                bundle = item.toBundle();
            }
            iMediaController2.onBufferingStateChanged(bundle, state, bufferedPositionMs);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onSeekCompleted(long eventTimeMs, long positionMs, long seekPositionMs) throws RemoteException {
            this.mIControllerCallback.onSeekCompleted(eventTimeMs, positionMs, seekPositionMs);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onError(int errorCode, Bundle extras) throws RemoteException {
            this.mIControllerCallback.onError(errorCode, extras);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onCurrentMediaItemChanged(MediaItem2 item) throws RemoteException {
            this.mIControllerCallback.onCurrentMediaItemChanged(item == null ? null : item.toBundle());
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onPlaylistChanged(List<MediaItem2> playlist, MediaMetadata2 metadata) throws RemoteException {
            Bundle bundle;
            IMediaController2 iMediaController2 = this.mIControllerCallback;
            List<Bundle> convertMediaItem2ListToBundleList = MediaUtils2.convertMediaItem2ListToBundleList(playlist);
            if (metadata == null) {
                bundle = null;
            } else {
                bundle = metadata.toBundle();
            }
            iMediaController2.onPlaylistChanged(convertMediaItem2ListToBundleList, bundle);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onPlaylistMetadataChanged(MediaMetadata2 metadata) throws RemoteException {
            this.mIControllerCallback.onPlaylistMetadataChanged(metadata.toBundle());
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onShuffleModeChanged(int shuffleMode) throws RemoteException {
            this.mIControllerCallback.onShuffleModeChanged(shuffleMode);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onRepeatModeChanged(int repeatMode) throws RemoteException {
            this.mIControllerCallback.onRepeatModeChanged(repeatMode);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onRoutesInfoChanged(List<Bundle> routes) throws RemoteException {
            this.mIControllerCallback.onRoutesInfoChanged(routes);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onGetLibraryRootDone(Bundle rootHints, String rootMediaId, Bundle rootExtra) throws RemoteException {
            this.mIControllerCallback.onGetLibraryRootDone(rootHints, rootMediaId, rootExtra);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onChildrenChanged(String parentId, int itemCount, Bundle extras) throws RemoteException {
            this.mIControllerCallback.onChildrenChanged(parentId, itemCount, extras);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onGetChildrenDone(String parentId, int page, int pageSize, List<MediaItem2> result, Bundle extras) throws RemoteException {
            this.mIControllerCallback.onGetChildrenDone(parentId, page, pageSize, MediaUtils2.convertMediaItem2ListToBundleList(result), extras);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onGetItemDone(String mediaId, MediaItem2 result) throws RemoteException {
            this.mIControllerCallback.onGetItemDone(mediaId, result == null ? null : result.toBundle());
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onSearchResultChanged(String query, int itemCount, Bundle extras) throws RemoteException {
            this.mIControllerCallback.onSearchResultChanged(query, itemCount, extras);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onGetSearchResultDone(String query, int page, int pageSize, List<MediaItem2> result, Bundle extras) throws RemoteException {
            this.mIControllerCallback.onGetSearchResultDone(query, page, pageSize, MediaUtils2.convertMediaItem2ListToBundleList(result), extras);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2.ControllerCb
        public void onDisconnected() throws RemoteException {
            this.mIControllerCallback.onDisconnected();
        }
    }
}
