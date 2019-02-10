import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Clasa ZeroOrderedHold extinde clasa abstracta definita si implementeaza interfata RendarePixeli
//Clasa implementeaza metoda de ZOOM ZeroOrderedHold
/*
    Scurta descriere a metodei

    INTRODUCTION
    Zero order hold method is another method of zooming. It is also known as zoom twice.
    Because it can only zoom twice. We will see in the below example that why it does that.

WORKING
    In zero order hold method , we pick two adjacent elements from the rows respectively and then we add them and divide the result by two, and place their result in between those two elements.
    We first do this row wise and then we do this column wise.
 */

public class ZeroOrderedHold extends Picture implements RendarePixeli {

    private BufferedImage zoomedImage;   //variabila ce o sa contina imaginea prelucrata;

    public ZeroOrderedHold(String path) throws IOException {
        super(path);
    }

    //Metoda de export din clasa Picture suprascrisa
    @Override
    public void exportPicture() throws IOException {
        ImageIO.write(zoomedImage,"bmp",new File("ZeroOrderedHold.bmp"));
    }

    //Metoda de modificare a dimensiunilor imaginii
    @Override
    public BufferedImage modifyDimensions(int width, int height) {
        return new BufferedImage((2 * width) - 1, (2 * height) - 1, BufferedImage.TYPE_INT_RGB);
    }

    //Metoda din interfata suprascrisa pentru a realiza redarea si exportul imaginii intr-o singura metoda
    @Override
    public void performAction() throws IOException {
        renderImage();
        exportPicture();
    }

    //Metoda suprascrisa a rendarii imaginii pentru metoda ZeroOrderedHold
    @Override
    public void renderImage() {

        zoomedImage = modifyDimensions(bufferedImage.getWidth(),bufferedImage.getHeight());

        for(int i = 0; i < zoomedImage.getHeight(); i += 2) {
            for(int j = 0; j < zoomedImage.getWidth(); j += 2) {
                zoomedImage.setRGB(j, i, bufferedImage.getRGB(j / 2,i / 2));

                if (j + 1 < zoomedImage.getWidth()) {
                    zoomedImage.setRGB(j + 1, i, mediePixeli(bufferedImage.getRGB(j / 2, i / 2), bufferedImage.getRGB((j + 2) / 2, i / 2)));
                }
            }
        }


        for(int i = 1; i < zoomedImage.getHeight(); i += 2){
            for(int j = 0; j < zoomedImage.getWidth(); j++ ){
                zoomedImage.setRGB(j, i, mediePixeli(bufferedImage.getRGB((j - 1) / 2, i / 2), bufferedImage.getRGB((j + 1) / 2, (i / 2))));
            }
        }
    }

    //Fuctie auxiliara pentru calcula media dintre 2 pixeli. Aceasta foloseste prelucrarea la nivel de bit
    private int mediePixeli(int pixel1, int pixel2) {
        return  (int) (((((pixel1) ^ (pixel2)) & 0xfffefefeL) >> 1) + ((pixel1) & (pixel2)));
    }

    //Getteri si Setteri


    public BufferedImage getZoomedImage() {
        return zoomedImage;
    }

    public void setZoomedImage(BufferedImage zoomedImage) {
        this.zoomedImage = zoomedImage;
    }
}
