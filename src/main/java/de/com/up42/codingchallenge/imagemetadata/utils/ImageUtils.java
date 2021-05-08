package de.com.up42.codingchallenge.imagemetadata.utils;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class ImageUtils {

    public static byte[] convertBase64DataToByteArray(String assetInBase64Format) {

        if (!StringUtils.isEmpty(assetInBase64Format)) {

            return Base64.getDecoder().decode(assetInBase64Format);

        }

        return null;
    }

    public static byte[] generateEasterEgg(byte[] sourceImageAsBytesArray) throws IOException {

        byte[] imageAsByteArrasResult = null;

        if (sourceImageAsBytesArray != null && sourceImageAsBytesArray.length > 0) {

            InputStream is = new ByteArrayInputStream(sourceImageAsBytesArray);
            BufferedImage newBi = ImageIO.read(is);

            // add a text on top on the image, optional, just for fun
            Graphics2D g = newBi.createGraphics();
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.setColor(Color.CYAN);
            g.drawString("UP42 Rocks!", 100, 100);

            g.setFont(new Font("Verdana", Font.BOLD, 25));
            g.setColor(Color.YELLOW);
            g.drawString("I really wanna join this Team!", 60, 180);

            g.setFont(new Font("Verdana", Font.BOLD, 50));
            g.setColor(Color.GREEN);
            g.drawString(":-)", 140, 240);

            // save it
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(newBi, "png", baos);

            imageAsByteArrasResult = baos.toByteArray();
        }

        return imageAsByteArrasResult;

    }

}
