package org.sitenv.portlets.ccdavalidator;

import java.util.ArrayList;

import org.sitenv.portlets.ccdavalidator.models.SampleCCDATreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("JSTreeResultsService")
@Scope(value="request",proxyMode= ScopedProxyMode.INTERFACES)
public class JSTreeResultsImpl implements JSTreeResults {

	private ArrayList<SampleCCDATreeNode> roots = new ArrayList<SampleCCDATreeNode>();
	
	public void setRoots(ArrayList<SampleCCDATreeNode> roots) {
		this.roots = roots;
	}

	public ArrayList<SampleCCDATreeNode> getRoots() {
		return roots;
	}
	
	public void addRoot(SampleCCDATreeNode root){
		roots.add(root);
	}

}
