package com.oculus.vrshell.home.module;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import com.facebook.acra.ErrorReporter;
import com.facebook.debug.log.BLog;
import com.google.common.util.concurrent.FutureCallback;
import com.google.gson.Gson;
import com.oculus.nuxpreferencesapi.OVRNuxPreferences;
import com.oculus.panellib.ThreadExecutor;
import com.oculus.panellib.modules.EarlyDestroyable;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import com.oculus.provider.OculusContent;
import com.oculus.vrshell.home.HomeApplication;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NuxPreferencesModule extends RCTBaseJavaModule implements EarlyDestroyable {
    private static final String ACTION_NAME = "com.oculus.vrshell.home.nuxpref";
    private static final String COMMAND_KEY = "command";
    private static String MODULE_NAME = new String("NuxPreferences");
    private static final String TAG = NuxPreferencesModule.class.getSimpleName();
    private static Gson gson = null;
    private static final List<Integer> mInitialFetchCallbacks = new ArrayList();
    private static final ReentrantLock mInitialFetchLock = new ReentrantLock(true);
    @Nullable
    private static NuxPreferencesModule mInstance = null;
    private static final long mNuxPreferencesFetchBackoff = 10;
    @Nullable
    private static String mPreferencesJson = null;
    private final Context mContext;
    private boolean mShutdown = false;
    private final ReentrantReadWriteLock mShutdownLock = new ReentrantReadWriteLock(true);

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0048, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0049, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005d, code lost:
        r1 = th;
     */
    static {
        /*
            r2 = 0
            java.lang.String r1 = new java.lang.String
            java.lang.String r3 = "NuxPreferences"
            r1.<init>(r3)
            com.oculus.vrshell.home.module.NuxPreferencesModule.MODULE_NAME = r1
            java.lang.Class<com.oculus.vrshell.home.module.NuxPreferencesModule> r1 = com.oculus.vrshell.home.module.NuxPreferencesModule.class
            java.lang.String r1 = r1.getSimpleName()
            com.oculus.vrshell.home.module.NuxPreferencesModule.TAG = r1
            com.oculus.vrshell.home.module.NuxPreferencesModule.mInstance = r2
            java.util.concurrent.locks.ReentrantLock r1 = new java.util.concurrent.locks.ReentrantLock
            r3 = 1
            r1.<init>(r3)
            com.oculus.vrshell.home.module.NuxPreferencesModule.mInitialFetchLock = r1
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            com.oculus.vrshell.home.module.NuxPreferencesModule.mInitialFetchCallbacks = r1
            com.oculus.vrshell.home.module.NuxPreferencesModule.mPreferencesJson = r2
            com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
            java.lang.String r1 = com.oculus.vrshell.home.module.NuxPreferencesModule.TAG
            java.lang.String r3 = "GSON"
            r0.<init>(r1, r3)
            com.google.gson.Gson r1 = new com.google.gson.Gson     // Catch:{ Throwable -> 0x0046, all -> 0x005d }
            r1.<init>()     // Catch:{ Throwable -> 0x0046, all -> 0x005d }
            com.oculus.vrshell.home.module.NuxPreferencesModule.gson = r1     // Catch:{ Throwable -> 0x0046, all -> 0x005d }
            if (r0 == 0) goto L_0x003c
            if (r2 == 0) goto L_0x0042
            r0.close()     // Catch:{ Throwable -> 0x003d }
        L_0x003c:
            return
        L_0x003d:
            r1 = move-exception
            r2.addSuppressed(r1)
            goto L_0x003c
        L_0x0042:
            r0.close()
            goto L_0x003c
        L_0x0046:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r2 = move-exception
            r4 = r2
            r2 = r1
            r1 = r4
        L_0x004c:
            if (r0 == 0) goto L_0x0053
            if (r2 == 0) goto L_0x0059
            r0.close()     // Catch:{ Throwable -> 0x0054 }
        L_0x0053:
            throw r1
        L_0x0054:
            r3 = move-exception
            r2.addSuppressed(r3)
            goto L_0x0053
        L_0x0059:
            r0.close()
            goto L_0x0053
        L_0x005d:
            r1 = move-exception
            goto L_0x004c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.home.module.NuxPreferencesModule.<clinit>():void");
    }

    public NuxPreferencesModule(Context context) {
        mInstance = this;
        this.mContext = context;
        if (!Build.TYPE.equals("user")) {
            this.mContext.registerReceiver(this, new IntentFilter(ACTION_NAME));
        }
    }

    @Override // com.oculus.panellib.modules.RCTBaseJavaModule
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "NuxPreferencesModule.onReceive");
        if (intent.getAction().equals(ACTION_NAME)) {
            String command = intent.getStringExtra(COMMAND_KEY);
            if (command == null) {
                Log.e(TAG, "No command given.");
                return;
            }
            char c = 65535;
            switch (command.hashCode()) {
                case -2115514114:
                    if (command.equals("resetNUXController")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    Log.e(TAG, "Sending event for command: " + command);
                    nativeEmitEvent(this.RVRCtxTag, "onResetNUXController");
                    return;
                default:
                    Log.e(TAG, "No handler for command: " + command);
                    return;
            }
        }
    }

    @Override // com.oculus.panellib.modules.EarlyDestroyable
    public void shutdownModule() {
        try {
            this.mContext.unregisterReceiver(this);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Trying to unregister nonexistent Receiver. This should be harmless.");
        }
        this.mShutdownLock.writeLock().lock();
        this.mShutdown = true;
        mInstance = null;
        this.mShutdownLock.writeLock().unlock();
    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public static void initialFetchPreferencesAsync() {
        internalFetchPreferences(new FutureCallback<String>() {
            /* class com.oculus.vrshell.home.module.NuxPreferencesModule.AnonymousClass1 */

            public void onSuccess(@Nullable String preferences) {
                if (preferences == null) {
                    BLog.e(NuxPreferencesModule.TAG, "Received null String");
                    return;
                }
                NuxPreferencesModule.mInitialFetchLock.lock();
                try {
                    String unused = NuxPreferencesModule.mPreferencesJson = preferences;
                    if (NuxPreferencesModule.mInstance != null) {
                        for (Integer callbackID : NuxPreferencesModule.mInitialFetchCallbacks) {
                            NuxPreferencesModule.mInstance.internalFetchPreferencesCallback(callbackID.intValue());
                        }
                    }
                } finally {
                    NuxPreferencesModule.mInitialFetchLock.unlock();
                }
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(@Nonnull Throwable throwable) {
                BLog.e(NuxPreferencesModule.TAG, "Exception while getting preferences", throwable);
            }
        });
    }

    private static void internalFetchPreferences(FutureCallback<String> callback) {
        ThreadExecutor.getInstance().execute(new Callable<String>() {
            /* class com.oculus.vrshell.home.module.NuxPreferencesModule.AnonymousClass2 */

            @Override // java.util.concurrent.Callable
            public String call() {
                long sleepTime = 0;
                while (true) {
                    final OVRNuxPreferences.ClientPreferenceData preferences = OVRNuxPreferences.getClientPreferenceData(HomeApplication.instance.getApplicationContext());
                    if (NuxPreferencesModule.arePreferencesValid(preferences)) {
                        HomeApplication.handler.post(new Runnable() {
                            /* class com.oculus.vrshell.home.module.NuxPreferencesModule.AnonymousClass2.AnonymousClass1 */

                            public void run() {
                                HomeApplication.instance.onUserId(preferences.userID);
                            }
                        });
                        ErrorReporter.getInstance();
                        ErrorReporter.putCustomData(OculusContent.Profile.ROLLOUT_TOKEN, preferences.rolloutToken);
                        return NuxPreferencesModule.gson.toJson(preferences);
                    }
                    sleepTime += NuxPreferencesModule.mNuxPreferencesFetchBackoff;
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }, callback, "NuxPreferencesModule::internalFetchPreferences");
    }

    /* access modifiers changed from: private */
    public static boolean arePreferencesValid(OVRNuxPreferences.ClientPreferenceData preferences) {
        return (preferences == null || preferences.userID == null) ? false : true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void internalFetchPreferencesCallback(int callbackID) {
        nativeInvokeCallback(this.RVRCtxTag, callbackID, "[" + mPreferencesJson + "]");
    }

    public static List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("fetchPreferencesAsync", "+ii"));
        list.add(new Pair<>("forceFetchPreferences", "i"));
        list.add(new Pair<>("markHasNuxFinished", ""));
        list.add(new Pair<>("markNuxSeen", ""));
        list.add(new Pair<>("markNuxNotSeen", ""));
        list.add(new Pair<>("markNuxNotFinished", ""));
        list.add(new Pair<>("markPartyCallsSeen", ""));
        list.add(new Pair<>("markSavedPromptSeen", ""));
        list.add(new Pair<>("markTutorialPromptSeen", ""));
        list.add(new Pair<>("setHasSeenHandTrackingNux", "b"));
        list.add(new Pair<>("setNuxSeenCount", "i"));
        return list;
    }

    public void fetchPreferencesAsync(int resolveID, int rejectID) {
        if (mPreferencesJson != null) {
            internalFetchPreferencesCallback(resolveID);
            return;
        }
        mInitialFetchLock.lock();
        try {
            if (mPreferencesJson != null) {
                internalFetchPreferencesCallback(resolveID);
            } else {
                mInitialFetchCallbacks.add(Integer.valueOf(resolveID));
            }
        } finally {
            mInitialFetchLock.unlock();
        }
    }

    public void forceFetchPreferences(final int callbackID) {
        internalFetchPreferences(new FutureCallback<String>() {
            /* class com.oculus.vrshell.home.module.NuxPreferencesModule.AnonymousClass3 */

            public void onSuccess(@Nullable String preferences) {
                if (preferences == null) {
                    BLog.e(NuxPreferencesModule.TAG, "Received null String");
                    return;
                }
                String unused = NuxPreferencesModule.mPreferencesJson = preferences;
                NuxPreferencesModule.this.mShutdownLock.readLock().lock();
                if (!NuxPreferencesModule.this.mShutdown) {
                    NuxPreferencesModule.nativeInvokeCallback(NuxPreferencesModule.this.RVRCtxTag, callbackID, "[" + NuxPreferencesModule.mPreferencesJson + "]");
                }
                NuxPreferencesModule.this.mShutdownLock.readLock().unlock();
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(@Nonnull Throwable throwable) {
                BLog.e(NuxPreferencesModule.TAG, "Exception while getting preferences", throwable);
            }
        });
    }

    public void markHasNuxFinished() {
        OVRNuxPreferences.markHasNuxFinished(this.mContext.getApplicationContext());
    }

    public void markNuxSeen() {
        OVRNuxPreferences.markNuxSeen(this.mContext.getApplicationContext());
    }

    public void markNuxNotSeen() {
        OVRNuxPreferences.markNuxNotSeen(this.mContext.getApplicationContext());
    }

    public void markNuxNotFinished() {
        OVRNuxPreferences.markNuxNotFinished(this.mContext.getApplicationContext());
    }

    public void markPartyCallsSeen() {
        OVRNuxPreferences.markPartyCallsNUXSeen(this.mContext.getApplicationContext());
    }

    public void markSavedPromptSeen() {
        OVRNuxPreferences.markSavedPromptSeen(this.mContext.getApplicationContext());
    }

    public void markTutorialPromptSeen() {
        OVRNuxPreferences.markTutorialPromptSeen(this.mContext.getApplicationContext());
    }

    public void setHasSeenHandTrackingNux(boolean seen) {
        OVRNuxPreferences.setHasSeenHandTrackingNux(this.mContext.getApplicationContext(), seen);
    }

    public void setNuxSeenCount(int count) {
        OVRNuxPreferences.setNuxSeenCount(this.mContext.getApplicationContext(), count);
    }
}
