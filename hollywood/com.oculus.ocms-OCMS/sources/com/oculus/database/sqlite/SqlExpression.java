package com.oculus.database.sqlite;

import android.database.DatabaseUtils;
import androidx.annotation.VisibleForTesting;
import com.facebook.debug.log.LoggingUtil;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public class SqlExpression {
    @VisibleForTesting
    static final int SQLITE_MAXIMUM_PARAMETER_COUNT = 450;

    public static abstract class Expression {
        public abstract String getExpression();

        /* access modifiers changed from: package-private */
        public abstract Iterable<String> getParameterIterable();

        public abstract String[] getParameters();
    }

    public static ConjunctionExpression and() {
        return new AndExpression();
    }

    public static ConjunctionExpression and(Expression... expressionArr) {
        AndExpression andExpression = new AndExpression();
        for (Expression expression : expressionArr) {
            andExpression.add(expression);
        }
        return andExpression;
    }

    public static ConjunctionExpression or() {
        return new OrExpression();
    }

    public static ConjunctionExpression or(Expression... expressionArr) {
        OrExpression orExpression = new OrExpression();
        for (Expression expression : expressionArr) {
            orExpression.add(expression);
        }
        return orExpression;
    }

    public static Expression eq(String str, String str2) {
        return new EqualityExpression(str, str2);
    }

    public static Expression lt(String str, String str2) {
        return new LtExpression(str, str2);
    }

    public static Expression lte(String str, String str2) {
        return new LteExpression(str, str2);
    }

    public static Expression like(String str, String str2) {
        return new LikeExpression(str, str2);
    }

    public static Expression gt(String str, String str2) {
        return new GtExpression(str, str2);
    }

    public static Expression gte(String str, String str2) {
        return new GteExpression(str, str2);
    }

    public static Expression in(String str, Collection<?> collection) {
        return new InExpression(str, collection);
    }

    public static Expression in(String str, String... strArr) {
        return in(str, (Collection<?>) Arrays.asList(strArr));
    }

    public static Expression in(String str, Iterable<?> iterable) {
        return in(str, (Collection<?>) Lists.newArrayList(iterable));
    }

    public static Expression notIn(String str, Collection<?> collection) {
        return new InExpression(str, collection, true);
    }

    public static Expression notIn(String str, String... strArr) {
        return notIn(str, Arrays.asList(strArr));
    }

    public static Expression not(Expression expression) {
        return new NotExpression(expression);
    }

    public static Expression isNotNull(String str) {
        return new FieldNotNullExpression(str);
    }

    public static Expression raw(String str) {
        return new RawExpression(str);
    }

    public static class ConjunctionExpression extends Expression {
        private final String conjunction;
        private final List<Expression> expressions = new ArrayList();

        public ConjunctionExpression(String str) {
            this.conjunction = str;
        }

        public void add(Expression expression) {
            this.expressions.add(expression);
        }

        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public String getExpression() {
            if (this.expressions.isEmpty()) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            boolean z = true;
            for (Expression expression : this.expressions) {
                if (!z) {
                    sb.append(" ");
                    sb.append(this.conjunction);
                    sb.append(" ");
                }
                sb.append(expression.getExpression());
                z = false;
            }
            sb.append(")");
            return sb.toString();
        }

        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public String[] getParameters() {
            return (String[]) Iterables.toArray(getParameterIterable(), String.class);
        }

        /* access modifiers changed from: package-private */
        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public Iterable<String> getParameterIterable() {
            return Iterables.concat(Iterables.transform(this.expressions, new Function<Expression, Iterable<String>>() {
                /* class com.oculus.database.sqlite.SqlExpression.ConjunctionExpression.AnonymousClass1 */

                public Iterable<String> apply(Expression expression) {
                    return expression.getParameterIterable();
                }
            }));
        }
    }

    private static class AndExpression extends ConjunctionExpression {
        public AndExpression() {
            super("AND");
        }
    }

    private static class OrExpression extends ConjunctionExpression {
        public OrExpression() {
            super("OR");
        }
    }

    private static class BinaryExpression extends Expression {
        private final String key;
        private final String operator;
        private final String value;

        public BinaryExpression(String str, String str2, String str3) {
            this.key = str;
            this.value = str2;
            this.operator = str3;
        }

        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public String getExpression() {
            return this.key + this.operator + "?";
        }

        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public String[] getParameters() {
            return new String[]{this.value};
        }

        /* access modifiers changed from: package-private */
        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public Collection<String> getParameterIterable() {
            return Collections.singleton(this.value);
        }
    }

    /* access modifiers changed from: private */
    public static class EqualityExpression extends BinaryExpression {
        public EqualityExpression(String str, String str2) {
            super(str, str2, "=");
        }
    }

    /* access modifiers changed from: private */
    public static class GtExpression extends BinaryExpression {
        public GtExpression(String str, String str2) {
            super(str, str2, ">");
        }
    }

    /* access modifiers changed from: private */
    public static class GteExpression extends BinaryExpression {
        public GteExpression(String str, String str2) {
            super(str, str2, ">=");
        }
    }

    /* access modifiers changed from: private */
    public static class LtExpression extends BinaryExpression {
        public LtExpression(String str, String str2) {
            super(str, str2, "<");
        }
    }

    /* access modifiers changed from: private */
    public static class LteExpression extends BinaryExpression {
        public LteExpression(String str, String str2) {
            super(str, str2, "<=");
        }
    }

    private static class LikeExpression extends BinaryExpression {
        public LikeExpression(String str, String str2) {
            super(str, str2, " like ");
        }
    }

    /* access modifiers changed from: private */
    public static class InExpression extends Expression {
        private final Collection<?> inlineValues;
        private final String name;
        private final boolean notIn;
        private final Collection<?> parameterValues;

        public InExpression(String str, Collection<?> collection, boolean z) {
            this.name = (String) Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(collection);
            this.parameterValues = collection.size() < 450 ? collection : Collections.emptyList();
            this.inlineValues = collection.size() < 450 ? Collections.emptyList() : collection;
            this.notIn = z;
        }

        public InExpression(String str, Collection<?> collection) {
            this(str, collection, false);
        }

        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public String getExpression() {
            String str;
            if (this.parameterValues.isEmpty()) {
                str = SqlExpression.formatInClause(this.inlineValues);
            } else {
                str = "(?" + Strings.repeat(",?", this.parameterValues.size() - 1) + ")";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(this.name);
            sb.append(this.notIn ? " NOT" : "");
            sb.append(" IN ");
            sb.append(str);
            return sb.toString();
        }

        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public String[] getParameters() {
            return (String[]) Iterables.toArray(getParameterIterable(), String.class);
        }

        /* access modifiers changed from: package-private */
        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public Iterable<String> getParameterIterable() {
            return Iterables.transform(this.parameterValues, Functions.toStringFunction());
        }
    }

    private static class NotExpression extends Expression {
        private Expression original;

        public NotExpression(Expression expression) {
            Preconditions.checkNotNull(expression);
            this.original = expression;
        }

        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public String getExpression() {
            return "NOT (" + this.original.getExpression() + ")";
        }

        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public String[] getParameters() {
            return this.original.getParameters();
        }

        /* access modifiers changed from: package-private */
        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public Iterable<String> getParameterIterable() {
            return this.original.getParameterIterable();
        }
    }

    private static class FieldNotNullExpression extends Expression {
        private final String mColumnName;

        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public String[] getParameters() {
            return new String[0];
        }

        public FieldNotNullExpression(String str) {
            this.mColumnName = str;
        }

        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public String getExpression() {
            return this.mColumnName + " NOT NULL";
        }

        /* access modifiers changed from: package-private */
        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public Iterable<String> getParameterIterable() {
            return Collections.emptyList();
        }
    }

    private static class RawExpression extends Expression {
        private final String clause;

        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public String[] getParameters() {
            return new String[0];
        }

        public RawExpression(String str) {
            this.clause = str;
        }

        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public String getExpression() {
            return this.clause;
        }

        /* access modifiers changed from: package-private */
        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public Collection<String> getParameterIterable() {
            return Collections.emptyList();
        }
    }

    public static String formatInClause(Iterable<?> iterable) {
        Iterable<String> quoteIterator = quoteIterator(iterable);
        return "(" + Joiner.on(',').join(quoteIterator) + ")";
    }

    private static Iterable<String> quoteIterator(Iterable<?> iterable) {
        return Iterables.transform(iterable, new Function<Object, String>() {
            /* class com.oculus.database.sqlite.SqlExpression.AnonymousClass1 */

            @Override // com.google.common.base.Function
            public String apply(@Nullable Object obj) {
                return DatabaseUtils.sqlEscapeString(obj == null ? LoggingUtil.NO_HASHCODE : obj.toString());
            }
        });
    }
}
