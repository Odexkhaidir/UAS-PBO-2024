package mgr.app;

import mgr.view.LoginView;

/**
 *
 * @author ACER
 */
public class MainApp {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
                loginView.pack();
                loginView.setLocationRelativeTo(null);
            }
        });
    }
}
