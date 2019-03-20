
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class Interface {
    private static final String BUILD = "1.1.5";
    private static StyledDocument consoleWindowDocument;
    private static List<SimpleAttributeSet> listOfAttributes = new ArrayList<SimpleAttributeSet>();
    private static JTextField subjectCodeTextField;
    private static JButton manualDownload;
    private static boolean downloadStatus;
    private static JButton automaticDownload;
    private static GUI jFrame;
    private final SimpleAttributeSet blueText;
    private final SimpleAttributeSet blackBold;
    private final SimpleAttributeSet blackText;
    private final SimpleAttributeSet grayText;
    private final SimpleAttributeSet grayTextItalic;
    private final SimpleAttributeSet greenText;
    private final SimpleAttributeSet redText;
    private final SimpleAttributeSet darkGrayText;
    private final SimpleAttributeSet greenBoldText;
    
    Interface(){
        //0 = blue
        //1 = blackBold
        //2 = black
        //3 = gray
        //4 = grayItalic
        //5 = green
        //6 = red
        //7 = darkGray
        //8 = greenBold
        blueText = new SimpleAttributeSet();
        StyleConstants.setForeground(blueText, Color.BLUE);
        StyleConstants.setBold(blueText, true);
        listOfAttributes.add(blueText);

        blackBold = new SimpleAttributeSet();
        StyleConstants.setForeground(blackBold, Color.BLACK);
        StyleConstants.setBold(blackBold, true);
        listOfAttributes.add(blackBold);
        
        blackText = new SimpleAttributeSet();
        StyleConstants.setForeground(blackText, Color.BLACK);
        listOfAttributes.add(blackText);

        grayText = new SimpleAttributeSet();
        StyleConstants.setForeground(grayText, Color.GRAY);
        listOfAttributes.add(grayText);
        
        grayTextItalic = new SimpleAttributeSet();
        StyleConstants.setForeground(grayTextItalic, Color.GRAY);
        StyleConstants.setItalic(grayTextItalic, true);
        listOfAttributes.add(grayTextItalic);

        greenText = new SimpleAttributeSet();
        StyleConstants.setForeground(greenText, Color.GREEN);
        StyleConstants.setBold(greenText, false);
        listOfAttributes.add(greenText);
        
        redText = new SimpleAttributeSet();
        StyleConstants.setForeground(redText, Color.RED);
        StyleConstants.setBold(redText, false);
        listOfAttributes.add(redText);
        
        darkGrayText = new SimpleAttributeSet();
        StyleConstants.setForeground(darkGrayText, Color.DARK_GRAY);
        StyleConstants.setBold(darkGrayText, false);
        listOfAttributes.add(darkGrayText);
        
        greenBoldText = new SimpleAttributeSet();
        StyleConstants.setForeground(greenBoldText, Color.GREEN);
        StyleConstants.setBold(greenBoldText, true);
        listOfAttributes.add(greenBoldText);
    }

    public StyledDocument getConsoleWindowDocument() {
        return consoleWindowDocument;
    }

    public void setConsoleWindowDocument(StyledDocument consoleWindowDocument) {
        this.consoleWindowDocument = consoleWindowDocument;
    }
    
    public void printMessage(String s, int style){
        try {
            consoleWindowDocument.insertString(consoleWindowDocument.getLength(), s+"\n", listOfAttributes.get(style));
        } catch (BadLocationException ex) {}
    }

    public void setSubjectCodeTextField(JTextField studentIDTextField) {
        this.subjectCodeTextField = studentIDTextField;
    }

    public JTextField getSubjectCodeTextField() {
        return subjectCodeTextField;
    }

    public JButton getManuallyDownload() {
        return manualDownload;
    }

    public void setManuallyDownload(JButton aManualDownload) {
        manualDownload = aManualDownload;
    }

    public boolean isDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(boolean aDownloadStatus) {
        downloadStatus = aDownloadStatus;
    }

    public JButton getAutomaticDownload() {
        return automaticDownload;
    }

    public void setAutomaticDownload(JButton aAutomaticDownload) {
        automaticDownload = aAutomaticDownload;
    }

    public void setJFrame(GUI aThis) {
        this.jFrame = aThis;
    }
    
    public GUI getJFrame() {
        return jFrame;
    }

    public String getBUILD() {
        return BUILD;
    }
}
