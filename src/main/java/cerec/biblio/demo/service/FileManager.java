package cerec.biblio.demo.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public interface FileManager {

    String uploadDocument(CommonsMultipartFile multipartFile);
    String uploadImage(CommonsMultipartFile multipartFile);
    String updateImage(MultipartFile multipartFile);
    String updateDocument(MultipartFile multipartFile);

    boolean removeImage(String url);
    boolean removeDocument(String url);

    /*void remove(Collection<String> urls);*/
}
