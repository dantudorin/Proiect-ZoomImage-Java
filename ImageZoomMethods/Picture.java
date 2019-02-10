import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

// Clasa ABSTRACTA Picture care cuprinde un BufferedImage pentru stocarea imaginii citita din fisier

public abstract class Picture {

    public BufferedImage bufferedImage;


    public Picture(String path) throws IOException {

        this.bufferedImage = ImageIO.read(new File(path));  //Constructorul care pune in BufferedImage imaginea citita din fisier

    }

    public abstract void exportPicture() throws IOException;        //metoda abstracta care o sa implemeteze scrierea imaginii prelucrate in fisier
    public abstract BufferedImage modifyDimensions(int width, int height); // metoda abstracta care o sa modifice dimensiunile imaginii datorita ZOOM-ului
    public abstract void performAction() throws IOException;    // metoda abstracta care realizeaza etapa de prelucrare a fiecarui pixel din BufferedImage

    public BufferedImage getBufferedImage() {
        return bufferedImage;                   //getter pentru BufferedImage
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;     // setter pentru BufferedImage
    }
}
