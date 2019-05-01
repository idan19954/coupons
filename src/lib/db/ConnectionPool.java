package lib.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {
    // the ConnectionPool ONLY instance (object) - this will be returned using getInstance()
    private static ConnectionPool instance = new ConnectionPool();
    // the list of all connections in the pool
    private ArrayList<Connection> connections;
    // the max connections in the pool
    private static final int MAX_CONNECTIONS = 5;

    /**
     * Private ctor for creating a new instance for the ConnectionPool singleton.
     * Here we initialize the connections ArrayList
     */
    private ConnectionPool() {
        connections = new ArrayList<>();
        for ( int i = 0; i < MAX_CONNECTIONS; i++ ) {
            Connection con;
            try {
                Class.forName( "com.microsoft.jdbc.ClientDriver" );
                con = DriverManager.getConnection( "jdbc:sqlserver://localhost:1433/database=Project;IntegratedSecurity=true;" );
                connections.add( con );

            } catch ( SQLException | ClassNotFoundException e ) {
                System.out.println( e.getMessage() );
            }

        }
    }

    /**
     * The public getter for the ConnectionPool instance
     *
     * @return The ConnectionPool object (instance)
     */
    public static ConnectionPool getInstance() {
        if ( instance == null ) {
            instance = new ConnectionPool();
        }

        return instance;
    }

    public synchronized Connection getConnection() {
        if ( connections.isEmpty() ) {

            try {
                wait();
            } catch ( InterruptedException e ) {
                // TODO Auto-generated catch block
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
     * @param con - the used connection to return
     */
    public synchronized void returnConnection( Connection con ) {
        connections.add( con );
        notify();
    }

    public void closeAllConnections( Connection con ) {
        for ( Connection connection : connections ) {
            try {
                connection.close();
            } catch ( SQLException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }

}



