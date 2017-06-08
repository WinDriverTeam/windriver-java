package com.windriver.util;

import java.io.InputStream;
import java.util.Scanner;

public class InputStreamConverter {

    public static String convert(InputStream inputStream) {
        return new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();
    }
}
