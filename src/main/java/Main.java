import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));
//        System.out.println(engine.search("бизнес"));

        // здесь создайте сервер, который отвечал бы на нужные запросы
        // слушать он должен порт 8989
        int port = 8989;
        try(ServerSocket serverSocket = new ServerSocket(port); Socket clientSocket = serverSocket.accept();
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){
            // отвечать на запросы /{word} -> возвращённое значение метода search(word) в JSON-формате

            System.out.println("New connection accepted");

            printWriter.println("Введите слово для поиска");

            final String word = bufferedReader.readLine();

            printWriter.println(engine.search(word));

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}