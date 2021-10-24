import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;


public class Client implements Runnable {

    // TODO: TEST
    private static final int SERVER_PORT = 312;

    static String command = null;
    int clients;

    // TODO: Need command line arguments for IP Address and Port Number
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);


        // a queue to hold threads and remove them as called
        Queue<Thread> clientQueue = new LinkedList<Thread>();

        while(command != "7") {

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
            command = scanner.nextLine();

            // TODO: Need to check user input for correct commands

            // If user chooses 7, quit program
            if (command.equals("7")) {
                break;
            }

            // how many threads will be needed
            System.out.println("\nHow many requests would you like to run? (Please select from 1, 5, 10, 15, 20, or 25)");
            int requestAmount = scanner.nextInt();

            // test for a correct amount of threads
            while (requestAmount != 1 && requestAmount != 5 && requestAmount != 10 && requestAmount != 15 && requestAmount != 20 && requestAmount != 25) {
                System.out.println("\nInvalid entry, please enter either 1, 5, 10, 15, 20, or 25:");
                requestAmount = scanner.nextInt();
            }


            // filling queue with threads
            for (int i = 0; i < requestAmount; i++) {
                // TODO: Come back when client class is made
                clientQueue.add(new Thread(new Client(i)));
            }

            // Calling the Threads from the queue
            for (int j = 0; j < requestAmount; j++) {
                clientQueue.poll().start();
            }
        }// end of while loop

        // Grab the server IP Address
        // TODO: Wont be necessary, need command line arguments
//        String SERVER_IP = scanner.next();

//        try {
//            // Create socket with server IP Address and port number
//            // TODO: Test, need to get rid of host IP for later
//            Socket socket = new Socket("192.168.1.84", SERVER_PORT);
//
//            DataInputStream input2 = new DataInputStream(System.in);
//            DataOutputStream out2 = new DataOutputStream(socket.getOutputStream());
//
//            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
//
//            // Pulling the menu from the server
//            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//
//            String serverResponse;
//
//
//
//            // Testing if server and client are connected
////            while ((serverResponse = input.readLine()) != null) {
////                System.out.println(serverResponse);
////                if (serverResponse.contains("*")) {
////                    break;
////                }
////            }
//
//            System.out.println("TESTTESTTEST");
////            System.out.print("Enter the Hostname: ");
//            Scanner userInput = new Scanner(System.in);
//            String userChoice = userInput.next();
//            Thread testThread = new Thread(userChoice);
//            testThread.start();
//            System.out.println(testThread.getName());
//
//            while(true) {
//                serverResponse = input.readLine();
////            for(int i = 0; i < 25; i++) {
////                Scanner userInput = new Scanner(System.in);
////                Thread testThread = new Thread(userInput.next());
////                testThread.start();
////                System.out.println(testThread.getName());
////            }
//                System.out.println("Working");
//
//                // Only for menu, probably needs to go
////                while ((serverResponse = input.readLine()) != null) {
////                    System.out.println(serverResponse);
////                    if (serverResponse.contains("*")) {
////                        break;
////                    }
////                }
//
//
//
//                if (serverResponse == null)
//                    break;
//
//            }
//
//            socket.close();
//            System.exit(0);
//        } catch(SocketException se) {
//            System.out.println("Not working");
//            System.exit(1);
//        }

    }// end main

    public Client(int value) {
        clients = value;
    }

    // Runs as many times as the user asks for
    public void run() {
        try {
            // start time for client request
            long startTime = System.currentTimeMillis();

            // Create socket with server IP Address and port number
            // TODO: Test, need to get rid of host IP for later
            // TODO: You need to type in your own IP Address
            Socket socket = new Socket("192.168.1.84", SERVER_PORT);

            // Pulling the menu from the server
            PrintWriter sent = new PrintWriter(socket.getOutputStream());
            sent.println(command);
            sent.flush();

            DataInputStream input2 = new DataInputStream(System.in);
            DataOutputStream out2 = new DataOutputStream(socket.getOutputStream());

            BufferedReader serverRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));



            String serverResponse = "";
            System.out.println(serverResponse);



            // Testing if server and client are connected
//            while ((serverResponse = input.readLine()) != null) {
//                System.out.println(serverResponse);
//                if (serverResponse.contains("*")) {
//                    break;
//                }
//            }

            System.out.println("TESTTESTTEST");
//            System.out.print("Enter the Hostname: ");
            Scanner userInput = new Scanner(System.in);
            String userChoice = userInput.next();
            Thread testThread = new Thread(userChoice);
            testThread.start();
            System.out.println(testThread.getName());

            while(true) {
                serverResponse = serverRead.readLine();

                System.out.println("Working");

                // Only for menu, probably needs to go
//                while ((serverResponse = input.readLine()) != null) {
//                    System.out.println(serverResponse);
//                    if (serverResponse.contains("*")) {
//                        break;
//                    }
//                }

                if (serverResponse == null)
                    break;

            }

            socket.close();
            System.exit(0);
        } catch(SocketException se) {
            System.out.println("Not working");
            System.exit(1);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}// end Client class
