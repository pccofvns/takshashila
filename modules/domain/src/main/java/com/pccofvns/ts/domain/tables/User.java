/*
 * This file is generated by jOOQ.
 */
package com.pccofvns.ts.domain.tables;


import com.pccofvns.ts.domain.Keys;
import com.pccofvns.ts.domain.Ts;
import com.pccofvns.ts.domain.tables.records.UserRecord;

import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function7;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User extends TableImpl<UserRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>ts.user</code>
     */
    public static final User USER = new User();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserRecord> getRecordType() {
        return UserRecord.class;
    }

    /**
     * The column <code>ts.user.id</code>.
     */
    public final TableField<UserRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>ts.user.username</code>.
     */
    public final TableField<UserRecord, String> USERNAME = createField(DSL.name("username"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>ts.user.password</code>.
     */
    public final TableField<UserRecord, String> PASSWORD = createField(DSL.name("password"), SQLDataType.VARCHAR(255).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>ts.user.credentials_non_expired</code>.
     */
    public final TableField<UserRecord, Boolean> CREDENTIALS_NON_EXPIRED = createField(DSL.name("credentials_non_expired"), SQLDataType.BIT.nullable(false), this, "");

    /**
     * The column <code>ts.user.non_expired</code>.
     */
    public final TableField<UserRecord, Boolean> NON_EXPIRED = createField(DSL.name("non_expired"), SQLDataType.BIT.nullable(false), this, "");

    /**
     * The column <code>ts.user.non_locked</code>.
     */
    public final TableField<UserRecord, Boolean> NON_LOCKED = createField(DSL.name("non_locked"), SQLDataType.BIT.nullable(false), this, "");

    /**
     * The column <code>ts.user.enabled</code>.
     */
    public final TableField<UserRecord, Boolean> ENABLED = createField(DSL.name("enabled"), SQLDataType.BIT.nullable(false), this, "");

    private User(Name alias, Table<UserRecord> aliased) {
        this(alias, aliased, null);
    }

    private User(Name alias, Table<UserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>ts.user</code> table reference
     */
    public User(String alias) {
        this(DSL.name(alias), USER);
    }

    /**
     * Create an aliased <code>ts.user</code> table reference
     */
    public User(Name alias) {
        this(alias, USER);
    }

    /**
     * Create a <code>ts.user</code> table reference
     */
    public User() {
        this(DSL.name("user"), null);
    }

    public <O extends Record> User(Table<O> child, ForeignKey<O, UserRecord> key) {
        super(child, key, USER);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Ts.TS;
    }

    @Override
    public Identity<UserRecord, Long> getIdentity() {
        return (Identity<UserRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<UserRecord> getPrimaryKey() {
        return Keys.KEY_USER_PRIMARY;
    }

    @Override
    public User as(String alias) {
        return new User(DSL.name(alias), this);
    }

    @Override
    public User as(Name alias) {
        return new User(alias, this);
    }

    @Override
    public User as(Table<?> alias) {
        return new User(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(String name) {
        return new User(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Name name) {
        return new User(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Table<?> name) {
        return new User(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, String, String, Boolean, Boolean, Boolean, Boolean> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super Long, ? super String, ? super String, ? super Boolean, ? super Boolean, ? super Boolean, ? super Boolean, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super Long, ? super String, ? super String, ? super Boolean, ? super Boolean, ? super Boolean, ? super Boolean, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
