package de.synyx.synli.client.ui.widgets;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;

import de.synyx.synli.shared.domain.Rating;

public class RatingWidget extends Composite implements LeafValueEditor<Rating> {

	private final AbstractImagePrototype star;
	private final AbstractImagePrototype empty;

	private FlowPanel container;
	private Image[] ratingImageArray;

	private Rating rating;

	public RatingWidget(ImageResource star, ImageResource emptyStar) {
		this(star, emptyStar, true);
	}
	
	public RatingWidget(ImageResource star, ImageResource emptyStar, boolean editable) {
		
		this.star = AbstractImagePrototype.create(star);
		this.empty = AbstractImagePrototype.create(emptyStar);

		Image badStar = createStar(Rating.BAD, editable);
		Image goodStar = createStar(Rating.GOOD, editable);
		Image superStar = createStar(Rating.SUPER, editable);
		Image awesomeStar = createStar(Rating.AWESOME, editable);
		
		ratingImageArray = new Image[4];
		ratingImageArray[0] = badStar;
		ratingImageArray[1] = goodStar;
		ratingImageArray[2] = superStar;
		ratingImageArray[3] = awesomeStar;
		
		container = new FlowPanel();
		container.add(badStar);
		container.add(goodStar);
		container.add(superStar);
		container.add(awesomeStar);
		initWidget(container);
	}

	@Override
	public void setValue(Rating value) {
		rating = value;
		showOriginalRating();
	}

	@Override
	public Rating getValue() {
		return rating;
	}

	private Image createStar(final Rating rating, boolean editable) {
		Image star = new Image();
		if (!editable) {
			return star;
		}
		star.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RatingWidget.this.rating = rating;
			}
		});
		star.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				showRating(rating);
			}
		});
		star.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				showOriginalRating();
			}
		});
		return star;
	}
	
	private void showOriginalRating() {
		showRating(rating);
	}
	
	private void showRating(Rating rating) {
		switch (rating) {
		case NONE:
			empty.applyTo(ratingImageArray[0]);
			empty.applyTo(ratingImageArray[1]);
			empty.applyTo(ratingImageArray[2]);
			empty.applyTo(ratingImageArray[3]);
			break;
		case BAD:
			star.applyTo(ratingImageArray[0]);
			empty.applyTo(ratingImageArray[1]);
			empty.applyTo(ratingImageArray[2]);
			empty.applyTo(ratingImageArray[3]);
			break;
		case GOOD:
			star.applyTo(ratingImageArray[0]);
			star.applyTo(ratingImageArray[1]);
			empty.applyTo(ratingImageArray[2]);
			empty.applyTo(ratingImageArray[3]);
			break;
		case SUPER:
			star.applyTo(ratingImageArray[0]);
			star.applyTo(ratingImageArray[1]);
			star.applyTo(ratingImageArray[2]);
			empty.applyTo(ratingImageArray[3]);
			break;
		case AWESOME:
			star.applyTo(ratingImageArray[0]);
			star.applyTo(ratingImageArray[1]);
			star.applyTo(ratingImageArray[2]);
			star.applyTo(ratingImageArray[3]);
			break;
		}
	}

}
