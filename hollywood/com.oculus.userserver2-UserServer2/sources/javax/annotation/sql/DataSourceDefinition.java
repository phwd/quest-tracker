package javax.annotation.sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceDefinition {
    String className();

    String databaseName() default "";

    String description() default "";

    String name();

    String password() default "";

    String serverName() default "localhost";

    String url() default "";

    String user() default "";
}
