
public class MyProg {

    public static void main(String[] args) throws NothingToUndo {

        Account acc1;
        Loadable save1,save2,save3,save4;

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
        //SAVE------------
        save1=acc1.save(); // сменено имя и заполнен список валют кроме USD

        acc1.setCurrency(CurTypes.USD,40);  // Действие 5
        acc1.setCurrency(CurTypes.GOLD,2);  // Действие 6
        acc1.printAccSaldo();
        //SAVE------------
        save2=acc1.save(); // сменено имя, и заполнен список валют

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
        //SAVE------------
        save3=acc1.save(); // только смененное имя, список валют не заполнен

        System.out.println(acc1.getName());
        acc1.undo(); // Действие 1
        acc1.printAccSaldo();
        System.out.println(acc1.getName());
        //acc1.undo(); выдается исключение, отменили все изменения
        // блок проверки типа счета
        System.out.println("Проверка типа счета");
        acc1.printTypeAccount();
        acc1.setTypeAccount(TypesAccount.USUAL);
        //acc1.setTypeAccount(null); выдается исключение
        acc1.setTypeAccount(TypesAccount.PREMIAL);
        //SAVE------------
        save4=acc1.save(); // только изменение типа счета

        acc1.printTypeAccount();
        acc1.undo();
        acc1.printTypeAccount();
        //acc1.undo(); выдается исключение
        System.out.println("Проверка функции getTypeAccount");
        System.out.println(acc1.getTypeAccount());

        // проверка восстановления сохранений
        System.out.println("Восстанавливаем состояние №3 только смененное имя, список валют не заполнен");
        save3.load();
        acc1.printTypeAccount();
        acc1.printAccSaldo();
        System.out.println(acc1.getName());

        System.out.println("Восстанавливаем состояние №4 только изменение типа счета");
        save4.load();
        acc1.printTypeAccount();
        acc1.printAccSaldo();
        System.out.println(acc1.getName());

        System.out.println("Восстанавливаем состояние №2 - сменено имя, и заполнен список валют");
        save2.load();
        acc1.printTypeAccount();
        acc1.printAccSaldo();
        System.out.println(acc1.getName());

        System.out.println("Восстанавливаем состояние №1 - сменено имя и заполнен список валют кроме USD");
        save1.load();
        acc1.printTypeAccount();
        acc1.printAccSaldo();
        System.out.println(acc1.getName());
    }

}
