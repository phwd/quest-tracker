package com.facebook.stetho.inspector.elements.android;

import android.graphics.Rect;
import android.view.View;
import android.view.Window;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.Descriptor;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public final class WindowDescriptor extends AbstractChainedDescriptor<Window> implements HighlightableDescriptor<Window> {
    WindowDescriptor() {
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, com.facebook.stetho.common.Accumulator] */
    /* access modifiers changed from: protected */
    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    public /* bridge */ /* synthetic */ void onGetChildren(Window window, Accumulator accumulator) {
        onGetChildren(window, (Accumulator<Object>) accumulator);
    }

    /* access modifiers changed from: protected */
    public void onGetChildren(Window window, Accumulator<Object> accumulator) {
        View peekDecorView = window.peekDecorView();
        if (peekDecorView != null) {
            accumulator.store(peekDecorView);
        }
    }

    @Nullable
    public View getViewAndBoundsForHighlighting(Window window, Rect rect) {
        return window.peekDecorView();
    }

    @Nullable
    public Object getElementToHighlightAtPosition(Window window, int i, int i2, Rect rect) {
        View view;
        HighlightableDescriptor highlightableDescriptor;
        Descriptor.Host host = getHost();
        if (host instanceof AndroidDescriptorHost) {
            view = window.peekDecorView();
            highlightableDescriptor = ((AndroidDescriptorHost) host).getHighlightableDescriptor(view);
        } else {
            view = null;
            highlightableDescriptor = null;
        }
        if (highlightableDescriptor == null) {
            return null;
        }
        return highlightableDescriptor.getElementToHighlightAtPosition(view, i, i2, rect);
    }
}
