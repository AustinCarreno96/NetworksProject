import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

public class Client extends Thread{

    private static int portNumber = 3001;
    public String command;

    // Constructor
    public Client(String command) {
        this.command = command;
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int amountOfThreads = 0;
        Queue<Thread> threadQueue = new LinkedList<Thread>();

        // menu for the user
        System.out.println("Choose from menu options");
        System.out.println("1. Date and time");
        System.out.println("2. Uptime");
        System.out.println("3. Memory use");
        System.out.println("4. Netstat");
        System.out.println("5. Current Users");
        System.out.println("6. Running Processes");
        System.out.println("7. Exit");
        System.out.print("> ");
        String command = userInput.nextLine();

        // TODO: CREATING THREADS
        System.out.print("Enter in the amount of times you'd like to run this command: ");
        amountOfThreads = userInput.nextInt();

        // TODO: COME BACK TO LATER
//            while(amountOfThreads > 25 && amountOfThreads % 5 != 0) {
//                if(amountOfThreads == 1) {
//                    break;
//                }
//                System.out.print("Invalid Entry, Please Try Again: ");
//                amountOfThreads = userInput.nextInt();
//            }


        // Threads being created
        for(int i = 0; i < amountOfThreads; i++) {
            Client newClient = new Client(command);
            threadQueue.add(newClient);
        }

        for(int j = 0; j < amountOfThreads; j++) {
            threadQueue.poll().start();
        }
    }// end main

    public void run() {
        long time = 0;
        // TODO: TEST
        System.out.println("Threads are Working");
        StringBuilder builtString = new StringBuilder();

        try(Socket sock = new Socket("192.168.1.84", portNumber)) {
            long start = System.currentTimeMillis();
            System.out.println("Connected");

            PrintWriter send = new PrintWriter(sock.getOutputStream());
            send.println(command);
            send.flush();

            InputStream input = sock.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String serverMessage;

            while((serverMessage = reader.readLine()) != null) {
                builtString.append(serverMessage + "\n");
            }
            // TODO: LINES 21 - 23 ARE TESTS
            String currentTime = reader.readLine();

            System.out.println(currentTime);

        } catch(UnknownHostException UHE) {
            System.out.println("Server cannot be found at this time");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
