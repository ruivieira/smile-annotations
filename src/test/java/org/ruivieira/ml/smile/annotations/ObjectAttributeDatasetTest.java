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

    private final List<Foo> foos = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        foos.add(Foo.create("one", 1));
        foos.add(Foo.create("two", 2));
        foos.add(Foo.create("three", 3));
    }

    @Test
    public void addSize() throws ParseException, IllegalAccessException {

        ObjectAttributeDataset<Foo, Boolean> dataset = new ObjectAttributeDataset<>("test", "truth");

        for (Foo foo : foos) {
            dataset.add(foo, true);
        }

        AttributeDataset d = dataset.getDataset();

        assertEquals(d.size(), 3);
    }

    @Test
    public void addElements() throws ParseException, IllegalAccessException {

        ObjectAttributeDataset<Foo, Boolean> dataset = new ObjectAttributeDataset<>("test", "truth");

        for (Foo foo : foos) {
            dataset.add(foo, true);
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
}