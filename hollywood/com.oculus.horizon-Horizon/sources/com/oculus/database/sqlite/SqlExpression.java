package com.oculus.database.sqlite;

import X.AnonymousClass0eE;
import X.C03670du;
import androidx.annotation.VisibleForTesting;
import com.google.common.base.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SqlExpression {
    @VisibleForTesting
    public static final int SQLITE_MAXIMUM_PARAMETER_COUNT = 450;

    public static class AndExpression extends ConjunctionExpression {
        public AndExpression() {
            super("AND");
        }
    }

    public static class BinaryExpression extends Expression {
        public final String key;
        public final String operator;
        public final String value;
    }

    public static class ConjunctionExpression extends Expression {
        public final String conjunction;
        public final List<Expression> expressions = new ArrayList();

        @Override // com.oculus.database.sqlite.SqlExpression.Expression
        public final Iterable<String> A00() {
            List<Expression> list = this.expressions;
            AnonymousClass1 r0 = new Function<Expression, Iterable<String>>() {
                /* class com.oculus.database.sqlite.SqlExpression.ConjunctionExpression.AnonymousClass1 */

                /* Return type fixed from 'java.lang.Object' to match base method */
                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                @Override // com.google.common.base.Function
                public final Iterable<String> apply(Expression expression) {
                    return expression.A00();
                }
            };
            if (list != null) {
                return new AnonymousClass0eE(new C03670du(list, r0));
            }
            throw null;
        }

        public ConjunctionExpression(String str) {
            this.conjunction = str;
        }
    }

    public static class EqualityExpression extends BinaryExpression {
    }

    public static abstract class Expression {
        public abstract Iterable<String> A00();
    }

    public static class FieldNotNullExpression extends Expression {
        public final String mColumnName;
    }

    public static class GtExpression extends BinaryExpression {
    }

    public static class GteExpression extends BinaryExpression {
    }

    public static class InExpression extends Expression {
        public final Collection<?> inlineValues;
        public final String name;
        public final boolean notIn;
        public final Collection<?> parameterValues;
    }

    public static class LikeExpression extends BinaryExpression {
    }

    public static class LtExpression extends BinaryExpression {
    }

    public static class LteExpression extends BinaryExpression {
    }

    public static class NotExpression extends Expression {
        public final Expression original;
    }

    public static class OrExpression extends ConjunctionExpression {
        public OrExpression() {
            super("OR");
        }
    }

    public static class RawExpression extends Expression {
        public final String clause;
    }
}
