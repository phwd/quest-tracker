package com.fasterxml.jackson.annotation;

import com.oculus.common.build.BuildConfig;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Locale;
import java.util.TimeZone;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonFormat {
    String locale() default "##default";

    String pattern() default "";

    Shape shape() default Shape.ANY;

    String timezone() default "##default";

    public enum Shape {
        ANY,
        SCALAR,
        ARRAY,
        OBJECT,
        NUMBER,
        NUMBER_FLOAT,
        NUMBER_INT,
        STRING,
        BOOLEAN;

        public boolean isNumeric() {
            return this == NUMBER || this == NUMBER_INT || this == NUMBER_FLOAT;
        }

        public boolean isStructured() {
            return this == OBJECT || this == ARRAY;
        }
    }

    public static class Value {
        private final Locale locale;
        private final String pattern;
        private final Shape shape;
        private final TimeZone timezone;

        public Value() {
            this(BuildConfig.PROVIDER_SUFFIX, Shape.ANY, BuildConfig.PROVIDER_SUFFIX, BuildConfig.PROVIDER_SUFFIX);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public Value(java.lang.String r4, com.fasterxml.jackson.annotation.JsonFormat.Shape r5, java.lang.String r6, java.lang.String r7) {
            /*
                r3 = this;
                java.lang.String r0 = "##default"
                r1 = 0
                if (r6 == 0) goto L_0x0018
                int r2 = r6.length()
                if (r2 == 0) goto L_0x0018
                boolean r2 = r0.equals(r6)
                if (r2 == 0) goto L_0x0012
                goto L_0x0018
            L_0x0012:
                java.util.Locale r2 = new java.util.Locale
                r2.<init>(r6)
                goto L_0x0019
            L_0x0018:
                r2 = r1
            L_0x0019:
                if (r7 == 0) goto L_0x002c
                int r6 = r7.length()
                if (r6 == 0) goto L_0x002c
                boolean r6 = r0.equals(r7)
                if (r6 == 0) goto L_0x0028
                goto L_0x002c
            L_0x0028:
                java.util.TimeZone r1 = java.util.TimeZone.getTimeZone(r7)
            L_0x002c:
                r3.<init>(r4, r5, r2, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.annotation.JsonFormat.Value.<init>(java.lang.String, com.fasterxml.jackson.annotation.JsonFormat$Shape, java.lang.String, java.lang.String):void");
        }

        public Value(String str, Shape shape2, Locale locale2, TimeZone timeZone) {
            this.pattern = str;
            this.shape = shape2;
            this.locale = locale2;
            this.timezone = timeZone;
        }

        public String getPattern() {
            return this.pattern;
        }

        public Shape getShape() {
            return this.shape;
        }

        public Locale getLocale() {
            return this.locale;
        }

        public TimeZone getTimeZone() {
            return this.timezone;
        }
    }
}
