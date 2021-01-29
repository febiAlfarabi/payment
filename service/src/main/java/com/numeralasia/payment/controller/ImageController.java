package com.numeralasia.payment.controller;

import com.google.common.net.HttpHeaders;
import com.numeralasia.payment.model.exception.AppException;
import com.numeralasia.payment.util.Constant;
import io.github.biezhi.webp.WebpIO;
import io.github.biezhi.webp.WebpIOException;
import io.github.febialfarabi.utility.MediaTypeUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "${api}")
@ApiIgnore
public class ImageController extends BasicController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class.getName());

    @Autowired ServletContext servletContext;


    @GetMapping(value = Constant.REST_TEMPORARY_IMAGE+"/{filename:.+}")
    public ResponseEntity<ByteArrayResource> temporary(
            @PathVariable("filename") String filename,
            @RequestParam(name = "webp", required = false, defaultValue = "false") Boolean webp) throws Exception {

        String path = temporaryDir+File.separator+filename;
        File compressedFile = compress(path, 0, BooleanUtils.isTrue(webp));
        return grabFile(filename, compressedFile.getAbsolutePath());
    }


    @GetMapping(Constant.REST_TEMPLATE_IMAGE+"/{id}/{filename:.+}")
    public ResponseEntity<ByteArrayResource> templateImage(
            @PathVariable("id") Long id,
            @PathVariable("filename") String filename,
            @RequestParam(name = "webp", required = false, defaultValue = "false") Boolean webp) throws Exception {

        String path = templateImageDir+File.separator+id+File.separator+filename;
        File compressedFile = compress(path, Constant.MEDIUM_QUALITY, BooleanUtils.isTrue(webp));

        if(!compressedFile.exists()){
            throw new RuntimeException(message("image.not.found"));
        }
        return grabFile(filename, compressedFile.getAbsolutePath());
    }



    @GetMapping(Constant.FORM_MEDIA_DIRECTORY+"/**")
    public ResponseEntity<ByteArrayResource> formImage(
            HttpServletRequest request) throws Exception {
        logger.debug("URI "+request.getRequestURI());

        String requestUrl = request.getRequestURL().toString();
        logger.debug("URL "+requestUrl);
        String path = Constant.FORM_MEDIA_DIRECTORY+requestUrl.split(Constant.FORM_MEDIA_DIRECTORY)[1];

//        String path = userIdCardDir+File.separator+id+File.separator+filename;
        File compressedFile = compress(path, Constant.HIGH_QUALITY, false);
        return grabFile(compressedFile.getName(), compressedFile.getAbsolutePath());
    }


    @GetMapping(Constant.REST_MIDTRANS_MEDIATOR_IMAGE+"/{id}/{filename:.+}")
    public ResponseEntity<ByteArrayResource> midtransMediatorImage(
            HttpServletRequest request,
            @PathVariable("id") Long id,
            @PathVariable("filename") String filename,
            @RequestParam(name = "webp", required = false, defaultValue = "false") Boolean webp) throws Exception {

        String path = midtransMediatorImageDir+File.separator+id+File.separator+filename;
        File compressedFile = compress(path, Constant.MEDIUM_QUALITY, BooleanUtils.isTrue(webp));

        if(!compressedFile.exists()){
            throw new AppException(Constant.IMAGE_NOT_FOUND, message("image.not.found"));
        }
        return grabFile(filename, compressedFile.getAbsolutePath());
    }

    @Cacheable(cacheNames = "images", key = "{#pathFile, #filename}")
    public ResponseEntity<ByteArrayResource> grabFile(String filename, String pathFile) throws Exception{
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, filename);
        Path path = Paths.get(pathFile);
        byte[] data = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(data);


        return ResponseEntity.ok()
                // Content-Disposition
                .cacheControl(CacheControl.maxAge(360, TimeUnit.DAYS).cachePublic())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                // Content-Type
                .contentType(mediaType) //
                // Content-Lengh
                .contentLength(data.length) //
                .body(resource);

    }

    public File compress(String path, float quality, Boolean webp) throws Exception{
        if(BooleanUtils.isTrue(webp)){
            return webp(path, 1);
        }else{
            return defaultExtension(path, quality);
        }
    }



    public File defaultExtension(String path, float quality) throws Exception{
        File input = new File(path);
        String extension = FilenameUtils.getExtension(input.getName());
        if(StringUtils.isEmpty(extension)){
            // Force to hardcode as image/jpeg
            extension = "jpeg";
        }

        BufferedImage image = ImageIO.read(input);

        File output = new File("/tmp/"+input.getName());
        if(output.exists()){
            output.delete();
        }
        OutputStream out = new FileOutputStream(output);

        ImageWriter writer =  ImageIO.getImageWritersByFormatName(extension).next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(out);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();
        if (param.canWriteCompressed()){
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);
        }
        writer.write(null, new IIOImage(image, null, null), param);

        out.close();
        ios.close();
        writer.dispose();

        return output ;
    }

    public File webp(String path, int recount) throws Exception{
        File input = new File(path);
        BufferedImage image = ImageIO.read(input);
        String tempPath = "/tmp/"+FilenameUtils.getBaseName(input.getName())+".webp";
        File output = new File(tempPath);
        if(output.exists()){
            output.delete();
        }
        try{
            WebpIO.create().toWEBP(input, output);
        }catch (WebpIOException e){
            if(recount<10){
                Thread.sleep(1000);
                return webp(path, ++recount);
            }else{
                throw e;
            }
        }

        return output ;
    }



//    public File compress(String path, float quality, boolean compress) throws Exception {
//        File input = null;
//        BufferedImage image = null;
//        try{
//            logger.debug("PATH = {} ", path);
//            input = new File(path);
//            image = ImageIO.read(input);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            input = new File(staticImageDir+"/no_image.png");
//            image = ImageIO.read(input);
//        }
//        String tempPath = "/tmp/"+FilenameUtils.getBaseName(input.getName())+".webp";
//        File output = new File(tempPath);
//        if(output.exists()){
//            output.delete();
//        }
//
//        WebpIO.create().toWEBP(input, output);
//
//        return output ;
//    }





}
