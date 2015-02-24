package org.sitenv.portlets.ccdavalidator;
import java.util.ArrayList;
import org.sitenv.portlets.ccdavalidator.models.SampleCCDATreeNode;


public interface JSTreeResults {
	
	void setRoots(ArrayList<SampleCCDATreeNode> roots);
	ArrayList<SampleCCDATreeNode> getRoots();
	void addRoot(SampleCCDATreeNode root);

}
