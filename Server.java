import java.io.*;
import java.net.*;
import java.util.Date;

public class Server implements Runnable{
    private Socket sock;
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
        System.out.println("Connecting to client");

        // Listening for Client
        Socket client = listener.accept();
        String request;

        // This is where the menu is coming from
        out = new PrintWriter(client.getOutputStream(), true);

        System.out.println("Connection established");

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        try {
            while (true) {
                request = in.readLine();
                switch(request) {
                    case "1":
//                        exec("date");
                        out.println("Server Test");
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
                        out.println("Invalid option, please try again: ");
                        request = in.readLine();
                }
            }
        } catch(SocketException e){
            System.out.println("Connection terminated");
        }

        System.out.println("Client disconnected");
        client.close();
        listener.close();

    }

    public static boolean isValidCommand(String command){
        if(command == null);
        return command.equals("1") || command.equals("2") || command.equals("3") || command.equals("4") || command.equals("5") || command.equals("6") || command.equals("7");
    }
}
