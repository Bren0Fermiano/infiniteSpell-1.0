package main;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView; 
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class App extends Application { 

    @Override
    public void start(Stage stage) {
        
       // CriateCards criatecards = new CriateCards();
        SqlMngr sqlmngr = new SqlMngr();
        WebView janela = new WebView();
        janela.setContextMenuEnabled(true);
        WebEngine engine = janela.getEngine();
    
        engine.setOnError(event -> System.err.println("Deu erro man :(" + event.getMessage()));
    
        stage.setTitle("Infinite Spell");
        stage.getIcons().add(new Image("file:src/main/resources/game front/img/logo.png"));
        stage.setResizable(false);
        
        engine.getHistory().getEntries().clear();
         
   
        String urlIndex = getClass().getResource("../game front/static/index.html").toExternalForm();
        engine.load(urlIndex);

   engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
    if (newState == Worker.State.SUCCEEDED) {
        JSObject window = (JSObject) engine.executeScript("window");
        
        window.setMember("returnJava", new returnJava());

        try {
            engine.executeScript("loadDeck()");
        } catch (Exception e) {
         
        }
    }
});



 
        VBox vbox = new VBox(janela);
        janela.prefWidthProperty().bind(vbox.widthProperty());
        janela.prefHeightProperty().bind(vbox.heightProperty());
        janela.setCache(true);
        engine.setJavaScriptEnabled(true);
        
        //gambiarra mt master n mexa se jogar para qualquer outro lugar vai ferrar o time do java
         engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
          if (newState == Worker.State.SUCCEEDED) {
        JSObject window = (JSObject) engine.executeScript("window");

        window.setMember("criatec", new CriateCards());

        }
        });

        Scene scene = new Scene(vbox, 1024, 768);
        stage.setScene(scene);
        stage.show();    
     //   sqlmngr.initData();
     //  criatecards.criate();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


/* 
cd C:\Users\2TDS\Documents\infiniteSpell\infiniteSpell-main
& "C:\Users\2TDS\Documents\infiniteSpell\infiniteSpell-main\maven\bin\mvnd.cmd" javafx:run  

 cd C:\Users\Pichau\Desktop\infiniteSpell\infiniteSpell-main
& "C:\Users\Pichau\Desktop\infiniteSpell\infiniteSpell-main\maven\bin\mvnd.cmd" javafx:run
*/


/* opaa se vc quiser rodar esse codigo maldito ja lhe digo  que sera um pouco turbulento 
usse a seginte estrutura para gerar o comando de execuçao 

    --- cd (caminho para pasta infiniteSpell-main )
    ---& ( caminho para o mvnd.cmd) javafx:run

    os comandos anteriores tem que ser rodados em conjuto e os caminhos presisam 
    ser completos   e  o caminho tem que ser colocadoo entre aspas ""
      
    os comandos no começo sao os que eu uso no meu pc e no da escola 
    porem n funcionam em um outro pc
    ignora os erros de  portugues foi escrito  na correria 
*/
 
//