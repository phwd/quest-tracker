package com.oculus.library.utils.app;

import android.database.MatrixCursor;
import com.oculus.library.database.contract.LibraryDBContract;

public class PublicAppsCursor extends MatrixCursor {
    public static final String[] COLUMNS = {LibraryDBContract.Columns.SUPPORTED_PLATFORM.name, LibraryDBContract.Columns.HEAD_TRACKING.name, LibraryDBContract.Columns.SYSTEM_UI_AS_OVERLAY_MODE.name};
}
