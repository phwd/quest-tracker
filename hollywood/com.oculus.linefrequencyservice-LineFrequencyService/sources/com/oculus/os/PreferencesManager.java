package com.oculus.os;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.util.Pair;
import java.util.NoSuchElementException;
import oculus.internal.IPreferencesListener;
import oculus.internal.IPreferencesService;

public class PreferencesManager {
    private static final String HANDLER_THREAD_NAME = "PreferencesManagerHandlerThread";
    private static final String PREFERENCES_SERVICE = "PreferencesService";
    private static final String TAG = PreferencesManager.class.getSimpleName();
    private Handler mHandler = null;
    private HandlerThread mHandlerThread = null;
    protected final IBinder.DeathRecipient mPreferencesDeathReceipient = new IBinder.DeathRecipient() {
        /* class com.oculus.os.$$Lambda$PreferencesManager$2PYJ9fAfVJoGIehlA_Y_lLyuiYY */

        public final void binderDied() {
            PreferencesManager.this.lambda$new$1$PreferencesManager();
        }
    };
    private PreferencesListenerWrapper mPreferencesListenerWrapper = null;
    private IPreferencesService mService = null;

    public interface PreferencesListener {
        void onChanged(String[] strArr);

        void onConnectionEstablished();

        void onConnectionLost();
    }

    public enum Type {
        INTEGER,
        DOUBLE,
        STRING,
        LONG,
        BOOLEAN,
        FLOAT,
        UNKNOWN
    }

    public /* synthetic */ void lambda$new$1$PreferencesManager() {
        synchronized (this) {
            Log.d(TAG, "Remote service died, resetting mService to null");
            if (this.mService != null) {
                this.mService = null;
                if (this.mPreferencesListenerWrapper != null) {
                    this.mPreferencesListenerWrapper.connectionLost();
                    if (this.mHandlerThread == null) {
                        this.mHandlerThread = new HandlerThread(HANDLER_THREAD_NAME);
                        this.mHandlerThread.start();
                        this.mHandler = new Handler(this.mHandlerThread.getLooper());
                    } else {
                        Log.i(TAG, "binderDied() happens only when binder is broken right after linkToDeath() in ensureServiceConnected()");
                    }
                    this.mHandler.postDelayed(new Runnable() {
                        /* class com.oculus.os.$$Lambda$PreferencesManager$cuLkjBn8JQHTleUuB9uENtbvIc */

                        public final void run() {
                            PreferencesManager.this.lambda$new$0$PreferencesManager();
                        }
                    }, 100);
                }
            }
        }
    }

    public synchronized boolean set(String key, int value) {
        lambda$new$0$PreferencesManager();
        if (this.mService != null) {
            try {
                this.mService.setInteger(key, value);
                return true;
            } catch (Exception e) {
                Log.e(TAG, "Exception while calling PreferencesService setInteger method: ", e);
            }
        }
        return false;
    }

    public synchronized boolean set(String key, double value) {
        lambda$new$0$PreferencesManager();
        if (this.mService != null) {
            try {
                this.mService.setDouble(key, value);
                return true;
            } catch (Exception e) {
                Log.e(TAG, "Exception while calling PreferencesService setDouble method: ", e);
            }
        }
        return false;
    }

    public synchronized boolean set(String key, String value) {
        lambda$new$0$PreferencesManager();
        if (this.mService != null) {
            try {
                this.mService.setString(key, value);
                return true;
            } catch (Exception e) {
                Log.e(TAG, "Exception while calling PreferencesService setString method: ", e);
            }
        }
        return false;
    }

    public synchronized boolean set(String key, long value) {
        lambda$new$0$PreferencesManager();
        if (this.mService != null) {
            try {
                this.mService.setLong(key, value);
                return true;
            } catch (Exception e) {
                Log.e(TAG, "Exception while calling PreferencesService setLong method: ", e);
            }
        }
        return false;
    }

