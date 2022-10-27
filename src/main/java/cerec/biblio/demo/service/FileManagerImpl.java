package cerec.biblio.demo.service;

import cerec.biblio.demo.common.Consts;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Service
public class FileManagerImpl implements FileManager {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    @Override
    public String uploadDocument(CommonsMultipartFile multipartFile) {
        String storageRoot = Consts.FILE_STORAGE_ROOT+ File.separator+"documents";
        String path = "";
        String suffix = Objects.requireNonNull(multipartFile.getOriginalFilename())
                .substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        /*String fileName = SIMPLE_DATE_FORMAT.format(new Date())+"-"
                + UUID.randomUUID().toString().replaceAll("-","").toLowerCase()
                + suffix;*/
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(storageRoot+File.separator+fileName);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
            path = "/"+fileName;
        }catch (IOException e){
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public String uploadImage(CommonsMultipartFile multipartFile) {
        String storageRoot = Consts.FILE_STORAGE_ROOT+ File.separator+"images";
        String path = "";
        String suffix = Objects.requireNonNull(multipartFile.getOriginalFilename())
                .substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        /*String fileName = SIMPLE_DATE_FORMAT.format(new Date())+"-"
                + UUID.randomUUID().toString().replaceAll("-","").toLowerCase()
                + suffix;*/
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(storageRoot+File.separator+fileName);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
            path = "/"+fileName;
        }catch (IOException e){
            e.printStackTrace();
        }
        return path;
    }

    @Override
    public boolean removeImage(String url) {
        String path = Consts.FILE_STORAGE_ROOT+ File.separator+"images"+url;
        File file = new File(path);
        if(file.exists() && file.isFile()){
            return file.delete();
        }
        return false;
    }

    @Override
    public String updateImage(MultipartFile multipartFile) {
        String storageRoot = Consts.FILE_STORAGE_ROOT+ File.separator+"images";
        String path = "";
        String suffix = Objects.requireNonNull(multipartFile.getOriginalFilename())
                .substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        /*String fileName = SIMPLE_DATE_FORMAT.format(new Date())+"-"
                + UUID.randomUUID().toString().replaceAll("-","").toLowerCase()
                + suffix;*/
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(storageRoot+File.separator+fileName);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
            path = "/"+fileName;
        }catch (IOException e){
            e.printStackTrace();
        }
        return path;
    }

    @Override
    public String updateDocument(MultipartFile multipartFile) {
        String storageRoot = Consts.FILE_STORAGE_ROOT+ File.separator+"documents";
        String path = "";
        String suffix = Objects.requireNonNull(multipartFile.getOriginalFilename())
                .substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        /*String fileName = SIMPLE_DATE_FORMAT.format(new Date())+"-"
                + UUID.randomUUID().toString().replaceAll("-","").toLowerCase()
                + suffix;*/
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(storageRoot+File.separator+fileName);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
            path = "/"+fileName;
        }catch (IOException e){
            e.printStackTrace();
        }
        return fileName;
    }


    @Override
    public boolean removeDocument(String url) {
        String path = Consts.FILE_STORAGE_ROOT+ File.separator+"documents"+url;
        File file = new File(path);
        if(file.exists() && file.isFile()){
            return file.delete();
        }
        return false;
    }

    /*@Override
    public void remove(Collection<String> urls) {
        if(!CollectionUtils.isEmpty(urls)){
            urls.stream().allMatch(this::r);
        }
    }*/
}
