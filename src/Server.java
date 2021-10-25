import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Scanner;


public class Server {

    private static int portNumber = 3001;

    // TODO: Look into making a single thread for the server that updates. Need to make switch statement and that's it
    public static void main(String[] args) {
        String message = "";

        // Creation of a server socket
        try(ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Socket successfully created at port " + portNumber);

            // calling accept to start listening for client requests
            while(true) {
                Socket sock = serverSocket.accept();
                System.out.println("Listening");

                // TODO: LINES 20 - 25 ARE ABOUT READING IN THE INPUT FROM THE CLIENT
                // used to read data sent from client
                // TODO: TEST
                InputStream input = sock.getInputStream();

                // used to read the input stream at a higher, more legible level
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));


//            System.out.println(theReader);

                // Sending data to the Client
                OutputStream output = sock.getOutputStream();
                // Wrapped in PrintWriter to send data in text format
                PrintWriter writer = new PrintWriter(output, true);
                String theReader = inputReader.readLine();

                switch(theReader) {
                    case "1":
                        message = commandRequest("date");
                        break;
                    case "2":
                        message = commandRequest("uptime");
                        break;
                    case "3":
                        message = commandRequest("free -h");
                        break;
                    case "4":
                        message = commandRequest("netstat");
                        break;
                    case "5":
                        message = commandRequest("w");
                        break;
                    case "6":
                        message = commandRequest("ps -aux");
                        break;
                    default:
                        message = "Incorrect Entry, Please Try Again";

                }

                // TODO: TEST
//                writer.println(new Date().toString());
                writer.println(message);
                writer.flush();
                sock.close();
            }
        } catch(IOException e) {
            System.out.println("The server did not connect correctly");
            e.printStackTrace();
        }
    }// end main

    private static String commandRequest(String requestedCommand) {
        StringBuilder message = new StringBuilder();

        try {
            // starting the process for whichever command is chosen by the user
            Process process = Runtime.getRuntime().exec(requestedCommand);

            // reading the output from the process ran
            BufferedReader theReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output;

            while((output = theReader.readLine()) != null) message.append(output + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return message.toString();
    }
}














































































//    private static PrintWriter out;
//    private static final int port = 312;
//
//    // Austin
//    private Socket sock;
//
//    public static void exec(String cmd) throws IOException{
//        Process p = new ProcessBuilder(cmd).start();
//        BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        String connect = bf.readLine();
//
//
//        while (connect != null){
//            out.println(connect);
//            connect = bf.readLine();
//        }
//        out.println("*");
//        bf.close();
//    }
//
//
//    public static void main(String[] args) throws IOException {
//        long start = System.currentTimeMillis();
//        Scanner clientInput = new Scanner(System.in);
//
//        ServerSocket listener = new ServerSocket(port);
//        System.out.println("Connecting to client");
//
//        // Listening for Client
//        Socket client = listener.accept();
//        String request;
//
//        // This is where the menu is coming from
//        out = new PrintWriter(client.getOutputStream(), true);
//
//        System.out.println("Connection established");
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//
//        try {
//            while (true) {
//                request = in.readLine();
//                switch(request) {
//                    case "1":
////                        exec("date");
//                        out.println("Server Test");
//                        break;
//
//                    case "2":
//                        exec("uptime");
//                        break;
//
//                    case "3":
//                        exec("free");
//                        break;
//
//                    case "4":
//                        exec("netstat");
//                        break;
//
//                    case "5":
//                        exec("who");
//                        break;
//
//                    case "6":
//                        exec("ps -e");
//                        break;
//
//                    case "7":
//                        client.close();
//                        listener.close();
//
//                    default:
//                        out.println("Invalid option, please try again: ");
//                        request = in.readLine();
//                }
//            }
//        } catch(SocketException e){
//            System.out.println("Connection terminated");
//        }
//
//        System.out.println("Client disconnected");
//        client.close();
//        listener.close();
//
//    }
//
//    public static boolean isValidCommand(String command){
//        if(command == null);
//        return command.equals("1") || command.equals("2") || command.equals("3") || command.equals("4") || command.equals("5") || command.equals("6") || command.equals("7");
//    }
}
