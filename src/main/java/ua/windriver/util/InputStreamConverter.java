package ua.windriver.util;

import java.io.InputStream;
import java.util.Scanner;

public class InputStreamConverter {

    public static String convert(InputStream inputStream,String charset) {
        return new Scanner(inputStream, charset).useDelimiter("\\A").next();
    }
}
