import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.lang.Thread;


public class Client {

    // TODO: TEST
    private static final int SERVER_PORT = 312;

    // TODO: Need command line arguments for IP Address and Port Number
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String command = null;

        // TODO: TEST

        // Grab the server IP Address
        // TODO: Wont be necessary, need command line arguments
//        String SERVER_IP = scanner.next();

        try {
            // Create socket with server IP Address and port number
            Socket socket = new Socket("192.168.1.84", SERVER_PORT);

            DataInputStream input2 = new DataInputStream(System.in);
            DataOutputStream out2 = new DataOutputStream(socket.getOutputStream());

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            // Pulling the menu from the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String serverResponse;

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
                serverResponse = input.readLine();
//            for(int i = 0; i < 25; i++) {
//                Scanner userInput = new Scanner(System.in);
//                Thread testThread = new Thread(userInput.next());
//                testThread.start();
//                System.out.println(testThread.getName());
//            }
                System.out.println("Working");

                // Only for menu, probably needs to go
//                while ((serverResponse = input.readLine()) != null) {
//                    System.out.println(serverResponse);
//                    if (serverResponse.contains("*")) {
//                        break;
//                    }
//                }

                System.out.println("Choose from menu options");
                System.out.println("1. Date and time");
                System.out.println("2. Uptime");
                System.out.println("3. Memory use");
                System.out.println("4. Netstat");
                System.out.println("5. Current Users");
                System.out.println("6. Running Processes");
                System.out.println("7. Exit");
                System.out.print("> ");
                command = keyboard.readLine();

                System.out.println(command);

                if (serverResponse == null)
                    break;

            }

            socket.close();
            System.exit(0);
        } catch(SocketException se) {
            System.out.println("Not working");
            System.exit(1);
        }
    }// end main
}// end Client class
