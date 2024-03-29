import java.util.Scanner;

public class CommandLineManager {
    Scanner scanner = new Scanner(System.in);
    StepTracker stepTracker;

    public CommandLineManager(StepTracker stepTracker) {
        this.stepTracker = stepTracker;
    }

    void printMenuAndHandleCommand () {

        final int EXIT = 0;
        final int ADD_STEPS_PER_DAY = 1;
        final int PRINT_STAT_PER_MONTH = 2;
        final int CHANGE_TARGET_STEPS_DAY = 3;

        while (true) {
            printMenu();

            int command = scanner.nextInt();

            if (command == ADD_STEPS_PER_DAY) {
                enterAmountOfSteps();
            } else if (command == PRINT_STAT_PER_MONTH) {
                statsOfMonth();
            } else if (command == CHANGE_TARGET_STEPS_DAY) {
                targetOfSteps();
            } else if (command == EXIT) {
                System.out.println("Выход.");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    private void targetOfSteps() {
        System.out.println("Текущая цель по количеству шагов в день: " + stepTracker.targetAtStart);
        System.out.println("Введите новую цель: ");
        int newTargetSteps = scanner.nextInt();
        while (newTargetSteps < 0) {
            System.out.println("Неверное значение!" +
                    " Цель не может быть отрицательной!" +
                    " Введите новое значение:");
            newTargetSteps = scanner.nextInt();
        }
        stepTracker.changeTargetStepsDay(newTargetSteps);
    }

    private void statsOfMonth() {
        System.out.println("За какой месяц вывести статистику?");
        System.out.println("Пример: 1 - Январь, 2 - Февраль, ... , 12 - Декабрь.");
        int month = scanner.nextInt();
        while (month < 1 || month > 12) {
            System.out.println("Неверное значение! Введите номер месяца от 1 до 12:");
            month = scanner.nextInt();
        }
        stepTracker.monthStats(month);
    }

    private void enterAmountOfSteps() {
        System.out.println("Введите номер месяца: ");
        System.out.println("Пример: 1 - Январь, 2 - Февраль, ... , 12 - Декабрь.");
        int month = scanner.nextInt();
        while (month < 1 || month > 12){
            System.out.println("Неверное значение! Введите номер месяца от 1 до 12:");
            month = scanner.nextInt();
        }
        System.out.println("Введите номер дня:");
        int day = scanner.nextInt();
        while (day < 1 || day > 30) {
            System.out.println("Неверное значение! Введите номер дня от 1 до 30:");
            day = scanner.nextInt();
        }
        System.out.println("Введите количество шагов:");
        int steps = scanner.nextInt();
        while (steps < 0) {
            System.out.println("Неверное значение! " +
                    "Количество шагов не может быть отрицательным. " +
                    "Введите количество шагов:");
            steps = scanner.nextInt();
        }
        stepTracker.saveSteps(month, day, steps);
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Ввести количество шагов за определенный день.");
        System.out.println("2 - Напечатать статистику за определенный месяц.");
        System.out.println("3 - Изменить цель по количеству шагов в день.");
        System.out.println("0 - Выход.");
    }
}
