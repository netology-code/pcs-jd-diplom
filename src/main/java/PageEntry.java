import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;

public class PageEntry implements Comparable<PageEntry> {
    private final String pdfName;
    private final int page;
    private final int count;

    public PageEntry(String pdfName, int page, int count) {
        this.pdfName = pdfName;
        this.page = page;
        this.count = count;
    }

    @Override
    public int compareTo(PageEntry o) {
        return o.compareTo(this);
    }

    @Override
    public String toString() {

//        String string = "такого слова нет в исследуемых документах";
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//           Object jsonObject = mapper.readValue(toJsonPageEntry(this), Object.class);
//                    new TypeReference<HashMap<String, Object>>(){};
//          string  = mapper.writerWithDefaultPrettyPrinter()
//                    .writeValueAsString(jsonObject);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

        String string = toJsonPageEntry(this);

        return string;
    }

    public String toJsonPageEntry(PageEntry pageEntry) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(pageEntry);
    }
}
