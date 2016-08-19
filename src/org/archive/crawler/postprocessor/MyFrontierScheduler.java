package org.archive.crawler.postprocessor;

import org.archive.crawler.datamodel.CandidateURI;

public class MyFrontierScheduler extends FrontierScheduler {

	private static final long serialVersionUID = 4936731991816379250L;

	public MyFrontierScheduler(String name) {
		super(name);
	}
	protected void schedule(CandidateURI caUri) {
		//¹ýÂËURI
		if(caUri.toString().contains("chzu.edu.cn")){
			getController().getFrontier().schedule(caUri);
		}     
    }
}
