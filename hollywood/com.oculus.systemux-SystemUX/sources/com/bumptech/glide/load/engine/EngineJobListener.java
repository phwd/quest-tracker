package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;

/* access modifiers changed from: package-private */
public interface EngineJobListener {
    void onEngineJobCancelled(EngineJob<?> engineJob, Key key);

    void onEngineJobComplete(EngineJob<?> engineJob, Key key, EngineResource<?> engineResource);
}
