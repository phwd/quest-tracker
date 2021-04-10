package com.oculus.libraryapi;

import android.content.Context;
import com.oculus.library.model.App;

@Deprecated
public class OVRLibraryInternal extends OVRLibrary {
    @Deprecated
    public OVRLibraryInternal(Context context) {
        super(context);
    }

    @Deprecated
    public void insertApp(App app) {
        refetch();
    }

    @Deprecated
    public App getAppInternalById(String id) {
        App[] apps = getAllApps();
        for (App app : apps) {
            if (app.id.equals(id)) {
                return app;
            }
        }
        return null;
    }
}
