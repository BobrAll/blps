package bobr.blps_lab.image;

import bobr.blps_lab.realty.flat.Flat;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Base64ImageService {
    private final ImageRepository imageRepository;

    public static Base64Image download(String url, Flat flat) {
        byte[] imageBytes = new byte[0];
        String imgExtension = url.substring(url.length() - 3);

        try {
            BufferedImage image = ImageIO.read(new URL(url));
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            ImageIO.write(image, imgExtension, out);
            imageBytes = out.toByteArray();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        return new Base64Image(base64Image, flat);
    }

    public static byte[] toByteImage(Base64Image img) {
        try {
            byte[] imageBytes = Base64.getDecoder().decode(img.getBase64Image());
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", out);

            return out.toByteArray();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

//    @Async
    public Base64Image save(Base64Image image) {
        return imageRepository.save(image);
    }

    public List<Base64Image> findAllByFlatId(Integer flatId) {
        return imageRepository.findAllByFlatId(flatId);
    }
}
