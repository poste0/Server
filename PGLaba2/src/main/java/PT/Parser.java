package PT;

import javax.xml.bind.JAXBException;
import java.io.File;

public interface Parser {
    Object getObject(File file , Class clazz) throws JAXBException;

    void saveObject(File file , Object o) throws JAXBException;
}
