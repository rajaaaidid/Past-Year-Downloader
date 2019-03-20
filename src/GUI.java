
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import org.apache.commons.io.FileUtils;


public class GUI extends javax.swing.JFrame {

    private UserDetails userDetails = new UserDetails();
    private static ArrayList<SimpleAttributeSet> listOfAttributes = new ArrayList<SimpleAttributeSet>();
    private SubjectRetrieve subjectRetrieve = new SubjectRetrieve();
    private Interface inter = new Interface();
    private boolean shift;
    private boolean downloadState;
    private boolean updatePrompt = false;
    private String latestVersion = null;
    private StyledDocument consoleWindowDocument;
    
    // Get the size of the screen
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    // Determine the new location of the window
    int w;
    int h;
    int x;
    int y;
    
    
    
    public GUI() {
        Login text = new Login();
        initComponents();
        initCredits();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollpane1 = new javax.swing.JScrollPane();
        consoleWindow = new javax.swing.JTextPane();
        subjectCodeTextField = new javax.swing.JTextField();
        subjectCodeLabel = new javax.swing.JLabel();
        manuallyDownload = new javax.swing.JButton();
        seperator1 = new javax.swing.JSeparator();
        studentIDLabel = new javax.swing.JLabel();
        studentIDTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JPasswordField();
        login = new javax.swing.JButton();
        automaticDownload = new javax.swing.JButton();
        scrollpane2 = new javax.swing.JScrollPane();
        inputTextField = new javax.swing.JTextPane();
        autoDownload = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MMU Past Year Downloader");
        setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Chick.png")));
        setLocation(new java.awt.Point(0, 0));

        scrollpane1.setAutoscrolls(true);

        consoleWindow.setEditable(false);
        inter.setConsoleWindowDocument(consoleWindow.getStyledDocument());
        scrollpane1.setViewportView(consoleWindow);

        subjectCodeTextField.setEditable(false);
        subjectCodeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectCodeTextFieldActionPerformed(evt);
            }
        });

        subjectCodeLabel.setText("Subject Code:");

        manuallyDownload.setText("Manually Download");
        manuallyDownload.setEnabled(false);
        manuallyDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manuallyDownloadActionPerformed(evt);
            }
        });

        seperator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        studentIDLabel.setText("Student ID:");

        studentIDTextField.setToolTipText("");
        studentIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentIDTextFieldActionPerformed(evt);
            }
        });

        passwordLabel.setText("Password:");

        passwordTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextFieldActionPerformed(evt);
            }
        });

        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        automaticDownload.setText("Automatic Download");
        automaticDownload.setEnabled(false);
        automaticDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                automaticDownloadActionPerformed(evt);
            }
        });

        inputTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputTextFieldKeyReleased(evt);
            }
        });
        scrollpane2.setViewportView(inputTextField);

        autoDownload.setSelected(true);
        autoDownload.setText("Auto Download");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollpane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(manuallyDownload, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(subjectCodeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(subjectCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seperator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studentIDLabel)
                            .addComponent(passwordLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(studentIDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(passwordTextField))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(automaticDownload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(autoDownload))))
                    .addComponent(scrollpane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(subjectCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subjectCodeLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(manuallyDownload))
                    .addComponent(seperator1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(studentIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentIDLabel)
                            .addComponent(login)
                            .addComponent(autoDownload))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordLabel)
                            .addComponent(automaticDownload)
                            .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputTextFieldKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
            shift = true;
        }
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(!shift){
                evt.consume();
                try {
                    inter.printMessage("COMMAND: "+inputTextField.getText(), 3);
                    executeCommand(inputTextField.getText());
                } catch (BadLocationException ex){}
                inputTextField.setEditable(false);
                inputTextField.setText("");
                inputTextField.setEditable(true);
            }else{
                Document inputTextFieldDocument = inputTextField.getDocument();
                try {
                    inputTextFieldDocument.insertString(inputTextField.getCaretPosition(), "\n", null);
                } catch (BadLocationException ex){}
            }
        }
    }//GEN-LAST:event_inputTextFieldKeyPressed

    private void inputTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputTextFieldKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
            shift = false;
        }
    }//GEN-LAST:event_inputTextFieldKeyReleased

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        login();
    }//GEN-LAST:event_loginActionPerformed

    private void manuallyDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuallyDownloadActionPerformed
        manuallyDownload();
    }//GEN-LAST:event_manuallyDownloadActionPerformed

    private void studentIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentIDTextFieldActionPerformed
        login();
    }//GEN-LAST:event_studentIDTextFieldActionPerformed

    private void passwordTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextFieldActionPerformed
        login();
    }//GEN-LAST:event_passwordTextFieldActionPerformed

    private void automaticDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_automaticDownloadActionPerformed
        automaticDownload();
    }//GEN-LAST:event_automaticDownloadActionPerformed

    private void subjectCodeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectCodeTextFieldActionPerformed
        manuallyDownload();
    }//GEN-LAST:event_subjectCodeTextFieldActionPerformed

    public static void main(String args[]) {
        //org.apache.commons.io.FileUtils.copyURLToFile(new URL("ftp://a:a@127.0.0.1/Download.exe"), new File("Hello.exe"));
        File downloads = new File("Past Year Downloads");
        if(!(downloads.isDirectory()&&downloads.exists())){
            System.out.println(downloads.mkdir()?"Downloads file created...":"Downloads file already exist!");
        }
        //BELOW IS GUI AND STARTING OF PROGRAM
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox autoDownload;
    private javax.swing.JButton automaticDownload;
    private javax.swing.JTextPane consoleWindow;
    private javax.swing.JTextPane inputTextField;
    private javax.swing.JButton login;
    private javax.swing.JButton manuallyDownload;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JScrollPane scrollpane1;
    private javax.swing.JScrollPane scrollpane2;
    private javax.swing.JSeparator seperator1;
    private javax.swing.JLabel studentIDLabel;
    private javax.swing.JTextField studentIDTextField;
    private javax.swing.JLabel subjectCodeLabel;
    private javax.swing.JTextField subjectCodeTextField;
    // End of variables declaration//GEN-END:variables

    private void initCredits(){
        w = this.getSize().width;
        h = this.getSize().height;
        x = (dim.width-w)/2;
        y = (dim.height-h)/2;
        this.setLocation(x, y);
        inter.setJFrame(this);
        inter.setAutomaticDownload(automaticDownload);
        inter.setManuallyDownload(manuallyDownload);
        inter.setSubjectCodeTextField(subjectCodeTextField);
        String text = "Created by Raja Muhammad Aidid.\n"
        + "For help, type \"help\" or \"?\".\n"
        + "https://github.com/rajaaaidid. Current Build: "+inter.getBUILD()+"\n";
        inter.printMessage(text, 3);
    }

    private void executeCommand(String s) throws BadLocationException {
        String[] splitS = s.split(" ");
        if(splitS[0].equals("override")){
            subjectCodeTextField.setEditable(true);
            manuallyDownload.setEnabled(true);
            inter.printMessage("INFO: Override succesful!...", 2);
        }else if(splitS[0].equals("build")){
            inter.printMessage("INFO: Version: "+inter.getBUILD(), 3);
        }else if(splitS[0].equals("help")||splitS[0].equals("?")){
            inter.printMessage("\nType in the following commands:\n"
            + "build: Checks the current version of the past year downloader\n"
            + "update: Updates to the latest version of the past year downloader\n"
            + "help: Displays the help menu\n", 3);
        }else if(splitS[0].equals("update")){
            try {
                FileUtils.copyURLToFile(new URL("https://raw.githubusercontent.com/rajaaaidid/Past-Year-Downloader/master/version"), new File("version.pyd"));
                latestVersion = FileUtils.readFileToString(new File("version.pyd"), "UTF-8");
                new File("version.pyd").delete();
                if(Integer.parseInt(inter.getBUILD().replace(".", "")) < Integer.parseInt(latestVersion.replace(".", ""))){
                    inter.printMessage("Current Build: "+inter.getBUILD()+" | Latest Build: "+latestVersion+"\n"
                            + "Would you like to update to the latest version? Y/N", 1);
                    updatePrompt = true;
                }else{
                    inter.printMessage("There are no updates available.", 1);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                inter.printMessage(ex.toString(), 6);
            }
        }else if(splitS[0].toUpperCase().equals("Y")){
            if(updatePrompt==true){
                System.out.println("INFO: Initiating download...");
                inter.printMessage("INFO: Initiating download...", 7);
                System.out.println("Downloading... Updates");
                inter.printMessage("Downloading... Updates", 0);
                try {
                    FileUtils.copyURLToFile(new URL("https://github.com/rajaaaidid/Past-Year-Downloader/blob/master/versioning/pyd_"+latestVersion+".exe?raw=true"), new File("Past Year Downloader ("+latestVersion+").exe"));
                } catch (Exception e){
                    inter.printMessage(e.toString(), 6);
                }
                System.out.println("Downloading... Updates Finished");
                inter.printMessage("Downloading... Updates Finished", 0);
                inter.printMessage("You may now close the current application and run the new version", 1);
                updatePrompt = false;
            }
        }else if(splitS[0].toUpperCase().equals("N")){
            updatePrompt = false;
        }
    }
    
    private void login(){
        if(studentIDTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,
            "Student ID must be filled!",
            "Warning",
            JOptionPane.WARNING_MESSAGE);
        }else if(passwordTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,
            "Password must be filled!",
            "Warning",
            JOptionPane.WARNING_MESSAGE);
        }else{
            login.setEnabled(false);
            autoDownload.setEnabled(false);
            studentIDTextField.setEnabled(false);
            passwordTextField.setEnabled(false);
            userDetails.setStudentID(studentIDTextField.getText());
            userDetails.setPassword(passwordTextField.getText());
            Thread threadedProcess = new Thread(){
                @Override
                public void run(){
                    try {
                        new Login().mmlsLogin();
                        if(userDetails.isLoginStatus()){
                            automaticDownload.setEnabled(true);
                            manuallyDownload.setEnabled(true);
                            subjectCodeTextField.setEditable(true);
                            if(autoDownload.isSelected()){
                                automaticDownload();
                            }
                        }else{
                            login.setEnabled(true);
                            studentIDTextField.setEnabled(true);
                            passwordTextField.setEnabled(true);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            };
            threadedProcess.start();
        }
    }

    public ArrayList<SimpleAttributeSet> getListOfAttributes() {
        return listOfAttributes;
    }
    
    public javax.swing.JTextField getStudentIDTextField(){
        return studentIDTextField;
    }

    private void manuallyDownload() {
        
        if(subjectCodeTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,
            "Subject code must be filled!",
            "Warning",
            JOptionPane.WARNING_MESSAGE);
        }else if(!subjectCodeTextField.getText().matches("\\p{Alpha}\\p{Alpha}\\p{Alpha}\\p{Space}\\p{Digit}\\p{Digit}\\p{Digit}\\p{Digit}")){
            JOptionPane.showMessageDialog(this,
            "Invalid subject code format! Etc.'XXX 9999'",
            "Warning",
            JOptionPane.WARNING_MESSAGE);
        }else{
            inter.setDownloadStatus(true);
            automaticDownload.setEnabled(false);
            manuallyDownload.setEnabled(false);
            subjectCodeTextField.setEditable(false);
            Thread threadedProcess = new Thread(){
                @Override
                public void run(){
                    try {
                        new SubjectRetrieve().download(new Subject(subjectCodeTextField.getText().toUpperCase()));
                    } catch (IOException ex) {
                        inter.printMessage(ex.toString(), 6);
                    }
                }
            };
            threadedProcess.start();
        }       
    }

    private void automaticDownload() {
        inter.setDownloadStatus(true);
        automaticDownload.setEnabled(false);
        manuallyDownload.setEnabled(false);
        subjectCodeTextField.setEditable(false);
        Thread threadedProcess = new Thread(){
            @Override
            public void run(){
                try {
                    new SubjectRetrieve().download(userDetails.getSubjects());
                } catch (IOException ex) {
                    inter.printMessage(ex.toString(), 6);
                }
            }
        };
        threadedProcess.start();
    }
    
}
