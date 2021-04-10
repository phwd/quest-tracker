package com.oculus.library.utils.app;

import android.database.MatrixCursor;
import com.oculus.library.database.contract.LibraryDBContract;
import com.oculus.library.model.App;
import java.util.Collection;

public class PublicAppsCursor extends MatrixCursor {
    private static final String[] COLUMNS = {LibraryDBContract.Columns.SUPPORTED_PLATFORM.name, LibraryDBContract.Columns.HEAD_TRACKING.name, LibraryDBContract.Columns.SYSTEM_UI_AS_OVERLAY_MODE.name};

    public PublicAppsCursor(int size) {
        super(COLUMNS, size);
    }

    public void fill(Collection<App> apps) {
        for (App app : apps) {
            addRow(new Object[]{app.platform.name(), app.headTracking.name(), app.systemUiAsOverlayMode});
        }
    }
}
