package ru.vt.entities.piudb;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Operation {

    public enum OperationValues {
        INSERT(1),
        DELETE(2),
        UPDATE(3),
        EXISTS(4),
        REVIVE(5),
        CROSS(6);

        public final int operationId;

        OperationValues(int operationId) {
            this.operationId = operationId;
        }
    }


    @Id
    int operationId;

    @Column(name = "internalTitle")
    String name;

    @Override
    public String toString() {
        return name;
    }

}
