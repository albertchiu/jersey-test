package system.adapter;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ProcessingException;

import imp.ImportProcessor;

public class ImportProcessorAdapterFactory {
	private static final Map<Class<? extends ImportProcessor>, ImportProcessorAdapter> cache = 
			new HashMap<Class<? extends ImportProcessor>, ImportProcessorAdapter>();

	public static ImportProcessorAdapter getImportProcessorWrapperInstance(Class<? extends ImportProcessor> clazz) {
		synchronized (cache)
		{
			ImportProcessorAdapter instance = cache.get(clazz);
			if (instance != null) return instance;

			try {
				instance = new ImportProcessorAdapter(clazz.newInstance());
				cache.put(clazz, instance);
				return instance;
			}
			catch (InstantiationException ex) {
				throw new ProcessingException("Is it an abstract class?", ex);
			}
			catch (IllegalAccessException ex) {
				throw new ProcessingException("Is the constructor accessible?", ex);
			}
		}
	}
}