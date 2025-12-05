import config.ApplicationContext;
import db.DatabaseInitializer;
import view.BankConsoleApp;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
//        try {
//            db.DatabaseInitializer.initialize();
//            System.out.println("Schema initialized, program started...");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        DatabaseInitializer.initialize();

        ApplicationContext ctx = ApplicationContext.getInstance();

        BankConsoleApp app = new BankConsoleApp(
                ctx.getUserService(),
                ctx.getCardService(),
                ctx.getTransactionService()
        );

        app.run();
    }
}