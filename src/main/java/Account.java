import java.util.HashMap;
import java.util.*;


enum CurTypes {GOLD, USD, EUR, RUB}

public class Account {

    private String name;
    private HashMap<CurTypes,Integer> currency;


    private Account(){};

    public Account(String name)
    {
        this.setName(name);
        this.currency = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException();
        this.name = name;
    }

    public HashMap<CurTypes, Integer> getCurrency() {
        return new HashMap<CurTypes, Integer>(this.currency);
    }


    public void setCurrency(CurTypes curtype, Integer val) {
        if (val<0) throw new IllegalArgumentException();
        this.currency.put(curtype,val);
    }

    public void printAccSaldo()
    {
        this.currency.entrySet().stream().forEach(System.out::println);
        System.out.println("---------------------");
    }

}
