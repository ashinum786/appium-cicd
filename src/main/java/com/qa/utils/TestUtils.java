package com.qa.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {
public static final long WAIT=10;
public String getDateTime() {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	Date date = new Date();
	return dateFormat.format(date);
}


}
