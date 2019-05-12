package dao;

import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    List<T> getAll();

    T getOne( long id );

    void create( T t ) throws UniqueValueException;

    void update( T t ) throws SqlServerException;

    void delete( T t ) throws SQLException;
}