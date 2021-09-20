import java.io.*;
import java.net.Socket;


public class Client {

    private static Socket clientSocket;// сокет сервера
    private static BufferedReader reader;// для чтения с консоли
    private static BufferedReader in; //поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 10223;

        try {
            try {
                clientSocket = new Socket(host, port);// запрос на соединение
                reader = new BufferedReader(new InputStreamReader(System.in)); // читать сообщение с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                // Будем общаться с сервером в цикле
                while (true) {

                    String answer = in.readLine(); // ждём, что скажет сервер
                    System.out.println(answer); // получив - выводим на экран
                    String request = reader.readLine();
                    out.write(request + "\n"); // оправляем имя на сервер
                    out.flush();

                }

            } finally {
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}


