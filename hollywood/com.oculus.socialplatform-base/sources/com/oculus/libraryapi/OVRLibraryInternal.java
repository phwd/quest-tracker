package com.oculus.libraryapi;

import android.content.Context;
import com.oculus.library.model.App;

@Deprecated
public class OVRLibraryInternal extends OVRLibrary {
    @Deprecated
    public void insertApp(App app) {
        refetch(null);
    }

    @Deprecated
    public App getAppInternalById(String str) {
        App[] allApps = getAllApps();
        for (App app : allApps) {
            if (app.id.equals(str)) {
                return app;
            }
        }
        return null;
    }

    @Deprecated
    public OVRLibraryInternal(Context context) {
        super(context);
    }
}
