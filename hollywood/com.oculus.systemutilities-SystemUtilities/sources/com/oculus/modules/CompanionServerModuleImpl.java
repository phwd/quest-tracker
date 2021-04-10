package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Process;
import android.os.ResultReceiver;
import android.util.Log;
import com.oculus.modules.codegen.CompanionServerModule;
import com.oculus.modules.codegen.Resolver;
import org.json.JSONObject;

public class CompanionServerModuleImpl extends CompanionServerModule {
    private static final String ACTION_LOCALE_UPDATED = "com.oculus.companion.server.LOCALE_UPDATED";
    private static final String ACTION_PIN_SET = "companion.PIN_SET";
    private static final String ACTION_PIN_STATUS = "companion.PIN_STATUS";
    private static final String ACTION_PREFIX = "companion.";
    private static final String ACTION_UPDATE_HORIZON_STATE = "companion.UPDATE_HORIZON_STATE";
    private static final String COMPANION_SERVER_PACKAGE = "com.oculus.companion.server";
    private static final String COMPANION_SERVER_RESULT_EXTRA = "RESULT_RECEIVER";
    private static final String COMPANION_SERVER_SERVICE = "com.oculus.companion.server.CompanionService";
    private static final String COMPANION_SERVER_SET_EXTRA = "SET";
    private static final String EXTRA_BASE = "";
    private static final String EXTRA_LOCALE_COUNTRY = "COUNTRY";
    private static final String EXTRA_LOCALE_LANGUAGE = "LANGUAGE";
    private static final String EXTRA_LOCK_AFTER_PIN_SET = "LOCK_AFTER_PIN_SET";
    private static final String EXTRA_PIN = "PIN";
    private static final String EXTRA_USER_HANDLE = "USER_HANDLE";
    private static final String TAG = CompanionServerModuleImpl.class.getSimpleName();
    private static JSONObject sOverrides = new JSONObject();
    private Context mContext;

    public CompanionServerModuleImpl(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public IntentFilter getIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_LOCALE_UPDATED);
        return filter;
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public void processIntent(Intent intent, boolean forceNativeUpdate) {
        String action = intent.getAction();
        char c = 65535;
        switch (action.hashCode()) {
            case -193025337:
                if (action.equals(ACTION_LOCALE_UPDATED)) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                handleLocaleUpdate(intent);
                return;
            default:
                Log.e(TAG, String.format("Unhandled action: %s", action));
                return;
        }
    }

    private void handleLocaleUpdate(Intent intent) {
        CompanionServerModule.LocaleUpdate update = new CompanionServerModule.LocaleUpdate();
        update.country = intent.getStringExtra(EXTRA_LOCALE_COUNTRY);
        update.language = intent.getStringExtra(EXTRA_LOCALE_LANGUAGE);
        emitOnLocaleUpdated(update);
    }

    public static void loadOverrides(Context context) {
        try {
            String overrides = context.getSharedPreferences("companion_server_module", 0).getString("overrides", null);
            sOverrides = overrides != null ? new JSONObject(overrides) : new JSONObject();
        } catch (Exception e) {
            Log.e(TAG, "Failed to load companion server overrides", e);
        }
    }

    public static void clearOverrides(Context context) {
        try {
            context.getSharedPreferences("companion_server_module", 0).edit().clear().apply();
            sOverrides = new JSONObject();
        } catch (Exception e) {
            Log.e(TAG, "Failed to load companion server overrides", e);
        }
    }

