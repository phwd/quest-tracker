package com.facebook.gk.store;

import com.facebook.gk.store.AtomicFileHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
class StateFileSerializer implements AtomicFileHelper.FileSerializer<Object> {
    StateFileSerializer() {
    }
}
