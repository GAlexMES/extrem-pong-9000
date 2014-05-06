package de.szut.dqi12.extremepong.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * @author http://www.rgagnon.com/javadetails/java-0198.html
 *
 */
public class JTextFieldLimit extends PlainDocument {

	private static final long serialVersionUID = 447756834210790647L;
	private int limit;
	private boolean upper;
	
	public JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	public JTextFieldLimit(int limit, boolean upper) {
		super();
		this.limit = limit;
		this.upper = upper;
	}

	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null)
			return;

		if ((getLength() + str.length()) <= limit) {
			if(upper) str = str.toUpperCase();
			super.insertString(offset, str, attr);
		}
	}
}

