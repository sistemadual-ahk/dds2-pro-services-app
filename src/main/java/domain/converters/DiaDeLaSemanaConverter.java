package domain.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.DayOfWeek;

@Converter(autoApply = true)
public class DiaDeLaSemanaConverter implements AttributeConverter<DayOfWeek, String> {
    @Override
    public String convertToDatabaseColumn(DayOfWeek dayOfWeek) {
        String dia;
        switch (dayOfWeek){
            case MONDAY: dia = "Lunes";
                break;
            case TUESDAY: dia = "Martes";
                break;
            case WEDNESDAY: dia = "Miercoles";
                break;
            case THURSDAY: dia = "Jueves";
                break;
            case FRIDAY: dia = "Viernes";
                break;
            case SATURDAY: dia = "Sabado";
                break;
            case SUNDAY: dia = "Domingo";
                break;
            default: dia = "";
        }
        return dia;
    }

    @Override
    public DayOfWeek convertToEntityAttribute(String s) {
        if(s == null)
            return null;
        DayOfWeek day;
        switch (s){
            case "Lunes": day = DayOfWeek.MONDAY;
                break;
            case "Martes": day = DayOfWeek.TUESDAY;
                break;
            case "Miercoles": day = DayOfWeek.WEDNESDAY;
                break;
            case "Jueves": day = DayOfWeek.THURSDAY;
                break;
            case "Viernes": day = DayOfWeek.FRIDAY;
                break;
            case "Sabado": day = DayOfWeek.SATURDAY;
                break;
            case "Domingo": day = DayOfWeek.SUNDAY;
                break;
            default: throw new IllegalArgumentException(s + " not supported.");
        }
        return day;
    }
}
