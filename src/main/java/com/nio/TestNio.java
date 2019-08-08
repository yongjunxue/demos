package com.nio;

import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class TestNio {
	public static void main(String[] args) throws IOException {
//		ByteBuffer bb = new Byteb
		FileChannel fc = FileChannel.open(Paths.get("F:/eclipse-jee-luna-SR2-win32-x86_64.zip"), StandardOpenOption.READ);
		FileChannel w = FileChannel.open(Paths.get("‪F:/mobansdfd.zip"), EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));
		Selector s = Selector.open();
		ByteBuffer b = ByteBuffer.allocate(1024);
		while(fc.read(b) != -1) {
			w.write(b);
		}
	}
}
