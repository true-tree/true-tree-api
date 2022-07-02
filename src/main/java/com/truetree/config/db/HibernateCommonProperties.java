package com.truetree.config.db;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;

import java.util.HashMap;
import java.util.Map;

public class HibernateCommonProperties {
    public static Map<String ,Object> commonProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(AvailableSettings.HBM2DDL_AUTO, "none");
        properties.put(AvailableSettings.STORAGE_ENGINE, "innodb");
        properties.put(AvailableSettings.SHOW_SQL, true);
        properties.put(AvailableSettings.FORMAT_SQL, true);
        properties.put(AvailableSettings.PHYSICAL_NAMING_STRATEGY, CamelCaseToUnderscoresNamingStrategy.class.getName());
        properties.put(AvailableSettings.IMPLICIT_NAMING_STRATEGY, SpringImplicitNamingStrategy.class.getName());
        return properties;
    }
}
