package test;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-base.xml", "classpath*:spring-ehcache.xml" })
public class EhcacheDemo {

	private static final String CACHE_NAME = "testCache";

	@Autowired
	private CacheManager ehcacheManager;

	private Cache cache;

	@Test
	public void demo() {

		cache = ehcacheManager.getCache(CACHE_NAME);

		String key = "foo";
		String value = "boo";

		put(key, value);
		Object result = get(key);
		System.out.println(result);
	}

	public Object get(String key) {
		Element element = cache.get(key);
		return element.getObjectValue();
	}

	public void put(String key, Object value) {
		Element element = new Element(key, value);
		cache.put(element);
	}
}
