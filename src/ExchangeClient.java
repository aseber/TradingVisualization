/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author atamarkin2
 */
public class ExchangeClient {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Usage: \nclientTask <host> <port> <user> <password> <command...>");

        }
        String host = "codebb.cloudapp.net";
        int port = 17429;
        String user = "TraderGators";
        String pass = "insidertrading";
        Socket socket = new Socket(host, port);
        PrintWriter pout = new PrintWriter(socket.getOutputStream());
        BufferedReader bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pout.println(user + " " + pass);
        for (int i = 0; i < args.length; i++) {
            pout.println(args[i]);
        }
        pout.println("CLOSE_CONNECTION");
        pout.flush();
        String line;
        while ((line = bin.readLine()) != null) {
            System.out.println(line);
        }
        pout.close();
        bin.close();
    }

}
