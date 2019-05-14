package lib.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLConnectionPool {
    private static SQLConnectionPool instance = new SQLConnectionPool();
    private static final int MAX_CONNECTIONS = 5;
    private ArrayList<Connection> connections;

    /**
     * Private constructor for creating a new instance for the SQLConnectionPool singleton.
     * Here we initialize the connections ArrayList
     */
    private SQLConnectionPool() {
        connections = new ArrayList<>();

        for ( int i = 0; i < MAX_CONNECTIONS; i++ ) {
            Connection connection;

            try {
                Class.forName( "com.mysql.jdbc.Driver" ).newInstance();
                connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/coupons", "root", "" );
                connections.add( connection );
            } catch ( SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e ) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The public getter for the SQLConnectionPool instance
     *
     * @return The SQLConnectionPool object (instance)
     */
    public static SQLConnectionPool getInstance() {
        if ( instance == null ) {
            instance = new SQLConnectionPool();
        }

        return instance;
    }

    public synchronized Connection getConnection() {
        if ( connections.isEmpty() ) {
            try {
                wait();
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }

        Connection con = connections.get( connections.size() - 1 );
        connections.remove( con );

        return con;
    }

    /**
     * A method to return a used connection to the pool
     *
     * @param connection - the used connection to return
     */
    public synchronized void returnConnection( Connection connection ) {
        connections.add( connection );
        notify();
    }

    public void closeAllConnections() {
        for ( Connection connection : connections ) {
            try {
                connection.close();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }
    }
}



