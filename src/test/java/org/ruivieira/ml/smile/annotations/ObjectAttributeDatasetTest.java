package org.ruivieira.ml.smile.annotations;

import org.junit.Before;
import org.junit.Test;
import smile.data.AttributeDataset;
import smile.data.Datum;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ObjectAttributeDatasetTest {

    private final List<FooDefault> fooDefaults = new ArrayList<>();
    private final List<FooExplicitType> fooExplicitType = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        fooDefaults.add(FooDefault.create("one", 1));
        fooDefaults.add(FooDefault.create("two", 2));
        fooDefaults.add(FooDefault.create("three", 3));

        fooExplicitType.add(FooExplicitType.create("one", 1));
        fooExplicitType.add(FooExplicitType.create("two", 2));
        fooExplicitType.add(FooExplicitType.create("three", 3));

    }

    @Test
    public void addSize() throws ParseException, IllegalAccessException {

        ObjectAttributeDataset<FooDefault, Boolean> dataset = new ObjectAttributeDataset<>("test", "truth");

        for (FooDefault fooDefault : fooDefaults) {
            dataset.add(fooDefault, true);
        }

        AttributeDataset d = dataset.getDataset();

        assertEquals(d.size(), 3);
    }

    @Test
    public void addElements() throws ParseException, IllegalAccessException {

        ObjectAttributeDataset<FooDefault, Boolean> dataset = new ObjectAttributeDataset<>("test", "truth");

        for (FooDefault fooDefault : fooDefaults) {
            dataset.add(fooDefault, true);
        }

        AttributeDataset d = dataset.getDataset();

        Datum<double[]> datum = d.get(0);

        assertEquals(datum.x[0], 0.0, 1e-30);
        assertEquals(datum.x[1], 0.0, 1e-30);
        assertEquals(datum.y, 0.0, 1e-30);

        datum = d.get(1);

        assertEquals(datum.x[0], 1.0, 1e-30);
        assertEquals(datum.x[1], 1.0, 1e-30);
        assertEquals(datum.y, 0.0, 1e-30);
    }
    @Test
    public void addSizeExplicit() throws ParseException, IllegalAccessException {

        ObjectAttributeDataset<FooExplicitType, Boolean> dataset = new ObjectAttributeDataset<>("test", "truth");

        for (FooExplicitType fooDefault : fooExplicitType) {
            dataset.add(fooDefault, true);
        }

        AttributeDataset d = dataset.getDataset();

        assertEquals(d.size(), 3);
    }

    @Test
    public void addElementsExplicit() throws ParseException, IllegalAccessException {

        ObjectAttributeDataset<FooExplicitType, Boolean> dataset = new ObjectAttributeDataset<>("test", "truth");

        for (FooExplicitType fooDefault : fooExplicitType) {
            dataset.add(fooDefault, true);
        }

        AttributeDataset d = dataset.getDataset();
        System.out.println(d.toString());
        Datum<double[]> datum = d.get(0);


        assertEquals(datum.x[0], 1.0, 1e-30);
        assertEquals(datum.x[1], 1.0, 1e-30);
        assertEquals(datum.y, 0.0, 1e-30);

        datum = d.get(1);

        assertEquals(datum.x[0], 2.0, 1e-30);
        assertEquals(datum.x[1], 2.0, 1e-30);
        assertEquals(datum.y, 0.0, 1e-30);
    }

}