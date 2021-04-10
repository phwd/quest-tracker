package com.facebook.msys.mca;

import X.AnonymousClass1NZ;
import X.AnonymousClass1Ns;
import X.AnonymousClass1Nt;
import X.AnonymousClass1Z6;
import X.AnonymousClass1ZY;
import android.annotation.SuppressLint;
import com.facebook.msys.annotations.AppState;
import com.facebook.msys.annotations.MailboxState;
import com.facebook.msys.mcd.MediaSendManager;
import com.facebook.msys.mci.AuthDataContext;
import com.facebook.msys.mci.DatabaseHealthMonitor;
import com.facebook.msys.mci.NetworkSession;
import com.facebook.msys.mci.NotificationCenter;
import com.facebook.msys.mcs.DasmConfigCreator;
import com.facebook.msys.mcs.SyncHandler;
import com.facebook.msys.util.NotificationScope;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@DoNotStrip
@NotThreadSafe
@SuppressLint({"MissingNativeLoadLibrary"})
public class Mailbox {
    public final Executor mCallbackExecutor;
    @DoNotStrip
    public final NativeHolder mNativeHolder;
    public final NotificationCenter mNotificationCenter;
    public final AnonymousClass1ZY mNotificationCenterCallbackManager;
    public final NotificationCenter.NotificationCallback mQueryChangeCallback = new AnonymousClass1Ns(this);
    public final ArrayList<AnonymousClass1Nt> mStoredProcedureChangedListeners = new ArrayList<>();
    @Nullable
    public final SyncHandler mSyncHandler;
    @Nullable
    public AnonymousClass1Z6 mSynchronousMailboxProvider = null;

    public Mailbox(int i, String str, AuthDataContext authDataContext, MediaSendManager mediaSendManager, NetworkSession networkSession, NotificationCenter notificationCenter, DasmConfigCreator dasmConfigCreator, String str2, String str3, @Nullable MailboxExperimentCache mailboxExperimentCache, Executor executor) {
        this.mNotificationCenter = notificationCenter;
        this.mNotificationCenterCallbackManager = new AnonymousClass1ZY(notificationCenter);
        this.mSyncHandler = null;
        this.mNativeHolder = initNativeHolder(0, str, authDataContext, mediaSendManager, networkSession, notificationCenter, authDataContext.mDatabase.mDatabaseHealthMonitor, dasmConfigCreator, str2, str3, mailboxExperimentCache);
        this.mNotificationCenter.addObserver(this.mQueryChangeCallback, "MCDSyncCompletionNotification", null);
        this.mCallbackExecutor = executor;
    }

    @DoNotStrip
    @AppState
    private native int getAppStateNative();

    @DoNotStrip
    private native int getEventSampleRateNative(int i);

    @MailboxState
    @DoNotStrip
    private native int getStateNative();

    @DoNotStrip
    @Nullable
    private native SyncHandler getSyncHandlerNative();

    @DoNotStrip
    public static native NativeHolder initNativeHolder(int i, String str, AuthDataContext authDataContext, MediaSendManager mediaSendManager, NetworkSession networkSession, NotificationCenter notificationCenter, DatabaseHealthMonitor databaseHealthMonitor, DasmConfigCreator dasmConfigCreator, String str2, String str3, @Nullable MailboxExperimentCache mailboxExperimentCache);

    @DoNotStrip
    @Deprecated
    public static native NativeHolder initNativeHolder_DEPRECATED(int i, String str, AuthDataContext authDataContext, MediaSendManager mediaSendManager, NetworkSession networkSession, NotificationCenter notificationCenter, DatabaseHealthMonitor databaseHealthMonitor, SyncHandler syncHandler, String str2, String str3, @Nullable MailboxExperimentCache mailboxExperimentCache);

    @DoNotStrip
    private native void invalidateNative();

    @DoNotStrip
    private native boolean isValidNative();

    /* access modifiers changed from: private */
    @DoNotStrip
    public native void logoutAndDeleteNative(NotificationScope notificationScope);

    /* access modifiers changed from: private */
    @DoNotStrip
    public native void logoutAndEncryptNative(NotificationScope notificationScope);

    @DoNotStrip
    @Nullable
    private native String mailboxCreateLogIdNative(int i);

    /* access modifiers changed from: private */
    @DoNotStrip
    public native void setStateNative(@MailboxState int i, NotificationScope notificationScope);

    @DoNotStrip
    private native void setSyncNative(SyncHandler syncHandler);

    @DoNotStrip
    private native void shutdownNative(NotificationScope notificationScope);

    static {
        AnonymousClass1NZ.A00();
    }

    @MailboxState
    public int getState() {
        return getStateNative();
    }

    @Nullable
    public SyncHandler getSyncHandler() {
        return getSyncHandlerNative();
    }
}
