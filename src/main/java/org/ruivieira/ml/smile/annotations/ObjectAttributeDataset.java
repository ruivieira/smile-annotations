package org.ruivieira.ml.smile.annotations;

import smile.data.Attribute;
import smile.data.AttributeDataset;
import smile.data.NominalAttribute;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ObjectAttributeDataset<T, R> {

    private List<String> attributesNames = new ArrayList<>();
    private Attribute[] attributes = new Attribute[]{};
    private Attribute responseAttribute = null;
    private AttributeDataset dataset = null;
    private final String name;
    private final String responseName;

    public ObjectAttributeDataset(String name, String responseName) {
        this.name = name;
        this.responseName = responseName;
    }

    private final List<String> extractAnnotatedAttributes(T object) {
        List<String> fieldNames = new ArrayList<>();
        for(Field field  : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(RandomForestFeature.class)) {
                fieldNames.add(field.getName());
            }
        }
        return fieldNames;
    }

    public void add(T object, R response) throws IllegalAccessException, ParseException {
        if (attributesNames.size() == 0) {
            attributesNames = extractAnnotatedAttributes(object);
            final int n = attributesNames.size();
            attributes = new Attribute[n];
            for (int i = 0 ; i < n ; i++) {
                attributes[i] = new NominalAttribute(attributesNames.get(i));
            }
            responseAttribute = new NominalAttribute(responseName);
            dataset = new AttributeDataset(name, attributes, responseAttribute);
        }

        Field[] fields = object.getClass().getDeclaredFields();
        final int k = fields.length;
        final double[] features = new double[k];
        for (int i = 0 ; i < k ; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            Object value = field.get(object);
            Attribute attribute = attributes[i];
            features[i] = attribute.valueOf(value.toString());
        }
        dataset.add(features, (int) responseAttribute.valueOf(response.toString()));
    }

    public AttributeDataset getDataset() {
        return dataset;
    }
}
