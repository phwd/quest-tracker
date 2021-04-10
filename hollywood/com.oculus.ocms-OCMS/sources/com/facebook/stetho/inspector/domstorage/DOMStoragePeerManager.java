package com.facebook.stetho.inspector.domstorage;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeerRegistrationListener;
import com.facebook.stetho.inspector.helper.PeersRegisteredListener;
import com.facebook.stetho.inspector.protocol.module.DOMStorage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DOMStoragePeerManager extends ChromePeerManager {
    private final Context mContext;
    private final PeerRegistrationListener mPeerListener = new PeersRegisteredListener() {
        /* class com.facebook.stetho.inspector.domstorage.DOMStoragePeerManager.AnonymousClass1 */
        private final List<DevToolsSharedPreferencesListener> mPrefsListeners = new ArrayList();

        /* access modifiers changed from: protected */
        @Override // com.facebook.stetho.inspector.helper.PeersRegisteredListener
        public synchronized void onFirstPeerRegistered() {
            for (String str : SharedPreferencesHelper.getSharedPreferenceTags(DOMStoragePeerManager.this.mContext)) {
                SharedPreferences sharedPreferences = DOMStoragePeerManager.this.mContext.getSharedPreferences(str, 0);
                DevToolsSharedPreferencesListener devToolsSharedPreferencesListener = new DevToolsSharedPreferencesListener(sharedPreferences, str);
                sharedPreferences.registerOnSharedPreferenceChangeListener(devToolsSharedPreferencesListener);
                this.mPrefsListeners.add(devToolsSharedPreferencesListener);
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.stetho.inspector.helper.PeersRegisteredListener
        public synchronized void onLastPeerUnregistered() {
            for (DevToolsSharedPreferencesListener devToolsSharedPreferencesListener : this.mPrefsListeners) {
                devToolsSharedPreferencesListener.unregister();
            }
            this.mPrefsListeners.clear();
        }
    };

    public DOMStoragePeerManager(Context context) {
        this.mContext = context;
        setListener(this.mPeerListener);
    }

    public void signalItemRemoved(DOMStorage.StorageId storageId, String str) {
        DOMStorage.DomStorageItemRemovedParams domStorageItemRemovedParams = new DOMStorage.DomStorageItemRemovedParams();
        domStorageItemRemovedParams.storageId = storageId;
        domStorageItemRemovedParams.key = str;
        sendNotificationToPeers("DOMStorage.domStorageItemRemoved", domStorageItemRemovedParams);
    }

    public void signalItemAdded(DOMStorage.StorageId storageId, String str, String str2) {
        DOMStorage.DomStorageItemAddedParams domStorageItemAddedParams = new DOMStorage.DomStorageItemAddedParams();
        domStorageItemAddedParams.storageId = storageId;
        domStorageItemAddedParams.key = str;
        domStorageItemAddedParams.newValue = str2;
        sendNotificationToPeers("DOMStorage.domStorageItemAdded", domStorageItemAddedParams);
    }

    public void signalItemUpdated(DOMStorage.StorageId storageId, String str, String str2, String str3) {
        DOMStorage.DomStorageItemUpdatedParams domStorageItemUpdatedParams = new DOMStorage.DomStorageItemUpdatedParams();
        domStorageItemUpdatedParams.storageId = storageId;
        domStorageItemUpdatedParams.key = str;
        domStorageItemUpdatedParams.oldValue = str2;
        domStorageItemUpdatedParams.newValue = str3;
        sendNotificationToPeers("DOMStorage.domStorageItemUpdated", domStorageItemUpdatedParams);
    }

    private class DevToolsSharedPreferencesListener implements SharedPreferences.OnSharedPreferenceChangeListener {
        private final Map<String, Object> mCopy;
        private final SharedPreferences mPrefs;
        private final DOMStorage.StorageId mStorageId = new DOMStorage.StorageId();

        public DevToolsSharedPreferencesListener(SharedPreferences sharedPreferences, String str) {
            this.mPrefs = sharedPreferences;
            DOMStorage.StorageId storageId = this.mStorageId;
            storageId.securityOrigin = str;
            storageId.isLocalStorage = true;
            this.mCopy = DOMStoragePeerManager.prefsCopy(sharedPreferences.getAll());
        }

        public void unregister() {
            this.mPrefs.unregisterOnSharedPreferenceChangeListener(this);
        }

        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            Map<String, ?> all = sharedPreferences.getAll();
            boolean containsKey = this.mCopy.containsKey(str);
            boolean containsKey2 = all.containsKey(str);
            Object obj = containsKey2 ? all.get(str) : null;
            if (containsKey && containsKey2) {
                DOMStoragePeerManager.this.signalItemUpdated(this.mStorageId, str, SharedPreferencesHelper.valueToString(this.mCopy.get(str)), SharedPreferencesHelper.valueToString(obj));
                this.mCopy.put(str, obj);
            } else if (containsKey) {
                DOMStoragePeerManager.this.signalItemRemoved(this.mStorageId, str);
                this.mCopy.remove(str);
            } else if (containsKey2) {
                DOMStoragePeerManager.this.signalItemAdded(this.mStorageId, str, SharedPreferencesHelper.valueToString(obj));
                this.mCopy.put(str, obj);
            } else {
                LogUtil.i("Detected rapid put/remove of %s", str);
            }
        }
    }

    /* access modifiers changed from: private */
    public static Map<String, Object> prefsCopy(Map<String, ?> map) {
        HashMap hashMap = new HashMap(map.size());
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Set) {
                hashMap.put(key, shallowCopy((Set) value));
            } else {
                hashMap.put(key, value);
            }
        }
        return hashMap;
    }

    private static <T> Set<T> shallowCopy(Set<T> set) {
        HashSet hashSet = new HashSet();
        for (T t : set) {
            hashSet.add(t);
        }
        return hashSet;
    }
}
