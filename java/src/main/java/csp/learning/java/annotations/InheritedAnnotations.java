package csp.learning.java.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class InheritedAnnotations {

    static final String ACCESS_ME = "access me";
    static final String NEW_VALUE = "new value";

    @Inherited
    @Target(TYPE)
    @Retention(RUNTIME)
    @interface InheritableAnnotation {
        String value();
    }

    @Target(TYPE)
    @Retention(RUNTIME)
    @interface UninheritableAnnotation {
    }

    @InheritableAnnotation(ACCESS_ME)
    @UninheritableAnnotation
    static class DoublyAnnotated {
    }

    static class InheritsOneAnnotation extends DoublyAnnotated {
    }

    @InheritableAnnotation(NEW_VALUE)
    static class OverridesInheritedAnnotation extends InheritsOneAnnotation {
    }

}
