package com.esliceu;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.gjt.mm.mysql.Driver;

public class Main {

    public static void main(String[] args) throws IOException {
        PoolConnections poolCon = new PoolConnections();

        ServerSocket listener = new ServerSocket(8080);
        try {

            while (true) {
                Socket socket = listener.accept();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    // Establecemos la conexión con la base de datos.
                    Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/empresa","root", "test");
                    // Preparamos la consulta
                    Statement s = conexion.createStatement();
                    ResultSet rs = s.executeQuery ("select * from CLIENT");
                    StringBuilder response = new StringBuilder();
                    // Recorremos el resultado, mientras haya registros para leer, y escribimos el resultado en pantalla.
                    while (rs.next())
                    {
                        response.append (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getString(3));
                        //response.append("<html><body><h1>Hello!</h1></body></html>");
                    }

                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    response.append("<html><body><h1>Hello!</h1></body></html>");


                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: text/html");
                    out.println("Content-Length: " + response.length());
                    out.println();
                    out.println(response);
                    out.flush();
                    out.close();
                    // Cerramos la conexion a la base de datos.
                    conexion.close();
                 
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    socket.close();
                }
            }

        }
        finally {
            listener.close();
        }


    }
}
