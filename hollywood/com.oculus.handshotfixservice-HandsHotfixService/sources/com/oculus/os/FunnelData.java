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

    FunnelData(Type type, String funnelName) {
        this.mType = type;
        this.mFunnelName = funnelName;
        this.mInstanceId = null;
        this.mActionName = null;
        this.mTag = null;
        this.mActionData = null;
    }

    FunnelData(Type type, String funnelName, long instanceId) {
        this.mType = type;
        this.mFunnelName = funnelName;
        this.mInstanceId = Long.valueOf(instanceId);
        this.mActionName = null;
        this.mTag = null;
        this.mActionData = null;
    }

    FunnelData(String funnelName, String actionName, String tag, PersistableBundle actionData) {
        this.mType = Type.ACTION;
        this.mFunnelName = funnelName;
        this.mInstanceId = null;
        this.mActionName = actionName;
        this.mTag = tag;
        this.mActionData = actionData;
    }

    FunnelData(String funnelName, long instanceId, String actionName, String tag, PersistableBundle actionData) {
        this.mType = Type.ACTION;
        this.mFunnelName = funnelName;
        this.mInstanceId = Long.valueOf(instanceId);
        this.mActionName = actionName;
        this.mTag = tag;
        this.mActionData = actionData;
    }
}
