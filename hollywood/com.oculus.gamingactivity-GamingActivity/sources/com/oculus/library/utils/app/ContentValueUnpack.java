package com.oculus.library.utils.app;

import com.oculus.library.model.App;
import java.util.Set;

public class ContentValueUnpack {
    public final App app;
    public final Set<String> missingColumns;

    public ContentValueUnpack(App app2, Set<String> missingColumns2) {
        this.app = app2;
        this.missingColumns = missingColumns2;
    }
}
