package eu.leads;


import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.sweble.wikitext.dumpreader.DumpReader;
import org.sweble.wikitext.dumpreader.export_0_8.PageType;


public class XmlDumpParser {
	
	static Logger logger = Logger.getLogger(XmlDumpParser.class.getClass());
	
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Usage: XmlDumpParser snapshot.XML");
			System.exit(-1);
		}
		
		String xmlFileName = args[0];
		
		final File file = new File(xmlFileName);
		
		final DumbInfinispanClient ispnClient= new DumbInfinispanClient();
		
		
		DumpReader dr = new DumpReader(file, logger) {
			@Override
			protected void processPage(Object mediaWiki, Object page) {
				PageType p = (PageType) page;			
				List<Object> items = p.getRevisionOrUpload();				
				System.out.println("nbRevisions: "
						+ items.size());				
				for (Object v: items){
					ispnClient.insertPageVersion(p, v);
				}			
			}
		};
		dr.unmarshal();		
	}
}
