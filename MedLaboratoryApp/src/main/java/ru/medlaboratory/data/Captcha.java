package ru.medlaboratory.data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Captcha {
    private static char[] getRandomStringImpl() {

        char[] randomString = new char[8];

        Random random = new Random();

        int capitalLetter;

        for (int i = 0; i < 8; i++) {
            // Capital English letters in the ASCII
            // symbol table start with code 65
            capitalLetter = 65 + random.nextInt(26);
            randomString[i] = (char) capitalLetter;
        }

        return randomString;
    }
    public static BufferedImage generateCaptcha() throws IOException {
        char[] captcha = getRandomStringImpl();
        BufferedImage[] images = new BufferedImage[8];

        for (int i = 0; i < captcha.length; i++) {
            images[i] = ImageIO.read(Captcha.class.getResourceAsStream("/captcha/" + captcha[i] + ".png"));
            if (i % 2 == 0) {
                images[i] = rotateImage(images[i], 35);
            } else {
                images[i] = rotateImage(images[i], -35);
            }
        }

        int imageSize = 30;

        int rotatedImageSize = (int) Math.sqrt(imageSize * imageSize * 2);

        BufferedImage captcha_img = new BufferedImage(rotatedImageSize + rotatedImageSize * 7 / 10 * 6, rotatedImageSize, BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < captcha.length; i++) {
            captcha_img.getGraphics().drawImage(images[i], rotatedImageSize * i / 10 * 6, 0, null);
        }
        return captcha_img;
    }

    private static BufferedImage rotateImage(BufferedImage buffImage, double angle) {

        double radian = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(radian));
        double cos = Math.abs(Math.cos(radian));

        int width = buffImage.getWidth();
        int height = buffImage.getHeight();

        int nWidth = (int) Math.floor((double) width * cos + (double) height * sin);
        int nHeight = (int) Math.floor((double) height * cos + (double) width * sin);

        BufferedImage rotatedImage = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = rotatedImage.createGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics.translate((nWidth - width) / 2, (nHeight - height) / 2);
        graphics.rotate(radian, (double) (width / 2), (double) (height / 2));
        graphics.drawImage(buffImage, 0, 0,null);
        graphics.dispose();

        return rotatedImage;
    }
}
