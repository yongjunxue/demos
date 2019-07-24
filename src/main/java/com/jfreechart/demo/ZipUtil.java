package com.jfreechart.demo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	public static File toZip(File zip, File source) {
		if(!source.exists()) {
			source.mkdirs();
		}
		
		File [] pdfFileList = source.listFiles();
		ZipOutputStream out = null;
		BufferedInputStream in = null;
		try {
			File p = zip.getParentFile();
			if(!p.exists()) {
				p.mkdirs();
			}
			zip.createNewFile();
			out = new ZipOutputStream(new FileOutputStream(zip));
			
			if(pdfFileList == null || pdfFileList.length==0) {
				
			}else {
				for(int i=0;i<pdfFileList.length;i++) {
					File f = pdfFileList[i];
					toZip(out,f,"");
				}
			}
			out.closeEntry();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return zip;
	}
	
	private static void toZip(ZipOutputStream out, File f,String path) throws IOException {
		BufferedInputStream in = null;
		if(f.isFile()) {
			in = new BufferedInputStream(new FileInputStream(f));
			byte [] b = new byte[1024*8];
			out.putNextEntry(new ZipEntry(path+f.getName()));
			while(in.read(b) != -1) {
				out.write(b);
			}
			out.closeEntry();
		}else {
			File [] pdfFileList = f.listFiles();
			for(int i=0;i<pdfFileList.length;i++) {
				File f2 = pdfFileList[i];
				toZip(out,f2,f2.getName()+"/");
			}
		}
	}

	public static void main(String[] args) {
		File f = new File("F://asd/me.zip");
		File dir = new File("F://111/");
		System.out.println(f.getName());
		System.out.println(dir.getName());
		System.out.println(dir.getParent());
		toZip(f,dir);
	}
}
