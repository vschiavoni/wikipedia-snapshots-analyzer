package eu.leads;

import org.apache.log4j.Logger;
import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.sweble.wikitext.dumpreader.export_0_8.PageType;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;

import static java.lang.System.getProperties;

public class DumbInfinispanClient  {
	
	Logger logger = Logger.getLogger(DumbInfinispanClient.class.getClass());
	
	private EmbeddedCacheManager cacheManager;
	private Cache<BigInteger, Object> cache ;
	
	
	public DumbInfinispanClient(){
		
		startManager();
		
	}

	public  void startManager(){  
        try{
            Properties properties = getProperties();
            properties.load(DumbInfinispanClient.class.getClassLoader().getResourceAsStream("config.properties"));
            logger.info("Found properties file.");
        } catch (IOException e) {
            logger.info("Found no config.properties file; defaulting.");
        }
        String infinispanConfig = getProperties().getProperty("infinispanConfigFile");
        
        if(infinispanConfig != null){
            try {
            	cacheManager = new DefaultCacheManager(infinispanConfig);
                this.cache = cacheManager.getCache();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Incorrect Infinispan configuration file");
            }
        }else{
        	cacheManager = new DefaultCacheManager();
        	String newCacheName = "wikipedia";
        	this.cache = cacheManager.getCache(newCacheName);
        }
        cacheManager.start();
        logger.info("Cache manager started.");
    }
	
	/**
	 * Insert a new version for the given page. 
	 * @param pt the page. 
	 * @param version a new version
	 */
	public void insertPageVersion(PageType pt, Object version){		
		this.cache.put(pt.getId(), version);
		
	}
	
}
