package com.intersysconsulting.derive4jexamples;

import fj.Equal;
import fj.Hash;
import fj.Ord;
import fj.Show;
import fj.data.Option;
import lombok.NonNull;
import org.derive4j.*;

import java.util.function.Function;

@Data(@Derive(withVisibility = Visibility.Smart,
        value = @Instances({Show.class, Hash.class, Equal.class, Ord.class})))
public abstract class PersonName {

    PersonName() {
    }

    public abstract <R> R match(@FieldNames("value") Function<String, R> Name);

    /**
     * This method is reexported with public modifier as
     * {@link PersonNames#parseName(String)}. Also the javadoc is copied over.
     *
     * @param value unparse string
     * @return a valid {@link PersonName}, maybe.
     */
    @ExportAsPublic
    static Option<PersonName> parseName(@NonNull String value) {
        // A name cannot be only spaces, must not start or end with space.
        return (value.trim().isEmpty() || value.endsWith(" ") || value.startsWith(" "))
                ? Option.none()
                : Option.some(PersonNames.Name0(value));
    }

}