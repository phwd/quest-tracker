package androidx.loader.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContentResolverCompat;
import androidx.core.os.CancellationSignal;
import androidx.core.os.OperationCanceledException;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

public class CursorLoader extends AsyncTaskLoader<Cursor> {
    private CancellationSignal mCancellationSignal;
    private Cursor mCursor;
    private final Loader<Cursor>.ForceLoadContentObserver mObserver = new Loader.ForceLoadContentObserver();
    private String[] mProjection;
    private String mSelection;
    private String[] mSelectionArgs;
    private String mSortOrder;
    private Uri mUri;

    @Override // androidx.loader.content.AsyncTaskLoader
    @Nullable
    public Cursor loadInBackground() {
        synchronized (this) {
            if (!isLoadInBackgroundCanceled()) {
                this.mCancellationSignal = new CancellationSignal();
            } else {
                throw new OperationCanceledException();
            }
        }
        try {
            Cursor query = ContentResolverCompat.query(getContext().getContentResolver(), this.mUri, this.mProjection, this.mSelection, this.mSelectionArgs, this.mSortOrder, this.mCancellationSignal);
            if (query != null) {
                try {
                    query.getCount();
                    query.registerContentObserver(this.mObserver);
                } catch (RuntimeException e) {
                    query.close();
                    throw e;
                }
            }
            synchronized (this) {
                this.mCancellationSignal = null;
            }
            return query;
        } catch (Throwable th) {
            synchronized (this) {
                this.mCancellationSignal = null;
                throw th;
            }
        }
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
        synchronized (this) {
            if (this.mCancellationSignal != null) {
                this.mCancellationSignal.cancel();
            }
        }
    }

    public void deliverResult(@Nullable Cursor cursor) {
        if (!isReset()) {
            Cursor cursor2 = this.mCursor;
            this.mCursor = cursor;
            if (isStarted()) {
                super.deliverResult((Object) cursor);
            }
            if (cursor2 != null && cursor2 != cursor && !cursor2.isClosed()) {
                cursor2.close();
            }
        } else if (cursor != null) {
            cursor.close();
        }
    }

    public CursorLoader(@NonNull Context context) {
        super(context);
    }

    public CursorLoader(@NonNull Context context, @NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        super(context);
        this.mUri = uri;
        this.mProjection = strArr;
        this.mSelection = str;
        this.mSelectionArgs = strArr2;
        this.mSortOrder = str2;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.loader.content.Loader
    public void onStartLoading() {
        Cursor cursor = this.mCursor;
        if (cursor != null) {
            deliverResult(cursor);
        }
        if (takeContentChanged() || this.mCursor == null) {
            forceLoad();
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.loader.content.Loader
    public void onStopLoading() {
        cancelLoad();
    }

    public void onCanceled(@Nullable Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.loader.content.Loader
    public void onReset() {
        super.onReset();
        onStopLoading();
        Cursor cursor = this.mCursor;
        if (cursor != null && !cursor.isClosed()) {
            this.mCursor.close();
        }
        this.mCursor = null;
    }

    @NonNull
    public Uri getUri() {
        return this.mUri;
    }

    public void setUri(@NonNull Uri uri) {
        this.mUri = uri;
    }

    @Nullable
    public String[] getProjection() {
        return this.mProjection;
    }

    public void setProjection(@Nullable String[] strArr) {
        this.mProjection = strArr;
    }

    @Nullable
    public String getSelection() {
        return this.mSelection;
    }

    public void setSelection(@Nullable String str) {
        this.mSelection = str;
    }

    @Nullable
    public String[] getSelectionArgs() {
        return this.mSelectionArgs;
    }

    public void setSelectionArgs(@Nullable String[] strArr) {
        this.mSelectionArgs = strArr;
    }

    @Nullable
    public String getSortOrder() {
        return this.mSortOrder;
    }

    public void setSortOrder(@Nullable String str) {
        this.mSortOrder = str;
    }

    @Override // androidx.loader.content.AsyncTaskLoader, androidx.loader.content.Loader
    @Deprecated
    public void dump(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("mUri=");
        printWriter.println(this.mUri);
        printWriter.print(str);
        printWriter.print("mProjection=");
        printWriter.println(Arrays.toString(this.mProjection));
        printWriter.print(str);
        printWriter.print("mSelection=");
        printWriter.println(this.mSelection);
        printWriter.print(str);
        printWriter.print("mSelectionArgs=");
        printWriter.println(Arrays.toString(this.mSelectionArgs));
        printWriter.print(str);
        printWriter.print("mSortOrder=");
        printWriter.println(this.mSortOrder);
        printWriter.print(str);
        printWriter.print("mCursor=");
        printWriter.println(this.mCursor);
    }
}