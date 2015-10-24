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
import java.util.*;

/**
 *
 * @author atamarkin2
 */
public class ExchangeClientWithThread {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String host = "codebb.cloudapp.net";
        int port = 17429;
        String user = "TraderGators";
        String pass = "insidertrading";
        Socket socket = new Socket(host, port);
        PrintWriter pout = new PrintWriter(socket.getOutputStream());
        BufferedReader bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        ArrayList<StockItem> stocks = new ArrayList<StockItem>();


        Runnable r = new Runnable(){
            public void run() {
                boolean exit = false;
                int count = 0;
                while(!exit){
                    try{
                        Thread.sleep(100);   
                    }
                    catch(Exception e){
                        System.out.println("ERERER");
                    }

                    pout.println(user + " " + pass);
                    pout.print("SECURITIES" + " ");
                    pout.println();
                    pout.println("CLOSE_CONNECTION");
                    pout.flush();
                    String line;
                    try{
                        while ((line = bin.readLine()) != null) {
                            System.out.println(line);
                        }
                    }
                    catch(IOException e){
                        System.out.println("READLINE ERROR");
                    }
                    System.out.println("\n" + count);
                    if(count++ == 10){
                        exit = true;
                    }
                }

                
            }
        };

        new Thread(r).start();


    }
}

class StockItem{
    public String ticker;
    public double value;
    public double dividend;
    public double volatility;
    public Date time;

    public StockItem(String ticker, double value, double dividend, double volatility){
        this.ticker = ticker;
        this.value = value;
        this.dividend = dividend;
        this.volatility = volatility;
        this.time = new Date();
    }
}


