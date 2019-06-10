package com.ihm.playground.jasper.util;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


/**
 * The DataUtil class
 * @author Joly Mathew
 */
@Slf4j
public class DataUtil {

    /**
     * This method converts the object passed in to a String
     *
     * @param o Object
     * @return String
     */
    public static String toJsonString(Object o) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (IOException e) {
           log.error("Failed to convert object to JSON {}", e.getMessage());
        }
        return null;
    }
}
