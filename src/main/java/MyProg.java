
public class MyProg {

    public static void main(String[] args) throws NothingToUndo {

        Account acc1;

        //проверка работы функционала
        System.out.println("Проверка работы функционала");
        acc1=new Account("Счет мультивалютный");
        System.out.println(acc1.getName());
        acc1.setName("Счет со смененным именем"); // Действие 1
        System.out.println(acc1.getName());
        acc1.printAccSaldo();
        acc1.setCurrency(CurTypes.GOLD,1);  // Действие 2
        acc1.printAccSaldo();
        acc1.setCurrency(CurTypes.RUB,10);  // Действие 3
        acc1.setCurrency(CurTypes.EUR,12);  // Действие 4
        acc1.printAccSaldo();
        acc1.setCurrency(CurTypes.USD,40);  // Действие 5
        acc1.setCurrency(CurTypes.GOLD,2);  // Действие 6
        acc1.printAccSaldo();
        System.out.println("------Проверяем отмену действий");
        acc1.undo(); // Действие 6
        acc1.printAccSaldo();
        acc1.undo(); // Действие 5
        acc1.printAccSaldo();
        acc1.undo(); // Действие 4
        acc1.printAccSaldo();
        acc1.undo(); // Действие 3
        acc1.printAccSaldo();
        acc1.undo(); // Действие 2
        acc1.printAccSaldo();
        System.out.println(acc1.getName());
        acc1.undo(); // Действие 1
        acc1.printAccSaldo();
        System.out.println(acc1.getName());
        //acc1.undo(); выдается исключение
        // блок проверки типа счета
        System.out.println("Проверка типа счета");
        acc1.printTypeAccount();
        acc1.setTypeAccount(TypesAccount.USUAL);
        //acc1.setTypeAccount(null); выдается исключение
        acc1.setTypeAccount(TypesAccount.PREMIAL);
        acc1.printTypeAccount();
        acc1.undo();
        acc1.printTypeAccount();
        //acc1.undo(); выдается исключение
        System.out.println("Проверка функции getTypeAccount");
        System.out.println(acc1.getTypeAccount());


    }

}
