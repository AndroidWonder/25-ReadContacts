/*
 * This example is for reading the Contacts database.
 * It requires a specific permission in the Manifest.
 * This permission is dangerous so a Setting was made.
 */

package com.course.examples.readcontacts;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

public class ReadContacts extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); 
		
		TextView contactView = (TextView) findViewById(R.id.contactview);

		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String[] projection    = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER};

		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
				+ " COLLATE LOCALIZED ASC";

		Cursor people = getContentResolver().query(uri, projection, null, null, sortOrder);

		int indexName = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
		int indexNumber = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

		while (people.moveToNext()) {
			String name   = people.getString(indexName);
			String number = people.getString(indexNumber);
			contactView.append( name + "    " + number + "\n");
		} ;

		people.close();

	}
}