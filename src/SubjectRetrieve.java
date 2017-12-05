
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HTMLParserListener;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.apache.commons.io.input.CountingInputStream;
import org.apache.commons.io.output.CountingOutputStream;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.ErrorHandler;


public class SubjectRetrieve {
    
    private UserDetails userDetails = new UserDetails();
    private boolean singleSubject = false;
    private int subjectIndex = 0;
    private int tries = 0;
    private int limit = 5;
    
    public void download(Subject subject) throws IOException{
        String SubjectCode = subject.getSubjectCode();
        if(SubjectCode.split(" ").length==1){
            String alphabet;
            String numbers;
            alphabet = SubjectCode.substring(0,3);
            numbers = SubjectCode.substring(3);
            SubjectCode = alphabet+" "+numbers;
            subject.setSubjectCode(SubjectCode);
        }
        List<Subject> a = new ArrayList<Subject>();
        a.add(subject);
        download(a);
    }
    
    public void download(List<Subject> subjectCodes) throws IOException{
        Interface inter = new Interface();
        try{
            final WebClient webClient = new WebClient();
            setupWebClient(webClient);
            
            //PAGE 1 (LOGIN)
            System.out.println("Verifying...");
            inter.printMessage("INFO: Verifying...", 7);
            HtmlPage page = (HtmlPage) webClient.getPage("http://proxyvlib.mmu.edu.my/login?url=http://library.mmu.edu.my/library2/diglib/exam_col/"); 
            
            HtmlSubmitInput button = page.getFirstByXPath("//input[@value='Login']");
            HtmlInput user = page.getFirstByXPath("//input[@name='user']");
            HtmlInput pass = page.getFirstByXPath("//input[@name='pass']");
            //Login Retry
            while(tries<=limit){
                try{
                    user.setValueAttribute(userDetails.getStudentID().toString());
                    pass.setValueAttribute(userDetails.getPassword().toString());
                    break;
                }catch(Exception e){
                    if(tries>=limit){
                        System.out.println("Skipping...");
                        tries = 0;
                        break;
                    }else{
                        tries++;
                        System.out.println("Retrying...");
                    }
                }
            }
            //user.setValueAttribute(userDetails.getStudentID().toString());
            //pass.setValueAttribute(userDetails.getPassword().toString());
            //PAGE 2 (SEARCH)
            System.out.println("Indexing..");
            inter.printMessage("INFO: Indexing...", 7);
            HtmlPage page2 = null;
            while(tries<=limit){
                try{
                    page2 = button.click();
                    tries = 0;
                    break;
                }catch(Exception e){
                    if(tries>=limit){
                        System.out.println("Skipping...");
                        tries = 0;
                        break;
                    }else{
                        tries++;
                        System.out.println("Retrying...");
                    }
                }
            }
            //Search Box Retry
            HtmlInput searchBox = null;
            HtmlInput searchButton = null;
            while(tries<=limit){
                try{
                    searchBox = page2.getFirstByXPath("//input[@name='rt']");
                    searchButton = page2.getFirstByXPath("//input[@value='Search']");
                    tries = 0;
                    break;
                }catch(Exception e){
                    if(tries>=limit){
                        System.out.println("Skipping...");
                        searchBox = page.getFirstByXPath("//input[@name='rt']");
                        searchButton = page.getFirstByXPath("//input[@value='Search']");
                        tries = 0;
                        break;
                    }else{
                        tries++;
                        System.out.println("Retrying...");
                    }
                }
            }
            //HtmlInput searchBox = page2.getFirstByXPath("//input[@name='rt']");
            //HtmlInput searchButton = page2.getFirstByXPath("//input[@value='Search']");
            Iterator<Subject> subjects = subjectCodes.iterator();
            
            subjectIndex = 0;
            while(subjects.hasNext()){
                Subject subjectVar = subjects.next();
                searchBox.setValueAttribute(subjectVar.getSubjectCode());

                //PAGE 3 (PRESS DETAILED VIEW)
                System.out.println("Detailed Subject Overview..");
                inter.printMessage("INFO: Getting detailed subject overview...", 7);
                HtmlPage page3 = null;
                while(tries<=limit){
                    try{
                        page3 = searchButton.click();
                        tries = 0;
                        break;
                    }catch(Exception e){
                        if(tries>=limit){
                            System.out.println("Skipping...");
                            tries = 0;
                            break;
                        }else{
                            tries++;
                            System.out.println("Retrying...");
                        }
                    }
                }
                //CREATE NEW DIRECTORY
                HtmlElement subjectNameElement = page3.getFirstByXPath("//a[@title='More details']");
                try{
                    new File("Past Year Downloads\\"+userDetails.getSubjectsString().get(subjectIndex)).mkdir();
                }catch (java.lang.NullPointerException e){
                    System.out.println("Skipping...");
                    inter.printMessage("INFO: Skipping...", 7);
                }catch(java.lang.IndexOutOfBoundsException e){
                    singleSubject = true;
                    new File("Past Year Downloads\\"+subjectNameElement.getTextContent()).mkdir();
                }
                //END OF CREATE NEW DIRECTORY
                boolean hasNextPage = false;

                do{
                List<HtmlAnchor> detailedTextView = page3.getByXPath("//a[@title='View the complete item content']");
                Iterator<HtmlAnchor> itDetailedTextView = detailedTextView.iterator();

                    while(itDetailedTextView.hasNext()){
                        //PAGE 4 (DOWNLOAD)
                        System.out.println("Initating Download..");
                        inter.printMessage("INFO: Initiating download...", 7);
                        HtmlPage page4 = null;
                        while(tries<=limit){
                            try{
                                page4 = itDetailedTextView.next().click();
                                tries = 0;
                                break;
                            }catch(Exception e){
                                if(tries>=limit){
                                    System.out.println("Skipping...");
                                    tries = 0;
                                    break;
                                }else{
                                    tries++;
                                    System.out.println("Retrying...");
                                }
                            }
                        }
                        HtmlElement year = page4.getFirstByXPath("//tr//td//b[contains(text(),'Year')]");
                        HtmlInput downloadButton = page4.getFirstByXPath("//input[@value='Download']");
                        HtmlInput fileNameID = page4.getFirstByXPath("//input[@name='xfile']");
                        String yearValue = year.getParentNode().getParentNode().getLastChild().getLastChild().getNodeValue().replaceAll(" ", "").replaceAll("/", "_");
                        String fileName = fileNameID.getValueAttribute();

                        //PAGE 5 (CONFIRM DOWNLOAD)
                        System.out.println("Downloading... "+yearValue+"_"+fileName);
                        inter.printMessage("Downloading... "+yearValue+"_"+fileName, 0);
                        HtmlPage page5 = null;
                        while(tries<=limit){
                            try{
                                page5 = downloadButton.click();
                                tries = 0;
                                break;
                            }catch(Exception e){
                                if(tries>=limit){
                                    System.out.println("Skipping...");
                                    tries = 0;
                                    break;
                                }else{
                                    tries++;
                                    System.out.println("Retrying...");
                                }
                            }
                        }
                        HtmlInput downloadImage = page5.getFirstByXPath("//input[@type='image']");

                        //PAGE 6(DOWNLOAD PAGE AMD SAVE)
                        while(true){ //ADDED
                            WebResponse response;
                            while(true){
                                try{
                                    response = downloadImage.click().getWebResponse();
                                    break;
                                }catch (Exception e){
                                    System.out.println("Retrying...");
                                }
                            }
                            InputStream inputStream = null;
                            OutputStream outputStream = null;
                            try{
                                inputStream = response.getContentAsStream();
                                if(!singleSubject){
                                    outputStream = new FileOutputStream(new File("Past Year Downloads\\"+userDetails.getSubjectsString().get(subjectIndex)+"\\"+yearValue+"_"+fileName));
                                }else{
                                    outputStream = new FileOutputStream(new File("Past Year Downloads\\"+subjectNameElement.getTextContent()+"\\"+yearValue+"_"+fileName));
                                }

                                int read = 0;
                                byte[] bytes = new byte[1024];
                                while((read = inputStream.read(bytes)) != -1){
                                    outputStream.write(bytes, 0 , read);                      
                                }

                                System.out.println("Downloading... "+fileName+" Finished");
                                inter.printMessage("Downloading... "+yearValue+"_"+fileName+" Finished", 0);
                                break; //ADDED
                            }catch(Exception e){
                                inter.printMessage("ERROR: "+e.toString(), 6);
                                e.printStackTrace();
                                Thread.sleep(1000); //ADDED
                                System.out.println("Retrying");
                                continue; //ADDED
                            }finally{
                                outputStream.close();
                                inputStream.close();
                            }
                        }//ADDED
                        //END OF DOWNLOAD
                            
                    }

                    HtmlAnchor nextPage = page3.getFirstByXPath("//a[@title='Go to Next Page']");
                    if(nextPage==null){
                        hasNextPage = false;
                    }else{
                        System.out.println("Accessing next page...");
                        while(tries<=limit){
                            try{
                                page3 = nextPage.click();
                                System.out.println("Next page accessed...");
                                hasNextPage = true;
                                tries = 0;
                                break;
                            }catch(Exception e){
                                hasNextPage = false;
                                if(tries>=limit){
                                    System.out.println("Skipping...");
                                    tries = 0;
                                    break;
                                }else{
                                    tries++;
                                    System.out.println("Retrying...");
                                }
                            }
                        }
                    }
                }while(hasNextPage);
                subjectIndex++;
            }
            inter.printMessage("INFO: Downloads have successfully finished!", 8);
            inter.getSubjectCodeTextField().setText("");
            inter.getSubjectCodeTextField().setEditable(true);
            inter.getManuallyDownload().setEnabled(true);
            inter.getAutomaticDownload().setEnabled(true);
            inter.setDownloadStatus(false);
        }catch (Exception e){
            inter.printMessage("ERROR: "+e.toString(), 6);
            if(e.toString().equals("java.net.UnknownHostException: proxyvlib.mmu.edu.my")){
                inter.printMessage("ERROR: Failed to download! Make sure you have an active connection!", 6);
            }
            e.printStackTrace();
            inter.getSubjectCodeTextField().setText("");
            inter.getSubjectCodeTextField().setEditable(true);
            inter.getManuallyDownload().setEnabled(true);
            if(new UserDetails().isLoginStatus()){
                inter.getAutomaticDownload().setEnabled(true);
            }else{
                inter.getAutomaticDownload().setEnabled(false);
            }
            inter.setDownloadStatus(false);
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
