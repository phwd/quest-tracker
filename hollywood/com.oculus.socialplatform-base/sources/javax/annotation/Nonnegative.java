package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifier;
import javax.annotation.meta.TypeQualifierValidator;
import javax.annotation.meta.When;
import org.apache.commons.cli.PatternOptionBuilder;

@TypeQualifier(applicableTo = PatternOptionBuilder.NUMBER_VALUE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Nonnegative {
    When when() default When.ALWAYS;

    public static class Checker implements TypeQualifierValidator<Nonnegative> {
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x002f, code lost:
            if (r0 < 0) goto L_0x0014;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
            return javax.annotation.meta.When.ALWAYS;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
            if (r4 < 0) goto L_0x0014;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public javax.annotation.meta.When forConstantValue(javax.annotation.Nonnegative r6, java.lang.Object r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof java.lang.Number
                if (r0 == 0) goto L_0x0014
                java.lang.Number r7 = (java.lang.Number) r7
                boolean r0 = r7 instanceof java.lang.Long
                if (r0 == 0) goto L_0x0017
                long r2 = r7.longValue()
                r0 = 0
                int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            L_0x0012:
                if (r4 >= 0) goto L_0x0037
            L_0x0014:
                javax.annotation.meta.When r0 = javax.annotation.meta.When.NEVER
                return r0
            L_0x0017:
                boolean r0 = r7 instanceof java.lang.Double
                if (r0 == 0) goto L_0x0024
                double r2 = r7.doubleValue()
                r0 = 0
                int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                goto L_0x0012
            L_0x0024:
                boolean r0 = r7 instanceof java.lang.Float
                if (r0 == 0) goto L_0x0032
                float r1 = r7.floatValue()
                r0 = 0
                int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            L_0x002f:
                if (r0 >= 0) goto L_0x0037
                goto L_0x0014
            L_0x0032:
                int r0 = r7.intValue()
                goto L_0x002f
            L_0x0037:
                javax.annotation.meta.When r0 = javax.annotation.meta.When.ALWAYS
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: javax.annotation.Nonnegative.Checker.forConstantValue(javax.annotation.Nonnegative, java.lang.Object):javax.annotation.meta.When");
        }
    }
}
