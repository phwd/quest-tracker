package com.oculus.appmanager.installer.broadcast;

import X.AbstractC06640p5;
import X.C003108z;
import android.content.Context;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
@ApplicationScoped
public class AssetBroadcastDispatch {
    public static final String ASSET_BROADCAST_ACTION = "com.oculus.asset_file_download_update";
    public static final String ASSET_BYTES_TOTAL = "bytes_total";
    public static final String ASSET_BYTES_TRANSFERRED = "bytes_transferred";
    public static final String ASSET_COMPLETED = "completed";
    public static final String ASSET_ID = "asset_id";
    public static final String ASSET_ID_DEPRECATED = "asset_file_id";
    public static final String TAG = "com.oculus.appmanager.installer.broadcast.AssetBroadcastDispatch";
    public static volatile AssetBroadcastDispatch _UL__ULSEP_com_oculus_appmanager_installer_broadcast_AssetBroadcastDispatch_ULSEP_INSTANCE;
    public final Set<Long> mCompletedSet = new HashSet();
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;

    @Inject
    public AssetBroadcastDispatch(AbstractC06640p5 r2) {
        this.mContext = C003108z.A02(r2);
    }
}
