package com.facebook.stetho.inspector.elements.android;

import android.app.Application;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.NodeType;

final class AndroidDocumentRoot extends AbstractChainedDescriptor<AndroidDocumentRoot> {
    private final Application mApplication;

    /* access modifiers changed from: protected */
    public String onGetNodeName(AndroidDocumentRoot androidDocumentRoot) {
        return "root";
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, com.facebook.stetho.common.Accumulator] */
    /* access modifiers changed from: protected */
    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    public /* bridge */ /* synthetic */ void onGetChildren(AndroidDocumentRoot androidDocumentRoot, Accumulator accumulator) {
        onGetChildren(androidDocumentRoot, (Accumulator<Object>) accumulator);
    }

    public AndroidDocumentRoot(Application application) {
        this.mApplication = (Application) Util.throwIfNull(application);
    }

    /* access modifiers changed from: protected */
    public NodeType onGetNodeType(AndroidDocumentRoot androidDocumentRoot) {
        return NodeType.DOCUMENT_NODE;
    }

    /* access modifiers changed from: protected */
    public void onGetChildren(AndroidDocumentRoot androidDocumentRoot, Accumulator<Object> accumulator) {
        accumulator.store(this.mApplication);
    }
}
