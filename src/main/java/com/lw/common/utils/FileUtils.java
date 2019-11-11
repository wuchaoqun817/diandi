package com.lw.common.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
    public static String saveFile(byte[] file, String filePath, String fileName) {
        int random = (int) (Math.random() * 100 + 1);
        int random1 = (int) (Math.random() * 100 + 1);
        filePath = filePath + random + File.separator + random1 + File.separator;
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath + fileName);
            FileChannel fileChannel = fileOutputStream.getChannel();
            ByteBuffer buf = ByteBuffer.wrap(file);
            while (fileChannel.write(buf) != 0) {
            }
        } catch (Exception e) {

        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //url
        return random + "/" + random1 + "/" + fileName;
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String renameToUUID(String fileName) {
        return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    
    public static boolean writeFile(byte[] file, String targetFilePath, String fileName) {
        boolean isSuccess = false;
        FileOutputStream out = null;
        try {
            File targetFile = new File(targetFilePath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            out = new FileOutputStream(targetFilePath + fileName);
            out.write(file);
            out.flush();
            isSuccess = true;
        } catch (FileNotFoundException e) {
        	logger.error("File not found", e);
        } catch (IOException e) {
        	logger.error("IOException ", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("Close FileOutputStream exception  ", e);
                }
            }
        }
        return isSuccess;
    }
    
    /**
     * move file
     * @param originFileName  源文件名
     * @param targetFileName  移动后的文件名
     * @return
     */
	public static boolean moveFile(String originFileName, String targetFileName) {
		File file = new File(originFileName);
		// 如果文件路径所对应的文件存在
		if (file.exists() && file.isFile()) {
			int lastIndex = targetFileName.lastIndexOf(File.separator);
			if (lastIndex <= 0) {
				return false;
			}
			File targetFile = new File(targetFileName.substring(0, lastIndex));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			return file.renameTo(new File(targetFileName));
		} else {
			return false;
		}
	}
	
	public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
