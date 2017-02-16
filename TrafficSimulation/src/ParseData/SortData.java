package ParseData;

import core.UserProfile;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

/**
 * Created by gigaw on 2/10/2017.
 */
public class SortData {
    public void readFile(String fileName){
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try{
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(fileName));
        while(reader.hasNext()) {
            if("node".equals(reader.getLocalName())){
                System.out.println(reader.getLocalName());
            }
        }
        }catch(Exception e){
            e.getMessage();
        }
    }
}
