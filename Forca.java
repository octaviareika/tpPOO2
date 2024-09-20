/*Classes:
○ Forca (controla a construção da imagem da forca com base nos erros)
 */
import javax.swing.*;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Forca {
    private int erros = 0;
    
    private ImageIcon[] imagens = new ImageIcon[11];

    public Forca() {
        imagens[0] = new ImageIcon(getClass().getResource("ImagensBoneco2/forca.png"));
        imagens[1] = new ImageIcon(getClass().getResource("ImagensBoneco2/cabeca.png"));
        imagens[2] = new ImageIcon(getClass().getResource("ImagensBoneco2/pescoco.png"));
        imagens[3] = new ImageIcon(getClass().getResource("ImagensBoneco2/corpo.png"));
        imagens[4] = new ImageIcon(getClass().getResource("ImagensBoneco2/braco1.png"));
        imagens[5] = new ImageIcon(getClass().getResource("ImagensBoneco2/braco2.png"));
        imagens[6] = new ImageIcon(getClass().getResource("ImagensBoneco2/perna1.png"));
        imagens[7] = new ImageIcon(getClass().getResource("ImagensBoneco2/perna2.png"));
        imagens[8] = new ImageIcon(getClass().getResource("ImagensBoneco2/olho1.png"));
        imagens[9] = new ImageIcon(getClass().getResource("ImagensBoneco2/olho2.png"));
        imagens[10] = new ImageIcon(getClass().getResource("ImagensBoneco2/boca.png"));

        

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
                imagens[1] = new ImageIcon(getClass().getResource("ImagensBoneco2/cabeca.png"));
                break;
            case 2:
                imagens[2] = new ImageIcon(getClass().getResource("ImagensBoneco2/pescoco.png"));
                break;
            case 3:
                imagens[3] = new ImageIcon(getClass().getResource("ImagensBoneco2/corpo.png"));
                break;
            case 4:
                imagens[4] = new ImageIcon(getClass().getResource("ImagensBoneco2/braco1.png"));
                break;
            case 5:
                imagens[5] = new ImageIcon(getClass().getResource("ImagensBoneco2/braco2.png"));
                break;
            case 6:
                imagens[6] = new ImageIcon(getClass().getResource("ImagensBoneco2/perna1.png"));
                break;
            case 7:
                imagens[7] = new ImageIcon(getClass().getResource("ImagensBoneco2/perna2.png"));
                break;
            case 8:
                imagens[8] = new ImageIcon(getClass().getResource("ImagensBoneco2/olho1.png"));
                break;
            case 9:
                imagens[9] = new ImageIcon(getClass().getResource("ImagensBoneco2/olho2.png"));
                break;
            case 10:
                imagens[10] = new ImageIcon(getClass().getResource("ImagensBoneco2/boca.png"));
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

    public void setErros(int erros) {
        this.erros = erros;
    }


    

    public void resetarBoneco() {
        imagens[0] = new ImageIcon(getClass().getResource("ImagensBoneco2/forca.png"));
    }

    public void resetarErros() {
        erros = 0;
        
    }
    
}
