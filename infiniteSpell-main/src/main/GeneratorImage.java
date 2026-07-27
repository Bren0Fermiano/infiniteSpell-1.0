package main;

public class GeneratorImage {
    public Object image(int id, String description) {
        try {
            
            return "https://picsum.photos/id/" + id + "/150/200";
            
        } catch (Exception e) {
            System.out.print("deu erro na imagem foi para o catch");
            // generico caso de merda
            return "https://picsum.photos/200/300";
        }
    }
}