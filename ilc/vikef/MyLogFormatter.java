/* $Id: MyLogFormatter.java,v 1.2 2006/08/24 09:37:37 simone Exp $ */
package ilc.vikef;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
/**
 * This class is here to provide more succinct formatting of log records
 *
 * @author Kirill Grouchnikov
 */
public class MyLogFormatter extends Formatter {
    private SimpleDateFormat dateFormatter;

    public MyLogFormatter () {
        this.dateFormatter = new SimpleDateFormat("HH:mm:ss");
        //System.err.println("MyLogFormatter()");
    }

    /**
     * Format log record
     *
     * @param record log record
     * @return formatted string
     */
    public synchronized String format(LogRecord record) {
       // System.err.println("MyLogFormatter(): format()");
        StringBuffer sb = new StringBuffer();

        String levelName = record.getLevel().getName();
//        if (levelName.length() > 4)
//            levelName = levelName.substring(0, 3);
        if(! ("ilc.vikef.XQuery".equals(record.getLoggerName())) )
        	sb.append(levelName);

        // format time (without the date)
        Date time = new Date(record.getMillis());
        sb.append(" [" + this.dateFormatter.format(time) + "] ");

        // remove package names from class name
        String className = record.getSourceClassName();
        int lastPointIndex = className.lastIndexOf('.');
        if (lastPointIndex >= 0) {
            className = className.substring(lastPointIndex + 1);
        }
        sb.append(className + "." + record.getSourceMethodName() + " ");

        sb.append(record.getMessage());

        sb.append('\n');

        return sb.toString();
    }
}