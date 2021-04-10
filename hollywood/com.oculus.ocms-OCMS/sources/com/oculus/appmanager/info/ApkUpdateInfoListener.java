package com.oculus.appmanager.info;

import com.oculus.appmanager.info.ApkUpdateInfo;
import java.util.Set;

public interface ApkUpdateInfoListener {

    public enum CreationType {
        NEW,
        RESTORED
    }

    void onChanged(ApkUpdateInfo apkUpdateInfo, Set<String> set, ApkUpdateInfo.ApkUpdateExtras apkUpdateExtras);

    void onCreated(ApkUpdateInfo apkUpdateInfo, CreationType creationType);

    void onDeleted(ApkUpdateInfo apkUpdateInfo);
}
