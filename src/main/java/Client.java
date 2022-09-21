import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        String host = "netology.homework";
        int port = 8989;
        try (
                Socket socket = new Socket(host, port);
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {

            Scanner in = new Scanner(System.in);

            System.out.println(bufferedReader.readLine());
            String word = in.nextLine();
            printWriter.println(word);
            System.out.println(jsonToString(bufferedReader.readLine()));
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String jsonToString(String string) {
        String s = "такого слова нет";
        if (string != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                Object jsonObject = mapper.readValue(string, Object.class);
                new TypeReference<HashMap<String, Object>>() {
                };
                s = mapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(jsonObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }
}
