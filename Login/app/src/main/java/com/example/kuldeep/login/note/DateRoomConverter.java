package com.example.kuldeep.login.note;

import android.arch.persistence.room.TypeConverter;
import java.util.Date;


class DateRoomConverter {

    @TypeConverter
    static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }
}
