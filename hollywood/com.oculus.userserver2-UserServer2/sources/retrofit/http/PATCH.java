package retrofit.http;

import com.oculus.common.build.BuildConfig;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@RestMethod(hasBody = BuildConfig.IS_LIBCXX_BUILD, value = "PATCH")
public @interface PATCH {
    String value();
}
