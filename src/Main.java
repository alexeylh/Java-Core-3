import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Main {
    public static void main(String[] args) throws IOException {
        int port = 9800;
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            System.out.println("Server started");

            ClientThread clientThread = new ClientThread();
            clientThread.run();

            Socket clientSocket = serverSocket.accept(); // ждем подключения
            System.out.println("New connection accepted");

            try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

                final String input = in.readLine(); // читаем сообщение клиента
                System.out.println(String.format("Hi %s, your port is %d", input, clientSocket.getPort()));
            }
        }
    }
}
