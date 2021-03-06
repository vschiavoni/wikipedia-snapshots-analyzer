package eu.leads;

import org.apache.log4j.Logger;
import org.sweble.wikitext.dumpreader.DumpReader;
import org.sweble.wikitext.dumpreader.export_0_8.PageType;
import org.sweble.wikitext.dumpreader.export_0_8.RevisionType;

import java.io.File;
import java.util.List;

public class XmlDumpParser {

	static Logger logger = Logger.getLogger(XmlDumpParser.class.getClass());

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		final DumbInfinispanClient ispnClient = new DumbInfinispanClient();

		if (System.getProperty("ISPN_CLIENT") != null) {
			String xmlFileName = args[0];
			final File file = new File(xmlFileName);
			logger.info("Start parsing dump file "+xmlFileName);
			DumpReader dr = new DumpReader(file, logger) {
				@Override
				protected void processPage(Object mediaWiki, Object page) {
					PageType p = (PageType) page;
					List<Object> items = p.getRevisionOrUpload();
					logger.info("nbRevisions: " + items.size());
					for (Object v : items) {
						ispnClient.insertPageVersion(p, ((RevisionType)v).getText().getValue());
//						ispnClient.insertPageVersion(p, v);
					}
				}
			};
			dr.unmarshal();

			int storedKeys = ispnClient.countKeys();
			logger.info("Total number of stored keys at the end of parsing: "+storedKeys);
			
		}
		else {
			logger.info("Waiting for other nodes...");
			while (true){
				Thread.sleep(1000);
				logger.info("Current number of keys in cache: "+ ispnClient.countKeys());

			}
		}
	}
}
