import java.util.Scanner;

/**
 * class scannerUtil to catch error
 * @author
 */
public class ScannerUtil {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String splitLine = "---------------------------------------------------------------------------------------------------";
    public static int getInt() {
        while (true) {
            try {
                String s = scanner.nextLine();
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.err.print("please enter a number:");
            }
        }
    }

    public static int getInt(int min, int max) {
        while (true) {
            try {
                String s = scanner.nextLine();
                int value =  Integer.parseInt(s);
                if (value < min || value > max) {
                    System.err.print("invalid input, range:[" + min + "," + max + "], please try again:");
                } else {
                    return value;
                }
            } catch (Exception e) {
                System.err.print("invalid input, range:[" + min + "," + max + "], please try again:");
            }
        }
    }


    public static String getString(){
        while (true) {
            try {
                String s = scanner.nextLine();
                if (s.length() >0){
                    return s;
                }
            } catch (Exception e) {
                System.err.print("invalid input, please try again:");
            }
        }
    }
    public static  void  getYesOrNo(){
        String action;

        while (true) {
            //input
            action = ScannerUtil.getString();
            if (!action.equalsIgnoreCase("Y") && !action.equalsIgnoreCase("N")
                    && !action.equalsIgnoreCase("y")&& !action.equalsIgnoreCase("n" )) {
                // invalid input
                System.out.println("invalid input！ please enter: [Y/N]: ");
                continue;
            }
            LegendsOfValorGame legendsOfValorGame = new LegendsOfValorGame();

            if (action.equalsIgnoreCase("Y")) {
                System.out.println("welcome to next game ");
                legendsOfValorGame.play();
            } else {
                System.out.println("see you next time");
                legendsOfValorGame.quit();
                break;
            }
        }
    }
}
