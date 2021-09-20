import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket clientSocket; // сокет клиента
    private static ServerSocket serverSocket;// сокет сервера
    private static BufferedReader in; //поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        int port = 10223;

        try {
            try {
                serverSocket = new ServerSocket(port); // слушаем порт
                System.out.println("Сервер запущен!");
                clientSocket = serverSocket.accept(); // ждём подключения
                try {
                    // принимать и отправлять сообщения
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    // По запросу клиента сервер выдаёт приветствие
                    out.write("Доброго времени суток. Рады вас видеть. Как ваше имя? \n");
                    out.flush();
                    String name = in.readLine(); // ожидание пока клиент напишет своё имя

                    // Последующие диалоги
                    out.write("Привет " + name + " , Вы ребёнок? (y/n)\n");
                    out.flush();
                    String choiseClient = in.readLine();
                    if (choiseClient.equalsIgnoreCase("y")) {
                        // Выбрана детская зона зона
                        out.write("Великолепно " + name +
                                " Вы попадаете в игровую зону по мотивам 'Ну погоди'" +
                                " Введите персонаж, за которого хотите играть\n");
                        out.flush();
                        String character = in.readLine();
                        out.write("Поздравляю " + name + " , ваш выбор персонаж " + character + " Поехали, удачной игры!!! \n");
                        out.flush();
                    } else { // Зона для взрослых
                        out.write("Отлично " + name + " Добро пожаловать в наше казино, на что будете ставить: " +
                                "Красное, Чёрное или Зеро\n");
                        out.flush();
                        String bet = in.readLine();
                        out.write("Ваш выбор " + bet + " , Поехали, удачной игры!!! \n");
                        out.flush();
                    }


                } finally { // Закрытие сокетов и потоков
                    clientSocket.close();
                    // и потоки
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт");
                serverSocket.close();
            }
        } catch (IOException e) {
        }
    }
}

