package spark.utils;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.TagType;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.io.IOException;

public class EqualsHelper implements Helper<Object> {
    @Override
    public Object apply(Object a, Options options) throws IOException {
        Object b = options.param(0, null);
        boolean result = new EqualsBuilder().append(a, b).isEquals();
        if (options.tagType == TagType.SECTION) {
            return result ? options.fn() : options.inverse();
        }
        return result
                ? options.hash("yes", true)
                : options.hash("no", false);
    }
}
