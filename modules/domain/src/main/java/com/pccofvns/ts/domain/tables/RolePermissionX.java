/*
 * This file is generated by jOOQ.
 */
package com.pccofvns.ts.domain.tables;


import com.pccofvns.ts.domain.Keys;
import com.pccofvns.ts.domain.Ts;
import com.pccofvns.ts.domain.tables.records.RolePermissionXRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RolePermissionX extends TableImpl<RolePermissionXRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>ts.role_permission_x</code>
     */
    public static final RolePermissionX ROLE_PERMISSION_X = new RolePermissionX();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RolePermissionXRecord> getRecordType() {
        return RolePermissionXRecord.class;
    }

    /**
     * The column <code>ts.role_permission_x.role_id</code>.
     */
    public final TableField<RolePermissionXRecord, Long> ROLE_ID = createField(DSL.name("role_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>ts.role_permission_x.permission_id</code>.
     */
    public final TableField<RolePermissionXRecord, Long> PERMISSION_ID = createField(DSL.name("permission_id"), SQLDataType.BIGINT.nullable(false), this, "");

    private RolePermissionX(Name alias, Table<RolePermissionXRecord> aliased) {
        this(alias, aliased, null);
    }

    private RolePermissionX(Name alias, Table<RolePermissionXRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>ts.role_permission_x</code> table reference
     */
    public RolePermissionX(String alias) {
        this(DSL.name(alias), ROLE_PERMISSION_X);
    }

    /**
     * Create an aliased <code>ts.role_permission_x</code> table reference
     */
    public RolePermissionX(Name alias) {
        this(alias, ROLE_PERMISSION_X);
    }

    /**
     * Create a <code>ts.role_permission_x</code> table reference
     */
    public RolePermissionX() {
        this(DSL.name("role_permission_x"), null);
    }

    public <O extends Record> RolePermissionX(Table<O> child, ForeignKey<O, RolePermissionXRecord> key) {
        super(child, key, ROLE_PERMISSION_X);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Ts.TS;
    }

    @Override
    public List<ForeignKey<RolePermissionXRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK_ROLE_ID, Keys.FK_PERMISSION_ID);
    }

    private transient Role _role;
    private transient Permission _permission;

    /**
     * Get the implicit join path to the <code>ts.role</code> table.
     */
    public Role role() {
        if (_role == null)
            _role = new Role(this, Keys.FK_ROLE_ID);

        return _role;
    }

    /**
     * Get the implicit join path to the <code>ts.permission</code> table.
     */
    public Permission permission() {
        if (_permission == null)
            _permission = new Permission(this, Keys.FK_PERMISSION_ID);

        return _permission;
    }

    @Override
    public RolePermissionX as(String alias) {
        return new RolePermissionX(DSL.name(alias), this);
    }

    @Override
    public RolePermissionX as(Name alias) {
        return new RolePermissionX(alias, this);
    }

    @Override
    public RolePermissionX as(Table<?> alias) {
        return new RolePermissionX(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public RolePermissionX rename(String name) {
        return new RolePermissionX(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RolePermissionX rename(Name name) {
        return new RolePermissionX(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public RolePermissionX rename(Table<?> name) {
        return new RolePermissionX(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