    public synchronized boolean set(String key, boolean value) {
        lambda$new$0$PreferencesManager();
        if (this.mService != null) {
            try {
                this.mService.setBoolean(key, value);
                return true;
            } catch (Exception e) {
                Log.e(TAG, "Exception while calling PreferencesService setBoolean method: ", e);
            }
        }
        return false;
    }

    public synchronized boolean set(String key, float value) {
        lambda$new$0$PreferencesManager();
        if (this.mService != null) {
            try {
                this.mService.setFloat(key, value);
                return true;
            } catch (Exception e) {
                Log.e(TAG, "Exception while calling PreferencesService setFloat method: ", e);
            }
        }
        return false;
    }

    public synchronized Pair<Boolean, Integer> getInteger(String key) {
        lambda$new$0$PreferencesManager();
        if (this.mService != null) {
            try {
                return new Pair<>(true, Integer.valueOf(this.mService.getInteger(key)));
            } catch (Exception e) {
                Log.e(TAG, "Exception while calling PreferencesService getInteger method: ", e);
            }
        }
        return new Pair<>(false, 0);
    }

    public synchronized Pair<Boolean, Double> getDouble(String key) {
        lambda$new$0$PreferencesManager();
        if (this.mService != null) {
            try {
                return new Pair<>(true, Double.valueOf(this.mService.getDouble(key)));
            } catch (Exception e) {
                Log.e(TAG, "Exception while calling PreferencesService getDouble method: ", e);
            }
        }
        return new Pair<>(false, Double.valueOf(0.0d));
    }

    public synchronized Pair<Boolean, String> getString(String key) {
        lambda$new$0$PreferencesManager();
        if (this.mService != null) {
            try {
                return new Pair<>(true, this.mService.getString(key));
            } catch (Exception e) {
                Log.e(TAG, "Exception while calling PreferencesService getString method: ", e);
            }
        }
        return new Pair<>(false, "");
    }

    public synchronized Pair<Boolean, Long> getLong(String key) {
        lambda$new$0$PreferencesManager();
        if (this.mService != null) {
            try {
                return new Pair<>(true, Long.valueOf(this.mService.getLong(key)));
            } catch (Exception e) {
                Log.e(TAG, "Exception while calling PreferencesService getLong method: ", e);
            }
        }
        return new Pair<>(false, 0L);
    }

    public synchronized Pair<Boolean, Boolean> getBoolean(String key) {
        lambda$new$0$PreferencesManager();
        if (this.mService != null) {
            try {
                return new Pair<>(true, Boolean.valueOf(this.mService.getBoolean(key)));
            } catch (Exception e) {
                Log.e(TAG, "Exception while calling PreferencesService getBoolean method: ", e);
            }
        }
        return new Pair<>(false, false);
    }

    public synchronized Pair<Boolean, Float> getFloat(String key) {
        lambda$new$0$PreferencesManager();
        if (this.mService != null) {
            try {
                return new Pair<>(true, Float.valueOf(this.mService.getFloat(key)));
            } catch (Exception e) {
                Log.e(TAG, "Exception while calling PreferencesService getFloat method: ", e);
            }
        }
        return new Pair<>(false, Float.valueOf(0.0f));
    }

    public synchronized Pair<Boolean, Type> getType(String key) {
        lambda$new$0$PreferencesManager();
        if (this.mService != null) {
            try {
                return new Pair<>(true, toType(this.mService.getType(key)));
            } catch (Exception e) {
                Log.e(TAG, "Exception while calling PreferencesService getType method: ", e);
            }
        }
        return new Pair<>(false, Type.UNKNOWN);
    }

    public synchronized boolean setListener(PreferencesListener listener, String[] keys) {
        if (this.mPreferencesListenerWrapper != null && !clearListener()) {
            return false;
        }
        if (keys == null || keys.length == 0) {
            return false;
        }
        lambda$new$0$PreferencesManager();
        if (this.mService != null) {
            try {
                this.mPreferencesListenerWrapper = new PreferencesListenerWrapper(listener, keys);
                this.mService.registerListener(this.mPreferencesListenerWrapper, keys);
                return true;
            } catch (Exception e) {
                this.mPreferencesListenerWrapper = null;
                Log.e(TAG, "Couldn't register setListener", e);
            }
        }
        return false;
    }

