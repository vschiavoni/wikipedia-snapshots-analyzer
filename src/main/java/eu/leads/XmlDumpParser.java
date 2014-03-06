package eu.leads;

import info.bliki.wiki.dump.IArticleFilter;
import info.bliki.wiki.dump.WikiXMLParser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.sweble.wikitext.dumpreader.DumpReader;
import org.sweble.wikitext.dumpreader.export_0_8.PageType;

import eu.leads.CompressedDumpParser.DumpFilter;

public class XmlDumpParser {
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
//		URL resource = XmlDumpParser.class.getClass().getResource(
//				"/sewikimedia-20140223-pages-meta-history.xml");
//		String path = resource.getFile();
		
		final File file = new File(xmlFileName);
		Logger logger = Logger.getLogger(XmlDumpParser.class.getClass());
		DumpReader dr = new DumpReader(file, logger) {
			@Override
			protected void processPage(Object mediaWiki, Object page) {

				PageType p = (PageType) page;
				
				List<Object> items = p.getRevisionOrUpload();
				
				//System.out.println(p.getTitle() + " nbRevisions: "
					//	+ items.size());
				System.out.println("nbRevisions: "
						+ items.size());

			}

		};

		dr.unmarshal();

		
	}
}
