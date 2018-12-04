package google.image;

import com.intensostudios.grokit.lang.google.image.ImageService;
import com.intensostudios.grokit.lang.spring.SpringConfig;
import com.intensostudios.grokit.lang.util.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class GoogleImageServiceTest {

    @Autowired
    private ImageService imageService;

    @Test
    public void getFirstIconChair() {

        String path = "/home/ubuntu/Downloads/door.jpg";
        byte[] data = imageService.getFirstIcon("door");
        assertNotNull(data);
        TestUtils.writeFile(data, path);
        TestUtils.openImage(path);
    }
}