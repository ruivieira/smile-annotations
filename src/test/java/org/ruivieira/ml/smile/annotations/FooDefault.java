package org.ruivieira.ml.smile.annotations;

import java.util.Random;

public class FooDefault {

    final private int id;

    @RandomForestFeature
    private final String bar;

    @RandomForestFeature
    private final int baz;


    private FooDefault(String bar, int baz) {
        this.id = new Random().nextInt();
        this.bar = bar;
        this.baz = baz;
    }

    public static final FooDefault create(String bar, int baz) {
        return new FooDefault(bar, baz);
    }

}
