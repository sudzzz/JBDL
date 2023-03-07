package org.example;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ImageController {

    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@RequestParam("id") int id,
                           @RequestParam(value = "length", required = false, defaultValue = "200") int length,
                           @RequestParam(value = "breadth", required = false, defaultValue = "300") int breadth){
        String url = "https://picsum.photos/id/" + id + "/" + length + "/" + breadth;

        RestTemplate restTemplate = new RestTemplate();
        byte[] image = restTemplate.getForObject(url, byte[].class);

        // Can we add the content type in the response headers ??

        return image;

    }

//    @PostMapping(value = "/image", consumes = MediaType.IMAGE_PNG_VALUE)
//    public byte[] uploadImage(byte[] image){
//        // XYZ place
//        String url = "https://picsum.photos/id/" + id + "/" + length + "/" + breadth;
//
//        RestTemplate restTemplate = new RestTemplate();
//        byte[] image = restTemplate.getForObject(url, byte[].class);
//
//        // Can we add the content type in the response headers ??
//
//        return image;
//
//    }
}