package csp.learning.java.java8;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Lambdas {

    public static final ThreadLocal<DateFormat> threadLocalDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MMM-yyyy"));

}
