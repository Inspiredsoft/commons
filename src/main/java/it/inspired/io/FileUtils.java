/*******************************************************************************
 * Inspired commons is a set of utility classes.
 * Copyright (C) 2016 Inspired Soft
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.    
 *******************************************************************************/

package it.inspired.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * This class has some usefull methods to manage file
 * 
 * 
 * @author Massimo Romano
 *
 */
public class FileUtils {

	/**
	 * Perform file unzip recursivelly
	 * 
	 * @param zipFile
	 *            File to Unzip
	 * @param path
	 *            Source folder where the {@code zipFile} is located
	 * @throws IOException
	 */
	public static void unZip(String zipFile, String path) throws IOException {
		unZip(zipFile, path, path);
	}

	/**
	 * Perform file unzip recursivelly coping file from source folder to
	 * destination.
	 * 
	 * Special folder lik ".DS_Store" is escluded.
	 * 
	 * @param zipFile
	 *            File to Unzip
	 * @param sourcePath
	 *            Source folder where the {@code zipFile} is located
	 * @param destPath
	 *            Destination folder for the unzipped file
	 * @throws IOException
	 */
	public static void unZip(String zipFile, String sourcePath, String destPath)
			throws IOException {
		byte[] buffer = new byte[1024];

		// get the zip file content
		ZipInputStream zis = new ZipInputStream(new FileInputStream(sourcePath
				+ File.separator + zipFile));

		// get the zipped file list entry
		ZipEntry ze = zis.getNextEntry();

		if (ze == null) {
			zis.closeEntry();
			zis.close();
			throw new IOException("Archive " + zipFile + " is empty");
		}

		while (ze != null) {
			String fileName = ze.getName();
			File newFile = new File(destPath + File.separator + fileName);

			if (!ze.isDirectory() && !fileName.endsWith(".DS_Store")
					&& !newFile.getParent().endsWith(".DS_Store")) {

				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
			}

			ze = zis.getNextEntry();
		}

		zis.closeEntry();
		zis.close();

	}

	/**
	 * Delete a directory if the given path is effectively a directory
	 * 
	 * @param path
	 *            directory to delete
	 * @throws IOException
	 */
	public static void deleteDirectory(String path) throws IOException {
		final File folder = new File(path);

		if (folder.isDirectory()) {
			org.apache.commons.io.FileUtils.deleteDirectory(folder);
		}
	}

	/**
	 * Delete a directory content excluding some files/directories
	 * 
	 * @param path
	 *            The directory to delete
	 * @param excludede
	 *            A list of files/directories not to delete
	 * @throws IOException
	 */
	public static void deleteDirectory(String path, List<String> excludede)
			throws IOException {
		if (excludede == null || excludede.isEmpty()) {
			deleteDirectory(path);
		}

		final File folder = new File(path);

		if (folder.isDirectory()) {
			for (final File file : folder.listFiles()) {
				if (!excludede.contains(file.getName())) {
					if (file.isDirectory()) {
						org.apache.commons.io.FileUtils.deleteDirectory(file);
					} else {
						file.delete();
					}
				}
			}
		}
	}

	/**
	 * Delete a file in the directory
	 * 
	 * @param path
	 *            Directory where the {@code fileName} is located
	 * @param fileName
	 *            File to delete
	 */
	public static void deleteFile(String path, String fileName) {
		File file = new File(path + File.separator + fileName);

		if (file.exists() && !file.isDirectory()) {
			file.delete();
		}
	}

	/**
	 * Copy a directory
	 * 
	 * @param srcDir
	 *            Source directory
	 * @param destDir
	 *            Destination directory
	 * @throws IOException
	 */
	public static void copyDirectory(String srcDir, String destDir)
			throws IOException {
		File src = new File(srcDir);
		File dest = new File(destDir);

		org.apache.commons.io.FileUtils.copyDirectory(src, dest);
	}

	/**
	 * Zip a single file
	 * 
	 * @param zipFile
	 *            Name of the file to zip
	 * @param sourcePath
	 *            Source path
	 * @throws IOException
	 */
	public static String zip(String zipFileName, String sourcePath)
			throws IOException {
		return zip(zipFileName, sourcePath, sourcePath);
	}

	/**
	 * Zip a single file
	 * 
	 * @param zipFile
	 *            Name of the file to zip
	 * @param sourcePath
	 *            Source path
	 * @param destPath
	 *            Destination path
	 * @throws IOException
	 */
	public static String zip(String zipFileName, String sourcePath,
			String destPath) throws IOException {
		byte[] buffer = new byte[1024];

		FileOutputStream fos = new FileOutputStream(sourcePath + File.separator
				+ zipFileName + ".zip");
		ZipOutputStream zos = new ZipOutputStream(fos);

		ZipEntry ze = new ZipEntry(zipFileName);
		zos.putNextEntry(ze);

		FileInputStream in = new FileInputStream(sourcePath + File.separator
				+ zipFileName);

		int len;
		while ((len = in.read(buffer)) > 0) {
			zos.write(buffer, 0, len);
		}

		in.close();
		zos.closeEntry();

		// remember close it
		zos.close();

		return zipFileName + ".zip";
	}

}
