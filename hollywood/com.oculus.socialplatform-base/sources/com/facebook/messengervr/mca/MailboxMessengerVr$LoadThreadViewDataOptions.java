package com.facebook.messengervr.mca;

import com.facebook.proguard.annotations.DoNotStripAny;
import javax.annotation.Nullable;

@DoNotStripAny
public class MailboxMessengerVr$LoadThreadViewDataOptions {
    @Nullable
    public String anchoredMessageId;
    @Nullable
    public Object cachedThreadThemeVariantList;
    public boolean includeComposerState;
    public boolean includeHotlikePreview;
    public boolean includeLoadMore;
    public boolean includeNullStateHeader;
    public boolean includeThreadThemeVariantList;
    public boolean includeTypingIndicator;
    public int limitNewer;
    public int limitOlder;
    @Nullable
    public Number maxAlreadyLoadedTimestampMs;
    @Nullable
    public Number minAlreadyLoadedTimestampMs;
    public boolean shouldLoadSynchronously;
    public boolean useFragmentedThreadModel;
    public boolean withMessageList;
}
