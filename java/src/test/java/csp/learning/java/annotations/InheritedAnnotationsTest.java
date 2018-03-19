package csp.learning.java.annotations;

import org.junit.jupiter.api.Test;

import static csp.learning.java.annotations.InheritedAnnotations.*;
import static org.assertj.core.api.Assertions.assertThat;

class InheritedAnnotationsTest {

    @Test
    void testInheritedAnnotations() {
        assertThat(DoublyAnnotated.class).hasAnnotations(InheritableAnnotation.class, UninheritableAnnotation.class);
        assertThat(DoublyAnnotated.class.getAnnotation(InheritableAnnotation.class).value()).isEqualTo(ACCESS_ME);

        assertThat(new InheritsOneAnnotation()).isInstanceOf(DoublyAnnotated.class);
        assertThat(InheritsOneAnnotation.class.getDeclaredAnnotations().length).isEqualTo(0);
        assertThat(InheritsOneAnnotation.class).hasAnnotation(InheritableAnnotation.class);
        assertThat(InheritsOneAnnotation.class.getAnnotation(InheritableAnnotation.class).value()).isEqualTo(ACCESS_ME);

        assertThat(new OverridesInheritedAnnotation()).isInstanceOf(InheritsOneAnnotation.class);
        assertThat(OverridesInheritedAnnotation.class.getDeclaredAnnotations().length).isEqualTo(1);
        assertThat(OverridesInheritedAnnotation.class).hasAnnotation(InheritableAnnotation.class);
        assertThat(OverridesInheritedAnnotation.class.getAnnotation(InheritableAnnotation.class).value()).isEqualTo(NEW_VALUE);
    }

}
