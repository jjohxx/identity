package edu.umss.fcyt.posgrado.identity.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Component
public class SummarizedFieldsProcessor {

    public <T> void processFields(List<T> elements) {
        for (Object element : elements) {
            Class<?> clazz = element.getClass();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(SummarizedField.class)) {
                    field.setAccessible(true);
                    try {
                        field.set(element, null);
                    } catch (IllegalAccessException e) {
                        // Skip if the field was not found to set null
                    }
                }
            }
        }
    }
}
