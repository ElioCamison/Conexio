package com.esliceu;

import java.sql.Connection;
import java.util.LinkedList;

public class PoolConnections {
    LinkedList<Connection> stack = new LinkedList();
    private int MAX_NUM_COM = 10;

    public synchronized Connection getConnection(){
        Connection con = stack.getFirst();
        stack.remove();

        return con;
    }

    public synchronized void addConnection(Connection con){
        if (stack.size() <= MAX_NUM_COM){
            stack.push(con);
        }

    }

}
