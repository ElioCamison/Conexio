package com.esliceu;

import org.omg.CORBA.INTERNAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class PoolConnections {

    // Connexion creades per noltros.
    Queue<Connection> freeConnection = new LinkedList();
    // Connexion creades per l'usuari.
    LinkedList<Connection> userConnection = new LinkedList();
    private int MAX_NUM_COM = 10;


    // Constructor de connexions
    PoolConnections() throws SQLException {

        // Cream les connexions en base al número màxim que hem definit (MAX_NUM_COM).
        for (int i = 0; i < MAX_NUM_COM; i++) {
            freeConnection.add(DriverManager.getConnection("jdbc:mysql://localhost/empresa","root", "test"));
        }
    }

    // Retorna las connexions que h ha dins sa pila(freeConnectio)
    public synchronized Connection getConnection() throws Exception {

        // Si la pila està buida es llança un Exception
        if (freeConnection.isEmpty()) throw new Exception("");

        // Retorna la connexió
        return freeConnection.poll();
    }


    // Afegim les connexions fins sa pila una vegadas s'han alliberat
    public synchronized void addConnection(Connection con){

        // Controlam el numero de connexions que afegim.
        if (freeConnection.size() < MAX_NUM_COM){
            userConnection.push(con);
        }
    }

}
