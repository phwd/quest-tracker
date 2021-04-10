package com.facebook.secure.intent;

import android.content.Context;
import android.content.Intent;
import java.util.List;
import javax.annotation.Nullable;

public interface IntentScope {

    public enum ScopeType {
        INTERNAL,
        SAME_KEY,
        FAMILY,
        TRUSTED_APP,
        ACCESSIBLE_BY_ANY_APP,
        EXTERNAL,
        THIRD_PARTY,
        ANY
    }

    @Nullable
    Intent enforceActivityIntent(Intent intent, Context context);

    @Nullable
    Intent enforceActivityIntent(Intent intent, Context context, @Nullable String str);

    List<Intent> enforceBroadcastIntent(Intent intent, Context context);

    List<Intent> enforceBroadcastIntent(Intent intent, Context context, @Nullable String str);

    boolean enforceContentProvider(String str, Context context);

    @Nullable
    Intent enforceReceiverIntent(Intent intent, Context context);

    @Nullable
    Intent enforceReceiverIntent(Intent intent, Context context, @Nullable String str);

    @Nullable
    Intent enforceServiceIntent(Intent intent, Context context);

    @Nullable
    Intent enforceServiceIntent(Intent intent, Context context, @Nullable String str);

    ScopeType getScopeType();
}
