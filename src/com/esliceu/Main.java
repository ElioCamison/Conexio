package com.esliceu;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(8080);
        try {

            while (true) {
                Socket socket = listener.accept();
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    StringBuilder response = new StringBuilder();
                    response.append("<html><body><h1>Hello!</h1></body></html>");


                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: text/html");
                    out.println("Content-Length: " + response.length());
                    out.println();
                    out.println(response);
                    out.flush();
                    out.close();


                 
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
