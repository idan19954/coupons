package dao;

import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    List<T> getAll() throws SQLException;

    T   getOne( int id ) throws SQLException;

    int create( T t ) throws UniqueValueException;

    void update( T t ) throws SqlServerException;

    void delete( int id ) throws SQLException;
}