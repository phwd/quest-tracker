package com.facebook.crudolib.urimap.componenthelper;

import android.content.Intent;
import com.facebook.annotations.OkToExtend;
import com.facebook.infer.annotation.Nullsafe;

@OkToExtend
@Nullsafe(Nullsafe.Mode.LOCAL)
public class ComponentHelper {
    public boolean isEnabled() {
        return true;
    }

    public Intent transformIntent(Intent intent) {
        return intent;
    }
}
