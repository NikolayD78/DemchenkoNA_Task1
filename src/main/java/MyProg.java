
public class MyProg {

    public static void main(String[] args) {

        Account acc1;

        acc1=new Account("Счет мультивалютный");

        acc1.printAccSaldo();
        acc1.setCurrency(CurTypes.GOLD,1);
        acc1.printAccSaldo();
        acc1.setCurrency(CurTypes.RUB,10);
        acc1.setCurrency(CurTypes.EUR,12);
        acc1.printAccSaldo();
        acc1.setCurrency(CurTypes.USD,40);
        acc1.setCurrency(CurTypes.GOLD,2);
        acc1.printAccSaldo();





    }

}
