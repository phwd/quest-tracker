package com.oculus.library.job;

import com.facebook.inject.AbstractComponentProvider;

public class LibraryJobServiceAutoProvider extends AbstractComponentProvider<LibraryJobService> {
    public void inject(LibraryJobService libraryJobService) {
        LibraryJobService._UL_staticInjectMe(this, libraryJobService);
    }

    public boolean equals(Object obj) {
        return obj instanceof LibraryJobServiceAutoProvider;
    }
}
