import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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



}