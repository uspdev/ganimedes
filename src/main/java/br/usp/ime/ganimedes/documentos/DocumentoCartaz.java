package br.usp.ime.ganimedes.documentos;

import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import br.usp.ime.ganimedes.model.Anuncio;

public class DocumentoCartaz {


	public void downloadAnuncio(Anuncio a) throws IOException {
		// Prepare.
		byte[] pdf = a.getCartaz();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

		// Initialize response.
		response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
		response.setContentType("application/pdf"); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ServletContext#getMimeType() for auto-detection based on
													// filename.
		response.setHeader("Content-disposition", "attachment; filename=\"cartaz_" + a.getId() + ".pdf\""); // The Save As popup magic is done here. You can give it any filename you want, this only
																											// won't work in MSIE, it
		// will use current request URL as filename instead.

		// Write file to response.
		OutputStream output = response.getOutputStream();
		output.write(pdf);
		output.close();

		// Inform JSF to not take the response in hands.
		facesContext.responseComplete(); // Important! Else JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
	}
}