/*Classes:
○ Forca (controla a construção da imagem da forca com base nos erros)
 */
import javax.swing.*;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Forca {
    private int erros = 0;
    private String[] boneco = {"", "", "", "", "", "", "", "", "", ""};
    private ImageIcon[] imagens = new ImageIcon[11];

    public Forca() {
        imagens[0] = new ImageIcon(getClass().getResource("ImagensBoneco/forca.png"));
        imagens[1] = new ImageIcon(getClass().getResource("ImagensBoneco/cabeca.png"));
        imagens[2] = new ImageIcon(getClass().getResource("ImagensBoneco/pesoco.png"));
        imagens[3] = new ImageIcon(getClass().getResource("ImagensBoneco/corpo.png"));
        imagens[4] = new ImageIcon(getClass().getResource("ImagensBoneco/braco1.png"));
        imagens[5] = new ImageIcon(getClass().getResource("ImagensBoneco/braco2.png"));
        imagens[6] = new ImageIcon(getClass().getResource("ImagensBoneco/perna1.png"));
        imagens[7] = new ImageIcon(getClass().getResource("ImagensBoneco/perna2.png"));
        imagens[8] = new ImageIcon(getClass().getResource("ImagensBoneco/olho1.png"));
        imagens[9] = new ImageIcon(getClass().getResource("ImagensBoneco/olho2.png"));
        imagens[10] = new ImageIcon(getClass().getResource("ImagensBoneco/boca.png"));

        

    }

    public ImageIcon getImagemErro() {
        if (erros >= 0 && erros < imagens.length) {
            return redimensionarImagem(imagens[erros]);
        }
        return null;
    }


    public void adicionarErro() {
        erros++;
        switch (erros) {
            case 1:
                // colocar imagem a cada erro
                imagens[1] = new ImageIcon(getClass().getResource("ImagensBoneco/cabeca.png"));
                break;
            case 2:
                imagens[2] = new ImageIcon(getClass().getResource("ImagensBoneco/pesoco.png"));
                break;
            case 3:
                imagens[3] = new ImageIcon(getClass().getResource("ImagensBoneco/corpo.png"));
                break;
            case 4:
                imagens[4] = new ImageIcon(getClass().getResource("ImagensBoneco/braco1.png"));
                break;
            case 5:
                imagens[5] = new ImageIcon(getClass().getResource("ImagensBoneco/braco2.png"));
                break;
            case 6:
                imagens[6] = new ImageIcon(getClass().getResource("ImagensBoneco/perna1.png"));
                break;
            case 7:
                imagens[7] = new ImageIcon(getClass().getResource("ImagensBoneco/perna2.png"));
                break;
            case 8:
                imagens[8] = new ImageIcon(getClass().getResource("ImagensBoneco/olho1.png"));
                break;
            case 9:
                imagens[9] = new ImageIcon(getClass().getResource("ImagensBoneco/olho2.png"));
                break;
            case 10:
                imagens[10] = new ImageIcon(getClass().getResource("ImagensBoneco/boca.png"));
                break;
        }
    }

    public ImageIcon redimensionarImagem(ImageIcon icon) {
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH); // Ajuste o tamanho conforme necessário
        return new ImageIcon(newImg);
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
        imagens[0] = new ImageIcon(getClass().getResource("ImagensBoneco/forca.png"));
    }

    public void resetarErros() {
        erros = 0;
        
    }
    
}
