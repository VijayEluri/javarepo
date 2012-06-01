package org.jboss.demo.infinispan;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.VersionedValue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RemoteCacheTest {

	private RemoteCacheManager remoteCacheManager;
	private RemoteCache<String, String> cache;

	@Before
	public void setup() {
		// To use the versioned API, remote classes are specifically needed
		remoteCacheManager = new RemoteCacheManager();
		cache = remoteCacheManager.getCache("demoCache");
	}
	
	@Test
	public void testDisplaySize(){
		System.out.println("### testDisplaySize ###");
		this.logStuff();
		this.displayCacheItems();
	}

	@Test
	public void testRemotePutGetRemove() {
		System.out.println("### testRemotePutGetRemove ###");
		cache.put("hello", "world", Long.parseLong("-1"), TimeUnit.MILLISECONDS, Long.parseLong("-1"), TimeUnit.MILLISECONDS);

		this.displayCacheItems();
		
		@SuppressWarnings("unchecked")
		VersionedValue<String> valueBinary = cache.getVersioned("hello");

		// removal only takes place only if the version has not been changed
		// in between. (a new version is associated with 'car' key on each
		// change)
		cache.removeWithVersion("hello", valueBinary.getVersion());
		Assert.assertFalse(cache.containsKey("hello"));
		this.logStuff();
		System.out.println("valueBinary.getVersion(): " + valueBinary.getVersion());
	}

	@Test
	public void testPut() {
		System.out.println("\n ### testPut ###");
		cache.put("hello1", "world", Long.parseLong("-1"), TimeUnit.MILLISECONDS, Long.parseLong("-1"), TimeUnit.MILLISECONDS);
		
		@SuppressWarnings("unchecked")
		VersionedValue<String> valueBinary = cache.getVersioned("hello1");
		System.out.println("valueBinary.getVersion(): " + valueBinary.getVersion());

		Assert.assertTrue(cache.containsKey("hello1"));
		// Assert.assertEquals(1, cache.size());
	}

	private void displayCacheItems() {
		System.out.println("** testDisplayCacheItems **");
		try {
			if (cache.values() != null) {
				System.out.println("Trying to get values");
				Collection<String> items = cache.values();
				for (String item : items) {
					System.out.println("item: " + item);
				}
			}
		} catch (Exception e) {
			System.out.println("   Exception: " + e.getMessage());
		}
	}
	
	private void logStuff(){
		System.out.println("empty?: " + cache.isEmpty());
		System.out.println("cache.getName(): " + cache.getName().toString());
		System.out.println("cache.getVersion(): " + cache.getVersion().toString());
		System.out.println("cache.size(): " + cache.size());
	}

	@After
	public void finish() {
		cache.stop();
		remoteCacheManager.stop();
	}

}
