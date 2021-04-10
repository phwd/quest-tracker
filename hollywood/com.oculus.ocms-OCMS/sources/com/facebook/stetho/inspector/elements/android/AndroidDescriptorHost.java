package com.facebook.stetho.inspector.elements.android;

import com.facebook.stetho.inspector.elements.Descriptor;
import javax.annotation.Nullable;

interface AndroidDescriptorHost extends Descriptor.Host {
    @Nullable
    HighlightableDescriptor getHighlightableDescriptor(@Nullable Object obj);
}
