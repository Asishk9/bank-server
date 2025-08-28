package genpact.bank.user.utils;

import java.io.*;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileImpl implements MultipartFile{
	
	private final byte[] fileContent;
	private final String fileName;
	private final String contentType;
 
	public MultipartFileImpl(byte[] fileContent, String fileName, String contentType) {
		super();
		this.fileContent = fileContent;
		this.fileName = fileName;
		this.contentType = contentType;
	}
 
	@Override
	public String getName() {
		return fileName;
	}
 
	@Override
	public String getOriginalFilename() {
		return fileName;
	}
 
	@Override
	public String getContentType() {
		return contentType;
	}
 
	@Override
	public boolean isEmpty() {
		return fileContent == null && fileContent.length == 0;
	}
 
	@Override
	public long getSize() {
		return fileContent.length;
	}
 
	@Override
	public byte[] getBytes() throws IOException {
		return fileContent;
	}
 
	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(fileContent);
	}
 
	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		try (OutputStream outputStream = new FileOutputStream(dest)) {
			outputStream.write(fileContent);
		}
	}
}
