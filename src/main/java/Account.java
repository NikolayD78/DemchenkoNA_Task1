import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.Deque;

class NothingToUndo extends Exception{}
enum CurTypes {GOLD, USD, EUR, RUB}
enum TypesAccount {USUAL,PREMIAL}
interface Command{public void perform();}
interface Loadable{void load();}

public class Account {

    private String name;
    private HashMap<CurTypes,Integer> currency;
    private TypesAccount typeAccount;

    private Deque<Command> commands = new ArrayDeque<>();
    //>>---------------------------
    public Loadable save() {return new Snapshot();}

    private class Snapshot implements Loadable
    {
        private String name;
        private HashMap<CurTypes, Integer> currency;
        private TypesAccount typeAccount;

        public Snapshot ()
        {
            this.name = Account.this.name;
            this.currency = new HashMap<>(Account.this.currency);
            this.typeAccount=Account.this.typeAccount;
        }
        @Override
        public void load() {
            Account.this.name = this.name;
            Account.this.currency = new HashMap<>(this.currency);
            Account.this.typeAccount = this.typeAccount;
            Account.this.commands.clear(); // если восстановили, то Undo надо очистить полностью
        }
    }
    //<<---------------------------

    private Account(){}

    public Account(String name)
    {
        this.setName(name);
        this.currency = new HashMap<>();
        this.typeAccount=TypesAccount.USUAL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException();
        String oldName = this.name;
        if (oldName!=null)
                this.commands.push(()->{this.name = oldName;});

        this.name = name;

    }

    public void setTypeAccount (TypesAccount newType)
    {
        TypesAccount oldTypeAccount;
        if(newType==null) throw new IllegalArgumentException();
        if(this.typeAccount!=newType) {
            oldTypeAccount = this.typeAccount;
            this.typeAccount = newType;
            this.commands.push(() -> {this.typeAccount = oldTypeAccount;});
        }
    }

    public TypesAccount getTypeAccount()
    {
        return this.typeAccount;
    }

    public void printTypeAccount()
    {
        System.out.println(this.typeAccount);
    }


    public HashMap<CurTypes, Integer> getCurrency() {
        return new HashMap<CurTypes, Integer>(this.currency);
    }


    public void setCurrency(CurTypes curtype, Integer val) {
        if (val<0) throw new IllegalArgumentException();

        if (currency.containsKey(curtype)) //если мы изменили сущ. значение
        {
            int tempValue;

            tempValue=this.currency.get(curtype);
            this.commands.push(()->{this.currency.put(curtype, tempValue);});
        }
        else //если мы добавили новое значение
        {
            this.commands.push(()->{this.currency.remove(curtype);});
        }
        this.currency.put(curtype,val);
    }

    public void undo() throws NothingToUndo {
        if (commands.isEmpty()) throw new NothingToUndo();
        commands.pop().perform();

    }

    public void printAccSaldo()
    {
        this.currency.entrySet().stream().forEach(System.out::println);
        System.out.println("---------------------");
    }

}
