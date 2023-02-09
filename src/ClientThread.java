import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread implements Runnable {
    @Override
    public void run() {
        String host = "127.0.0.1";  // localhost
        int port = 9800;
        try (Socket clientSocket = new Socket(host, port);  // ждём подключения
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Вводим строку на клиенте:");
            String resp = in.readLine();
            out.println(resp + "\n"); // отправляем сообщение на сервер
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
