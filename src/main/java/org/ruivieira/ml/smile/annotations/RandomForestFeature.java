package org.ruivieira.ml.smile.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RandomForestFeature {
    AttributeType type() default AttributeType.NOMINAL;
}
