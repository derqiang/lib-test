package me.daqiang.utils.enums;

import java.text.DateFormat;
import java.util.Date;

public enum Signal {
    GREEN {
        @Override
        String getInfo() {
            return DateFormat.getDateInstance().format(new Date());
        }
    },
    YELLOW {
        @Override
        String getInfo() {
            return System.getenv("CLASSPATH");
        }
    },
    RED {
        @Override
        String getInfo() {
            return System.getProperty("java.version");
        }
    };

    abstract String getInfo();
}
