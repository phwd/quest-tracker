package com.oculus.database.supplier;

import X.AnonymousClass0NO;
import android.content.Context;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.google.common.collect.ImmutableList;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class AbstractDatabaseSupplier {
    public static final long DB_MAX_SIZE_NOT_SET = -1;
    public static final int DEFAULT_JOURNAL_SIZE_LIMIT = 51200;
    public static final int MAX_HARD_RETRIES = 5;
    public static final int MAX_SOFT_RETRIES = 5;
    public static final int RETRY_SLEEP_TIME_MS = 30;
    public static final Class<?> TAG = AbstractDatabaseSupplier.class;
    public final Context mContext;
    public SQLiteDatabase mDb;
    public final String mDbName;
    public final IErrorReporter mErrorReporter;
    public final ImmutableList<? extends SharedSQLiteSchemaPart> mSchemaParts;

    public static class SoftErrors {
        public static final String DATABASE_CORRUPT_RECOVERY = "AbstractDatabaseSupplier_DATABASE_CORRUPT_RECOVERY";
        public static final String DELETE_AND_RETRY = "AbstractDatabaseSupplier_DELETE_AND_RETRY";
        public static final String EXCESSIVE_OPEN_RETRIES = "AbstractDatabaseSupplier_EXCESSIVE_RETRIES";
        public static final String PREFIX = "AbstractDatabaseSupplier_";
    }

    private SQLiteDatabase A02() {
        try {
            return new SharedSQLiteOpenHelper(this.mContext, this.mDbName, this.mSchemaParts, new DefaultDatabaseErrorHandler()).getWritableDatabase();
        } catch (StackOverflowError e) {
            AnonymousClass0NO.A05(AbstractDatabaseSupplier.class, "Database %s corrupt and repair overflowed; deleting", this.mDbName, e);
            this.mErrorReporter.A97(SoftErrors.DATABASE_CORRUPT_RECOVERY, this.mDbName, e);
            synchronized (this) {
                SQLiteDatabase sQLiteDatabase = this.mDb;
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                    this.mDb = null;
                }
                this.mContext.deleteDatabase(this.mDbName);
                return A02();
            }
        }
    }

    public final synchronized SQLiteDatabase A03() {
        SQLiteDatabase sQLiteDatabase = this.mDb;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            SQLiteException e = null;
            this.mDb = null;
            int i = 0;
            while (true) {
                if (i <= 10) {
                    if (i <= 5) {
                        break;
                    }
                    try {
                        this.mErrorReporter.A99(SoftErrors.DELETE_AND_RETRY, String.format("Deleting database \"%s\": open failures %d", this.mDbName, Integer.valueOf(i)), e);
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
            this.mDb = A02();
            if (i > 0) {
                IErrorReporter iErrorReporter = this.mErrorReporter;
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append(" attempts for ");
                sb.append(this.mDbName);
                iErrorReporter.A98(SoftErrors.EXCESSIVE_OPEN_RETRIES, sb.toString());
            }
            if (this.mDb == null) {
                throw e;
            }
        }
        return this.mDb;
    }

    public AbstractDatabaseSupplier(Context context, IErrorReporter iErrorReporter, ImmutableList<? extends SharedSQLiteSchemaPart> immutableList, String str) {
        this.mContext = context;
        this.mSchemaParts = immutableList;
        this.mDbName = str;
        this.mErrorReporter = iErrorReporter;
    }
}
