package PT;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@XmlRootElement(name = "Digits")
public class NumberOfDigits {

    @XmlElement(name = "digit")
    //@XmlElementWrapper
    private List digits;

    private Random random;
    public NumberOfDigits(){
        this.digits = new ArrayList();
        random = new Random();
    }

    public void add(Object o){
        digits.add(o);
    }

    public Object delete(){
        int deletePlace = random.nextInt(digits.size());
        Object result = digits.get(deletePlace);
        digits.remove(deletePlace);
        return  result;
    }

    public int size(){
        return digits.size();
    }

    public Object get(int index){
        return digits.get(index);
    }
}