    public static void setOverride(Context context, String action, boolean value) {
        try {
            sOverrides.put(action, value);
            context.getSharedPreferences("companion_server_module", 0).edit().putString("overrides", sOverrides.toString()).apply();
        } catch (Exception e) {
            Log.e(TAG, "Failed to load companion server overrides", e);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.CompanionServerModule
    public void updateHorizonStateImpl(Resolver<Boolean> resolver) {
        setBoolean(ACTION_UPDATE_HORIZON_STATE, Boolean.TRUE, Boolean.FALSE, resolver);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.CompanionServerModule
    public void isPinSetImpl(final Resolver<Boolean> resolver) {
        Resolver<Byte> pinStatusResolver = new Resolver<Byte>() {
            /* class com.oculus.modules.CompanionServerModuleImpl.AnonymousClass1 */

            public void resolve(Byte resolution) {
                resolver.resolve(Boolean.valueOf(resolution.byteValue() > 0));
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                resolver.reject(error);
            }
        };
        Bundle extras = new Bundle(1);
        extras.putParcelable(EXTRA_USER_HANDLE, Process.myUserHandle());
        requestByte(ACTION_PIN_STATUS, extras, (byte) 0, pinStatusResolver);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.CompanionServerModule
    public void setPinImpl(String pin, Resolver<Boolean> resolver) {
        Intent intent = companionIntent(ACTION_PIN_SET);
        intent.putExtra(EXTRA_PIN, pin);
        intent.putExtra(EXTRA_LOCK_AFTER_PIN_SET, false);
        try {
            this.mContext.startService(intent);
            resolver.resolve(true);
        } catch (Exception exception) {
            resolver.reject(exception);
        }
    }

    private static Intent companionIntent(String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setClassName(COMPANION_SERVER_PACKAGE, COMPANION_SERVER_SERVICE);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void requestBoolean(String action, Boolean defaultValue, Resolver<Boolean> resolver) {
        if (sOverrides.has(action)) {
            Log.d(TAG, "Using override for " + action);
            resolver.resolve(Boolean.valueOf(sOverrides.optBoolean(action)));
            return;
        }
        Log.d(TAG, "Requesting " + action);
        SerializableResultReceiver<Boolean> resultReceiver = new SerializableResultReceiver<>(action, defaultValue, resolver);
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setClassName(COMPANION_SERVER_PACKAGE, COMPANION_SERVER_SERVICE);
        intent.putExtra(COMPANION_SERVER_RESULT_EXTRA, resultReceiver.serialized());
        try {
            this.mContext.startService(intent);
        } catch (Exception exception) {
            resolver.reject(exception);
        }
    }

    /* access modifiers changed from: protected */
    public void requestString(String action, String defaultValue, Resolver<String> resolver) {
        if (sOverrides.has(action)) {
            Log.d(TAG, "Using override for " + action);
            resolver.resolve(sOverrides.optString(action));
            return;
        }
        Log.d(TAG, "Requesting " + action);
        SerializableResultReceiver<String> resultReceiver = new SerializableResultReceiver<>(action, defaultValue, resolver);
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setClassName(COMPANION_SERVER_PACKAGE, COMPANION_SERVER_SERVICE);
        intent.putExtra(COMPANION_SERVER_RESULT_EXTRA, resultReceiver.serialized());
        try {
            this.mContext.startService(intent);
        } catch (Exception exception) {
            resolver.reject(exception);
        }
    }

    /* access modifiers changed from: protected */
    public void requestByte(String action, Bundle extras, Byte defaultValue, Resolver<Byte> resolver) {
        if (sOverrides.has(action)) {
            Log.d(TAG, "Using override for " + action);
            resolver.resolve(Byte.valueOf(sOverrides.optString(action)));
            return;
        }
        Log.d(TAG, "Requesting " + action);
        SerializableResultReceiver<Byte> resultReceiver = new SerializableResultReceiver<>(action, defaultValue, resolver);
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setClassName(COMPANION_SERVER_PACKAGE, COMPANION_SERVER_SERVICE);
        intent.putExtra(COMPANION_SERVER_RESULT_EXTRA, resultReceiver.serialized());
        if (extras != null) {
            intent.putExtras(extras);
        }
        try {
            this.mContext.startService(intent);
        } catch (Exception exception) {
            resolver.reject(exception);
        }
    }

    /* access modifiers changed from: protected */
    public void setBoolean(String action, Boolean value, Boolean defaultResult, Resolver<Boolean> resolver) {
        Log.d(TAG, "Setting " + action);
        SerializableResultReceiver<Boolean> resultReceiver = new SerializableResultReceiver<>(action, defaultResult, resolver);
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setClassName(COMPANION_SERVER_PACKAGE, COMPANION_SERVER_SERVICE);
        intent.putExtra(COMPANION_SERVER_SET_EXTRA, value);
        intent.putExtra(COMPANION_SERVER_RESULT_EXTRA, resultReceiver.serialized());
        try {
            this.mContext.startService(intent);
        } catch (Exception exception) {
            resolver.reject(exception);
        }
    }

    /* access modifiers changed from: private */
    public static class SerializableResultReceiver<T> extends ResultReceiver {
        private final String mAction;
        private final T mDefaultValue;
        private final Resolver<T> mResolver;

        public SerializableResultReceiver(String action, T defaultValue, Resolver<T> resolver) {
            super(null);
            this.mAction = action;
            this.mDefaultValue = defaultValue;
            this.mResolver = resolver;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: com.oculus.modules.codegen.Resolver<T> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: protected */
        public void onReceiveResult(int resultCode, Bundle resultData) {
            Object obj = resultData.get("CS_RESULT");
            Log.d(CompanionServerModuleImpl.TAG, "Received " + this.mAction + " result " + obj);
            if (obj == null) {
                this.mResolver.resolve(this.mDefaultValue);
            } else {
                this.mResolver.resolve(obj);
            }
        }

        public ResultReceiver serialized() {
            Parcel parcel = Parcel.obtain();
            writeToParcel(parcel, 0);
            parcel.setDataPosition(0);
            ResultReceiver serializedReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return serializedReceiver;
        }
    }
}
