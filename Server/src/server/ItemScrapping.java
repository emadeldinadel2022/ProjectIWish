/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author hatem
 */
public class ItemScrapping {
    public static void main(String args[]){
        String url ="https://books.toscrape.com/";
        ArrayList<DTOItem> scrapingitems = new ArrayList<DTOItem>();
        try {
            Document document = Jsoup.connect(url).get();
            
            Elements imageContainers = document.select(".image_container");
            
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("Images : Web Scraper");

            List<byte[]> imageBytesList = new ArrayList<>();

            for (Element imageContainer : imageContainers) {
                // Select the <img> tag and get the src attribute
                String imageUrl = imageContainer.select("img.thumbnail").attr("src");

                // Download the image content as an array of bytes
                byte[] imageBytes = downloadImage(imageUrl);
                
                // Add the image bytes to the list
                imageBytesList.add(imageBytes);
                scrapingitems.add(new DTOItem(imageBytes));
            }

            Elements books = document.select(".product_pod");
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("Books : Web Scrapper");
            
            int i = 0;
            for ( Element bk : books) {
                String title = bk.select("h3 > a").attr("title");
                //Double price = Double.parseDouble(bk.select(".price_color").text().replaceAll("[^0-9]", "").trim())* Math.pow(10, -2);
                   
                String price = bk.select(".price_color").text().replaceAll("[^\\d.]+", "").trim().substring(0, 2);

                System.out.println(title + "  " + price);
                scrapingitems.get(i).setItemname(title);
                scrapingitems.get(i).setItemprice(Integer.parseInt(price));
                i++;
                
            }
            System.out.println("++++++++++++++++++++++++++++++");

            System.out.println(scrapingitems);
            
           
         
    }   catch (IOException ex) {
            Logger.getLogger(ItemScrapping.class.getName()).log(Level.SEVERE, null, ex);
        }
}


private static byte[] downloadImage(String imageUrl) throws IOException {
    try {
        // Check if the URL is absolute
        URL url = new URL(imageUrl);

        try (InputStream in = url.openStream()) {
            return readAllBytesFromInputStream(in);
        }
    } catch (MalformedURLException e) {
        // The URL is relative, prepend the base URL
        URL baseUrl = new URL("https://books.toscrape.com/");
        URL absoluteUrl = new URL(baseUrl, imageUrl);

        try (InputStream in = absoluteUrl.openStream()) {
            return readAllBytesFromInputStream(in);
        }
    }
}

private static byte[] readAllBytesFromInputStream(InputStream in) throws IOException {
        // Create a buffer to read the input stream in chunks
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // Read from the input stream and write to the ByteArrayOutputStream
        while ((bytesRead = in.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }

        // Convert the ByteArrayOutputStream to a byte array
        return baos.toByteArray();
    }

}