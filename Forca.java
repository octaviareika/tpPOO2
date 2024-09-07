/*Classes:
○ Forca (controla a construção da imagem da forca com base nos erros)
 */



public class Forca {
    private int erros = 0;
    private String[] boneco = {"", "", "", "", "", "", "", "", "", ""};

    public Forca() {
    }

    public void adicionarErro() {
        erros++;
        switch (erros) {
            case 1:
                boneco[0] = "O";
                break;
            case 2:
                boneco[1] = "|";
                break;
            case 3:
                boneco[2] = "|";
                break;
            case 4:
                boneco[3] = "|";
                break;
            case 5:
                boneco[4] = "|";
                break;
            case 6:
                boneco[5] = "|";
                break;
            case 7:
                boneco[6] = "|";
                break;
            case 8:
                boneco[7] = "/";
                break;
            case 9:
                boneco[8] = "\\";
                break;
            case 10:
                boneco[9] = "/";
                break;
        }
    }

    public int getErros() {
        return erros;
    }

    public String[] getBoneco() {
        return boneco;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }

    public void setBoneco(String[] boneco) {
        this.boneco = boneco;
    }

    public void resetarBoneco() {
        for (int i = 0; i < boneco.length; i++) {
            boneco[i] = "";
        }
    }

    public void resetarErros() {
        erros = 0;
    }
    
}
