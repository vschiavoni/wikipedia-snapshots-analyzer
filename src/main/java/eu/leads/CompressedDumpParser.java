package eu.leads;

import java.util.HashMap;
import java.util.Map;

import info.bliki.wiki.dump.IArticleFilter;
import info.bliki.wiki.dump.Siteinfo;
import info.bliki.wiki.dump.WikiArticle;
import info.bliki.wiki.dump.WikiXMLParser;

import org.xml.sax.SAXException;

/**
 * Adapted from demo application of bliki project.
 * 
 * Reads a compressed or uncompressed Wikipedia XML dump
 * file (depending on the given file extension <i>.gz</i>, <i>.bz2</i> or
 * <i>.xml</i>) and prints the title and wiki text.
 * 
 * @author Valerio Schiavoni
 */
public class CompressedDumpParser {

	static HashMap<String, Integer> pageRevisions = new HashMap<String, Integer>();
	static int entries = 0;
	static class DumpFilter implements IArticleFilter {

		public void process(WikiArticle page, Siteinfo siteinfo)
				throws SAXException {
			entries++;
				
			String pId = page.getId();

			// System.out.println(pId+ " " + page.getRevisionId());

			if (pageRevisions.containsKey(pId)) {
				int cur = pageRevisions.get(pId);
				pageRevisions.put(pId, cur++);
			} else {
				pageRevisions.put(pId, 1);
			}
			
			if ((entries % 100) == 0) {
				System.out.println("Overall versions read so far: "+ entries);
			}
			
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: WikiDumpParser <snapshot.XML|snapshot.GZ|snapshot.BZ2>");
			System.exit(-1);
		}
		// String bz2Filename =
		// "c:\\temp\\dewikiversity-20100401-pages-articles.xml.bz2";
		String bz2Filename = args[0];
		try {
			IArticleFilter handler = new DumpFilter();
			WikiXMLParser wxp = new WikiXMLParser(bz2Filename, handler);
			wxp.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(pageRevisions.keySet().size() + " pages read.");

		for (Map.Entry<String, Integer> entry : pageRevisions.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = "
					+ entry.getValue());
		}
	}
}
