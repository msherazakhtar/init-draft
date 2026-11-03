package com.expense.tracker.utilities;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

    public static String uploadFile(MultipartFile profilePicture) {
        try{
  File uploadDir = new File("C:\\Users\\shera\\OneDrive\\Pictures\\ExpenseTracker\\Images\\ProfilePics");
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // create folders if not present
            }

            // Save the file locally
            String filePath = "C:\\Users\\shera\\OneDrive\\Pictures\\ExpenseTracker\\Images\\ProfilePics\\" +profilePicture.getOriginalFilename();
            //create a file object with the path
            File dest = new File(filePath);
            //transfer the file to the destination
            profilePicture.transferTo(dest);

            return "File uploaded successfully: " + filePath;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "File upload failed: " + e.getMessage();
        }
       
    }

}
