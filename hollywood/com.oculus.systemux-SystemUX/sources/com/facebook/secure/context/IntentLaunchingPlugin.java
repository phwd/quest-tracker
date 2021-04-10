package com.facebook.secure.context;

import android.content.Context;
import android.content.Intent;
import com.facebook.secure.intent.IntentScope;
import java.util.Set;
import javax.annotation.Nullable;

public interface IntentLaunchingPlugin {
    @Nullable
    Intent apply(Intent intent, Context context);

    @Nullable
    Intent apply(Intent intent, Context context, int i);

    Set<IntentScope.ScopeType> getApplicableScopeTypes();

    boolean isEligible(Intent intent, Context context);
}
