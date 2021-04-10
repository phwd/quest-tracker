package com.oculus.os;

import android.os.PersistableBundle;

/* access modifiers changed from: package-private */
public class FunnelData {
    final PersistableBundle mActionData;
    final String mActionName;
    final String mFunnelName;
    final Long mInstanceId;
    final String mTag;
    final Type mType;

    enum Type {
        START,
        ACTION,
        END
    }
}
