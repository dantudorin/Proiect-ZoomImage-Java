import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

//Clasa PixelReplicationPicture extinde clasa abstracta Picture si implementeaza interfata definita
//Aceasta clasa implementeaza metoda Pixel Replication

/*
    Scurta rezentare a metodei

    It is also known as Nearest neighbor interpolation. As its name suggest , in this method , we just replicate the neighboring pixels. As we have already discussed in the tutorial of Sampling , that zooming is nothing but increase amount of sample or pixels. This algorithm works on the same principle.

    WORKING:
    In this method we create new pixels form the already given pixels. Each pixel is replicated in this method n times row wise and column wise and you got a zoomed image. Its as simple as that.

    FOR EXAMPLE:
    if you have an image of 2 rows and 2 columns and you want to zoom it twice or 2 times using pixel replication, here how it can be done.
 */

public class PixelReplicationPicture extends Picture implements RendarePixeli {

   private BufferedImage zoomedImage;   //o variabila care o sa contina imaginea prelucrata
   private int resizeFactor;    //coeficientul de ZOOM:::: Exemplu x2, x3; etc;

   public PixelReplicationPicture(String path, int resizeFactor) throws IOException {
        super(path);
        this.resizeFactor = resizeFactor;
   }

   //Metoda de export din clasa Picture suprascrisa
   @Override
    public void exportPicture()throws IOException{
       ImageIO.write(zoomedImage,"bmp",new File("PixelReplicationPicture.bmp")); //creeaza un fisier nou cu noua imagine prelucrata
   }

   //Metoda de modificare a dimensiunilor imaginii
    @Override
    public BufferedImage modifyDimensions(int width, int height){
        return new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
    }

    //Metoda suprascrisa a rendarii imaginii pentru metoda PixelReplication
    @Override
    public void renderImage() {
        zoomedImage = modifyDimensions(resizeFactor * bufferedImage.getWidth(), resizeFactor * bufferedImage.getHeight());

        for(int i = 0; i < zoomedImage.getHeight(); i++) {
            for(int j = 0; j < zoomedImage.getWidth(); j++) {

                zoomedImage.setRGB(j, i, bufferedImage.getRGB(j / 2, i / 2));

            }
        }
   }
   //Metoda din interfata suprascrisa pentru a realiza redarea si exportul imaginii intr-o singura metoda
   @Override
   public void performAction() throws IOException {

       renderImage();
       exportPicture();
   }


   // Getteri si Setteri


    public BufferedImage getZoomedImage() {
        return zoomedImage;
    }

    public void setZoomedImage(BufferedImage zoomedImage) {
        this.zoomedImage = zoomedImage;
    }

    public int getResizeFactor() {
        return resizeFactor;
    }

    public void setResizeFactor(int resizeFactor) {
        this.resizeFactor = resizeFactor;
    }
}
