package eu.leads;

import org.apache.log4j.Logger;
import org.infinispan.versioning.VersionedCache;
import org.infinispan.versioning.VersionedCacheFactory;
import org.sweble.wikitext.dumpreader.export_0_8.PageType;


public class DumbInfinispanClient  {
	
	Logger logger = Logger.getLogger(DumbInfinispanClient.class.getClass());
	
	private VersionedCache<Object,Object> cache ;
	private VersionedCacheFactory factory;
	
	public DumbInfinispanClient(){
		
		this.factory= new VersionedCacheFactory();
		
		cache = factory.newVersionedCache(VersionedCacheFactory.VersioningTechnique.NAIVE, "default");
		
	}
	
	/**
	 * Insert a new version for the given page. 
	 * @param pt the page. 
	 * @param version a new version
	 */
	public void insertPageVersion(PageType pt, Object version){		
		this.cache.put(pt.getId(), version);
		
	}

	/**
	 * Return the number of keys stored in the cache.
	 * @return
	 */
	public int countKeys() {
		return this.cache.keySet().size();
	}
	
}
