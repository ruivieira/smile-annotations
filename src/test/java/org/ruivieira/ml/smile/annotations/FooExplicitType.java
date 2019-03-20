package org.ruivieira.ml.smile.annotations;

import java.util.Random;

public class FooExplicitType {

    final private int id;

    @RandomForestFeature(type=AttributeType.STRING)
    private final String bar;

    @RandomForestFeature(type=AttributeType.NUMERIC)
    private final int baz;


    private FooExplicitType(String bar, int baz) {
        this.id = new Random().nextInt();
        this.bar = bar;
        this.baz = baz;
    }

    public static final FooExplicitType create(String bar, int baz) {
        return new FooExplicitType(bar, baz);
    }

}
