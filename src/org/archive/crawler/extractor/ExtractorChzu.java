package org.archive.crawler.extractor;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.archive.crawler.datamodel.CrawlURI;
import org.archive.io.ReplayCharSequence;
import org.archive.util.HttpRecorder;

public class ExtractorChzu extends Extractor {

	private static final long serialVersionUID = 8082386142013745017L;

	public ExtractorChzu(String name, String description) {
		super(name, description);
	}
	public ExtractorChzu(String name) {
		super(name, "CSCI");
	}
	//匹配a标签中的有效链接
	//<a href='/s/15/t/199/e0/e4/info123108.htm' target=_blank title="xxxxxxx">
	private static final String A_HREF="<a(.*)href\\s*=\\s*(\'/\\w/\\d+/\\w/\\d+/\\w+/\\w+/info\\d+.htm\')(.*)>";
	private static final String DOMAIN="http://csci.chzu.edu.cn";
	@Override
	protected void extract(CrawlURI curi) {
		String url ="";
		try {
			HttpRecorder hr = curi.getHttpRecorder();
			if(hr==null){
				throw new IOException("HttpRecorder IOException");
			}
			ReplayCharSequence rc= hr.getReplayCharSequence();
			if(rc==null){
				return;
			}
			String content = rc.toString();
			
			Pattern pattern = Pattern.compile(A_HREF, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(content);
			while(matcher.find()){
				url = matcher.group(2).replaceAll("'", "");
				curi.createAndAddLinkRelativeToBase(DOMAIN+url, content, Link.NAVLINK_HOP);
			}					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
