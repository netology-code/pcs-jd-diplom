import java.io.File;
import java.io.IOException;
import java.util.*;

public class BooleanSearchEngine implements SearchEngine {
    //???

    public BooleanSearchEngine(File pdfsDir) throws IOException {
        // прочтите тут все pdf и сохраните нужные данные,
        // тк во время поиска сервер не должен уже читать файлы
    }

    @Override
    public List<PageEntry> search(String word) {
        // тут реализуйте поиск по слову
        return Collections.emptyList();
    }
}
