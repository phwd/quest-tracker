package java.util.logging;

import android.icu.impl.number.Padder;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import sun.util.logging.LoggingSupport;

public class SimpleFormatter extends Formatter {
    private static final String format = LoggingSupport.getSimpleFormat();
    private final Date dat = new Date();

    @Override // java.util.logging.Formatter
    public synchronized String format(LogRecord record) {
        String source;
        String message;
        String throwable;
        this.dat.setTime(record.getMillis());
        if (record.getSourceClassName() != null) {
            source = record.getSourceClassName();
            if (record.getSourceMethodName() != null) {
                source = source + Padder.FALLBACK_PADDING_STRING + record.getSourceMethodName();
            }
        } else {
            source = record.getLoggerName();
        }
        message = formatMessage(record);
        throwable = "";
        if (record.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.println();
            record.getThrown().printStackTrace(pw);
            pw.close();
            throwable = sw.toString();
        }
        return String.format(format, this.dat, source, record.getLoggerName(), record.getLevel().getLocalizedLevelName(), message, throwable);
    }
}
