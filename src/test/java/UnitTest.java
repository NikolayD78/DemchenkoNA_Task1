import org.junit.jupiter.api.Assertions;
import java.util.HashMap;

public class UnitTest {

    @org.junit.jupiter.api.Test
    public void testName()
    {
        Account accTest1;

        Assertions.assertThrows(IllegalArgumentException.class,()->new Account(""));

        accTest1=new Account("test_Account");
        Assertions.assertEquals(accTest1.getName(),"test_Account");
        accTest1.setName("Change_Name");
        Assertions.assertEquals(accTest1.getName(),"Change_Name");

    }

    @org.junit.jupiter.api.Test
    public void testCurrency()
    {
        HashMap<CurTypes,Integer> hm1=new HashMap<>();
        hm1.put(CurTypes.RUB,100);
        hm1.put(CurTypes.EUR,200);
        hm1.put(CurTypes.USD,300);
        hm1.put(CurTypes.GOLD,400);

        Account accTest1=new Account("test_Account");

        Assertions.assertThrows(IllegalArgumentException.class,()->accTest1.setCurrency(CurTypes.USD,-10));

        accTest1.setCurrency(CurTypes.GOLD,10);
        accTest1.setCurrency(CurTypes.GOLD,400);
        accTest1.setCurrency(CurTypes.EUR,200);
        accTest1.setCurrency(CurTypes.RUB,100);
        accTest1.setCurrency(CurTypes.USD,10);
        accTest1.setCurrency(CurTypes.USD,300);

        Assertions.assertEquals(accTest1.getCurrency(),hm1);

    }

    void checkTypeAccount()
    {
        Account acc1 = new Account("Qwerty");
        Assertions.assertThrows(IllegalArgumentException.class,()->acc1.setTypeAccount(null));
        acc1.setTypeAccount(TypesAccount.PREMIAL);
        Assertions.assertEquals(acc1.typeAccount,TypesAccount.PREMIAL);
    }

    @org.junit.jupiter.api.Test
    void checkUndo() throws NothingToUndo {

        Account acc1 = new Account("Qwerty");
        String oldName = acc1.getName();
        HashMap<CurTypes,Integer> hm1;

        acc1.setName("ASDFG");
        acc1.setCurrency(CurTypes.USD,100);
        acc1.setCurrency(CurTypes.EUR,200);
        hm1=acc1.getCurrency();
        acc1.setCurrency(CurTypes.EUR,300);
        acc1.setCurrency(CurTypes.GOLD,100);
        acc1.undo();
        Assertions.assertNotEquals(acc1.getCurrency(),hm1);
        acc1.undo();
        Assertions.assertEquals(acc1.getCurrency(),hm1);
        Assertions.assertNotEquals(oldName, acc1.getName());
        acc1.undo();
        acc1.undo();
        acc1.undo();
        Assertions.assertEquals(oldName, acc1.getName());
        Assertions.assertThrows(NothingToUndo.class,()->acc1.undo());
        // блок проверки отката изменения типа счета
        acc1.setTypeAccount(TypesAccount.PREMIAL);
        acc1.undo();
        Assertions.assertEquals(acc1.typeAccount,TypesAccount.USUAL);

    }


}
