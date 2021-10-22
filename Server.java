import java.io.*;
import java.net.*;
import java.util.Date;

public class Server {
    private static PrintWriter out;

    public static void exec(String cmd) throws IOException{
        Process p = new ProcessBuilder(cmd).start();
        BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String connect = bf.readLine();

        while (connect != null){
            out.println(connect);
            connect = bf.readLine();
        }
        out.println("*");
        bf.close();
    }

    private static final int port = 312;
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        ServerSocket listener = new ServerSocket(port);
        System.out.println("Connceting to client");

        // Listening for Client
        Socket client = listener.accept();
        String request;

        // This is where the menu is coming from
        out = new PrintWriter(client.getOutputStream(), true);

        System.out.println("Connection established");

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        menu(out);

        try {
            while (true) {
                request = in.readLine();
                switch(request) {
                    case "1":
                        exec("date");
                        break;

                    case "2":
                        exec("uptime");
                        break;

                    case "3":
                        exec("free");
                        break;

                    case "4":
                        exec("netstat");
                        break;

                    case "5":
                        exec("who");
                        break;

                    case "6":
                        exec("ps -e");
                        break;

                    case "7":
                        client.close();
                        listener.close();

                    default:
                        System.out.println("Invalid option, please try again: ");
                        request = in.readLine();
                }
//                if (isValidCommand(request)) {
//                    if (request.equals("1")) {
//                        exec("date");
//                    }
//
//                    if (request.equals("2")) {
//                        exec("uptime");
//                    }
//
//                    if (request.equals("3")) {
//                        exec("free");
//                    }
//
//                    if (request.equals("4")) {
//                        exec("netstat");
//                    }
//
//                    if (request.equals("5")) {
//                        exec("who");
//                    }
//
//                    if (request.equals("6")) {
//                        exec("ps -e");
//                    }
//
//                    if (request.equals("7")) {
//                        client.close();
//                        listener.close();
//                        break;
//                    }
//                } else {
//                    out.println("Invalid opperation");
//                }
            }
        } catch(SocketException e){
            System.out.println("Connection terminated");
        }

        System.out.println("Client disconected");
        client.close();
        listener.close();

    }
    public static void menu(PrintWriter out){
        out.println("Choose from menu options");
        out.println("1. Date and time");
        out.println("2. Uptime");
        out.println("3. Memory use");
        out.println("4. Netstat");
        out.println("5. Current Users");
        out.println("6. Running Processes");
        out.println("7. Exit");

    }
    public static boolean isValidCommand(String command){
        if(command == null);
        return command.equals("1") || command.equals("2") || command.equals("3") || command.equals("4") || command.equals("5") || command.equals("6") || command.equals("7");
    }
}
