package com.example.assignment.database;

import androidx.room.TypeConverter;
import java.util.Date;

public class DateConverter {
    // TypeConverter class for converting Date objects to and from Long timestamps

    // Convert a Long timestamp to a Date object
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        // Return null if the value is null; otherwise, create a Date object from the timestamp
        return value == null ? null : new Date(value);
    }

    // Convert a Date object to a Long timestamp
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        // Return null if the date is null; otherwise, return the timestamp of the Date object
        return date == null ? null : date.getTime();
    }
}
