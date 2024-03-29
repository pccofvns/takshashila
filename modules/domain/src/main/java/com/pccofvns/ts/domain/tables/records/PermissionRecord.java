/*
 * This file is generated by jOOQ.
 */
package com.pccofvns.ts.domain.tables.records;


import com.pccofvns.ts.domain.tables.Permission;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PermissionRecord extends UpdatableRecordImpl<PermissionRecord> implements Record2<Long, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>ts.permission.id</code>.
     */
    public PermissionRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>ts.permission.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>ts.permission.name</code>.
     */
    public PermissionRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>ts.permission.name</code>.
     */
    @NotNull
    @Size(max = 255)
    public String getName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Long, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Permission.PERMISSION.ID;
    }

    @Override
    public Field<String> field2() {
        return Permission.PERMISSION.NAME;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public PermissionRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public PermissionRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public PermissionRecord values(Long value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PermissionRecord
     */
    public PermissionRecord() {
        super(Permission.PERMISSION);
    }

    /**
     * Create a detached, initialised PermissionRecord
     */
    public PermissionRecord(Long id, String name) {
        super(Permission.PERMISSION);

        setId(id);
        setName(name);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PermissionRecord
     */
    public PermissionRecord(com.pccofvns.ts.domain.tables.pojos.Permission value) {
        super(Permission.PERMISSION);

        if (value != null) {
            setId(value.id());
            setName(value.name());
            resetChangedOnNotNull();
        }
    }
}
