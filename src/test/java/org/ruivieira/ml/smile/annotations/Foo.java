package org.ruivieira.ml.smile.annotations;

public class Foo {

    @RandomForestFeature
    private final String bar;

    @RandomForestFeature
    private final int baz;


    private Foo(String bar, int baz) {
        this.bar = bar;
        this.baz = baz;
    }

    public static final Foo create(String bar, int baz) {
        return new Foo(bar, baz);
    }

}
