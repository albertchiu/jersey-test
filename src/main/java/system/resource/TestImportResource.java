package system.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import system.adapter.ImportProcessorAdapter;
import system.adapter.ImportProcessorAdapterFactory;
import imp.ImportProcessor;;

@Path("invoke")
public class TestImportResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("TestImport")
	public String TestImport() {

		String result = null;
		try {
			ImportProcessorAdapter ipw = ImportProcessorAdapterFactory
					.getImportProcessorWrapperInstance(ImportProcessor.class);

			if (ipw.start())
				result = "OK";
			else
				result = "TestImport service is running";
		} 
		catch (Exception ex) {
			result = ex.toString();
		}

		return result;
	}
}