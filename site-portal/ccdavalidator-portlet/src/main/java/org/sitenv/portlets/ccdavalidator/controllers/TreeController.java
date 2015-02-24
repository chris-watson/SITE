package org.sitenv.portlets.ccdavalidator.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.sitenv.common.utilities.controller.BaseController;
import org.sitenv.portlets.ccdavalidator.JSTreeResults;
import org.sitenv.portlets.ccdavalidator.models.SampleCCDATreeNode;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class TreeController extends BaseController {

	public TreeController() {
	}
		
	@Autowired
	JSTreeResults tree;
	
	public abstract String getControllerName();
	
	public JSTreeResults getTree(){
		return tree;
	}
	
	protected void traverseDir(String dirPath) throws IOException{
		this.traverseDir(dirPath, dirPath, null, 1);
	}
	
	protected void traverseDir(
			String basePath, String path, SampleCCDATreeNode root, int deep)
			throws IOException {
		
		
		File[] files = (new File(path)).listFiles();

		if (files == null)
			return;

		Arrays.sort(files);

		int count = 1;
		deep++;
		for (File file : files) {
			count++;
			if (!file.getName().equalsIgnoreCase(".git")
					&& !file.getName().equalsIgnoreCase("README.md")) {

				if (file.isDirectory()) {
					String dirPath = file.getCanonicalPath().replace("\\", "/");
					SampleCCDATreeNode folder = new SampleCCDATreeNode(
							file.getName().replace("\\", "/"), "folder", "open", String.format(
									getControllerName()+"_%d_%d", deep, count), "helloword");
					folder.getMetadata().setDescription("This is CCDA file 1.");
					if (root == null) { // check for null
						tree.addRoot(folder);
					} else {
						root.addChild(folder);
					}
					traverseDir(basePath, dirPath, folder, deep);
				} else {
					
					String dirPath = file.getCanonicalPath().replace("\\", "/");
					SampleCCDATreeNode folder = new SampleCCDATreeNode(
							file.getName(), "file", "leaf", String.format(
									getControllerName()+"_%d_%d", deep, count), "helloword");
					folder.getMetadata().setDescription("This is file 1.");
					folder.getMetadata().setServerPath(
							
							dirPath.replace(basePath.replace("\\", "/")
									+ "/", ""));
					
					if (root == null) { // check for null
						tree.addRoot(folder);
					} else {
						root.addChild(folder);
					}
				}
			}
		}
	}
}
