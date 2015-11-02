package samples.customannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Miguel Reyes
 *         Date: 11/2/15
 *         Time: 11:56 AM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IssueInfo {
    public enum Type {
        BUG, IMPROVEMENT, FEATURE
    }

    Type type() default Type.BUG;

    String reporter() default "Auto";

    String created() default "11/01/2015";
}