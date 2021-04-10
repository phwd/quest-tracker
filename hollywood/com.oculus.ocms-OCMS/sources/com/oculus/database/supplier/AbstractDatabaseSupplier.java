package com.oculus.database.supplier;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.facebook.debug.log.BLog;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class AbstractDatabaseSupplier {
    private static final long DB_MAX_SIZE_NOT_SET = -1;
    private static final int DEFAULT_JOURNAL_SIZE_LIMIT = 51200;
    private static final int MAX_HARD_RETRIES = 5;
    private static final int MAX_SOFT_RETRIES = 5;
    private static final int RETRY_SLEEP_TIME_MS = 30;
    private static final Class<?> TAG = AbstractDatabaseSupplier.class;
    private final Context mContext;
    private SQLiteDatabase mDb;
    private final String mDbName;
    private final IErrorReporter mErrorReporter;
    private final ImmutableList<? extends SharedSQLiteSchemaPart> mSchemaParts;

    /* access modifiers changed from: protected */
    public int getJournalSizeLimit() {
        return DEFAULT_JOURNAL_SIZE_LIMIT;
    }

    /* access modifiers changed from: protected */
    public long getMaximumSizeLimit() {
        return -1;
    }

    private static class SoftErrors {
        private static final String DATABASE_CORRUPT_RECOVERY = "AbstractDatabaseSupplier_DATABASE_CORRUPT_RECOVERY";
        private static final String DELETE_AND_RETRY = "AbstractDatabaseSupplier_DELETE_AND_RETRY";
        private static final String EXCESSIVE_OPEN_RETRIES = "AbstractDatabaseSupplier_EXCESSIVE_RETRIES";
        private static final String PREFIX = "AbstractDatabaseSupplier_";

        private SoftErrors() {
        }
    }

    protected AbstractDatabaseSupplier(Context context, IErrorReporter iErrorReporter, ImmutableList<? extends SharedSQLiteSchemaPart> immutableList, String str) {
        this.mContext = context;
        this.mSchemaParts = immutableList;
        this.mDbName = str;
        this.mErrorReporter = iErrorReporter;
    }

    public synchronized SQLiteDatabase get() {
        ensureDatabase();
        return this.mDb;
    }

    private synchronized void ensureDatabase() {
        if (this.mDb == null || !this.mDb.isOpen()) {
            this.mDb = null;
            BLog.d(TAG, "Initializing database %s", this.mDbName);
            SQLiteException e = null;
            int i = 0;
            while (true) {
                if (i <= 10) {
                    if (i <= 5) {
                        break;
                    }
                    try {
                        this.mErrorReporter.softErrorWithCrash("AbstractDatabaseSupplier_DELETE_AND_RETRY", String.format("Deleting database \"%s\": open failures %d", this.mDbName, Integer.valueOf(i)), e);
                        this.mContext.deleteDatabase(this.mDbName);
                        break;
                    } catch (SQLiteException e2) {
                        e = e2;
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                        }
                        i++;
                    }
                } else {
                    break;
                }
            }
            this.mDb = initializeDatabase();
            if (i > 0) {
                this.mErrorReporter.softErrorWithCrash("AbstractDatabaseSupplier_EXCESSIVE_RETRIES", i + " attempts for " + this.mDbName);
            }
            if (this.mDb == null) {
                throw e;
            }
        }
    }

    private SQLiteDatabase initializeDatabase() {
        try {
            SQLiteDatabase writableDatabase = new SharedSQLiteOpenHelper(this.mContext, this.mDbName, this.mSchemaParts, getJournalSizeLimit(), getDatabaseErrorHandler()).getWritableDatabase();
            if (getMaximumSizeLimit() != -1) {
                writableDatabase.setMaximumSize(getMaximumSizeLimit());
            }
            return writableDatabase;
        } catch (StackOverflowError e) {
            BLog.e(TAG, "Database %s corrupt and repair overflowed; deleting", this.mDbName, e);
            this.mErrorReporter.softError("AbstractDatabaseSupplier_DATABASE_CORRUPT_RECOVERY", this.mDbName, e);
            deleteDatabase();
            return initializeDatabase();
        }
    }

    public synchronized void clearAllData() {
        get();
        clearAllDataInternal();
    }

    /* access modifiers changed from: protected */
    public synchronized void clearAllDataInternal() {
        UnmodifiableIterator<? extends SharedSQLiteSchemaPart> it = this.mSchemaParts.iterator();
        while (it.hasNext()) {
            ((SharedSQLiteSchemaPart) it.next()).clearAllData(this.mDb);
        }
    }

    public synchronized void deleteDatabase() {
        deleteDatabaseInternal();
    }

    /* access modifiers changed from: protected */
    public synchronized void deleteDatabaseInternal() {
        if (this.mDb != null) {
            this.mDb.close();
            this.mDb = null;
        }
        this.mContext.deleteDatabase(this.mDbName);
    }

    protected static DatabaseErrorHandler getDatabaseErrorHandler() {
        return new DefaultDatabaseErrorHandler();
    }
}
