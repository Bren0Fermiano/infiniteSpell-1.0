    package main;


    import java.util.ArrayList;
    import java.util.List;

    import com.google.genai.Client;
    import com.google.genai.types.GenerateContentResponse;

    public class ApiGemini {
            
        private static final String API_KEY = "AQ.Ab8RN6L9OwV_Ke2L_pbaEbD5q03qI0NSRQaBwOYIGPUbhnuuww";
        public List<Object> listJson = new ArrayList<>();
        //prompt pra ia burra kkkkk
    public String prompt = """
        Atue como um gerador de dados estritos para um card game RPG chamado 'Infinite Spell'.
        Sua tarefa é criar, de forma totalmente aleatória e criativa, um novo INGREDIENTE DE POÇÃO místico do zero. Você tem total liberdade criativa para inventar o conceito que quiser.
        
        Você deve responder ESTRITAMENTE com um objeto JSON válido, sem formatação Markdown (não use blocos de código com ```json), sem explicações, introduções ou notas.
        
        REGRAS DE BALANÇO E VIÉS (Siga rigorosamente):
        1. Distribuição de Raridade: Prefira majoritariamente (cerca de 70% das vezes) gerar cartas normais (1). Ocasionalmente gere raras (2) e raramente (10% das vezes) gere uma lendária (3) devastadora estilo 'insta-kill'.
        2. Relação Dano vs Tempo: O tempo_conjuracao deve ser o valor do dano dividido por 10. (Ex: Dano 30 = 3 segundos | Dano 80 = 8 segundos | Dano 100 = 10 segundos).
        3. Curva de Poder: Ingredientes comuns/raros devem focar em menos dano e menor tempo. Os ingredientes lendários devem ter dano massivo (próximo ou igual a 100) com tempo proporcionalmente alto.
       4. REGRA ANTIPESSOAS E TEXTURA (CRUCIAL): No campo 'prompt_imagem', você deve gerar o prompt estritamente em inglês. É OBRIGATÓRIO focar apenas no objeto mineral/animal. Use termos como 'reptilian eye', 'dragon eye', 'slit pupil' (pupila em fenda), 'scale texture' (textura de escama). PROIBIDO rostos humanos, lábios, narizes ou expressões humanas. O item deve parecer um asset de jogo isolado.

        O formato do JSON deve seguir exatamente esta estrutura e tipos de dados:
        {
        "nome": "String com o nome inventado do ingrediente da poção",
        "descricao": "String com a descrição mística descrevendo  em ingles como o igrediente se paresse  maximo de 4  palavras",
        "dano": Inteiro (valor de potencial de dano de 10 a 100),
        "tempo_conjuracao": Inteiro (calculado estritamente como dano dividido por 10, de 1 a 10),
        "elemento": Inteiro (ID do elemento de 1 a 5, baseado na Tabela 1),
        "raridade": Inteiro (ID da raridade de 1 a 3, baseado na Tabela 2)
        }
        
        Tabela 1 - Elementos:
        1 = Terra | 2 = Água | 3 = Fogo | 4 = Ar | 5 = Éter
        
        Tabela 2 - Raridade:
        1 = Normal | 2 = Raro | 3 = Lendário
        """;

        public  List<Object> geminiapi() {
            listJson.clear();
            try {
                System.out.println("p1");

                Client client = Client.builder()
                                    .apiKey(API_KEY)
                                    .build();
                 System.out.println("p2");

               GenerateContentResponse response = client.models.generateContent(
            "gemini-2.5-flash",
            prompt,
            null 
        );


                    System.out.println("p3");
                //outra merda de api dessa vez o json conv
                System.out.println(response.text());
                String jsonIA= (response.text());
    
                //converte p json bruto
                org.json.JSONObject jsonObject = new org.json.JSONObject(jsonIA);
        
                    System.out.println("p4");
                String nome = jsonObject.getString("nome");
                String descricao = jsonObject.getString("descricao");
                int dano = jsonObject.getInt("dano");
                int tempoConjuracao = jsonObject.getInt("tempo_conjuracao");
                int elemento = jsonObject.getInt("elemento");
                int raridade = jsonObject.getInt("raridade");

                // faz a lista pq o java é burro kkkkk
                      System.out.println("p4");
                listJson.add(nome);
                listJson.add(descricao);
                listJson.add(dano);
                listJson.add(tempoConjuracao);
                listJson.add(elemento);
                listJson.add(raridade);
                  System.out.println("funfou ");
            return(listJson);

            } catch (Exception e) {    
            System.out.println("deu errado p envia as cartas man ");
            e.printStackTrace();
            return null;
        
            }
            
        }

    }
