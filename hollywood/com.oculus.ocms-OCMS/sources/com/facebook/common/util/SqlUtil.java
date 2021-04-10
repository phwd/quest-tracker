package com.facebook.common.util;

import android.database.DatabaseUtils;
import com.facebook.debug.log.LoggingUtil;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.mobileconfig.metadata.ParamsMapEntry;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Deprecated
public class SqlUtil {
    public static String formatInClause(String... strArr) {
        return formatInClause(Arrays.asList(strArr));
    }

    public static String formatInClauseOfLongs(Iterable<Long> iterable) {
        return "(" + Joiner.on(',').join(iterable) + ")";
    }

    public static String formatInClause(Iterable<?> iterable) {
        Iterable<String> quoteIterator = quoteIterator(iterable);
        return "(" + Joiner.on(',').join(quoteIterator) + ")";
    }

    public static String andExpressions(String... strArr) {
        return andExpressions(Arrays.asList(strArr));
    }

    public static String andExpressions(Collection<String> collection) {
        return "(" + Joiner.on(" AND ").skipNulls().join(collection) + ")";
    }

    public static String formatOrderColumnBySetClause(String str, Iterable<?> iterable) {
        Iterable<String> quoteIterator = quoteIterator(iterable);
        StringBuilder sb = new StringBuilder("CASE ");
        sb.append(str);
        sb.append(" ");
        int i = 0;
        for (String str2 : quoteIterator) {
            sb.append("WHEN ");
            sb.append(str2);
            sb.append(" THEN ");
            sb.append(i);
            sb.append(" ");
            i++;
        }
        sb.append(ParamsMapEntry.END_MARKER);
        return sb.toString();
    }

    private static Iterable<String> quoteIterator(Iterable<?> iterable) {
        return Iterables.transform(iterable, new Function<Object, String>() {
            /* class com.facebook.common.util.SqlUtil.AnonymousClass1 */

            @Override // com.google.common.base.Function
            public String apply(@Nullable Object obj) {
                return DatabaseUtils.sqlEscapeString(obj == null ? LoggingUtil.NO_HASHCODE : obj.toString());
            }
        });
    }
}
