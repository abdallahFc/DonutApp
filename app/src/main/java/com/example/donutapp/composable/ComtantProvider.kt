package com.example.donutapp.composable

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun ConstantProvider() {
    val context = LocalContext.current
    val contentResolver: ContentResolver = context.contentResolver
    val contactsUri = ContactsContract.Contacts.CONTENT_URI
    LaunchedEffect(key1 =true){
        logContactDetails(contactsUri, contentResolver)
    }
}

@SuppressLint("Range")
fun logContactDetails(uri: Uri, contentResolver: ContentResolver) {
    val cursor = contentResolver.query(uri, null, null, null, null)
    cursor?.use { cursor ->
        val displayNameColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
        val hasPhoneNumberColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
        while (cursor.moveToNext()) {
            val displayName = cursor.getString(displayNameColumnIndex)
            val hasPhoneNumber = cursor.getInt(hasPhoneNumberColumnIndex)
            Log.d("ContactDetails", "displayName: $displayName")
            if (hasPhoneNumber > 0) {
                val contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val phoneNumber = getPhoneNumber(contentResolver, contactId)
                Log.d("ContactDetails", "phoneNumber: $phoneNumber")
            }
        }
    }
}


fun getPhoneNumber(contentResolver: ContentResolver, contactId: String): String {
    val phoneCursor = contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        null,
        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
        arrayOf(contactId),
        null
    )
    var phoneNumber = ""
    phoneCursor?.use { cursor ->
        if (cursor.moveToNext()) {
            val phoneNumberColumnIndex =
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            phoneNumber = cursor.getString(phoneNumberColumnIndex)
        }
    }
    return phoneNumber
}
