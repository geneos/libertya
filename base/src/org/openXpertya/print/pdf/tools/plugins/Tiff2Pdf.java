/*
 * $Id: Tiff2Pdf.java,v  1.0 $
 * $Name:  $
 *
 * Copyright 2005 by Bruno Lowagie.
 *
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the License.
 *
 * The Original Code is 'iText, a free JAVA-PDF library'.
 *
 * The Initial Developer of the Original Code is Bruno Lowagie. Portions created by
 * the Initial Developer are Copyright (C) 1999, 2000, 2001, 2002 by Bruno Lowagie.
 * All Rights Reserved.
 * Co-Developer of the code is Paulo Soares. Portions created by the Co-Developer
 * are Copyright (C) 2000, 2001, 2002 by Paulo Soares. All Rights Reserved.
 *
 * Contributor(s): all the names of the contributors are added in the source code
 * where applicable.
 *
 * Alternatively, the contents of this file may be used under the terms of the
 * LGPL license (the "GNU LIBRARY GENERAL PUBLIC LICENSE"), in which case the
 * provisions of LGPL are applicable instead of those above.  If you wish to
 * allow use of your version of this file only under the terms of the LGPL
 * License and not to allow others to use your version of this file under
 * the MPL, indicate your decision by deleting the provisions above and
 * replace them with the notice and other provisions required by the LGPL.
 * If you do not delete the provisions above, a recipient may use your version
 * of this file under either the MPL or the GNU LIBRARY GENERAL PUBLIC LICENSE.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the MPL as stated above or under the terms of the GNU
 * Library General Public License as published by the Free Software Foundation;
 * either version 2 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Library general Public License for more
 * details.
 *
 * If you didn't download this code from the following link, you should check if
 * you aren't using an obsolete version:
 * http://www.lowagie.com/iText/
 */
package org.openXpertya.print.pdf.tools.plugins;

import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import org.openXpertya.print.pdf.text.Document;
import org.openXpertya.print.pdf.text.Image;
import org.openXpertya.print.pdf.text.Paragraph;
import org.openXpertya.print.pdf.text.pdf.PdfContentByte;
import org.openXpertya.print.pdf.text.pdf.PdfWriter;
import org.openXpertya.print.pdf.text.pdf.RandomAccessFileOrArray;
import org.openXpertya.print.pdf.text.pdf.codec.TiffImage;
import org.openXpertya.print.pdf.tools.arguments.FileArgument;
import org.openXpertya.print.pdf.tools.arguments.ImageFilter;
import org.openXpertya.print.pdf.tools.arguments.PdfFilter;
import org.openXpertya.print.pdf.tools.arguments.ToolArgument;

/**
 * Converts a Tiff file to a PDF file.
 */
public class Tiff2Pdf extends AbstractTool {
	/**
	 * Constructs a Tiff2Pdf object.
	 */
	public Tiff2Pdf() {
		arguments.add(new FileArgument(this, "srcfile", "The file you want to convert", false, new ImageFilter(false, false, false, false, false, true)));
		arguments.add(new FileArgument(this, "destfile", "The file to which the converted TIFF has to be written", true, new PdfFilter()));
	}

	/**
	 * @see org.openXpertya.print.pdf.tools.plugins.AbstractTool#createFrame()
	 */
	protected void createFrame() {
		internalFrame = new JInternalFrame("Tiff2Pdf", true, true, true);
		internalFrame.setSize(550, 250);
		internalFrame.setJMenuBar(getMenubar());
		internalFrame.getContentPane().add(getConsole(40, 30));
	}

	/**
	 * @see org.openXpertya.print.pdf.tools.plugins.AbstractTool#execute()
	 */
	public void execute() {
		try {
			if (getValue("srcfile") == null) throw new InstantiationException("You need to choose a sourcefile");
			File tiff_file = (File)getValue("srcfile");
			if (getValue("destfile") == null) throw new InstantiationException("You need to choose a destination file");
			File pdf_file = (File)getValue("destfile");
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf_file));
			int pages = 0;
			document.open();
			PdfContentByte cb = writer.getDirectContent();
            RandomAccessFileOrArray ra = null;
            int comps = 0;
            ra = new RandomAccessFileOrArray(tiff_file.getAbsolutePath());
            comps = TiffImage.getNumberOfPages(ra);
            for (int c = 0; c < comps; ++c) {
                Image img = TiffImage.getTiffImage(ra, c + 1);
                if (img != null) {
                    if (img.scaledWidth() > 500 || img.scaledHeight() > 700) {
                        img.scaleToFit(500, 700);
                    }
                    img.setAbsolutePosition(20, 20);
                    document.add(new Paragraph(tiff_file + " - page " + (c + 1)));
                    cb.addImage(img);
                    System.out.println("Finished page " + (c + 1));
                    document.newPage();
                    ++pages;
                }
            }
            ra.close();
            document.close();
		} catch (Exception e) {
        	JOptionPane.showMessageDialog(internalFrame,
        		    e.getMessage(),
        		    e.getClass().getName(),
        		    JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());
		}
	}

	/**
	 * @see org.openXpertya.print.pdf.tools.plugins.AbstractTool#valueHasChanged(org.openXpertya.print.pdf.tools.arguments.ToolArgument)
	 */
	public void valueHasChanged(ToolArgument arg) {
		if (internalFrame == null) {
			// if the internal frame is null, the tool was called from the commandline
			return;
		}
		// represent the changes of the argument in the internal frame
	}

	
    /**
     * Converts a tiff file to PDF.
     * @param args
     */
	public static void main(String[] args) {
    	Tiff2Pdf tool = new Tiff2Pdf();
    	if (args.length < 2) {
    		System.err.println(tool.getUsage());
    	}
    	tool.setArguments(args);
        tool.execute();
	}

	/**
	 * @see org.openXpertya.print.pdf.tools.plugins.AbstractTool#getDestPathPDF()
	 */
	protected File getDestPathPDF() throws InstantiationException {
		return (File)getValue("destfile");
	}
}