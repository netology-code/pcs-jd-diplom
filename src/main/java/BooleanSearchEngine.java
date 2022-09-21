import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BooleanSearchEngine implements SearchEngine {

    PageEntry pageEntry;

    private String fileName;

    Map<String, List<PageEntry>> pageEntryMap = new HashMap<>();

    List<String> sort;

    public BooleanSearchEngine(File pdfsDir) throws IOException {
        // прочтите тут все pdf и сохраните нужные данные,
        // тк во время поиска сервер не должен уже читать файлы

        for (File file : pdfsDir.listFiles()) {

            fileName = file.getName();
            PdfDocument pdfDocument = new PdfDocument(new PdfReader(file));

            int pages = pdfDocument.getNumberOfPages();

            for (int i = 1; i <= pages; i++) {

                String text = PdfTextExtractor.getTextFromPage(pdfDocument.getPage(i));
                String[] word = text.split("\\P{IsAlphabetic}+");

                sort = Arrays.stream(word)
                        .map(String::toLowerCase)
                        .sorted()
                        .collect(Collectors.toList());

                int count = -1;

                String key = sort.get(0);

                for (int ii = 0; ii < sort.size(); ii++) {

                    count++;
                    if (sort.get(ii).equals(key)) {
                        if (ii == sort.size() - 1) {
                            count++;
                            pageEntryMapPut(i, count, key);
                        }
                        continue;
                    } else {
                        pageEntryMapPut(i, count, key);
                        key = sort.get(ii);
                    }
                    count = 0;
                }
            }
        }
    }

    @Override
    public List<PageEntry> search(String word) {
        // тут реализуйте поиск по слову

        List<PageEntry> l = pageEntryMap.get(word.toLowerCase());

        if (l == null) {
            System.out.println("такого слова нет");
        }
        return l;
    }

    public Map<String, List<PageEntry>> pageEntryMapPut(int i, int count, String key) {

        pageEntry = new PageEntry(fileName, i, count);

        List<PageEntry> pageEntryList;

        if (pageEntryMap.containsKey(key)) {
            pageEntryList = pageEntryMap.get(key);
        } else {
            pageEntryList = new ArrayList<>();
        }

        pageEntryList.add(pageEntry);
        pageEntryMap.put(key, pageEntryList);
        return pageEntryMap;
    }


}
