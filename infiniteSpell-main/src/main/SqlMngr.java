    package main;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.Statement;
    import java.util.List;
    import java.util.concurrent.ThreadLocalRandom;



    public class SqlMngr {
        private static final String URL = "jdbc:sqlite:infinite_spell.db";  
        public static Connection getConnection() throws Exception {
            return DriverManager.getConnection(URL);
        }
        ApiGemini apigemini =new ApiGemini();
        GeneratorImage generatorImage = new GeneratorImage();

        public void initData() {
            String sql = """
                CREATE TABLE IF NOT EXISTS cards (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    descricao TEXT,
                    dano INTEGER,
                    tempo_conjuracao INTEGER,
                    elemento INTEGER,
                    raridade INTEGER,
                    url TEXT
                );
            """;
        
            
            try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("Banco ta no ar ");
            } catch (Exception e) {
                System.err.println("Erro ao inicializar o banco seu codigo é ruim: " + e.getMessage());
            }
        }


        public  boolean recordData(List<Object> listJson2){
            
        System.out.println("lfhwlkhrthhjjytjyrjtrjtrjkjlgsliufwberfhrewgçiudfhç");
            int id= ThreadLocalRandom.current().nextInt(100, 1000);
            String nome = (String) listJson2.get(0);
            String description =  (String) listJson2.get(1);
            int dano = (int) listJson2.get(2);
            int tempoConjuracao =  (int) listJson2.get(3);
            int elemento =  (int) listJson2.get(4);
            int raridade =  (int) listJson2.get(5);

            String url = (String) generatorImage.image(id, description);

            String sql = "INSERT INTO cards (id,nome, descricao, dano, tempo_conjuracao, elemento, raridade,url) VALUES (?,?, ?, ?, ?, ?, ?,?);";
        try (Connection conn = getConnection(); 
                PreparedStatement  insrt = conn.prepareStatement(sql)) {

                insrt.setLong(1, id);
                insrt.setString(2, nome);
                insrt.setString(3, description);
                insrt.setInt(4, dano);
                insrt.setInt(5, tempoConjuracao);
                insrt.setInt(6, elemento);
                insrt.setInt(7, raridade);
                insrt.setString(8, url);
                
                insrt.executeUpdate();
                
                System.out.println(  "parabéns seu idiota ta tudo gravado :)");
                
                return true;
            } catch (Exception e) {
                System.err.println("Erro ao gravar o seu codigo lixo: " + e.getMessage());
                return false;
            }

          
        }
    

    }
        

