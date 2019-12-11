import com.example.elasticsearch.dao.UserRepository;
import com.example.elasticsearch.model.User;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.soap.Addressing;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Builder
public class test {


    public static void main(String[] args){
        /*

        List<String> nameList=new ArrayList();
        Collection<String> nameColl = new ArrayList();
        String inputData = "huobi,binance,Okex";
        for (String name : inputData.split(",")){
            nameList.add(name);
            nameColl.add(name);
        }

        System.out.println(nameList);
        // Collections.addall(list,o);
        System.out.println(nameColl);


        String inputDataEmpyt = "";
        Collection<String> nameCollEmpty = new ArrayList();
        for (String name : inputDataEmpyt.split(",")){
            nameCollEmpty.add(name);
        }

        System.out.println(nameCollEmpty);
        */


        System.out.println("hello world");

    }
}
