# smile-annotations

A set of Java annotations to select class fields as features for SMILE models.

## Random forests

Assuming we have an example object of class `Foo` with fields `String bar` and `int baz` which we want to use as
attributes for a SMILE random forest classifier.

With `smile-annotations` we simply need to annotate the fields with `@RandomForestFeature` as follows:

```java
public class Foo {

    @RandomForestFeature
    private final String bar;

    @RandomForestFeature
    private final int baz;


    public Foo(String bar, int baz) {
        this.bar = bar;
        this.baz = baz;
    }
}
```

We then use the proxy dataset object `ObjectAttributeDataset` and add directly
object instances (and their associated response):

```java
ObjectAttributeDataset<Foo, Boolean> dataset = new ObjectAttributeDataset<>("test", "truth");

dataset.add(foo1, true);
dataset.add(foo2, true);
dataset.add(foo3, false);
// ...
```

Internally, `ObjectAttributeDataset` builds a SMILE `AttributeDataset` which we
can retrieve at any point:

```java
AttributeDataset ad = dataset.getDataset();
```

By default, the annotation created a _nominal_ attribute for the field, but this
can be explicitly declared by using the `AttributeType`:

```java
public class Foo {

    @RandomForestFeature(type = AttributeType.STRING)
    private final String bar;

    @RandomForestFeature(type = AttributeType.NUMERIC)
    private final int baz;


    public Foo(String bar, int baz) {
        this.bar = bar;
        this.baz = baz;
    }
}
```