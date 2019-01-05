package abc.tzous4j.kpsys.annotation;

import abc.tzous4j.kpsys.helper.ResultTypeEnum;

import java.lang.annotation.*;

/**
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FireAuthority {
    ResultTypeEnum resultType() default ResultTypeEnum.page;    // page or json
}