    public synchronized boolean clearListener() {
        if (this.mPreferencesListenerWrapper == null) {
            return true;
        }
        if (this.mService == null) {
            if (this.mHandlerThread != null) {
                this.mHandlerThread.quit();
                this.mHandlerThread = null;
            }
            this.mHandler = null;
            this.mPreferencesListenerWrapper = null;
            return true;
        }
        try {
            this.mService.unregisterListener(this.mPreferencesListenerWrapper);
            this.mPreferencesListenerWrapper = null;
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Couldn't unregister setListener", e);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static class PreferencesListenerWrapper extends IPreferencesListener.Stub {
        private final String[] mKeys;
        private final PreferencesListener mListener;

        private PreferencesListenerWrapper(PreferencesListener listener, String[] keys) {
            this.mListener = listener;
            this.mKeys = keys;
        }

        @Override // oculus.internal.IPreferencesListener
        public void onChanged(String[] keys) {
            this.mListener.onChanged(keys);
        }

        public void connectionLost() {
            this.mListener.onConnectionLost();
        }

        public String[] getKeys() {
            return this.mKeys;
        }

        public void connectionEstablished() {
            this.mListener.onConnectionEstablished();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: ensureServiceConnected */
    public synchronized void lambda$new$0$PreferencesManager() {
        if (this.mService == null) {
            IBinder b = getServiceBinder(PREFERENCES_SERVICE);
            this.mService = getPreferencesService(b);
            if (this.mService != null) {
                try {
                    b.linkToDeath(this.mPreferencesDeathReceipient, 0);
                    if (this.mPreferencesListenerWrapper != null) {
                        try {
                            this.mService.registerListener(this.mPreferencesListenerWrapper, this.mPreferencesListenerWrapper.getKeys());
                            if (this.mHandlerThread != null) {
                                this.mHandlerThread.quit();
                                this.mHandlerThread = null;
                            }
                            this.mHandler = null;
                            this.mPreferencesListenerWrapper.connectionEstablished();
                        } catch (Exception e) {
                            Log.e(TAG, "registerListener failed on re-establishing", e);
                            this.mService = null;
                            try {
                                b.unlinkToDeath(this.mPreferencesDeathReceipient, 0);
                            } catch (NoSuchElementException e2) {
                            }
                        }
                    } else {
                        return;
                    }
                } catch (RemoteException e3) {
                    Log.e(TAG, "linkToDeath failed", e3);
                    this.mService = null;
                    return;
                }
            }
            if (this.mService == null && this.mHandler != null) {
                this.mHandler.postDelayed(new Runnable() {
                    /* class com.oculus.os.$$Lambda$PreferencesManager$mSRYJuHgv2oQ7Rlib_RFvKYJAHo */

                    public final void run() {
                        PreferencesManager.this.lambda$ensureServiceConnected$2$PreferencesManager();
                    }
                }, 100);
            }
        }
    }

    /* access modifiers changed from: protected */
    public IBinder getServiceBinder(String serviceName) {
        return ServiceManager.getService(serviceName);
    }

    /* access modifiers changed from: protected */
    public IPreferencesService getPreferencesService(IBinder b) {
        return IPreferencesService.Stub.asInterface(b);
    }

    private Type toType(int type) {
        Type type2 = Type.UNKNOWN;
        if (type == 0) {
            return Type.INTEGER;
        }
        if (type == 1) {
            return Type.DOUBLE;
        }
        if (type == 2) {
            return Type.STRING;
        }
        if (type == 3) {
            return Type.LONG;
        }
        if (type == 4) {
            return Type.BOOLEAN;
        }
        if (type != 5) {
            return Type.UNKNOWN;
        }
        return Type.FLOAT;
    }
}
