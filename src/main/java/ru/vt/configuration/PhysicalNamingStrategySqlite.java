package ru.vt.configuration;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class PhysicalNamingStrategySqlite extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        var newName = name.getText().substring(0, 1).toLowerCase() +
                name.getText().substring(1);

        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                newName,
                name.isQuoted()
        );
    }

}
