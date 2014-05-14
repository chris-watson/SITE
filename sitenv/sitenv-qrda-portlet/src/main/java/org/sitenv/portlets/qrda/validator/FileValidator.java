package org.sitenv.portlets.qrda.validator;

import org.sitenv.portlets.qrda.models.UploadedFile;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FileValidator implements Validator {

	public boolean supports(Class clazz) {
		return UploadedFile.class.equals(clazz);
	}

	public void validate(Object uploadedFile, Errors errors) {

		UploadedFile file = (UploadedFile) uploadedFile;
		if (file.getCategory().equals("NONE"))
			errors.rejectValue("category", "qrdaSandboxGUI.selectCategory",
					"Please select the validation category!");

		if (file.getFileData() == null
				|| (file.getFileData() != null && file.getFileData().getSize() == 0)) {
			errors.rejectValue("fileData", "qrdaSandboxGUI.selectFile",
					"Please select a file!");
		}

	}
}