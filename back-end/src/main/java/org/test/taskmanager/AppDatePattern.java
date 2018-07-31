package org.test.taskmanager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class AppDatePattern extends JsonSerializer<Date> {
    // TODO : Define Date Format in i18n ResourceBundle
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    
    public AppDatePattern() {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }
    
	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException, JsonProcessingException {
        
        gen.writeString(dateFormat.format(value));
    }
    
    public SimpleDateFormat getDateFormat() { return dateFormat; }
}
