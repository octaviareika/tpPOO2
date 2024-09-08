import java.util.*;

public class Palavra {
    private ArrayList<String> palavrasFaceis = new ArrayList<String>(Arrays.asList(
        "casa", "carro", "moto", "bicicleta", "aviao", "navio", "trem", "onibus", "caminhao", "moto"
    ));
    private ArrayList<String> palavrasMedias = new ArrayList<String>(Arrays.asList(
        "computador", "celular", "tablet", "notebook", "teclado", "mouse", "monitor", "impressora", "roteador", "modem"
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


    // criar mascara 
    public String criarMascara(String palavra) {
        StringBuilder mascara = new StringBuilder();
        for (int i = 0; i < palavra.length(); i++) {
            mascara.append("_");
        }
        return mascara.toString();
    }
}
