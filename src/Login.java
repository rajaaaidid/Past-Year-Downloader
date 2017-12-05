
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HTMLParserListener;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.ErrorHandler;


public class Login {
    private static SubjectRetrieve subject;
    private static UserDetails userDetails = new UserDetails();
    private Interface inter = new Interface();
    
    public void mmlsLogin() throws IOException{
        try{
            WebClient webClient = new WebClient();
            setupWebClient(webClient);

            //PAGE 1 (LOGIN PAGE)
            System.out.println("Connecting...");
            inter.printMessage("INFO: Connecting to mmls...", 7);
            HtmlPage page = (HtmlPage) webClient.getPage("https://mmls.mmu.edu.my/");
            HtmlInput username = page.getFirstByXPath("//input[@placeholder='Username']");
            HtmlInput password = page.getFirstByXPath("//input[@placeholder='Password']");
            HtmlSubmitInput login = page.getFirstByXPath("//input[@value='Log In']");
            System.out.println("Entering credentials...");
            inter.printMessage("INFO: Entering credentials...", 7);
            System.out.println(userDetails.getStudentID()+" | "+userDetails.getPassword());
            username.setValueAttribute(userDetails.getStudentID());
            password.setValueAttribute(userDetails.getPassword());

            //PAGE 2 (MMLS HOMEPAGE)
            System.out.println("Logging in...");
            inter.printMessage("INFO: Logging in...", 7);
            HtmlPage page2 = login.click();
            if(page2.getUrl().toString().equals("https://mmls.mmu.edu.my/home")){
                System.out.println("Retrieving subject registration list...");
                inter.printMessage("INFO: Retrieveing subject registration list...", 7);
                HtmlElement subjectCourseList = page2.getFirstByXPath("//div[@style='margin-top:-15px']");
                Iterator<DomElement> children = subjectCourseList.getChildElements().iterator();
                while(children.hasNext()){
                    String subjectName = children.next().getFirstElementChild().getTextContent();
                    userDetails.getSubjectsString().add(subjectName);
                    System.out.print(ColorCode.ANSI_GREEN+"FOUND ");
                    System.out.print(subjectName+"\n");
                    inter.printMessage("FOUND "+ subjectName, 5);
                }
                System.out.println("Subject registration list sucessfully retrieved...");  
                inter.printMessage("INFO: Subject registration list sucessfully retrieved...", 7);
                userDetails.convertSubjects();
                userDetails.setLoginStatus(true);
            }else{
                inter.printMessage("ERROR: Failed to Login! Either student ID or password is incorrect!", 6);
                JOptionPane.showMessageDialog(inter.getJFrame(),
                "Either student ID or password is incorrect!",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
                userDetails.setLoginStatus(false);
            }
        }catch(Exception e){
            inter.printMessage("ERROR: "+e.toString(), 6);
            if(e.toString().equals("java.net.UnknownHostException: mmls.mmu.edu.my")){
                inter.printMessage("ERROR: Connection to the server failed, make sure you have an active connection!", 6);
            }
            e.printStackTrace();
            userDetails.setLoginStatus(false);
        }
    }
    
    private WebClient setupWebClient(WebClient webClient) {
        //DISABLE INCORRECTNESSLISTENER ERROR
        webClient.setIncorrectnessListener(new IncorrectnessListener(){
            @Override
            public void notify(String string, Object o) {
                
            }
            
        });
        //DISABLE CSS ERROR
        webClient.setCssErrorHandler(new ErrorHandler(){
            @Override
            public void warning(CSSParseException csspe) throws CSSException {
                
            }

            @Override
            public void error(CSSParseException csspe) throws CSSException {
                
            }

            @Override
            public void fatalError(CSSParseException csspe) throws CSSException {
                
            }
            
        });
        //DISABLE JAVASCRIPT ERROR
        webClient.setJavaScriptErrorListener(new JavaScriptErrorListener(){
            @Override
            public void scriptException(HtmlPage hp, ScriptException se) {
               
            }

            @Override
            public void timeoutError(HtmlPage hp, long l, long l1) {
                
            }

            @Override
            public void malformedScriptURL(HtmlPage hp, String string, MalformedURLException murle) {
                
            }

            @Override
            public void loadScriptError(HtmlPage hp, java.net.URL url, Exception excptn) {
                
            }
            
        });
        //DISABLE HTML PARSING ERROR
        webClient.setHTMLParserListener(new HTMLParserListener(){
            @Override
            public void error(String string, java.net.URL url, String string1, int i, int i1, String string2) {
                
            }

            @Override
            public void warning(String string, java.net.URL url, String string1, int i, int i1, String string2) {
                
            }
            
        });
        
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        
        return webClient;
    }
}
