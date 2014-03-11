package eu.leads;

import java.io.IOException;

import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.sweble.wikitext.dumpreader.export_0_8.PageType;

import static java.lang.System.getProperties;

public class DumbInfinispanClient  {
	
	private EmbeddedCacheManager manager;
	
	public DumbInfinispanClient(){
		
		startManager();
		
	}

	public  void startManager(){
        String infinispanConfig = getProperties().getProperty("infinispanConfigFile");

        if(infinispanConfig != null){
            try {
                manager = new DefaultCacheManager(infinispanConfig);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Incorrect Infinispan configuration file");
            }
        }else{
            manager = new DefaultCacheManager();
        }
        manager.start();
        
    }
	
	
	public void insertPageVersion(PageType pt, Object version){
		throw new UnsupportedOperationException("todo");
	}
	
}
