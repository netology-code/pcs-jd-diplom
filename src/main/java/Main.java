import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Exception {
        BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));
//        System.out.println(engine.search("бизнес"));

        // здесь создайте сервер, который отвечал бы на нужные запросы
        // слушать он должен порт 8989
        int port = 8989;
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
                    // отвечать на запросы /{word} -> возвращённое значение метода search(word) в JSON-формате

                    printWriter.println("Введите слово для поиска");

                    final String word = bufferedReader.readLine();

                    printWriter.println(engine.search(word));

                    System.out.println("задание выполнено. жду новый запрос");
                }
            }
        } catch (IOException e) {
            System.out.println("не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}