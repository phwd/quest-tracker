package com.oculus.userserver.managerservice;

import X.C0246hr;
import X.Mi;
import X.h6;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.base.MoreObjects;
import com.oculus.userserver.api.user.OculusUser;
import com.oculus.userserver.api.user.SparseOculusUser;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_oculus_userserver_managerservice_ForDeviceProtectedStorage_ULSEP_BINDING_ID"})
@ApplicationScoped
public class OculusUserStore {
    public static final int CURRENT_VERSION = 2;
    public static final C0246hr GSON = new C0246hr();
    @VisibleForTesting
    public static final String KEY_DATA = "data";
    @VisibleForTesting
    public static final String KEY_VERSION = "version";
    @VisibleForTesting
    public static final String PREFS_NAME = "oculus_user_store";
    public static final String TAG = "OculusUserStore";
    public static volatile OculusUserStore _UL__ULSEP_com_oculus_userserver_managerservice_OculusUserStore_ULSEP_INSTANCE;
    public final Map<Integer, OculusUserInternal> mCache = new LinkedHashMap();
    public final SharedPreferences mPrefs;

    @Nullable
    public final synchronized OculusUserInternal A01(int i) {
        return this.mCache.get(Integer.valueOf(i));
    }

    public final synchronized List<OculusUserInternal> A02() {
        return new ArrayList(this.mCache.values());
    }

    public final synchronized void A03(int i) {
        Map<Integer, OculusUserInternal> map = this.mCache;
        Integer valueOf = Integer.valueOf(i);
        if (!map.containsKey(valueOf)) {
            Mi.A05(TAG, "Deleting nonexistent user, userId: %d", valueOf);
        }
        this.mCache.remove(valueOf);
        A00(this);
    }

    public final synchronized void A04(int i, SparseOculusUser sparseOculusUser, boolean z) {
        Map<Integer, OculusUserInternal> map = this.mCache;
        Integer valueOf = Integer.valueOf(i);
        OculusUserInternal oculusUserInternal = map.get(valueOf);
        if (oculusUserInternal == null) {
            Mi.A04(TAG, "Attempted to update nonexistent user, userId: %d, aborting...", valueOf);
        } else {
            String str = sparseOculusUser.mUserName;
            OculusUser oculusUser = oculusUserInternal.mOculusUser;
            this.mCache.put(valueOf, new OculusUserInternal(new OculusUser(i, (String) MoreObjects.firstNonNull(str, oculusUser.mUserName), (String) MoreObjects.firstNonNull(sparseOculusUser.mPictureUri, oculusUser.mPictureUri), oculusUser.mCreationTime, z), oculusUserInternal.mAccountId, oculusUserInternal.mSyncId));
            A00(this);
        }
    }

    public final synchronized void A05(int i, String str, String str2) {
        Map<Integer, OculusUserInternal> map = this.mCache;
        Integer valueOf = Integer.valueOf(i);
        OculusUserInternal oculusUserInternal = map.get(valueOf);
        if (oculusUserInternal == null) {
            Mi.A04(TAG, "Attempted to update nonexistent user, userId: %d, aborting...", valueOf);
        } else {
            this.mCache.put(valueOf, new OculusUserInternal(oculusUserInternal.mOculusUser, str, str2));
            A00(this);
        }
    }

    public static void A00(OculusUserStore oculusUserStore) {
        oculusUserStore.mPrefs.edit().putInt(KEY_VERSION, 2).putString(KEY_DATA, GSON.A05(oculusUserStore.mCache.values())).commit();
    }

    @Inject
    public OculusUserStore(@ForDeviceProtectedStorage Context context) {
        Object A04;
        Object A042;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        this.mPrefs = sharedPreferences;
        int i = sharedPreferences.getInt(KEY_VERSION, 0);
        if (i == 1) {
            String string = this.mPrefs.getString(KEY_DATA, null);
            Type type = new h6<List<OculusUser>>() {
                /* class com.oculus.userserver.managerservice.OculusUserStore.AnonymousClass1 */
            }.type;
            C0246hr hrVar = GSON;
            if (string == null) {
                A04 = null;
            } else {
                A04 = hrVar.A04(new StringReader(string), type);
            }
            List<OculusUser> list = (List) A04;
            if (list != null) {
                for (OculusUser oculusUser : list) {
                    this.mCache.put(Integer.valueOf(oculusUser.mUserId), new OculusUserInternal(oculusUser, null, null));
                }
            }
        } else if (i == 2) {
            String string2 = this.mPrefs.getString(KEY_DATA, null);
            Type type2 = new h6<List<OculusUserInternal>>() {
                /* class com.oculus.userserver.managerservice.OculusUserStore.AnonymousClass2 */
            }.type;
            C0246hr hrVar2 = GSON;
            if (string2 == null) {
                A042 = null;
            } else {
                A042 = hrVar2.A04(new StringReader(string2), type2);
            }
            List<OculusUserInternal> list2 = (List) A042;
            if (list2 != null) {
                for (OculusUserInternal oculusUserInternal : list2) {
                    this.mCache.put(Integer.valueOf(oculusUserInternal.mOculusUser.mUserId), oculusUserInternal);
                }
            }
        }
    }
}
