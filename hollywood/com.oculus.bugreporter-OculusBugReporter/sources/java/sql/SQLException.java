package java.sql;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class SQLException extends Exception implements Iterable<Throwable> {
    private static final AtomicReferenceFieldUpdater<SQLException, SQLException> nextUpdater = AtomicReferenceFieldUpdater.newUpdater(SQLException.class, SQLException.class, "next");
    private static final long serialVersionUID = 2135244094396331484L;
    private String SQLState;
    private volatile SQLException next;
    private int vendorCode;

    public SQLException(String reason, String SQLState2, int vendorCode2) {
        super(reason);
        this.SQLState = SQLState2;
        this.vendorCode = vendorCode2;
        if (!(this instanceof SQLWarning) && DriverManager.getLogWriter() != null) {
            DriverManager.println("SQLState(" + SQLState2 + ") vendor code(" + vendorCode2 + ")");
            printStackTrace(DriverManager.getLogWriter());
        }
    }

    public SQLException(String reason, String SQLState2) {
        super(reason);
        this.SQLState = SQLState2;
        this.vendorCode = 0;
        if (!(this instanceof SQLWarning) && DriverManager.getLogWriter() != null) {
            printStackTrace(DriverManager.getLogWriter());
            DriverManager.println("SQLException: SQLState(" + SQLState2 + ")");
        }
    }

    public SQLException(String reason) {
        super(reason);
        this.SQLState = null;
        this.vendorCode = 0;
        if (!(this instanceof SQLWarning) && DriverManager.getLogWriter() != null) {
            printStackTrace(DriverManager.getLogWriter());
        }
    }

    public SQLException() {
        this.SQLState = null;
        this.vendorCode = 0;
        if (!(this instanceof SQLWarning) && DriverManager.getLogWriter() != null) {
            printStackTrace(DriverManager.getLogWriter());
        }
    }

    public SQLException(Throwable cause) {
        super(cause);
        if (!(this instanceof SQLWarning) && DriverManager.getLogWriter() != null) {
            printStackTrace(DriverManager.getLogWriter());
        }
    }

    public SQLException(String reason, Throwable cause) {
        super(reason, cause);
        if (!(this instanceof SQLWarning) && DriverManager.getLogWriter() != null) {
            printStackTrace(DriverManager.getLogWriter());
        }
    }

    public SQLException(String reason, String sqlState, Throwable cause) {
        super(reason, cause);
        this.SQLState = sqlState;
        this.vendorCode = 0;
        if (!(this instanceof SQLWarning) && DriverManager.getLogWriter() != null) {
            printStackTrace(DriverManager.getLogWriter());
            DriverManager.println("SQLState(" + this.SQLState + ")");
        }
    }

    public SQLException(String reason, String sqlState, int vendorCode2, Throwable cause) {
        super(reason, cause);
        this.SQLState = sqlState;
        this.vendorCode = vendorCode2;
        if (!(this instanceof SQLWarning) && DriverManager.getLogWriter() != null) {
            DriverManager.println("SQLState(" + this.SQLState + ") vendor code(" + vendorCode2 + ")");
            printStackTrace(DriverManager.getLogWriter());
        }
    }

    public String getSQLState() {
        return this.SQLState;
    }

    public int getErrorCode() {
        return this.vendorCode;
    }

    public SQLException getNextException() {
        return this.next;
    }

    public void setNextException(SQLException ex) {
        SQLException current = this;
        while (true) {
            SQLException next2 = current.next;
            if (next2 != null) {
                current = next2;
            } else if (!nextUpdater.compareAndSet(current, null, ex)) {
                current = current.next;
            } else {
                return;
            }
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Throwable> iterator() {
        return new Iterator<Throwable>() {
            /* class java.sql.SQLException.AnonymousClass1 */
            Throwable cause = this.firstException.getCause();
            SQLException firstException = SQLException.this;
            SQLException nextException = this.firstException.getNextException();

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.firstException == null && this.nextException == null && this.cause == null) {
                    return false;
                }
                return true;
            }

            @Override // java.util.Iterator
            public Throwable next() {
                if (this.firstException != null) {
                    Throwable throwable = this.firstException;
                    this.firstException = null;
                    return throwable;
                }
                Throwable th = this.cause;
                if (th != null) {
                    Throwable throwable2 = this.cause;
                    this.cause = th.getCause();
                    return throwable2;
                }
                SQLException sQLException = this.nextException;
                if (sQLException != null) {
                    Throwable throwable3 = this.nextException;
                    this.cause = sQLException.getCause();
                    this.nextException = this.nextException.getNextException();
                    return throwable3;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
