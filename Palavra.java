import java.util.*;

public class Palavra {
    private ArrayList<String> palavrasFaceis = new ArrayList<String>(Arrays.asList(
        "casa", "carro", "moto", "bicicleta", "aviao", "navio", "trem", "onibus", "caminhao", "moto", "rosa", "verde", "azul", "planta"
    ));
    private ArrayList<String> palavrasMedias = new ArrayList<String>(Arrays.asList(
        "computador", "celular", "tablet", "notebook", "teclado", "mouse", "monitor", "impressora", "roteador", "modem", "caravana", "planalto", "cachoeira", "laboratorio"
    ));
    private ArrayList<String> palavrasDificeis = new ArrayList<String>(Arrays.asList(
        "inextricavel", "paralelepipedo", "hipotenusa", "idiossincrasia", "esdruxulo", "onomatopeia", "procrastinacao", "hemeroteca", "inocuo", "cacofonia"
    ));

    Palavra() {
        
    }

    public String sortearPalavraFacil() {
        Random random = new Random();
        int index = random.nextInt(this.palavrasFaceis.size());
        return this.palavrasFaceis.get(index);
    }

    public String sortearPalavraMedia() {
        Random random = new Random();
        int index = random.nextInt(this.palavrasMedias.size());
        return this.palavrasMedias.get(index);
    }

    public String sortearPalavraDificil() {
        Random random = new Random();
        int index = random.nextInt(this.palavrasDificeis.size());
        return this.palavrasDificeis.get(index);
    }

    // getter e setter 

    public ArrayList<String> getPalavrasFaceis() {
        return this.palavrasFaceis;
    }

    public void setPalavrasFaceis(ArrayList<String> palavrasFaceis) {
        this.palavrasFaceis = palavrasFaceis;
    }

    public ArrayList<String> getPalavrasMedias() {
        return this.palavrasMedias;
    }

    public void setPalavrasMedias(ArrayList<String> palavrasMedias) {
        this.palavrasMedias = palavrasMedias;
    }

    public ArrayList<String> getPalavrasDificeis() {
        return this.palavrasDificeis;
    }

    public void setPalavrasDificeis(ArrayList<String> palavrasDificeis) {
        this.palavrasDificeis = palavrasDificeis;
    }

    public String adicionarDicasParaCadaPalavra(String palavra){
        if (palavra.equals("moto") || palavra.equals("carro")|| palavra.equals("navio")
            || palavra.equals("trem") || palavra.equals("bicicleta") || palavra.equals("aviao") || palavra.equals("caminhao"))
        {
            return "Veículo";
        } else if (palavra.equals("computador") || palavra.equals("celular") || palavra.equals("tablet")
            || palavra.equals("notebook") || palavra.equals("teclado") || palavra.equals("mouse") || palavra.equals("monitor")
        ){
            return "Eletrônico";
        } else if ( palavra.equals("paralelepipedo") || palavra.equals("hipotenusa")
          
        ){
            return "Geometria";
        }
        else if (palavra.equals("casa")){
            return "Moradia";
        }

        else if (palavra.equals("onomatopeia")){
            return "Som";
        } else if (palavra.equals("procrastinacao")){
            return "Adiar";
        } else if (palavra.equals("hemeroteca")){
            return "Jornal";
        } else if (palavra.equals("inocuo")){
            return "Inofensivo";
        } else if (palavra.equals("cacofonia")){
            return "Som desagradável";
        } else if (palavra.equals("esdruxulo")){
            return "Estranho";
        } else if (palavra.equals("inextricavel")){
            return "Difícil";
        } else if (palavra.equals("laboratorio")){
            return "Experiência";
        } else if (palavra.equals("cachoeira")){
            return "Água";
        } else if (palavra.equals("caravana")){
            return "Grupo";
        } else if (palavra.equals("planalto")){
            return "Montanha";
        } else if (palavra.equals("verde") || palavra.equals("azul") || palavra.equals("rosa")){
            return "Cor";
        }
        else if (palavra.equals("planta")){
            return "Natureza";
        }
        else if (palavra.equals("modem")){
            return "Internet";
        }

        else if (palavra.equals("impressora")){
            return "Papel";
        }
        else if (palavra.equals("roteador")){
            return "Internet";
        }

        else if (palavra.equals("idiossincrasia")){
            return "Característica";
        }

        return "";

    }


    // criar mascara 
    public String criarMascara(String palavra) {
        StringBuilder mascara = new StringBuilder();
        for (int i = 0; i < palavra.length(); i++) {
            mascara.append("_");
        }
        return mascara.toString();
    }
}
