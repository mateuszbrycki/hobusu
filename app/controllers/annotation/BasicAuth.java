package controllers.annotation;

import controllers.action.BasicAuthAction;
import play.mvc.With;

import java.lang.annotation.*;

/**
 * Created by Mateusz Brycki on 31/12/2016.
 */
@With(BasicAuthAction.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Documented
public @interface BasicAuth {
}