package de.synyx.synli.client.ioc;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import de.synyx.synli.client.place.BookListPlace;
import de.synyx.synli.client.place.BookEditPlace;

@WithTokenizers({ BookListPlace.Tokenizer.class, BookEditPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {

}
