package ru.vt.configuration;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitJoinColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitJoinTableNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;

public class ImplicitNamingStrategySqlite extends ImplicitNamingStrategyJpaCompliantImpl {
    @Override
    public Identifier determineJoinTableName(ImplicitJoinTableNameSource source) {
        final String name = source.getOwningPhysicalTableName()
                + source.getNonOwningPhysicalTableName();
        return toIdentifier(name, source.getBuildingContext());
    }

    @Override
    public Identifier determineJoinColumnName(ImplicitJoinColumnNameSource source) {
        return toIdentifier(source.getReferencedColumnName().getText(), source.getBuildingContext());
    }
}
