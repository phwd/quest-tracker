package com.oculus.modules;

import android.util.Log;
import com.facebook.acra.ErrorReporter;
import com.google.common.util.concurrent.FutureCallback;
import com.google.gson.Gson;
import com.oculus.modules.codegen.NuxPreferencesFetcherModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.nuxpreferencesapi.OVRNuxPreferences;
import com.oculus.panellib.ThreadExecutor;
import com.oculus.systemutilities.SystemUtilitiesApplication;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONException;
import org.json.JSONObject;

public class NuxPreferencesFetcherModuleImpl extends NuxPreferencesFetcherModule {
    private static final String TAG = NuxPreferencesFetcherModuleImpl.class.getSimpleName();
    private static Gson gson = new Gson();
    private static final long mNuxPreferencesFetchBackoff = 10;
    private static String mPreferencesJson = null;
    private boolean mShutdown = false;
    private final ReentrantReadWriteLock mShutdownLock = new ReentrantReadWriteLock(true);

    @Override // com.oculus.modules.codegen.NuxPreferencesFetcherModule
    public void shutdownModule() {
        this.mShutdownLock.writeLock().lock();
        this.mShutdown = true;
        this.mShutdownLock.writeLock().unlock();
    }

    private static void internalFetchPreferences(FutureCallback<String> callback) {
        ThreadExecutor.getInstance().execute(new Callable<String>() {
            /* class com.oculus.modules.NuxPreferencesFetcherModuleImpl.AnonymousClass1 */

            @Override // java.util.concurrent.Callable
            public String call() {
                long sleepTime = 0;
                while (true) {
                    final OVRNuxPreferences.ClientPreferenceData preferences = OVRNuxPreferences.getClientPreferenceData(SystemUtilitiesApplication.instance.getApplicationContext());
                    if (NuxPreferencesFetcherModuleImpl.arePreferencesValid(preferences)) {
                        SystemUtilitiesApplication.handler.post(new Runnable() {
                            /* class com.oculus.modules.NuxPreferencesFetcherModuleImpl.AnonymousClass1.AnonymousClass1 */

                            public void run() {
                                SystemUtilitiesApplication.instance.onUserId(preferences.userID);
                            }
                        });
                        ErrorReporter.getInstance();
                        ErrorReporter.putCustomData("rollout_token", preferences.rolloutToken);
                        return NuxPreferencesFetcherModuleImpl.gson.toJson(preferences);
                    }
                    sleepTime += NuxPreferencesFetcherModuleImpl.mNuxPreferencesFetchBackoff;
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        Log.e(NuxPreferencesFetcherModuleImpl.TAG, "Exception while getting preferences: sleep failed", e);
                    }
                }
            }
        }, callback, "NuxPreferencesFetcherModuleImpl::internalFetchPreferences");
    }

    /* access modifiers changed from: private */
    public static boolean arePreferencesValid(OVRNuxPreferences.ClientPreferenceData preferences) {
        return (preferences == null || preferences.userID == null) ? false : true;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.NuxPreferencesFetcherModule
    public void fetchPreferencesImpl(final Resolver<NuxPreferencesFetcherModule.NuxPreferences> resolver) {
        internalFetchPreferences(new FutureCallback<String>() {
            /* class com.oculus.modules.NuxPreferencesFetcherModuleImpl.AnonymousClass2 */

            public void onSuccess(String preferences) {
                if (preferences == null) {
                    Log.e(NuxPreferencesFetcherModuleImpl.TAG, "Received null String");
                    resolver.reject(new Exception("Received null String when fetching preferences."));
                    return;
                }
                String unused = NuxPreferencesFetcherModuleImpl.mPreferencesJson = preferences;
                NuxPreferencesFetcherModuleImpl.this.mShutdownLock.readLock().lock();
                if (!NuxPreferencesFetcherModuleImpl.this.mShutdown) {
                    try {
                        resolver.resolve(NuxPreferencesFetcherModule.NuxPreferences.makeFromJSONObject(new JSONObject(NuxPreferencesFetcherModuleImpl.mPreferencesJson)));
                    } catch (JSONException e) {
                        Log.e(NuxPreferencesFetcherModuleImpl.TAG, "Exception while parsing preferences");
                        resolver.reject(e);
                    }
                }
                NuxPreferencesFetcherModuleImpl.this.mShutdownLock.readLock().unlock();
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable throwable) {
                Log.e(NuxPreferencesFetcherModuleImpl.TAG, "Exception while getting preferences", throwable);
                resolver.reject(throwable);
            }
        });
    }
}
