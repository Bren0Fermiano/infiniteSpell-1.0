package main;

import java.util.List;

public class CriateCards {
    ApiGemini apigemini = new ApiGemini();
    SqlMngr sqlmngr = new SqlMngr();
    public boolean r;

   
    public void criate() {
        System.out.println("macacos pulando cordas");
        
        new Thread(() -> {
            try {
                List<Object> listJson2 = apigemini.geminiapi();
                if (listJson2 != null) {
                    r = sqlmngr.recordData(listJson2);
                }
            } catch (Exception e) {
                System.err.println("deu erro aq irmão: " + e.getMessage());
            } finally {
                System.err.println("fynaly respondru r = " + r);
            }
        }).start();
    }

    public boolean returnData() {
        System.err.println("aq essa merda " + r);
        return r;
    } 
}