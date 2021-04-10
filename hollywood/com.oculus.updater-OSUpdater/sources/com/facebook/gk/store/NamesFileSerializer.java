package com.facebook.gk.store;

import com.facebook.gk.store.AtomicFileHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
class NamesFileSerializer implements AtomicFileHelper.FileSerializer<Object> {
    NamesFileSerializer() {
    }
}
